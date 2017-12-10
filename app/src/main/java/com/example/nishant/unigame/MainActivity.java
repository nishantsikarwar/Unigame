package com.example.nishant.unigame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ListView game_listView;
    private GameListAdapter adapter;
    private ArrayList<ListItem> listItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mfirebaseAuth = FirebaseAuth.getInstance();
        game_listView = (ListView)findViewById(R.id.game_listView);
        adapter = new GameListAdapter(this,R.layout.activity_listitem,listItemArrayList);
        game_listView.setAdapter(adapter);
     game_listView.setOnItemClickListener();
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

  private void populateListViewValues(){

        for ( int i = 0 ;i <10;i++){
            ListItem listItem = new ListItem();
            String counter = Integer.toString(i);
            listItem.setGame_name("FOOTBALL");
            listItem.setGame_count(counter);
            adapter.add(listItem);
        }


  }

}
