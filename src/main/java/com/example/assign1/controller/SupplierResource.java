package com.example.assign1.controller;


import com.example.assign1.dto.SupplierDto;
import com.example.assign1.exception.BadRequestException;
import com.example.assign1.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/supplier")
public class SupplierResource {
    private final Logger log = LoggerFactory.getLogger(SupplierResource.class);

//    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
    private SupplierService supplierService; //the use of interface rather than class is important for loose coupling

// Constructor based  injection
    public SupplierResource(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllCategories() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }


    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        if (supplierDto.getId() != null) {
            log.error("Cannot have an ID {}", supplierDto);
            throw new BadRequestException(SupplierResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(supplierService.createSupplier(supplierDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@Valid @RequestBody SupplierDto supplierDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(supplierService.updateSupplier(supplierDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable(name = "id") long id) {
        supplierService.deleteSupplierById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
