package com.android.alan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder> {

    //
    private ArrayList<ChatsEntity> chatsEntityArrayList;

    //
    public ChatsAdapter(ArrayList<ChatsEntity> chatsEntityArrayList) {
        //
        this.chatsEntityArrayList = chatsEntityArrayList;
    }

    //
    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //
        return new ChatsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chats, parent, false));
    }

    //
    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        //
        holder.textView_chats_name.setText(chatsEntityArrayList.get(position).getName());
        holder.textView_chats_chats.setText(chatsEntityArrayList.get(position).getText());
    }

    //
    @Override
    public int getItemCount() {
        //
        return chatsEntityArrayList.size();
    }

    //
    public class ChatsViewHolder extends RecyclerView.ViewHolder {

        //
        TextView textView_chats_name, textView_chats_chats;

        //
        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_chats_name = itemView.findViewById(R.id.textView_chats_name);
            textView_chats_chats = itemView.findViewById(R.id.textView_chats_chats);
        }
    }
}
