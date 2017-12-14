package com.example.nishant.unigame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private ContactListAdapter adapter;
    private List<ContactList>contactLists=new ArrayList<>();
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRefernece;
    private ChildEventListener mchildEventListener;
     private String game_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
           game_name = intent.getStringExtra("GAME");


        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);

         adapter= new ContactListAdapter(contactLists);



        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);



        recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recycler_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ContactList professorList = contactLists.get(position);
                Toast.makeText(SecondActivity.this,"WORKIN'",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfirebaseDatabase=FirebaseDatabase.getInstance();

                mdatabaseRefernece=mfirebaseDatabase.getReference().child(game_name).push();

               ContactList contactList = new ContactList();
               contactList.setContact_count("1");
               contactList.setContact_name("Nishant");

                mdatabaseRefernece.setValue(contactList);

            }
        });
        prepareContacts();

    }

    public void prepareContacts(){
        mfirebaseDatabase=FirebaseDatabase.getInstance();
        mdatabaseRefernece= mfirebaseDatabase.getReference().child(game_name);
        mchildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                ContactList contactList = dataSnapshot.getValue(ContactList.class);
                contactLists.add(contactList);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        mdatabaseRefernece.addChildEventListener(mchildEventListener);

        adapter.notifyDataSetChanged();

    }


}
