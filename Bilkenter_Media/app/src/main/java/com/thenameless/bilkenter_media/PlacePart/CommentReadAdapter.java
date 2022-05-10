package com.thenameless.bilkenter_media.PlacePart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.thenameless.bilkenter_media.databinding.CommentviewBinding;

import java.util.ArrayList;

public class CommentReadAdapter extends RecyclerView.Adapter<CommentReadAdapter.CommentHolder> {
    ArrayList<Comment> comments;
    public CommentReadAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentviewBinding commentviewBinding = CommentviewBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CommentHolder(commentviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.binding.comment.setText(comments.get(position).comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        private CommentviewBinding binding;
        public CommentHolder( CommentviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
