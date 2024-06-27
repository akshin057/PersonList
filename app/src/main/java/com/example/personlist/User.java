package com.example.personlist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User  {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Имя: " + name + " Возраст: " + age;
    }
}
