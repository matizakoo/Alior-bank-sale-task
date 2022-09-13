package org.example.Entity;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
public class Goods {
    private String nameOfGood;
    private int amount;
    private double price;

    public Goods(String nameOfGood, int amount, double price) {
        this.nameOfGood = nameOfGood;
        this.amount = amount;
        this.price = price;
    }
}
