package com.example.t00533766.tourguide;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T00533766 on 10/4/2017.
 */

public class CityAdapter extends ArrayAdapter {


    private ArrayList<City> cities;
    private int selectedPosition;

    public CityAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<City> objects) {
        super(context, resource, objects);
        cities = (ArrayList<City>) objects;
        selectedPosition = -1;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CityItemHolder holder;
        if(convertView==null){
            holder = new CityItemHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_list_item,parent,false);
            holder.number = convertView.findViewById(R.id.count);
            holder.number.setText((position+1)+" )");
            holder.name = convertView.findViewById(R.id.name);
            City city = (City) cities.get(position);
            holder.name.setText(city.getName());
            convertView.setTag(holder);
        }
        else{
            holder = (CityItemHolder)convertView.getTag();
        }

        if(position!=selectedPosition){
            convertView.setBackgroundColor(getContext().getApplicationContext().getColor(R.color.transparent));
        }else{
	        convertView.setBackgroundColor(getContext().getApplicationContext().getColor(R.color.listClicked));
        }

        holder.number.setText((position+1)+" )");
        City city = (City) cities.get(position);
        holder.name.setText(city.getName());

        return convertView;
    }

    public void setSelectedPosition(int p){
        selectedPosition = p;
    }


    private class CityItemHolder{
        TextView number;
        TextView name ;

        public CityItemHolder(){

        }
    }
}
