package com.mytylor.app.controllers;


import com.mytylor.app.Dto.CustomerRequestDto;
import com.mytylor.app.Dto.CustomerResponseDto;
import com.mytylor.app.Service.CustomerService;
import com.mytylor.app.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCusomers")
    public ResponseEntity<CustomerResponseDto> saveCustomer(@RequestBody CustomerRequestDto cust) {
        CustomerResponseDto customer = customerService.saveCustomer(cust);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        List<CustomerResponseDto> customer = customerService.getCustomers();
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customersForTylor")
    public ResponseEntity<List<CustomerResponseDto>> getCustomers(@RequestBody Map<String, String> tylorId) {
        List<CustomerResponseDto> customer = customerService.getCustomersByTylor(Long.valueOf(tylorId.get("id")));
        return ResponseEntity.ok(customer);
    }
}
