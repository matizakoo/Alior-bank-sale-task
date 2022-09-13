package org.example.Promotions;

import org.example.Entity.Goods;
import org.example.Interfaces.SimplePromotion;
import org.example.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PromotionB implements SimplePromotion {
    Logger logger = LoggerFactory.getLogger(PromotionA.class);
    @Override
    public void promotion(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, Double sum, ArrayList<Goods> list) {
        if (sum >= Discount.MORE_THAN_SUM_PRICE) {
            logger.info("Promotion B");
            promotionalPrice.add((double) Math.round((sum * Discount.MORE_THAN_SUM_PRICE_DISCOUNT) * 100) / 100.0);
            linkedHashMap.put("Promotion B", (double) Math.round((sum * Discount.MORE_THAN_SUM_PRICE_DISCOUNT) * 100) / 100);
        }
    }
}
