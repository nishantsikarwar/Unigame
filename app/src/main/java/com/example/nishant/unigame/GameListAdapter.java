package com.example.nishant.unigame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nishant on 9/12/17.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.MyViewHolder>{


    private List<GameList> gameLists ;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView game_name,game_count;
        public MyViewHolder(View view) {
            super(view);
            game_name=view.findViewById(R.id.game_name);
            game_count=view.findViewById(R.id.game_count);
        }
    }

    public  GameListAdapter( List<GameList> gameLists)
    {this.gameLists=gameLists;}


    @Override
    public GameListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listitem, parent, false);

        return new GameListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameListAdapter.MyViewHolder holder, int position) {
        GameList gameList = gameLists.get(position);
        holder.game_name.setText(gameList.getGame_name());
        holder.game_count.setText(gameList.getGame_count());

    }

    @Override
    public int getItemCount() {
        return gameLists.size();
    }

}
