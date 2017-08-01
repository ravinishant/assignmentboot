package com.boot;

import com.boot.controller.CustomerHelper;
import com.boot.model.Customer;
import com.google.gson.Gson;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Unit test for Customer APIs.
 */
public class AppTest 
    extends TestCase
{
    Gson gson = new Gson();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testFetch() throws FileNotFoundException {
        List<Customer> data = (List<Customer>) CustomerHelper.list();
        String strData = gson.toJson(data);
        String content = new Scanner(new File("C:/Projects/customer.json")).useDelimiter("\\Z").next();
        assertTrue(strData.equals(content));
    }

    public void testInsert() throws IOException {
        CustomerHelper.create(new Customer("111","Test Name", 24, "Test Description", "test.name@email.com", "San Jose", "95113"));
        assertTrue(CustomerHelper.checkIfExist("111"));
        CustomerHelper.delete("111");
    }

    public void testDelete() throws IOException {
        CustomerHelper.create(new Customer("111","Test Name", 24, "Test Description", "test.name@email.com", "San Jose", "95113"));
        boolean inserted = CustomerHelper.checkIfExist("111");
        CustomerHelper.delete("111");
        assertTrue(inserted && !CustomerHelper.checkIfExist("111"));
    }

    public void testFechById() throws IOException {
        CustomerHelper.create(new Customer("111","Test Name", 24, "Test Description", "test.name@email.com", "San Jose", "95113"));
        Customer cust = CustomerHelper.fetch("111");
        CustomerHelper.delete("111");
        assertTrue(cust != null);
    }

    public void testUpdate() throws IOException {
        CustomerHelper.create(new Customer("111","Test Name", 24, "Test Description", "test.name@email.com", "San Jose", "95113"));
        CustomerHelper.update("111", new Customer("111","Test Name Updated", 24, "Test Description", "test.name@email.com", "San Jose", "95113"));
        Customer cust = CustomerHelper.fetch("111");
        assertTrue(cust.getName().equals("Test Name Updated"));
    }

    public void GenerateUsers() throws IOException {
        String[] data = {"Dalton Rugh", "Andrea Quintal","Arden Ridenour", "Eryn Carnegie", "Julius Riedl", "Maureen Denzer", "Monty Bjelland", "Stephane Saraiva","Thomas Swanner","Shanda Padgett",
                "Hilaria Vrieze","Orval Perera","Chastity Mowrer","Shizue Faith","Melisa Cao","Alda Bergeson","Mathilde Scheffer","Ella Caffee"};
        String[] cityy = {"Dallas", "San Jose", "Santa Clara", "San Francisco", "Arlington", "Seattle"};
        for (int i = 1; i <= 18; i++) {
            Random rand = new Random();
            String id = Integer.toString(i * 200);
            int age = rand.nextInt(80) + 20;
            String desc = "Awesome Customer of age" + Integer.toString(age);
            String zipcode = Integer.toString(rand.nextInt(29999) + 70000);
            String name =data[i-1];
            String city = cityy[rand.nextInt(6)];
            String email = name.split(" ")[0] + "." + name.split(" ")[1] + "@email.com";
            CustomerHelper.create(new Customer(id,name,age,desc,email,city,zipcode));
        }
    }
}
