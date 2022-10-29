package com.mytylor.app.Service;

import com.mytylor.app.Dto.DressRequestDto;
import com.mytylor.app.Dto.DressResponseDto;
import com.mytylor.app.entity.Customer;
import com.mytylor.app.entity.Dress;
import com.mytylor.app.repository.CustomerRepository;
import com.mytylor.app.repository.DressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DressService {
    @Autowired
    private DressRepository dressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public DressResponseDto createDress(DressRequestDto dress) {
        try {
            Customer customer = customerRepository.findById(dress.customer_id).get();
            Dress newDress = new Dress();
            // newDress.setId(dress.id);
            newDress.setCustomer(customer);
            newDress.setName(dress.name);
            DressRequestDto.Measurements measurements = dress.measurements;
            newDress.setMsrSholder(measurements.sholder);
            newDress.setMsrArm(measurements.arm);
            newDress.setMsrBelly(measurements.belly);
            newDress.setMsrLeg(measurements.leg);
            newDress.setMsrWaist(measurements.waist);

            //        saving dress
            Dress saveDress = dressRepository.save(newDress);

            // Response in new response formate
            DressResponseDto dressResponseDto = new DressResponseDto();
            dressResponseDto.id=  saveDress.getId();
            dressResponseDto.customer_id = saveDress.getCustomer().getId();
            dressResponseDto.name = saveDress.getName();

            return dressResponseDto;
        } catch (Exception e) {
            // Create custom CustomerNotFoundException and throw that exception from here
            throw e;
        }
    }

    public List<Map<Object, Object>> getDressByCustomer(Long customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).get();
            List<Dress> dressList = customer.getDressList();
//            formating the output
            List<Map<Object, Object>> dressListResponse = new ArrayList<>();
            dressList.forEach((dress) -> {
                Map<Object, Object> dressOPData = new HashMap<>();

                dressOPData.put("id", dress.getId());
                dressOPData.put("name",dress.getName());
                dressOPData.put("customer_id", dress.getCustomer().getId());
                Map<Object, Object> dressOPDataMesurment = new HashMap<>();
                dressOPDataMesurment.put("sholder", dress.getMsrSholder());
                dressOPDataMesurment.put("waist", dress.getMsrWaist());
                dressOPDataMesurment.put("belly", dress.getMsrBelly());
                dressOPDataMesurment.put("arm", dress.getMsrArm());
                dressOPDataMesurment.put("leg", dress.getMsrLeg());
                dressOPData.put("measurements", dressOPDataMesurment);
                dressListResponse.add(dressOPData);
            });

            return dressListResponse;
        } catch (Exception e ) {
            System.out.println(e);
            throw e;
        }

    }
}
