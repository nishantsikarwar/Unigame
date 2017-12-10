package com.example.nishant.unigame;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishant on 9/12/17.
 */

public class GameListAdapter extends ArrayAdapter<ListItem> {


    public GameListAdapter(Context context,int resources, List<ListItem> listItemList){
        super(context,resources,listItemList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_listitem, parent, false);
        }

        TextView game_name= convertView.findViewById(R.id.game_name);
        TextView game_count=convertView.findViewById(R.id.game_count);
        ListItem listItem =getItem(position);

        game_name.setText(listItem.getGame_name());
        game_count.setText(listItem.getGame_count());

        return convertView;
    }



}
