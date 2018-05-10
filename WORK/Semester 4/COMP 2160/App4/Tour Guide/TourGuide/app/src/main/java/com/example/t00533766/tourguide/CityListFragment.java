package com.example.t00533766.tourguide;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by T00533766 on 10/4/2017.
 */

public class CityListFragment extends ListFragment {
    private ArrayList<City> cities;
	private Parcelable state;
	private View previousClicked;
	private CityAdapter cityAdapter;
	private int selectedPosition = -1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cities = new ArrayList<City>();
	    Log.d("CREATING STATE", "CREATED: "+ selectedPosition);

	    cities.add(new City("Kamloops","Kamloops is a Canadian city in British Columbia, where the North and South Thompson rivers meet. Sun Peaks Resort’s hiking trails, bike park and numerous ski runs lie to the northeast. Cougars and bears inhabit the British Columbia Wildlife Park east of town. West, above Kamloops Lake are clay hoodoos (or spires). The riverside Secwepemc Museum & Heritage Park features the remains of a 2,000-year-old village."));

	    cities.add(new City("Vancouver","Vancouver, a bustling west coast seaport in British Columbia, is among Canada’s densest, most ethnically diverse cities. A popular filming location, it’s surrounded by mountains, and also has thriving art, theatre and music scenes. Vancouver Art Gallery is known for its works by regional artists, while the Museum of Anthropology houses preeminent First Nations collections."));

		cities.add(new City("Kampala","Kampala is Uganda's national and commercial capital bordering Lake Victoria, Africa's largest lake. Hills covered with red-tile villas and trees surround an urban centre of contemporary skyscrapers. In this downtown area, the Uganda Museum explores the country's tribal heritage through an extensive collection of artefacts. On nearby Mengo Hill is Lubiri Palace, the former seat of the Buganda Kingdom."));

	    cities.add(new City("Toronto","Toronto, the capital of the province of Ontario, is a major Canadian city along Lake Ontario’s northwestern shore. It's a dynamic metropolis with a core of soaring skyscrapers, all dwarfed by the iconic, free-standing CN Tower. Toronto also has many green spaces, from the orderly oval of Queen’s Park to 400-acre High Park and its trails, sports facilities and zoo."));

	    cities.add(new City("Prince George","Prince George is a city in British Columbia, Canada, on the Fraser and Nechako rivers. The Central British Columbia Railway and Forestry Museum displays old trains. Contemporary Canadian art is on show at Two Rivers Gallery. The Exploration Place has hands-on science exhibits. Beyond town, trails wind through wildlife-rich Eskers Provincial Park and Fort George Canyon Provincial Park, with its dramatic whirlpools."));

	    cities.add(new City("Carlifornia","California, a western U.S. state, stretches from the Mexican border along the Pacific for nearly 900 miles. Its terrain includes cliff-lined beaches, redwood forest, the Sierra Nevada Mountains, Central Valley farmland and the Mojave Desert. The city of Los Angeles is the seat of the Hollywood entertainment industry. Hilly San Francisco is known for the Golden Gate Bridge, Alcatraz Island and cable cars."));

	    cities.add(new City("New York","New York City comprises 5 boroughs sitting where the Hudson River meets the Atlantic Ocean. At its core is Manhattan, a densely populated borough that’s among the world’s major commercial, financial and cultural centers. Its iconic sites include skyscrapers such as the Empire State Building and sprawling Central Park. Broadway theater is staged in neon-lit Times Square."));

	    cityAdapter = new CityAdapter(getActivity(),R.layout.city_list_item, cities);
        setListAdapter(cityAdapter);
	    previousClicked = null;
    }

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

			if(savedInstanceState!=null){
				int selected  = savedInstanceState.getInt("SELECTED");
				if(selected>=0) {
					Log.d("CHANGING SELECTINo--", "onActivityCreated: "+"   "+selected+"     "+previousClicked);
					selectedPosition = selected;
					getListView().setSelection(savedInstanceState.getInt("SELECTED"));
					View v = (View) getListAdapter().getView(selected, null, getListView());
					v.setBackgroundColor(getResources().getColor(R.color.listClicked, getActivity().getTheme()));
					previousClicked = v;
					TextView detailsText = getActivity().findViewById(R.id.details);
					detailsText.setText(cities.get(selected).getDescription());
					cityAdapter.setSelectedPosition(selected);
				}else{
					TextView detailsText =getActivity().findViewById(R.id.details);
					detailsText.setText("MAKE A SELECTION FROM THE LIST");
				}
			}else{
				TextView detailsText =getActivity().findViewById(R.id.details);
				detailsText.setText("MAKE A SELECTION FROM THE LIST");
			}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt("SELECTED", selectedPosition);
		previousClicked.setBackgroundColor(getResources().getColor(R.color.transparent,getActivity().getTheme()));
		Log.d("SAVING STATE", "onSaveInstanceState: "+ selectedPosition);
	}



	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

		Log.d("=============", "onListItemClick: "+selectedPosition+" [][]["+previousClicked);

		if(previousClicked!=null){
			previousClicked.setBackgroundColor(getResources().getColor(R.color.transparent,getActivity().getTheme()));
		}

		previousClicked = v;
		v.setBackgroundColor(getResources().getColor(R.color.listClicked,getActivity().getTheme()));
		TextView detailsText =getActivity().findViewById(R.id.details);
        detailsText.setText(cities.get(position).getDescription());
		selectedPosition = position;
		cityAdapter.setSelectedPosition(position);
    }
}
