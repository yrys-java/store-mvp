package com.example.inventoryapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventoryapp.data.model.Inventory;

import java.util.List;

@Dao
public interface InventoryDao {
    @Insert
    void insert(Inventory inventory);

    @Update
    void update(Inventory inventory);

    @Delete
    void delete(Inventory inventory);

    @Query("DELETE FROM inventories")
    void deleteAll();

    @Query("SELECT * FROM inventories ORDER BY price ASC")
     LiveData<List<Inventory>> getAllInventory();

}
