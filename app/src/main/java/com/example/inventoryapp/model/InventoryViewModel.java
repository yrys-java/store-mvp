//package com.example.inventoryapp.model;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.inventoryapp.repository.InventoryRepository;
//
//import java.util.List;
//
//public class InventoryViewModel extends AndroidViewModel {
//
//    private InventoryRepository inventoryRepository;
//    private LiveData<List<Inventory>> allInventory;
//
//    public InventoryViewModel(@NonNull Application application) {
//        super(application);
//        inventoryRepository = new InventoryRepository(application);
//        allInventory = inventoryRepository.getAllInventory();
//    }
//
//    public void insert(Inventory inventory) {
//        inventoryRepository.insert(inventory);
//    }
//
//    public void update(Inventory inventory) {
//        inventoryRepository.update(inventory);
//    }
//
//    public void delete(Inventory inventory) {
//        inventoryRepository.delete(inventory);
//    }
//
//    public void deleteAll() {
//        inventoryRepository.deleteAll();
//    }
//
//    public LiveData<List<Inventory>> getAllInventory() {
//        return allInventory;
//    }
//}
