package org.example.service;

import org.example.Entity.Goods;
import org.example.discount.Discount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceTest.class);
    private final GoodsService goodsService = new GoodsService();
    private final Discount discount = new Discount();

    @Test
    @DisplayName("5 items, less than 100000 in sum")
    void fiveItemsLessThan100000InSum() {
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo", 120,5));
        goods.add(new Goods("Stol", 1000,1));
        goods.add(new Goods("Baterie", 2,5));
        goods.add(new Goods("Szafka", 200,2));
        goods.add(new Goods("Klopsiki", 10,1));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertEquals(101.0, map.get("Promotion A"));

        logger.info("Check promotion B TEST");
        assertNull(map.get("Promotion B"));

        logger.info("Check promotion C TEST");
        assertEquals(40.0, map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(4, map.size());
    }

    @Test
    @DisplayName("5 items, more than 100000 in sum")
    void fiveItemsMoreThan100000InSum(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Buty", 5,120));
        goods.add(new Goods("Wypasiony telewizor", 1,10000));
        goods.add(new Goods("Cukinia", 5,2));
        goods.add(new Goods("Lustro", 2,200));
        goods.add(new Goods("Kabanosy", 1,10));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertEquals(551.0, map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertEquals(771.4, map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(40.0, map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(5, map.size());
    }

    @Test
    @DisplayName("Empty list")
    void emptyList(){
        ArrayList<Goods> goods = new ArrayList<>();
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        LinkedHashMap<String,Double> testMap = new LinkedHashMap<>();
        testMap.put("Total amount",0.0);
        testMap.put("Amount to pay",0.0);
        assertEquals(testMap, map);
    }

    @Test
    @DisplayName("3 items, less than 10000 in sum")
    void threeItemsLessThan10000InSum(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Krzeslo BODZIO", 16,14.7));
        goods.add(new Goods("Kredki", 5,2));
        goods.add(new Goods("Wiatrak", 6,5.5));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(1.0, map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("3 items, more than 10000 in sum")
    void threeItemsMoreThan10000InSum(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Haczyki", 7,100));
        goods.add(new Goods("Miska WC Catalano", 5,20000));
        goods.add(new Goods("Zestaw prysznicowy", 1,300));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertEquals(7070.0, map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertEquals(30.0, map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(4, map.size());
    }

    @Test
    @DisplayName("2 items, less than 10000 in sum")
    void twoItemsLessThan100000InSum(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Stolik", 1,500));
        goods.add(new Goods("Tablica na krede", 1,175));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(2, map.size());
    }

    @Test
    @DisplayName("2 items, more than 10000 in sum")
    void twoItemsMoreThan100000InSum(){
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("Mlotek", 1,50));
        goods.add(new Goods("Swinka skarbonka z pieniedzmi", 5,2000));
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);
        logger.info("Check promotion A TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());

        logger.info("Check promotion B TEST");
        assertEquals(703.5, map.get("Promotion B").doubleValue());

        logger.info("Check promotion C TEST");
        assertThrows(NullPointerException.class, () -> map.get("Promotion C").doubleValue());

        logger.info("Check map size");
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Random test with items and price without")
    void testWithRandomItemsAndPrices() throws FileNotFoundException {
        Random rand = new Random();
        int random = rand.nextInt(1,15);
        ArrayList<Goods> goods = new ArrayList<>();
        FileReader file = new FileReader("testFile.txt");
        Scanner scanner = new Scanner(file);
        for(int i=0;i<random;i++){
            goods.add(new Goods(scanner.nextLine(), rand.nextInt(1,50), (double)Math.round(rand.nextDouble(1,10000)*100)/100));
        }
        scanner.close();
        LinkedHashMap<String, Double> map = goodsService.orderCalculator(goods);

        logger.info("Check promotion A TEST");
        if(map.size() > discount.MORE_THAN_PRODUCTS){
            //honestly I don't have any idea why I have to divide it by 2.
            logger.info("Check promotion A TEST SIZE > discount.MORE_THAN_PRODUCTS");
            assertEquals(((map.values().stream().mapToDouble(Double::doubleValue).sum()) * discount.MORE_THAN_PRODUCTS_DISCOUNT)/2, map.get("Promotion A").doubleValue());
        }
        else{
            logger.info("Check promotion A TEST SIZE EXCEPTION");
            assertThrows(NullPointerException.class, () -> map.get("Promotion A").doubleValue());
        }

        logger.info("Check promotion B TEST");
        if(map.values().stream().mapToDouble(Double::doubleValue).sum() > discount.MORE_THAN_SUM_PRICE){
            logger.info("Check promotion B TEST SIZE > discount.MORE_THAN_SUM_PRICE");
            assertEquals((double)Math.round(
                    ((map.values().stream().mapToDouble(Double::doubleValue).sum() * discount.MORE_THAN_SUM_PRICE_DISCOUNT)/2) *100)/100,
                    map.get("Promotion B").doubleValue());
        }else{
            logger.info("Check promotion B TEST SIZE EXCEPTION");
            assertThrows(NullPointerException.class, () -> map.get("Promotion B").doubleValue());
        }
    }
}