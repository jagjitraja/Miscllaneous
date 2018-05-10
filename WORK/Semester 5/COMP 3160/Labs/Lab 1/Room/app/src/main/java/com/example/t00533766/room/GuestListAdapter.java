package com.example.t00533766.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.t00533766.room.GuestInfo;
import com.example.t00533766.room.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class GuestListAdapter  extends RecyclerView.Adapter<GuestListAdapter.GuestInfoViewHolder>{

    private Context context;
    private final String TAG = GuestListAdapter.class.getSimpleName();
    private ArrayList<GuestInfo> guestInfos;

    public GuestListAdapter(Context applicationContext, ArrayList<GuestInfo> guestInfoArrayList) {
        context = applicationContext;
        guestInfos = guestInfoArrayList;
    }

    @Override
    public GuestListAdapter.GuestInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.guest_item_list,parent,false);

        return new GuestInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestListAdapter.GuestInfoViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return guestInfos.size();
    }


    public class GuestInfoViewHolder extends RecyclerView.ViewHolder{

        private TextView guestNum;
        private TextView guestName;
        private TextView partySize;

        public GuestInfoViewHolder(View itemView) {
            super(itemView);
            guestNum = itemView.findViewById(R.id.guest_num_text_view);
            guestName = itemView.findViewById(R.id.name_text_view);
            partySize = itemView.findViewById(R.id.size_text_view);
        }

        public void bind(int pos){
            guestNum.setText((pos+1)+"");
            guestName.setText(guestInfos.get(pos).getGuestName());
            partySize.setText(guestInfos.get(pos).getPartySize()+"");

        }
    }
}
