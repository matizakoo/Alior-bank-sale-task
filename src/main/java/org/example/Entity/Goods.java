package org.example.Entity;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
public class Goods {
    private String nameOfGood;
    private double amount;
    private int price;

    public Goods(String nameOfGood, double amount, int price) {
        this.nameOfGood = nameOfGood;
        this.amount = amount;
        this.price = price;
    }
}
