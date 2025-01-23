package com.java24.plantswap.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "users")
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private ArrayList<Plant> plants;

    private ArrayList<Transaction> transactions;

    //tomm konstruktor
    public User() {
    }

    //getters och setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
