package org.lucius.entity;

import java.io.Serializable;

/**
 * @Author: Lucius
 * @Date: 2022-04-04 10:34
 */

public class User implements Serializable {

    public User(){}

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    private String userName;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
