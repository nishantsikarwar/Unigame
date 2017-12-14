package com.example.nishant.unigame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mauthStateListener;
    private final static int RC_SIGN_IN =1;
    private RecyclerView recycler_view;
    private GameListAdapter adapter;
    private ArrayList<GameList> gameLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mfirebaseAuth = FirebaseAuth.getInstance();
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);

        adapter=new GameListAdapter(gameLists);


        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());

        recycler_view.setLayoutManager(layoutManager);

        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recycler_view.setItemAnimator(new DefaultItemAnimator());

        recycler_view.setAdapter(adapter);


        recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recycler_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                GameList gameList = gameLists.get(position);
                Toast.makeText(MainActivity.this,gameList.getGame_name(),Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("GAME",gameList.getGame_name());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




        populateListViewValues();

        mauthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = mfirebaseAuth.getCurrentUser();

                if(user!=null){
                    user.getDisplayName();
                    onSignedIn();
                }else {

                    onSignedOut();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);

                }

            }
        };





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:{
                AuthUI.getInstance().signOut(this);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "SIGNED_IN", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "SIGNED_CANCELLED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSignedIn(){
        Toast.makeText(MainActivity.this,"Signed_In",Toast.LENGTH_SHORT).show();

    }
    public void onSignedOut() {


    }




    @Override
    protected void onPause(){
        super.onPause();
        if(mauthStateListener!=null)
            mfirebaseAuth.removeAuthStateListener(mauthStateListener);
    }


    @Override
    protected void onResume(){
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mauthStateListener);

    }
    List<GameList>listItemList =new ArrayList<>();


  private void populateListViewValues(){

           int i=0;

             GameList listItem0 = new GameList();
             String counter0 = Integer.toString(i);
             listItem0.setGame_name("CRICKET");
             listItem0.setGame_count(counter0);
             gameLists.add(listItem0);

              i++;

             GameList listItem1 = new GameList();
             String counter1 = Integer.toString(i);
             listItem1.setGame_name("FOOTBALL");
             listItem1.setGame_count(counter1);
             gameLists.add(listItem1);
                i++;

             GameList listItem2 = new GameList();
             String counter2 = Integer.toString(i);
             listItem2.setGame_name("BADMINTON");
             listItem2.setGame_count(counter2);
             gameLists.add(listItem2);

             i++;


             GameList listItem3 = new GameList();
             String counter3 = Integer.toString(i);
             listItem3.setGame_name("TENNIS");
             listItem3.setGame_count(counter3);
             gameLists.add(listItem3);

             i++;

             GameList listItem4 = new GameList();
             String counter4 = Integer.toString(i);
             listItem4.setGame_name("TABLETENNIS");
             listItem4.setGame_count(counter4);
             gameLists.add(listItem4);
             i++;

             GameList listItem5 = new GameList();
             String counter5 = Integer.toString(i);
             listItem5.setGame_name("BASKETBALL");
             listItem5.setGame_count(counter5);
             gameLists.add(listItem5);

             i++;

             GameList listItem6 = new GameList();
             String counter6 = Integer.toString(i);
             listItem6.setGame_name("HOCKEY");
             listItem6.setGame_count(counter6);
             gameLists.add(listItem6);

      adapter.notifyDataSetChanged();

  }



}
