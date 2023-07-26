package com.azeroth.project.repository;

import com.azeroth.project.domain.AddressDomain;

import java.util.List;

public interface AddressRepository {
   List<AddressDomain> findByAdd(Long id);

}
