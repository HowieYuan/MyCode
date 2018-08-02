package com.howie.word;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-07-27
 * @Time 17:17
 */
@RestController
public class TestController {
    @RequestMapping(value = "test", method = RequestMethod.POST)
    public Object test(MultipartFile word) {
        try {
            //载入文档docx格式
            XWPFDocument xwpf = new XWPFDocument(word.getInputStream());//得到word文档的信息
            Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
            while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                //读取每一行数据
                for (int i = 1; i < rows.size(); i++) {
                    XWPFTableRow row = rows.get(i);
                    //读取每一列数据
                    List<XWPFTableCell> cells = row.getTableCells();
                    if (cells.get(0).getText().equals("风险设备")) {
                        System.out.println(cells.get(1).getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping(value = "test2", method = RequestMethod.POST)
    public Object test2(MultipartFile word) throws IOException {
        XWPFDocument document = new XWPFDocument(word.getInputStream());
        // 获取所有表格
        List<XWPFTable> tables = document.getTables();
        for (XWPFTable table : tables) {
            // 获取表格的行
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                // 获取表格的每个单元格
                List<XWPFTableCell> cells = row.getTableCells();
                if ("风险设备".equals(cells.get(0).getText())) {
                    System.out.println(cells.get(1).getText());
                }
//                for (XWPFTableCell cell : tableCells) {
//                    // 获取单元格的内容
//                    String text = cell.getText();
//                }
            }
        }
        return 1;
    }
}
