package org.example.Promotions;

import org.example.Entity.Goods;
import org.example.Interfaces.SimplePromotion;
import org.example.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PromotionA implements SimplePromotion {
    Logger logger = LoggerFactory.getLogger(PromotionA.class);

    @Override
    public void promotion(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, Double sum, ArrayList<Goods> list) {
        if (list.size() > Discount.MORE_THAN_PRODUCTS) {
            logger.info("Promotion A");
            promotionalPrice.add(sum * Discount.MORE_THAN_PRODUCTS_DISCOUNT);
            linkedHashMap.put("Promotion A", sum * Discount.MORE_THAN_PRODUCTS_DISCOUNT);
        }
    }
}
