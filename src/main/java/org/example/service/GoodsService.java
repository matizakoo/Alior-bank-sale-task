package org.example.service;

import org.example.Entity.Goods;
import org.example.Promotions.PromotionA;
import org.example.Promotions.PromotionB;
import org.example.Promotions.PromotionC;
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
    private final PromotionA a;
    private final PromotionB b;
    private final PromotionC c;

    public GoodsService() {
        this.discount = new Discount();
        this.sum = 0;
        this.promotionalPrice = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.linkedHashMap = new LinkedHashMap<>();
        this.summaryList = new LinkedHashMap<>();
        this.a = new PromotionA();
        this.b = new PromotionB();
        this.c = new PromotionC();

    }

    public LinkedHashMap<String, Double> orderCalculator(ArrayList<Goods> list){
        logger.info("orderCalculator class");
        for(Goods e: list){
            sum += (e.getPrice() * e.getAmount());
        }

        a.promotion(promotionalPrice, linkedHashMap, sum, list);
        b.promotion(promotionalPrice, linkedHashMap, sum, list);
        c.promotionC(promotionalPrice, linkedHashMap, priceList, list);

        summary(summaryList, linkedHashMap);

        return summaryList;
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
