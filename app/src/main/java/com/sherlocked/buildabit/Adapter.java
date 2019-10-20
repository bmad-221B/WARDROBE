package com.sherlocked.buildabit;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    List<String> list=new ArrayList<>();

    Adapter(List<String> strings){
        this.list=strings;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    holder.imageView.setImageURI(Uri.parse("https://cdnza.azureedge.net/wp-content/uploads/2018/03/ART_002B3-300x169.png"));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
        }
    }

    void setData(List<String> strings){
        this.list=strings;
    }
}
