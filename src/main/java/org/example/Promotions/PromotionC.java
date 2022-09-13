package org.example.Promotions;

import org.example.Entity.Goods;
import org.example.Interfaces.ComplicatedPromotion;
import org.example.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class PromotionC implements ComplicatedPromotion {
    Logger logger = LoggerFactory.getLogger(PromotionA.class);
    @Override
    public void promotionC(ArrayList<Double> promotionalPrice, LinkedHashMap<String, Double> linkedHashMap, ArrayList<Double> priceList, ArrayList<Goods> list) {
        if (list.size() >= Discount.PRODUCTS_TO_BE_PROMOTED) {
            logger.info("Promotion C");
            for (Goods e : list) {
                priceList.add(e.getPrice() * e.getAmount());
            }
            Collections.sort(priceList);
            promotionalPrice.add((priceList.get(priceList.size() - Discount.PRODUCTS_TO_BE_PROMOTED)) * Discount.PRODUCTS_TO_BE_PROMOTED_DISCOUNT);
            linkedHashMap.put("Promotion C", (priceList.get(priceList.size() - Discount.PRODUCTS_TO_BE_PROMOTED)) * Discount.PRODUCTS_TO_BE_PROMOTED_DISCOUNT);
        }
    }
}
