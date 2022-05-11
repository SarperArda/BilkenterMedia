package com.thenameless.bilkenter_media.BlogPart;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.thenameless.bilkenter_media.databinding.BlogviewBinding;

import java.util.ArrayList;

public class BlogAdapter  extends RecyclerView.Adapter<BlogAdapter.BlogHolder>{
    ArrayList<Blog> blogArrayList;


    public BlogAdapter(ArrayList<Blog> blog) {
        this.blogArrayList = blog;

    }
    @NonNull
    @Override
    public BlogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BlogviewBinding blogViewBinding = BlogviewBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new BlogHolder(blogViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogHolder holder, int position) {
        holder.binding.userBlog.setText(blogArrayList.get(position).blogName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), blogReadingActivity.class );
                intent.putExtra("blog",blogArrayList.get(position).blog);
                intent.putExtra("blogName",blogArrayList.get(position).blogName);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogArrayList.size();
    }

    public class BlogHolder extends RecyclerView.ViewHolder {
        private BlogviewBinding binding;
        public BlogHolder(BlogviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
