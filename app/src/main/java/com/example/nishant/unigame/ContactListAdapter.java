package com.example.nishant.unigame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nishant on 15/12/17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {


    private List<ContactList>contactLists;
    public class MyViewHolder extends RecyclerView.ViewHolder{
          TextView contact_name,contact_count;
        public MyViewHolder(View view){

            super(view);
            contact_name=view.findViewById(R.id.contact_name);
            contact_count=view.findViewById(R.id.contact_count);

        }
    }


    public ContactListAdapter( List<ContactList>contactLists){this.contactLists=contactLists;}


    @Override
    public ContactListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list, parent, false);

        return new ContactListAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ContactListAdapter.MyViewHolder holder, int position) {
        ContactList contactList = contactLists.get(position);
        holder.contact_count.setText(contactList.getContact_count());
        holder.contact_name.setText(contactList.getContact_name());
    }


    @Override
    public int getItemCount() {
        return contactLists.size();
    }
}
