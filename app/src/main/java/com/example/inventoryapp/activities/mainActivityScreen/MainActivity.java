package com.example.inventoryapp.activities.mainActivityScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inventoryapp.activities.addInventoryScreen.AddInventoryActivity;
import com.example.inventoryapp.data.model.Inventory;
import com.example.inventoryapp.adapter.InventoryAdapter;
import com.example.inventoryapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private MainPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addItemButton = findViewById(R.id.floatingActionButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddInventoryActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        final InventoryAdapter adapter = new InventoryAdapter();
        recyclerView.setAdapter(adapter);

        presenter = ViewModelProviders.of(this).get(MainPresenter.class);

        presenter.getAllInventory().observe(this, new Observer<List<Inventory>>() {
            @Override
            public void onChanged(List<Inventory> inventories) {
                adapter.setInventories(inventories);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
                builder.setTitle(R.string.remove);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(adapter.inventoryPosition(viewHolder.getAdapterPosition()));
                        Toast.makeText(MainActivity.this, R.string.product_remove_in_list, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                        Toast.makeText(getApplicationContext(),
                                R.string.notDeleted, Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Inventory inventory) {
                Intent intent = new Intent(MainActivity.this, AddInventoryActivity.class);
                intent.putExtra(AddInventoryActivity.EXTRA_ID, inventory.getId());
                intent.putExtra(AddInventoryActivity.EXTRA_NAME, inventory.getTitle());
                intent.putExtra(AddInventoryActivity.EXTRA_PRICE, inventory.getPrice());
                intent.putExtra(AddInventoryActivity.EXTRA_QUANTITY, inventory.getQuantity());
                intent.putExtra(AddInventoryActivity.EXTRA_MANUFACTURE, inventory.getManufacturer());
                intent.putExtra(AddInventoryActivity.EXTRA_IMAGE, inventory.getImage());
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddInventoryActivity.EXTRA_NAME);
            String price = data.getStringExtra(AddInventoryActivity.EXTRA_PRICE);
            String quantity = data.getStringExtra(AddInventoryActivity.EXTRA_QUANTITY);
            String manufacture = data.getStringExtra(AddInventoryActivity.EXTRA_MANUFACTURE);
            String image = data.getStringExtra(AddInventoryActivity.EXTRA_IMAGE);

            Inventory inventory = new Inventory(title, price, quantity, manufacture, image);
            presenter.insert(inventory);

            Toast.makeText(this, R.string.data_save, Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {

            int id = data.getIntExtra(AddInventoryActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, R.string.otmenaInsert, Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddInventoryActivity.EXTRA_NAME);
            String price = data.getStringExtra(AddInventoryActivity.EXTRA_PRICE);
            String quantity = data.getStringExtra(AddInventoryActivity.EXTRA_QUANTITY);
            String manufacture = data.getStringExtra(AddInventoryActivity.EXTRA_MANUFACTURE);
            String images = data.getStringExtra(AddInventoryActivity.EXTRA_IMAGE);
            Inventory inventory = new Inventory(title, price, quantity, manufacture, images);
            inventory.setId(id);
            presenter.update(inventory);
            Toast.makeText(this, R.string.updateData, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.otmenaSave, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
                builder.setTitle(R.string.deleteAll);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteAll();
                        Toast.makeText(MainActivity.this, R.string.allProductsDeleted, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                R.string.notDeleted, Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}