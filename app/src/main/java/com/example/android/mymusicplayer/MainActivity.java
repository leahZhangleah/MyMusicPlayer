package com.example.android.mymusicplayer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BrowseFragment browse = new BrowseFragment();
        final SearchFragment search = new SearchFragment();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.app_bar_browse:
                        switchFragment(browse);
                        return true;
                    case R.id.app_bar_search:
                        switchFragment(search);
                        return true;
                }
                return false;
            }
        });

        switchFragment(browse);
    }

    private void switchFragment(Fragment fragment){
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.page_container,fragment).commit();
    }


}
