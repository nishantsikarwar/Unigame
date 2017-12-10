package com.example.nishant.unigame;

import android.widget.ListView;

/**
 * Created by nishant on 9/12/17.
 */

public class ListItem {

    private String game_name,game_count;

    public ListItem(String game_count,String game_name){
        this.game_count=game_count;
        this.game_name=game_name;
    }

    public ListItem(){

    }

    public void setGame_name(String game_name ){this.game_name=game_name;}
    public String getGame_name(){return game_name;}
    public void setGame_count(String game_count ){this.game_count=game_count;}
    public String getGame_count(){return game_count;}
}
