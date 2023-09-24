package myspringboottestservice01.service.impl;

import com.alibaba.excel.EasyExcel;
import myspringboottestservice01.domain.ExcelPojo;
import myspringboottestservice01.service.IMyService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyService implements IMyService {

    @Resource
    private MyServiceB myServiceB;

    @Override
    public String service1(String port){
        return "service1 方法执行。。。 -- 当前端口为 ： " + port;
    }

    @Override
    public void downLoadExcel(HttpServletResponse response) throws IOException {
        // 写数据
        List<ExcelPojo> pojos = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            ExcelPojo excelPojo = new ExcelPojo();
            excelPojo.setId(i);
            excelPojo.setName("张同学" + i);
            excelPojo.setSex(i % 2 == 0 ? "女" : "男");
        }
        String abFileName = "D:\\temp\\"+System.currentTimeMillis()+".xlsx";
        String fileName = abFileName.substring(abFileName.lastIndexOf("\\"));
        String fileNameEncode = URLEncoder.encode(fileName, "utf-8");

        EasyExcel.write(abFileName,ExcelPojo.class).sheet("学生").doWrite(pojos);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置contentType为excel格式
        response.setHeader("Content-Disposition", "Attachment;Filename="+ fileNameEncode);

        try (FileInputStream fileInputStream = new FileInputStream(abFileName);
             ServletOutputStream outputStream = response.getOutputStream()) {
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fileInputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
        }

    }
}
