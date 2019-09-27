package com.controller;


import com.service.DataService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/service")
public class DataController {
    @Autowired
    private DataService dataService;

    @RequestMapping("/cpudata")
    public void cpuData(HttpServletRequest request, HttpServletResponse response) {
        try {
            String jsonString = dataService.queryOneCpuData();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/diskdata")
    public void diskData(HttpServletRequest request, HttpServletResponse response) {
        try {
            String jsonString = dataService.queryOneDiskData();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/memdata")
    public void memData(HttpServletRequest request, HttpServletResponse response){
        try {
            String jsonString = dataService.queryOneMemData();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/netdata")
    public void netData(HttpServletRequest request, HttpServletResponse response){
        try {
            String jsonString = dataService.queryOneNetData();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}