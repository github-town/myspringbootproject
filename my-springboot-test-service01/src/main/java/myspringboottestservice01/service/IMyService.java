package myspringboottestservice01.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IMyService {
    String service1(String port);

    void downLoadExcel(HttpServletResponse response) throws IOException;
}
