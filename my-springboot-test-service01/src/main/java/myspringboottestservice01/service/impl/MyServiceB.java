package myspringboottestservice01.service.impl;

import org.springframework.stereotype.Service;
import myspringboottestservice01.service.impl.MyService;

import javax.annotation.Resource;

@Service
public class MyServiceB {

    @Resource
    private MyService myService;
}
