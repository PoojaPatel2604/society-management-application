package com.example.dell.miniproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class accDetailsAdapter extends RecyclerView.Adapter<accDetailsAdapter.accDetailsViewHolder> {

    private  List<acc_details> ad1;
    private String data;

    public accDetailsAdapter(List<acc_details> ad)
    {

        //this.data=data;
        this.ad1=ad;
    }

    @NonNull
    @Override
    public accDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_view_item_3,parent,false);
        return new accDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull accDetailsViewHolder holder, int position) {

        acc_details acc= ad1.get(position);
        holder.tname.setText(acc.getOwnersname());
        holder.tflatno.setText(acc.getOwnnersflatno());
        holder.tamount.setText(acc.getOwnersamount());
        holder.imageView.setImageResource(R.drawable.usergreen);
    }

    @Override
    public int getItemCount() {
        return ad1.size();
    }

    public class accDetailsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tname,tamount,tflatno;
        public  accDetailsViewHolder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tname=itemView.findViewById(R.id.acc_name);
            tflatno=itemView.findViewById(R.id.acc_flatno);
            tamount=itemView.findViewById(R.id.acc_amount);

        }
    }
}
