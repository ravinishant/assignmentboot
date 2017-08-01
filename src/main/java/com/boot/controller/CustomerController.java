package com.boot.controller;

import com.boot.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by SaiVedNish on 7/31/2017.
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    /**
     Controller method to list all customers
     @return ResponseEntity<List<Customer>>
     */
    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>>  list() throws FileNotFoundException {
        List<Customer> customers = CustomerHelper.list();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    /**
     Controller method to add a customer
     @param customer Object
     @return ResponseEntity<String>
     */
    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Customer customer) throws IOException {
        String result = CustomerHelper.create(customer);
        if (result.equals("CUST_EXIST")) {
            return new ResponseEntity<String>("Unable to create. A Customer with Id " +
                    customer.getId() + " already exist.",HttpStatus.CONFLICT);
        }
        else if (result.equals("CREATED")) {
            return new ResponseEntity<String>("Created", HttpStatus.OK);
        }
        return null;
    }

    /**
     Controller method to get a customer by ID
     @param id
     @return ResponseEntity<Customer>
     */
    @RequestMapping(value = "customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> get(@PathVariable String id) throws FileNotFoundException {
        Customer data = CustomerHelper.fetch(id);
        if (data == null) {
            return new ResponseEntity<Customer>(data, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(data, HttpStatus.OK);
    }

    /**
     Controller method to update a customer by ID
     @param id
     @param customer
     @return ResponseEntity<String>
     */
    @RequestMapping(value = "customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Customer customer) throws IOException {
        String result = CustomerHelper.update(id, customer);
        if (result.equals("CUST_NOT_EXIST")) {
            return new ResponseEntity<String>("Unable to update. A Customer with Id " +
                    id + " does not exist.",HttpStatus.NOT_FOUND);
        }
        else if (result.equals("UPDATED")) {
            return new ResponseEntity<String>("UPDATED", HttpStatus.OK);
        }
        return null;
    }

    /**
     Controller method to delete a customer by ID
     @param id
     @return ResponseEntity<String>
     */
    @RequestMapping(value = "customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable String id) throws IOException {
        String result = CustomerHelper.delete(id);
        if (result.equals("CUST_NOT_EXIST")) {
            return new ResponseEntity<String>("Unable to delete. A Customer with Id " +
                    id + " does not exist.",HttpStatus.NOT_FOUND);
        }
        else if (result.equals("DELETED")) {
            return new ResponseEntity<String>("DELETED", HttpStatus.OK);
        }
        return null;
    }
}
