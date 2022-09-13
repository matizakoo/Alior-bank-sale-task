package org.example.service;

import org.example.Entity.Goods;
import org.example.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

public class GoodsService {
    private final Discount discount;
    private double sum;
    private final ArrayList<Double> promotionalPrice;
    private final ArrayList<Double> priceList;
    private final LinkedHashMap<String, Double> linkedHashMap;
    private final LinkedHashMap<String, Double> summaryList;
    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    public GoodsService() {
        this.discount = new Discount();
        this.sum = 0;
        this.promotionalPrice = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.linkedHashMap = new LinkedHashMap<>();
        this.summaryList = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Double> orderCalculator(ArrayList<Goods> list){
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
        if(list.size() >= discount.PRODUCTS_TO_BE_PROMOTED){
            promotionC(promotionalPrice, linkedHashMap, priceList, list);
        }
        summary(summaryList, linkedHashMap);

        return summaryList;
    }

    private void promotionA(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap){
        logger.info("Promotion A");
        promotionalPrice.add(sum * discount.MORE_THAN_PRODUCTS_DISCOUNT);
        linkedHashMap.put("Promotion A", sum * discount.MORE_THAN_PRODUCTS_DISCOUNT);
    }

    private void promotionB(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap){
        logger.info("Promotion B");
        promotionalPrice.add((double) Math.round((sum * discount.MORE_THAN_SUM_PRICE_DISCOUNT)*100)/100.0);
        linkedHashMap.put("Promotion B", (double) Math.round((sum * discount.MORE_THAN_SUM_PRICE_DISCOUNT)*100)/100);
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

    private void summary(LinkedHashMap<String, Double> summaryList, LinkedHashMap<String, Double> linkedHashMap){
        summaryList.put("Total amount", sum);
        summaryList.put("Amount to pay", (sum - linkedHashMap.values().stream().mapToDouble(Double::doubleValue).sum()));
        for(Map.Entry<String, Double> e: linkedHashMap.entrySet()){
            summaryList.put(e.getKey(), e.getValue());
        }
    }

    public void printSummary(LinkedHashMap<String, Double> summaryList){
        for(Map.Entry<String, Double> e: summaryList.entrySet()){
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
