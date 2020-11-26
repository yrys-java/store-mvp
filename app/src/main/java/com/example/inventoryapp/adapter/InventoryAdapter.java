package com.example.inventoryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inventoryapp.R;
import com.example.inventoryapp.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryHolder> {

    private List<Inventory> inventories = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Inventory inventory);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new InventoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryHolder holder, int position) {
        Inventory inventory = inventories.get(position);
        holder.title.setText(inventory.getTitle());
        holder.price.setText(String.valueOf(inventory.getPrice()));
        holder.quantity.setText(inventory.getQuantity());
        holder.manufacturer.setText(inventory.getManufacturer());
        String uri = inventory.getImage();
        Glide.with(holder.itemView.getContext())
                .load(uri)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
        notifyDataSetChanged();
    }

    public Inventory inventoryPosition(int position) {
        return inventories.get(position);
    }

    class InventoryHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView price;
        TextView quantity;
        TextView manufacturer;

        public InventoryHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageInventory);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            manufacturer = itemView.findViewById(R.id.manufacturer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(inventories.get(position));
                    }
                }
            });

        }
    }
}
