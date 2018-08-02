package com.howie.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-06-06
 * @Time 14:07
 */
public class ExcelUtil {
    private static ExcelTypeEnum excelTypeEnum = ExcelTypeEnum.XLSX;

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel  文件
     * @param object 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel object) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            if (object != null) {
                sheet.setClazz(object.getClass());
            }
            reader.read(sheet);
        }
        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel   文件
     * @param object  实体类映射，继承 BaseRowModel 类
     * @param sheetNo sheet 的序号
     *                当前版本中：
     *                XLS 类型文件 sheet 序号为顺序，第一个 sheet 序号为 1
     *                XLSX 类型 sheet 序号顺序为倒序，即最后一个 sheet 序号为 1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel object, int sheetNo) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        Sheet sheet = new Sheet(sheetNo);
        sheet.setClazz(object.getClass());
        reader.read(sheet);
        return excelListener.getDatas();
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel,
                                         ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
            throw new ExcelException("文件格式错误！");
        }
        if (filename.toLowerCase().endsWith(".xls")) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            return new ExcelReader(inputStream, excelTypeEnum,
                    null, excelListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
