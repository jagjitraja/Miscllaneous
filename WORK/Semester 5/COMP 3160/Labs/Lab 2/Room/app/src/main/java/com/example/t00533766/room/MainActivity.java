package com.example.t00533766.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t00533766.room.RoomFiles.GuestInfo;
import com.example.t00533766.room.RoomFiles.GuestInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<GuestInfo> guestInfoArrayList;
    private EditText nameEditText;
    private EditText sizeEditText;
    private RecyclerView recyclerView;
    private GuestListAdapter guestListAdapter;
    private GuestInfoViewModel guestInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guestInfoViewModel = ViewModelProviders.of(this).get(GuestInfoViewModel.class);
        guestInfoViewModel.createReporsitory(getApplicationContext());
        guestInfoArrayList = new ArrayList<>();
        guestListAdapter = new GuestListAdapter(getApplicationContext(),guestInfoArrayList);

        Observer<List<GuestInfo>> liveDataObserver = new Observer<List<GuestInfo>>() {
            @Override
            public void onChanged(@Nullable List<GuestInfo> listLiveData) {

                if(listLiveData!=null){
                    guestInfoArrayList = (ArrayList<GuestInfo>) listLiveData;
                    guestListAdapter.setGuestInfos(guestInfoArrayList);
                    Log.d(TAG, "onChanged: "+ guestInfoArrayList);
                }

            }
        };

        guestInfoViewModel.getGuestInfoLiveData().observe(this,liveDataObserver);
        Log.d(TAG, "onCreate: "+ guestInfoArrayList);

        nameEditText = findViewById(R.id.guest_name);
        sizeEditText = findViewById(R.id.num_of_guest);
        recyclerView = findViewById(R.id.all_guest_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(guestListAdapter);
        guestListAdapter.notifyDataSetChanged();
    }

    public void addToWaitlist(View view) {

        if(view.getId()==R.id.add_to_waitlist_button) {
            String name = String.valueOf(nameEditText.getText());
            String size = String.valueOf(sizeEditText.getText());
            if (!(name.length() > 0) || !(size.length() > 0)) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
                return;
            }

            GuestInfo info = new GuestInfo(name, Integer.parseInt(size));
            guestInfoArrayList.add(info);
            nameEditText.setText("");
            sizeEditText.setText("");
            Toast.makeText(getApplicationContext(), R.string.added,Toast.LENGTH_SHORT).show();
            guestListAdapter.notifyDataSetChanged();
            guestInfoViewModel.insertGuestData(info);
        }
    }

}
