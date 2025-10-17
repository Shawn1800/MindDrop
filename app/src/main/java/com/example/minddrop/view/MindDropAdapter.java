package com.example.minddrop.view;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minddrop.databinding.ItemMindDropBinding;
import com.example.minddrop.model.MindDropItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MindDropAdapter extends RecyclerView.Adapter<MindDropAdapter.MindDropViewHolder> {

    private final List<MindDropItem> items = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    @NonNull
    @Override
    public MindDropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use ViewBinding to inflate the item layout
        ItemMindDropBinding binding = ItemMindDropBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MindDropViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MindDropViewHolder holder, int position) {
        MindDropItem currentItem = items.get(position);
        holder.bind(currentItem);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<MindDropItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged(); // For simplicity now, we will use DiffUtil later for better performance
    }


    // The ViewHolder class
    class MindDropViewHolder extends RecyclerView.ViewHolder {
        private final ItemMindDropBinding binding;

        public MindDropViewHolder(ItemMindDropBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MindDropItem item) {
            binding.contentTextView.setText(item.getContent());  // from the item_mind_drop.xml layout
            binding.timestampTextView.setText(dateFormat.format(item.getTimestamp())); // this also
        }
    }
}
