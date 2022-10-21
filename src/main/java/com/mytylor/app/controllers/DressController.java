package com.mytylor.app.controllers;

import com.mytylor.app.Dto.CustomerRequestDto;
import com.mytylor.app.Dto.CustomerResponseDto;
import com.mytylor.app.Dto.DressRequestDto;
import com.mytylor.app.Dto.DressResponseDto;
import com.mytylor.app.Service.DressService;
import com.mytylor.app.entity.Dress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DressController {

    @Autowired
    private DressService dressService;

    @PostMapping("/saveDress")
    public ResponseEntity<DressResponseDto> saveDress(@RequestBody DressRequestDto dress) {
        DressResponseDto dressRes = dressService.createDress(dress);
        return ResponseEntity.ok(dressRes);
    }

    @PostMapping("/dressesForCustomer")
    public ResponseEntity<List<Map<Object, Object>>> getDressesByCustomerId(@RequestBody Map<String, Long> requestData) {
        List<Map<Object, Object>> dresses = dressService.getDressByCustomer(requestData.get("customerId"));
        return ResponseEntity.ok(dresses);
    }
}
