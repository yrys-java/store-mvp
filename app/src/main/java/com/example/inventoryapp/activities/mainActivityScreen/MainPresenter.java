package com.example.inventoryapp.activities.mainActivityScreen;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.inventoryapp.model.Inventory;

import java.util.List;

public class MainPresenter implements Contract.Presenter{

    Contract.View view;
    MainRepository repository;

    public MainPresenter(Contract.View mView) {
        this.view = mView;
        this.repository = new MainRepository(this);
    }


    @Override
    public void insert(Inventory inventory) {
        repository.insert(inventory);
        Log.d("TAG", "insert: "+inventory);
    }

    @Override
    public void update(Inventory inventory) {
        repository.update(inventory);

    }

    @Override
    public void delete(Inventory inventory) {
        repository.delete(inventory);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Inventory> getAllInventory() {
        return repository.getAllInventory();
    }
}
