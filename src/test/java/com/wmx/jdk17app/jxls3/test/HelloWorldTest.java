package com.wmx.jdk17app.jxls3.test;

import cn.hutool.core.date.DateUtil;
import com.wmx.jdk17app.jxls3.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.jxls.builder.JxlsOutputFile;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2025/6/14 16:06
 */
public class HelloWorldTest {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldTest.class);

    @Test
    public void test() throws FileNotFoundException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1001, "张三", DateUtil.parse("1993-04-22"), new BigDecimal("18833.54")));
        employees.add(new Employee(1002, "李四", DateUtil.parse("1994-09-02"), new BigDecimal("28855.14")));
        employees.add(new Employee(1003, "王五", DateUtil.parse("1995-08-11"), new BigDecimal("38988.50")));
        employees.add(new Employee(1004, "Jon", DateUtil.parse("1992-01-16"), new BigDecimal("8868.48")));
        employees.add(new Employee(1005, "约翰", DateUtil.parse("1991-06-08"), new BigDecimal("7888.66")));

        InputStream templateInputStream = HelloWorldTest.class.getClassLoader().getResourceAsStream("jxls3/HelloWorld.xlsx");
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        File outputFile = new File(homeDirectory, "HelloWorld.xlsx");
        JxlsOutputFile jxlsOutputFile = new JxlsOutputFile(outputFile);

        Map<String, Object> data = new HashMap<>();
        data.put("employees", employees);
        JxlsPoiTemplateFillerBuilder.newInstance()
                .withTemplate(templateInputStream)
                .build()
                .fill(data, jxlsOutputFile);
        log.info("输出文件：" + jxlsOutputFile.toString());
    }
}
