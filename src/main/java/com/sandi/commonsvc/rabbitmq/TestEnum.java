package com.sandi.commonsvc.rabbitmq;

import org.springframework.http.HttpStatus;

enum Enum {

    APPLE(1, "Apple"),GOPAL(2, "Gopal");

    private int value;
    private String status;

    Enum(int value, String status){
        this.value = value;
        this.status = status;
    }

}

public class TestEnum{
    public static void main(String...args){
        System.out.println(Enum.APPLE.toString());
        System.out.println(new Object());
        System.out.println(HttpStatus.CREATED);
    }
}
