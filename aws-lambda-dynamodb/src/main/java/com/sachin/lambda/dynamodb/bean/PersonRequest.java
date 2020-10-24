package com.sachin.lambda.dynamodb.bean;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersonRequest {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;

   /* public static void main(String[] args) {
        PersonRequest personRequest = new PersonRequest();
        int uuid = 123;
        personRequest.setId(uuid);
        personRequest.setFirstName("Sachin");
        personRequest.setLastName("Kapale");
        personRequest.setAge(30);
        personRequest.setAddress("United States");
        System.out.println(personRequest);
    }*/

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int uuid) {
        this.id = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
