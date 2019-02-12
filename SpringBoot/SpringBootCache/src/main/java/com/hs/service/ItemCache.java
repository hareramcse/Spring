package com.hs.service;

import com.hs.dao.ItemRepository;
import com.hs.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ItemCache {

    @Autowired
    ItemRepository itemRepo;

    @Cacheable(value="itemCache", key="#id")
    public Item getItem(int id) throws Exception{
        System.out.println("In ItemCache Component..");
        return itemRepo.getItem(id);
    }

    @CacheEvict(value="itemCache",key = "#id")
    public int deleteItem(int id){
        System.out.println("In ItemCache Component..");
        return itemRepo.deleteItem(id);
    }

    @CachePut(value="itemCache")
    public void updateItem(Item item){
        System.out.println("In ItemCache Component..");
        itemRepo.updateItem(item);
    }
}
