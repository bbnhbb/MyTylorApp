package com.mytylor.app.Service;

import com.mytylor.app.Dto.CustomerRequestDto;
import com.mytylor.app.Dto.CustomerResponseDto;
import com.mytylor.app.entity.Customer;
import com.mytylor.app.entity.User;
import com.mytylor.app.repository.CustomerRepository;
import com.mytylor.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;


    public CustomerResponseDto saveCustomer(CustomerRequestDto customer) {
        try {
            User user = userRepository.findById(customer.getTylor_id()).get();
            System.out.println("user found");
            System.out.println(user);
            Customer customer1 = new Customer();
            customer1.setUsername(customer.getUsername());
            customer1.setUser(user);
            Customer save = customerRepository.save(customer1);
            CustomerResponseDto newCust = new CustomerResponseDto();
            newCust.id = save.getId();
            newCust.username = save.getUsername();
            newCust.owner = save.getUser().getUsername();
            newCust.ownerId = save.getUser().getId();
            return newCust;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CustomerResponseDto> getCustomers() {
        try {
            List<Customer> all = customerRepository.findAll();
            List<CustomerResponseDto> all1 = new ArrayList<>();
            all.stream().forEach((cust) -> {
                CustomerResponseDto crd = new CustomerResponseDto();
                crd.username = cust.getUsername();
                crd.id = cust.getId();
                crd.owner = cust.getUser().getUsername();
                crd.ownerId = cust.getUser().getId();
                all1.add(crd);
            });
            return all1;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CustomerResponseDto> getCustomersByTylor(Long tylorId) {
        try {
            User user = userRepository.findById(tylorId).get();
            List<Customer> custAll = user.getCustomerList();
            List<CustomerResponseDto> all1 = new ArrayList<>();
            custAll.stream().forEach((cust) -> {
                CustomerResponseDto crd = new CustomerResponseDto();
                crd.username = cust.getUsername();
                crd.id = cust.getId();
                crd.owner = cust.getUser().getUsername();
                crd.ownerId = cust.getUser().getId();
                crd.noOfDresses = cust.getDressList().size();
                all1.add(crd);
            });
            return all1;
        } catch (Exception e) {
            throw e;
        }
    }
}
