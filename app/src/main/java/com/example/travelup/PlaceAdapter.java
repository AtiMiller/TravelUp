package com.example.travelup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> implements Filterable{

    private List<Place> AllPlaces;
    private List<Place> AllPlacesFull;
    private Context context;

    public PlaceAdapter(List<Place> mAllPlaces, Context context) {
        this.AllPlaces = mAllPlaces;
        AllPlacesFull = new ArrayList<>(mAllPlaces);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Place place = AllPlaces.get(position);

        holder.title.setText(place.getTitle());
        holder.description.setText(place.getDescription());

        Glide.with(context)
                .asBitmap()
                .load(place.getImage())
                .into(holder.image);

        Glide.with(context)
                .asBitmap()
                .load(place.getFlag())
                .into(holder.flag);

    }

    @Override
    public int getItemCount() {
        return AllPlaces.size();
    }


    @Override
    public Filter getFilter() {
        return placeFilter;
    }

    private Filter placeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Place> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0)
            {
                filteredList.addAll(AllPlacesFull);
            }
            else
            {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Place place : AllPlacesFull){
                    if(place.toString().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(place);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            AllPlaces.clear();
            AllPlaces.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };



    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image, flag;
        TextView title, description;

        private MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            flag = itemView.findViewById(R.id.flag);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }


}
