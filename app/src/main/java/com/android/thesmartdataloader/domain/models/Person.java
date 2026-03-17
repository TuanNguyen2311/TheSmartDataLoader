package com.android.thesmartdataloader.domain.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private boolean isVip;

    @Ignore
    public Person(Person person) {
        this(person.id, person.name, person.age, person.email, person.phone, person.gender, person.address, person.isVip);
    }


    public Person(int id,@NonNull String name, int age, @NonNull String email, @NonNull String phone, @NonNull String gender, @NonNull String address, boolean isVip) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.isVip = isVip;
    }

    @Ignore
    public Person(String name, int age, String gender, boolean isVip) {
        this.name = name;
        this.age = age;
        this.email = "Hidden email";
        this.phone = "Hidden phone";
        this.gender = gender;
        this.address = "Hidden address";
        this.isVip = isVip;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
