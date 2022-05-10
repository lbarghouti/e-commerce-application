package com.example.assign1.service;


import com.example.assign1.dto.SupplierDto;

import java.util.List;


public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto);

    List<SupplierDto> getAllSuppliers();

    SupplierDto getSupplierById(long id);

    SupplierDto updateSupplier(SupplierDto SupplierDto, long id);

    void deleteSupplierById(long id);
}
