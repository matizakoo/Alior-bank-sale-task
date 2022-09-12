package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.Entity.Goods;
import org.example.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.nio.DoubleBuffer;
import java.util.*;

public class GoodsService {
    private static Discount discount;
    private double sum;
    private ArrayList<Double> promotionalPrice;
    private ArrayList<Double> priceList;
    private LinkedHashMap<String, Double> linkedHashMap;
    private LinkedList<String> linkedList;
    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    public GoodsService() {
        this.discount = new Discount();
        this.sum = 0;
        this.promotionalPrice = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.linkedHashMap = new LinkedHashMap<>();
        this.linkedList = new LinkedList<>();
    }

    public ArrayList<Goods> orderCalculator(ArrayList<Goods> list){
        logger.info("orderCalculator class");
        for(Goods e: list){
            sum += (e.getPrice() * e.getAmount());
        }
        //promotion A
        if(list.size() > discount.MORE_THAN_PRODUCTS){
            promotionA(promotionalPrice, linkedHashMap);
        }
        //promotion B
        if(sum >= discount.MORE_THAN_SUM_PRICE){
            promotionB(promotionalPrice, linkedHashMap);
        }
        //promotion C
        if(list.size() > discount.PRODUCTS_TO_BE_PROMOTED){
            promotionC(promotionalPrice, linkedHashMap, priceList, list);
        }
        summary(linkedList, linkedHashMap);
        System.out.println(linkedList);
        return list;
    }

    private void promotionA(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap){
        logger.info("Promotion A");
        promotionalPrice.add(sum * discount.MORE_THAN_PRODUCTS_DISCOUNT);
        linkedHashMap.put("Promotion A", sum * discount.MORE_THAN_PRODUCTS_DISCOUNT);
    }

    private void promotionB(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap){
        logger.info("Promotion B");
        promotionalPrice.add(sum * discount.MORE_THAN_SUM_PRICE_DISCOUNT);
        linkedHashMap.put("Promotion B", sum * discount.MORE_THAN_SUM_PRICE_DISCOUNT);
    }

    private void promotionC(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, ArrayList<Double> priceList, ArrayList<Goods> list){
        logger.info("Promotion C");
        for(Goods e: list){
            priceList.add(e.getPrice() * e.getAmount());
        }
        Collections.sort(priceList);
        promotionalPrice.add((priceList.get(priceList.size()-discount.PRODUCTS_TO_BE_PROMOTED)) * discount.PRODUCTS_TO_BE_PROMOTED_DISCOUNT);
        linkedHashMap.put("Promotion C", (priceList.get(priceList.size()-discount.PRODUCTS_TO_BE_PROMOTED)) * discount.PRODUCTS_TO_BE_PROMOTED_DISCOUNT);
    }

    private void summary(LinkedList<String> linkedList, LinkedHashMap<String, Double> linkedHashMap){
        linkedList.add("Total amount: " + sum +"\n");
        linkedList.add("Amount to pay: " + (sum - linkedHashMap.values().stream().mapToDouble(Double::doubleValue).sum())+"\n");
        for(Map.Entry<String, Double> e: linkedHashMap.entrySet()){
            System.out.println("value: " + e.getValue());
            linkedList.add(e.getKey() + " " + e.getValue()+"\n");
        }
    }
}
