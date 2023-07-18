package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public SalesDomain sales(SalesDomain salesDomain) {
        SalesDomain salesDomain2 = new  SalesDomain();
        return salesDomain2;
    }

    @Override
    public List<SalesDomain> saleslist() {
        List<SalesDomain> list= new ArrayList<>();

        return list;
    }


}
