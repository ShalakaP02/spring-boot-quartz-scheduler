package com.shalaka.quartzschedular.model;

import java.util.Date;

public class Employee {

    private int id;
    private String name;
    private Date birthDate;
    private Date workJoiningDate;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getWorkJoiningDate() {
        return workJoiningDate;
    }

    public void setWorkJoiningDate(Date workJoiningDate) {
        this.workJoiningDate = workJoiningDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(int id, String name, Date birthDate, Date workJoiningDate, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.workJoiningDate = workJoiningDate;
        this.email = email;
    }

    public Employee(){

    }
}
