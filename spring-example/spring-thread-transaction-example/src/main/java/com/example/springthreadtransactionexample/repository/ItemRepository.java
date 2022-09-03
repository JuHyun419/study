package com.example.springthreadtransactionexample.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private Map<String, Integer> item = new HashMap<>();

    public void addCount(String name, Integer count) {
        item.put(name, count);
    }

    public void print() {
        System.out.println(item);
    }

}
