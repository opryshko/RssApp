package com.geekapps.rsstestapp.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.mvp.BaseMvpActivity;
import com.geekapps.rsstestapp.ui.audiobooks.AudiobooksFragment;
import com.geekapps.rsstestapp.ui.movies.MoviesFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    HashMap<String, Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragments = new HashMap<>();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String selectedTab = "";

                switch (menuItem.getItemId()) {
                    case R.id.audiobooks:
                        if (!fragments.containsKey("audiobooks"))
                            fragments.put("audiobooks", new AudiobooksFragment());
                        selectedTab = "audiobooks";
                        break;
                    case R.id.movies:
                        if (!fragments.containsKey("movies"))
                            fragments.put("movies", new MoviesFragment());
                        selectedTab = "movies";
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.category_fragment_container, fragments.get(selectedTab)).commit();
                return true;
            }
        });
    }

    @Override
    public void addFragment(Fragment fragment) {

    }

    @Override
    public void replaceFragment(Fragment fragment) {

    }
}
