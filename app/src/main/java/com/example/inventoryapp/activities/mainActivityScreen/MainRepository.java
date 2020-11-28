package com.example.inventoryapp.activities.mainActivityScreen;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.inventoryapp.data.InventoryDao;
import com.example.inventoryapp.data.InventoryDatabase;
import com.example.inventoryapp.data.model.Inventory;

import java.util.List;

public class MainRepository {

    private InventoryDao inventoryDao;
    private LiveData<List<Inventory>> allInventory;

    public LiveData<List<Inventory>> getAllInventory() {
        return allInventory;
    }

    public MainRepository(Application context) {
        InventoryDatabase database = InventoryDatabase.getInstance(context);
        inventoryDao = database.inventoryDao();
        allInventory = inventoryDao.getAllInventory();
    }

    public void insert(Inventory inventory) {
        new InsertInventoryAsyncTask(inventoryDao).execute(inventory);
    }


    public void update(Inventory inventory) {
        new UpdateInventoryAsyncTask(inventoryDao).execute(inventory);
    }

    public void delete(Inventory inventory) {
        new DeleteInventoryAsyncTask(inventoryDao).execute(inventory);
    }

    public void deleteAll() {
        new DeleteAllInventoryAsyncTask(inventoryDao).execute();
    }



    private static class InsertInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {

        private InventoryDao inventoryDao;

        public InsertInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.insert(inventories[0]);
            return null;
        }
    }

    private static class UpdateInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {

        private InventoryDao inventoryDao;

        public UpdateInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.update(inventories[0]);
            return null;
        }
    }

    private static class DeleteInventoryAsyncTask extends AsyncTask<Inventory, Void, Void> {

        private InventoryDao inventoryDao;

        public DeleteInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Inventory... inventories) {
            inventoryDao.delete(inventories[0]);
            return null;
        }
    }

    private static class DeleteAllInventoryAsyncTask extends AsyncTask<Void, Void, Void> {

        private InventoryDao inventoryDao;

        public DeleteAllInventoryAsyncTask(InventoryDao inventoryDao) {
            this.inventoryDao = inventoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            inventoryDao.deleteAll();
            return null;
        }
    }
}
