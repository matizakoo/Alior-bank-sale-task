package org.example.service;

import org.example.Entity.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceTest.class);
    private final GoodsService goodsService = new GoodsService();

    @Test
    @DisplayName("5 items, less than 100000 in sum")
    void orderCalculator() {
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo", 120,5));
        goods.add(new Goods("Stol", 1000,1));
        goods.add(new Goods("Baterie", 2,5));
        goods.add(new Goods("Szafka", 200,2));
        goods.add(new Goods("Klopsiki", 10,1));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertEquals(101.0, map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(40.0, map.get("Promotion C").doubleValue());
    }

    @Test
    @DisplayName("5 items, more than 100000 in sum")
    void orderCalculator2(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo", 120,5));
        goods.add(new Goods("Stol", 10000,1));
        goods.add(new Goods("Baterie", 2,5));
        goods.add(new Goods("Szafka", 200,2));
        goods.add(new Goods("Klopsiki", 10,1));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertEquals(551.0, map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertEquals(771.4, map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(40.0, map.get("Promotion C").doubleValue());
    }

    @Test
    @DisplayName("3 items, less than 10000 in sum")
    void orderCalculator3(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo", 120,5));
        goods.add(new Goods("Baterie", 2,5));
        goods.add(new Goods("Szafka", 200,2));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(1.0, map.get("Promotion C").doubleValue());
    }
}