package com.example.musfiqrahman.waitlist;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musfiqrahman.waitlist.Database.GuestInfoDatabaseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<GuestInfo> guestInfoArrayList;
    private EditText nameEditText;
    private EditText sizeEditText;
    private RecyclerView recyclerView;
    private GuestListAdapter guestListAdapter;
    private GuestInfoDatabaseController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new GuestInfoDatabaseController(getApplicationContext());

        guestInfoArrayList = (ArrayList<GuestInfo>) controller.getAllGuests();
        if (guestInfoArrayList==null){
            guestInfoArrayList = new ArrayList<>();
        }

        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.guest_name);
        sizeEditText = findViewById(R.id.num_of_guest);
        recyclerView = findViewById(R.id.all_guest_list_view);

        guestListAdapter = new GuestListAdapter(getApplicationContext(),guestInfoArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(guestListAdapter);

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
            controller.insertGuest(info);
            guestListAdapter.notifyDataSetChanged();
        }
    }
}
