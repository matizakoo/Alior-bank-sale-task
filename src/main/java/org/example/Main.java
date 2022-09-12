package org.example;

import org.example.Entity.Goods;
import org.example.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Initalization");
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo", 120,5));
        goods.add(new Goods("Stol", 1000,1));
        goods.add(new Goods("Baterie", 2,5));
        goods.add(new Goods("Szafka", 200,2));
        goods.add(new Goods("Klopsiki", 10,1));
        GoodsService goodsService = new GoodsService();
        goodsService.orderCalculator(goods);
    }
}