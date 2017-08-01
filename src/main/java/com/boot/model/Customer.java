package com.boot.model;

/**
 * Created by SaiVedNish on 7/31/2017.
 */
public class Customer {
    private String id;
    private String name;
    private Integer age;
    private String description;
    private String email;
    private String city;
    private String zipcode;

    public Customer() {}
    public Customer(String id, String name, Integer age, String description, String email, String city, String zipcode) {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setDescription(description);
        this.setEmail(email);
        this.setCity(city);
        this.setZipcode(zipcode);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
