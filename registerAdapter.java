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

public class registerAdapter extends RecyclerView.Adapter<registerAdapter.registerViewHolder> {

   private  List<regis> regis1;
    private String data;

    public registerAdapter(List<regis> regis)
    {

        //this.data=data;
        this.regis1=regis;
    }

    @NonNull
    @Override
    public registerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new registerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull registerViewHolder holder, int position) {

       //holder.itemView.setTag(regis.get(position));
       regis reg= regis1.get(position);
       //holder.imageView.setImageResource(regis1[position].ge);
      //  holder.imageView.setImageResource(reg.getImageUrl());
       holder.tname.setText(reg.getname());
       holder.tflatno.setText(reg.getflatno());
       holder.tcontact.setText(reg.getcontact());
        holder.imageView.setImageResource(R.drawable.usergreen);
    }

    @Override
    public int getItemCount() {
        return regis1.size();
    }

    public class registerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tname,tcontact,tflatno;
        public  registerViewHolder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tname=itemView.findViewById(R.id.own_name);
            tflatno=itemView.findViewById(R.id.own_flatno);
            tcontact=itemView.findViewById(R.id.own_contact);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    regis regis=(regis)v.getTag();
//                    Toast.makeText(v.getContext(),regis.getname()+"Stays in flat no: "+regis.getflatno()+" and contact no: is "+regis.getcontact(),Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    }
}
