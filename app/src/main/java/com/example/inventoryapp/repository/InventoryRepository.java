//package com.example.inventoryapp.repository;
//
//import android.app.Application;
//import android.os.AsyncTask;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.inventoryapp.data.InventoryDao;
//import com.example.inventoryapp.data.InventoryDatabase;
//import com.example.inventoryapp.model.Inventory;
//
//import java.util.List;
//
//public class InventoryRepository {
//
//    private InventoryDao inventoryDao;
//    private LiveData<List<Inventory>> allInventory;
//
//    public InventoryRepository(Application application) {
//        InventoryDatabase database = InventoryDatabase.getInstance(application);
//        inventoryDao = database.inventoryDao();
//        allInventory = inventoryDao.getAllInventory();
//    }
//
//    public void insert(Inventory inventory) {
//        new InsertInventoryAsyncTask(inventoryDao).execute(inventory);
//    }
//
//    public void update(Inventory inventory) {
//        new UpdateInventoryAsyncTask(inventoryDao).execute(inventory);
//    }
//
//    public void delete(Inventory inventory) {
//        new DeleteInventoryAsyncTask(inventoryDao).execute(inventory);
//    }
//
//    public void deleteAll(){
//        new DeleteAllInventoryAsyncTask(inventoryDao).execute();
//
//    }
//
//    public LiveData<List<Inventory>> getAllInventory() {
//        return allInventory;
//    }
//
////    public static class InsertInventoryAsyncTask  extends AsyncTask<Inventory, Void, Void> {
////
////        private InventoryDao inventoryDao;
////
////        private InsertInventoryAsyncTask(InventoryDao inventoryDao) {
////            this.inventoryDao = inventoryDao;
////        }
////
////        @Override
////        protected Void doInBackground(Inventory... inventories) {
////            inventoryDao.insert(inventories[0]);
////            return null;
////        }
////    }
////
////    private static class UpdateInventoryAsyncTask  extends AsyncTask<Inventory, Void, Void> {
////
////        private InventoryDao inventoryDao;
////
////        public UpdateInventoryAsyncTask(InventoryDao inventoryDao) {
////            this.inventoryDao = inventoryDao;
////        }
////
////        @Override
////        protected Void doInBackground(Inventory... inventories) {
////            inventoryDao.update(inventories[0]);
////            return null;
////        }
////    }
////    private static class DeleteInventoryAsyncTask  extends AsyncTask<Inventory, Void, Void> {
////
////        private InventoryDao inventoryDao;
////
////        public DeleteInventoryAsyncTask(InventoryDao inventoryDao) {
////            this.inventoryDao = inventoryDao;
////        }
////
////        @Override
////        protected Void doInBackground(Inventory... inventories) {
////            inventoryDao.delete(inventories[0]);
////            return null;
////        }
////    }
////
////    private static class DeleteAllInventoryAsyncTask  extends AsyncTask<Void, Void, Void> {
////
////        private InventoryDao inventoryDao;
////
////        public DeleteAllInventoryAsyncTask(InventoryDao inventoryDao) {
////            this.inventoryDao = inventoryDao;
////        }
////
////        @Override
////        protected Void doInBackground(Void... voids) {
////            inventoryDao.deleteAll();
////            return null;
////        }
////    }
//}
