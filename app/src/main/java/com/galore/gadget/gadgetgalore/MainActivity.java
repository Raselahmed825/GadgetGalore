package com.galore.gadget.gadgetgalore;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

        dl= (DrawerLayout)findViewById(R.id.dl);

        abdt = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.myprofile){
                    Toast.makeText(MainActivity.this,"myprofile",Toast.LENGTH_SHORT).show();
                }else  if(id==R.id.shop){
                    Toast.makeText(MainActivity.this,"shop",Toast.LENGTH_SHORT).show();
                }else  if(id==R.id.location){
                    Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                }else  if(id==R.id.cart){
                    Toast.makeText(MainActivity.this,"cart",Toast.LENGTH_SHORT).show();
                }else if(id==R.id.contact){
                    Toast.makeText(MainActivity.this,"contact",Toast.LENGTH_SHORT).show();
                }



                return true;
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000, 4000);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }




    public class MyTimerTask extends TimerTask{


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(3);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
