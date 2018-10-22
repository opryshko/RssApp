package com.geekapps.rsstestapp.ui;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.geekapps.rsstestapp.R;
import com.geekapps.rsstestapp.mvp.BaseMvpActivity;
import com.geekapps.rsstestapp.ui.categories.CustomViewPager;
import com.geekapps.rsstestapp.ui.categories.ViewPagerAdapter;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksFragment;
import com.geekapps.rsstestapp.ui.categories.movies.MoviesFragment;
import com.geekapps.rsstestapp.ui.categories.podcasts.PodcastsFragment;
import com.geekapps.rsstestapp.ui.favourites.FavouritesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    CustomViewPager viewPager;
    @BindView(R.id.category_fragment_container)
    RelativeLayout categoryFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.audiobooks:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.movies:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.podcasts:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.favourites:
                        viewPager.setCurrentItem(3);
                        break;
                }

                clearFragmentsBackstack();
                return true;
            }
        });

        setupViewPager();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new AudiobooksFragment());
        adapter.addFragment(new MoviesFragment());
        adapter.addFragment(new PodcastsFragment());
        adapter.addFragment(new FavouritesFragment());

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
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

    private void clearFragmentsBackstack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
