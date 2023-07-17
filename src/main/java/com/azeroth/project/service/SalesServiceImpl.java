package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    @Override
    public SalesDomain sales(SalesDomain salesDomain) {
        return null;
    }

    @Override
    public List<SalesDomain> saleslist() {
        return null;
    }


}
