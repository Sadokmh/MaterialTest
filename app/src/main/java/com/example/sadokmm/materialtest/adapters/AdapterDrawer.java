package com.example.sadokmm.materialtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadokmm.materialtest.R;
import com.example.sadokmm.materialtest.objects.Information;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

public class AdapterDrawer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Information> data = Collections.emptyList();
    private static final int TYPE_HEADER = 0  ;
    private static final int TYPE_ITEM = 1  ;
    private LayoutInflater inflater;
    private Context context;


    public AdapterDrawer(Context context, List<Information> data){
        this.context=context;
        this.inflater= LayoutInflater.from(context);
        this.data=data;
    }


    public void Delete(int position){
        data.remove(position);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.drawer_header, viewGroup, false);

            HeaderHolder holder = new HeaderHolder(view);
        return holder;
        }

        else {

            View view = inflater.inflate(R.layout.item_drawer, viewGroup, false);

            ItemHolder holder = new ItemHolder(view);
            return holder;
        }
        }



    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_HEADER;
        }
        else
            return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        if ( holder instanceof HeaderHolder) {

        }
        else{
            ItemHolder itemHolder=(ItemHolder)holder;
            Information current = data.get(i-1);  //get the current object from the list data

            itemHolder.title.setText(current.title);
            itemHolder.icon.setImageResource(current.iconId);
        }

    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }



    class ItemHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.listText);
            icon=(ImageView)itemView.findViewById(R.id.listIcon);


        }
    }




    class HeaderHolder extends RecyclerView.ViewHolder {




        public HeaderHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
