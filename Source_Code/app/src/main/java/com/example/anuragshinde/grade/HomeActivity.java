package com.example.anuragshinde.grade;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView welcome = (TextView) findViewById(R.id.welcome);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        /*actionBar.setLogo(R.drawable.ic_launcher_background);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        Intent intent = getIntent();
        if (intent.getExtras() == null) {
            // Do first time stuff here
        } else {
            // Do stuff with intent data here
            Bundle bundle = getIntent().getExtras();
            welcome.setText("Welcome "+bundle.getString("username"));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.help:
                customDialog("About",  "Welcome to Quick Notes \n \nThis app is for storing quick notes which you access take any time. It's simple, fast and super easy to refer stored notes that you may forget.\n \n1. Use Create Note button to create new notes\n \n2. Use view notes button to view your saved notes");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void customDialog(String title, String message){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builderSingle.show();
    }

    public void changeFragment(View view){
        Fragment fragment;


        if(view == findViewById(R.id.button)){

            FrameLayout fl = (FrameLayout) findViewById(R.id.fragment);
            fl.removeAllViews();
            fragment = new CreateNoteFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.fragment,fragment);
            ft.commit();
        }

        if(view == findViewById(R.id.button2)){
            FrameLayout fl = (FrameLayout) findViewById(R.id.fragment);
            fl.removeAllViews();


            fragment = new ViewNotesFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.fragment,fragment);
            ft.commit();
        }
    }
}
