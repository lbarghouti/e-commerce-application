package com.example.assign1.repository;

import com.example.assign1.entity.Category;
import com.example.assign1.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
