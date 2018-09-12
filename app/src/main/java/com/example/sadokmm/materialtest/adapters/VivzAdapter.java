package com.example.sadokmm.materialtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadokmm.materialtest.objects.Information;
import com.example.sadokmm.materialtest.R;

import java.util.Collections;
import java.util.List;

public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Information> data = Collections.emptyList();

    public VivzAdapter(Context context , List<Information> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }


    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= inflater.inflate(R.layout.custom_row,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder,  int i ) {

        Information current=data.get(i);  //get the current object from the list data

        viewHolder.title.setText(current.title);
        viewHolder.icon.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // find my VIEWs here

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.listText);
            icon=(ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

          //  delete(getLayoutPosition());
            Toast.makeText(context,"Hello" + getLayoutPosition(),Toast.LENGTH_LONG).show();

        }
    }
}
