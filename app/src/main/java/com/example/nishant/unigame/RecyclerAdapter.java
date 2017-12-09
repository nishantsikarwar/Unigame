package com.example.nishant.unigame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nishant on 9/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<ListItem> listItemArrayList= new ArrayList<>();
    private final LayoutInflater inflater;
    View view;
    MyViewHolder myViewHolder;
    private Context context;
    public RecyclerAdapter(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view=inflater.inflate(R.layout.activity_listitem, parent, false);
        myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ListItem list_items= listItemArrayList.get(position);
        myViewHolder.game_name.setText(list_items.getGame_name());
    }


    public void setListContent(ArrayList<ListItem> listItemArrayList){
        this.listItemArrayList= listItemArrayList;
        notifyItemRangeChanged(0,listItemArrayList.size());
    }


    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView game_name;

        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            game_name=itemView.findViewById(R.id.game_name);
        }


        @Override
        public void onClick(View v) {

            Toast.makeText(context,"CLICKED_OKK",Toast.LENGTH_SHORT).show();
        }

        public void removeAt(int position) {
            listItemArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, listItemArrayList.size());
        }


    }
}
