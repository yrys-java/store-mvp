package com.example.inventoryapp.activities.mainActivityScreen;

import com.example.inventoryapp.model.Inventory;

import java.util.List;

public interface Contract {

    interface Presenter{

        void insert(Inventory inventory);
        void update(Inventory inventory);
        void delete(Inventory inventory);
        void deleteAll();
        List<Inventory> getAllInventory();

    }

    interface View{

    }
}
