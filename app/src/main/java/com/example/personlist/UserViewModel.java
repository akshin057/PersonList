package com.example.personlist;

import android.widget.ArrayAdapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<User>> users;
    private ArrayList<User> userList;


    public UserViewModel() {
        userList = new ArrayList<>();
        this.users = new MutableLiveData<>(userList);
    }

    public void add(User user) {
        if (user != null) {
            userList.add(user);
            users.setValue(userList);
        }
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
