package com.example.inventoryapp.activities.mainActivityScreen;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.inventoryapp.data.model.Inventory;

import java.util.List;

public class MainPresenter extends AndroidViewModel implements Contract.Presenter {

    MainRepository repository;
    LiveData<List<Inventory>> getAllInventory;

    public MainPresenter(Application application) {
        super(application);
        repository = new MainRepository(application);
        getAllInventory = repository.getAllInventory();
    }


    @Override
    public void insert(Inventory inventory) {
        repository.insert(inventory);
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
    public LiveData<List<Inventory>> getAllInventory() {
        return repository.getAllInventory();
    }
}
