package com.example.assign1.repository;

import com.example.assign1.entity.Category;
import com.example.assign1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
