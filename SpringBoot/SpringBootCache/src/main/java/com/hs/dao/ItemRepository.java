package com.hs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hs.model.Item;

@Repository
public class ItemRepository {

    @Autowired
    JdbcTemplate template;

    /*Getting a specific item by item id from table*/
    public Item getItem(int itemId){
        System.out.println("Reading Item From Repository..");
        String query = "SELECT * FROM ITEM WHERE ID=?";
        return template.queryForObject(query,new Object[]{itemId},new BeanPropertyRowMapper<>(Item.class));
    }

    /*delete an item from database*/
    public int deleteItem(int id){
        String query = "DELETE FROM ITEM WHERE ID =?";
        int size = template.update(query,id);
        return size;
    }

    /*update an item from database*/
    public void updateItem(Item item){
        String query = "UPDATE ITEM SET name=?, category=? WHERE id =?";
        template.update(query,
                new Object[] {
                        item.getName(),item.getCategory(), Integer.valueOf(item.getId())
                });
    }

}
