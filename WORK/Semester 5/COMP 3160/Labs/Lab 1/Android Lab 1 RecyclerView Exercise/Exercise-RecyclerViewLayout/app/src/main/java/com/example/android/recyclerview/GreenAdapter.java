/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
// T-ODO (G4) From GreenAdapter, extend RecyclerView.Adapter<NumberViewHolder>
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private int numberListItems;
    private Context context;
    // T-ODO (G1) Add a private int variable called mNumberItems
    // T-ODO (G2) Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    // T-ODO (G3) Store the numberOfItems parameter in mNumberItems

    // T-ODO (G5) Override the onCreateViewHolder method
    // T-ODO (G6) Create and return a new NumberViewHolder within this method
    // T-ODO (G7) Override onBindViewHolder
    // T-ODO (G8) Within onBindViewHolder, call holder.bind and pass in the position
    // T-ODO (G9) Override getItemCount and return the number of items to display


    private static final String TAG = GreenAdapter.class.getSimpleName();

    public GreenAdapter(int numListItems, Context applicationContext) {
        numberListItems = numListItems;
        context = applicationContext;
    }


    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: "+"=============");

        View v =  LayoutInflater.from(context).inflate(R.layout.number_list_item,parent,false);
        return new NumberViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);

        Log.d(TAG, "onBindViewHolder: --------------------"+position+"   ");
    }

    @Override
    public int getItemCount() {
        return numberListItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView listItemNumberView;
        public NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void bind(int x){
            listItemNumberView.setText(x+"");
            int col = ColorUtils.getViewHolderBackgroundColorFromInstance(context,x%17);
            listItemNumberView.setBackgroundColor(col);
        }
    }
    // T-ODO (G10) Create a class called NumberViewHolder that extends RecyclerView.ViewHolder
    // T-ODO (G11) Create a constructor for NumberViewHolder that accepts a View called itemView as a parameter
    // T-ODO (G12) Within NumberViewHolder, create a TextView variable called listItemNumberView
    // T-ODO (G13) Within the constructor, call super(itemView) and then find listItemNumberView by ID
    // T-ODO (G14) Within the NumberViewHolder class, create a void method called bind that accepts an int parameter called listIndex
    // T-ODO (G15) Within bind, set the text of listItemNumberView to the listIndex
    // T-ODO (G16) Be careful to get the String representation of listIndex, as using setText with an int does something different



}
