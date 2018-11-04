package com.example.dell.miniproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class notifyAdapter  extends RecyclerView.Adapter<notifyAdapter.notifyViewHolder> {

    private List<notify> not;
    private String n;

    public notifyAdapter(List<notify> not1){

        this.not=not1;
        //this.n=n;
    }

    @NonNull
    @Override
    public notifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_view_item_1,parent,false);
        return new notifyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull notifyViewHolder holder, int position) {

        notify n1=not.get(position);
        holder.tnotify.setText(n1.getMessage());
        holder.imageView.setImageResource(R.drawable.usergreen);


    }

    @Override
    public int getItemCount() {
        return not.size();
    }

    public class notifyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tnotify;

        public notifyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.notify_img);
            tnotify=itemView.findViewById(R.id.notify_msg);
        }
    }

}
