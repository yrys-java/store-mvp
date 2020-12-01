package com.example.inventoryapp.activities.addInventoryScreen;

public class Presenter implements Contract.Presenter {

    private Contract.View view;


    public Presenter(Contract.View view) {
        this.view = view;
    }

}
