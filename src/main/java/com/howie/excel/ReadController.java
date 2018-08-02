package com.howie.excel;

import com.howie.publicPackage.model.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-06-05
 * @Time 16:56
 */
@RestController
public class ReadController {
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public ResultMap noModelMultipleSheet(MultipartFile excel) {
        List list = ExcelUtil.readExcel(excel, new Info());
        return new ResultMap().success().data(list);
    }
}
