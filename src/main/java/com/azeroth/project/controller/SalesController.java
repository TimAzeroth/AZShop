package com.azeroth.project.controller;

import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.service.SalesService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    private SalesService salesService;

    private SalesRepository salesRepository;


@GetMapping("/sales")
    public String sales(){
    System.out.println("실행 확인용");
    return "/siteSales/sales";
}


}
