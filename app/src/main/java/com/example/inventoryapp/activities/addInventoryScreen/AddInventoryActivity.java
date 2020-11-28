package com.example.inventoryapp.activities.addInventoryScreen;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inventoryapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddInventoryActivity extends AppCompatActivity implements Contract.View {

    public static final String EXTRA_ID = "package com.example.inventoryapp.EXTRA_ID";
    public static final String EXTRA_NAME = "package com.example.inventoryapp.EXTRA_NAME";
    public static final String EXTRA_PRICE = "package com.example.inventoryapp.EXTRA_PRICE";
    public static final String EXTRA_QUANTITY = "package com.example.inventoryapp.EXTRA_QUANTITY";
    public static final String EXTRA_MANUFACTURE = "package com.example.inventoryapp.EXTRA_MANUFACTURE";
    public static final String EXTRA_IMAGE = "package com.example.inventoryapp.EXTRA_IMAGE";

    private EditText editTitle;
    private EditText editPrice;
    private EditText editQuantity;
    private EditText editManufacture;
    public static ImageView imageViewInventory;
    private String currentImagePath = null;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_activivty);

        presenter = new Presenter(this);

        editTitle = findViewById(R.id.edit_title);
        editPrice = findViewById(R.id.edit_price);
        editQuantity = findViewById(R.id.edit_quantity);
        editManufacture = findViewById(R.id.edit_manufacture);
        imageViewInventory = findViewById(R.id.imageViewInventory);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backspace);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(getString(R.string.update));
            editTitle.setText(intent.getStringExtra(EXTRA_NAME));
            editPrice.setText(intent.getStringExtra(EXTRA_PRICE));
            editQuantity.setText(intent.getStringExtra(EXTRA_QUANTITY));
            editManufacture.setText(intent.getStringExtra(EXTRA_MANUFACTURE));
            imageViewInventory.setImageURI(Uri.parse(intent.getStringExtra(EXTRA_IMAGE)));

        } else {
            setTitle(getString(R.string.insertNew));

        }
    }

    public void saveInvent() {
        String title = editTitle.getText().toString();
        String price = editPrice.getText().toString();
        String quantity = editQuantity.getText().toString();
        String manufacture = editManufacture.getText().toString();
        String imageLink = currentImagePath;

        if (title.trim().isEmpty() || price.trim().isEmpty()
                || quantity.trim().isEmpty() || manufacture.trim().isEmpty() || imageLink == null) {
            Toast.makeText(this, R.string.zapolnite, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, title);
        intent.putExtra(EXTRA_PRICE, price);
        intent.putExtra(EXTRA_QUANTITY, quantity);
        intent.putExtra(EXTRA_MANUFACTURE, manufacture);
        intent.putExtra(EXTRA_IMAGE, imageLink);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_icon:
                saveInvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void buttonImage(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageFile != null) {
                Uri imageUrl = FileProvider
                        .getUriForFile(this, "com.example.android.fileProviders", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
                Glide.with(this).load(imageFile).into(imageViewInventory);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }

    private File getImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat")
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = "jpg_" + time + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(name, ".jpeg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;

    }
}