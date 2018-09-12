package com.example.sadokmm.materialtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sadokmm.materialtest.R;
import com.example.sadokmm.materialtest.objects.Personne;

import java.util.ArrayList;

public class AdapterForList extends RecyclerView.Adapter<AdapterForList.ViewHolder> {


    private ArrayList<Personne> myList=new ArrayList<>();
    private LayoutInflater layoutInflater;


    public AdapterForList(Context context) {
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.custom_fragment_list,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Personne currentPersonne=myList.get(i);
        viewHolder.itemTitle.setText(currentPersonne.getNom());
        viewHolder.itemDesc.setText(currentPersonne.getDes());
        viewHolder.itemAudianceScore.setRating(currentPersonne.getRating());
        viewHolder.itemThumbnail.setImageBitmap(currentPersonne.getAv());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setMyList(ArrayList<Personne> list){
        myList=list;
        notifyItemRangeChanged(0,list.size());
    }


    //

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle;
        private TextView itemDesc;
        private ImageView itemThumbnail;
        private RatingBar itemAudianceScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle=(TextView)itemView.findViewById(R.id.itemTitle);
            itemDesc=(TextView)itemView.findViewById(R.id.itemDesc);
            itemThumbnail=(ImageView)itemView.findViewById(R.id.itemThumbnail);
            itemAudianceScore=(RatingBar)itemView.findViewById(R.id.itemRatingBar);
        }
    }
}
