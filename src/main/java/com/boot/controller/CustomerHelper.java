package com.boot.controller;

import com.boot.model.Customer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.spark_project.guava.reflect.TypeToken;

/**
 * Created byy SaiVedNish on 7/31/2017.
 */
public class CustomerHelper {
    private static final Type CUSTOMER_TYPE = new TypeToken<List<Customer>>() {}.getType();
    private static Gson gson = new Gson();
    private static final String path = "/resources/data/customer.json";

    /**
     helper method to list all customers
     @return List<Customer>
     */
    public static List<Customer> list() throws FileNotFoundException {
        return readData();
    }

    /**
     helper method to create a customer
     @param customer
     @return String
     */
    public static String create(Customer customer) throws IOException {
        String id = customer.getId();
        if (checkIfExist(id))
            return "CUST_EXIST";
        List<Customer> data = readData();
        data.add(customer);
        writeData(data);
        return "CREATED";
    }

    /**
     helper method to update a customers record
     @param id
     @param customer
     @return String
     */
    public static String update(String id, Customer customer) throws IOException {
        if (!checkIfExist(id))
            return "CUST_NOT_EXIST";
        List<Customer> data = readData();
        for(Customer cust : data) {
            if (cust.getId().equals(id)) {
                cust.setAge(customer.getAge());
                cust.setCity(customer.getCity());
                cust.setDescription(customer.getDescription());
                cust.setEmail(customer.getEmail());
                cust.setName(customer.getName());
                cust.setZipcode(customer.getZipcode());
            }
        }
        writeData(data);
        return "UPDATED";
    }

    /**
     helper method to fetch customer by id
     @param id
     @return List<Customer>
     */
    public static Customer fetch(String id) throws FileNotFoundException {
        List<Customer> data = readData();
        for(Customer customer : data) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
    /**
     helper method to delete a customer by id
     @return String
     */
    public static String delete(String id) throws IOException {
        if (!checkIfExist(id))
            return "CUST_NOT_EXIST";
        List<Customer> data = readData();
        Iterator<Customer> iter = data.iterator();
        while (iter.hasNext()) {
            Customer cust = iter.next();
            if (cust.getId().equals(id)) {
                iter.remove();
            }
        }
        writeData(data);
        return "DELETED";
    }

    /**
     private method read data from json file
     @return List<Customer>
     */
    private static List<Customer> readData() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Customer> data = gson.fromJson(reader, CUSTOMER_TYPE);
        return data;
    }

    /**
     private method to write data to json file
     */
    private static void writeData(List<Customer> data) throws IOException {
        Writer writer = new FileWriter(path);
        Gson gsonOut = new GsonBuilder().create();
        gsonOut.toJson(data, writer);
        writer.close();
    }
    /**
     method to check if a customer exists by id. made public to incorporate in unit tests
     @return boolean
     */
    public static boolean checkIfExist(String id) throws FileNotFoundException {
        List<Customer> data = readData();
        for(Customer cust : data) {
            if (cust.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
