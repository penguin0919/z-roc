package com.test.roc.core.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/*
* 使用方式：
        ExcelParser excelParser = new ExcelParser();
        List<DeviceUploadBo> deviceData = excelParser.parseExcel(uploadFile, DeviceUploadBo.class);
* */
public class ExcelParser {

    public <T> List<T> parseExcel(MultipartFile multipartFile, Class<T> clazz) {
        if (multipartFile.isEmpty()) {
            return Collections.emptyList();
        }

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            return Collections.emptyList();
        }

        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        ExcelListener<T> excelListener = new ExcelListener<>();
        ReadSheet readSheet = EasyExcel.readSheet(0).head(clazz).registerReadListener(excelListener).build();
        excelReader.read(readSheet);

        return excelListener.getData();
    }
}