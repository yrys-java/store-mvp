package com.example.inventoryapp.activities.addInventoryScreen;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class Presenter implements Contract.Presenter {

    AddInventoryActivity addInventoryActivity;
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
    }

}
