package org.example.Interfaces;

import org.example.Entity.Goods;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface ComplicatedPromotion {
    void promotionC(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, ArrayList<Double> priceList, ArrayList<Goods> list);
}
