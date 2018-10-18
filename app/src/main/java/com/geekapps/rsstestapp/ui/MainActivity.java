package com.geekapps.rsstestapp.ui;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.mvp.BaseMvpActivity;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksFragment;
import com.geekapps.rsstestapp.ui.categories.movies.MoviesFragment;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsFragment;

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
        fragments.put("mediaContent", new AudiobooksFragment());
        replaceFragment(fragments.get("mediaContent"));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String selectedTab = "";

                switch (menuItem.getItemId()) {
                    case R.id.audiobooks:
                        if (!fragments.containsKey("mediaContent"))
                            fragments.put("mediaContent", new AudiobooksFragment());
                        selectedTab = "mediaContent";
                        break;
                    case R.id.movies:
                        if (!fragments.containsKey("movies"))
                            fragments.put("movies", new MoviesFragment());
                        selectedTab = "movies";
                        break;
                    case R.id.podcasts:
                        if (!fragments.containsKey("podcasts"))
                            fragments.put("podcasts", new PodcastsFragment());
                        selectedTab = "podcasts";
                }

                replaceFragment(fragments.get(selectedTab));
                return true;
            }
        });
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.category_fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.category_fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
