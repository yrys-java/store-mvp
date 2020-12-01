package com.example.inventoryapp.activities.mainActivityScreen;

import androidx.lifecycle.LiveData;

import com.example.inventoryapp.data.model.Inventory;

import java.util.List;

public interface Contract {

    interface Presenter{
        void insert(Inventory inventory);
        void update(Inventory inventory);
        void delete(Inventory inventory);
        void deleteAll();
        LiveData<List<Inventory>> getAllInventory();
    }

    interface View{

    }
}
