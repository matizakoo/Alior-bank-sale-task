package org.example.Interfaces;

import org.example.Entity.Goods;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface SimplePromotion {
    void promotion(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, Double sum, ArrayList<Goods> list);
}
