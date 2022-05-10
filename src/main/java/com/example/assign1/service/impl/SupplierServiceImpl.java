package com.example.assign1.service.impl;



import com.example.assign1.dto.SupplierDto;
import com.example.assign1.entity.Supplier;
import com.example.assign1.exception.ResourceNotFoundException;
import com.example.assign1.repository.SupplierRepository;
import com.example.assign1.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository SupplierRepository;

    public SupplierServiceImpl(SupplierRepository SupplierRepository) {
        this.SupplierRepository = SupplierRepository;
    }

    @Override
    public SupplierDto createSupplier(SupplierDto SupplierDto) {

        // convert DTO to entity
        Supplier Supplier = mapToEntity(SupplierDto);
        Supplier newSupplier = SupplierRepository.save(Supplier);

        // convert entity to DTO
        SupplierDto SupplierResponse = mapToDTO(newSupplier);
        return SupplierResponse;
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> categories = SupplierRepository.findAll();
        return categories.stream().map(Supplier -> mapToDTO(Supplier)).collect(Collectors.toList());
    }


    @Override
    public SupplierDto getSupplierById(long id) {
        Supplier Supplier = SupplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
        return mapToDTO(Supplier);
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto SupplierDto, long id) {
        // get Supplier by id from the database
        Supplier Supplier = SupplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
        Supplier.setName(SupplierDto.getName());
        Supplier updatedSupplier = SupplierRepository.save(Supplier);
        return mapToDTO(updatedSupplier);
    }

    @Override
    public void deleteSupplierById(long id) {
        // get Supplier by id from the database
        Supplier Supplier = SupplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
        SupplierRepository.delete(Supplier);
    }

    // convert Entity into DTO
    private SupplierDto mapToDTO(Supplier Supplier) {
        SupplierDto SupplierDto = new SupplierDto();
        SupplierDto.setId(Supplier.getId());
        SupplierDto.setName(Supplier.getName());

        return SupplierDto;
    }

    // convert DTO to entity
    private Supplier mapToEntity(SupplierDto SupplierDto) {
        Supplier Supplier = new Supplier();
        Supplier.setId(SupplierDto.getId());
        Supplier.setName(SupplierDto.getName());

        return Supplier;
    }
}
