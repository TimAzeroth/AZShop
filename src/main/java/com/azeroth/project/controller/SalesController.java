package com.azeroth.project.controller;

import com.azeroth.project.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {

    private SalesService salesService;

    @Autowired
    public  SalesController(SalesService salesService){
        this.salesService = salesService;
    }



}
