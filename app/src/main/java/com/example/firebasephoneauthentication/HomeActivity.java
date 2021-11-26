package com.example.firebasephoneauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.example.firebasephoneauthentication.frag.CallFragment;
import com.example.firebasephoneauthentication.frag.GoogleSearchViewFragment;
import com.example.firebasephoneauthentication.frag.BrowserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem;

    DrawerLayout drawerLayout;
    BrowserFragment chatFragment;
    GoogleSearchViewFragment callsFragment;
    CallFragment contactsFragment;
    Button btnLogout;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //btnLogout = (Button)findViewById(R.id.Logout);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Toolbar toolbar = findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navView = (NavigationView) findViewById(R.id.navView); // initiate a Navigation View
// implement setNavigationItemSelectedListener event on NavigationView

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment = null; // create a Fragment Object
                int itemId = menuItem.getItemId(); // get selected menu item's id
// check selected menu item's id and replace a Fragment Accordingly
                if (itemId == R.id.nav_my_order) {
                    fragment = new GoogleSearchViewFragment();
                } else if (itemId == R.id.nav_my_credit_score) {
                    fragment = new BrowserFragment();
                } else if (itemId == R.id.nav_logout) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                }
                return true;
            }
        });

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_call:
                                    viewPager.setCurrentItem(0);
                                    break;
                                case R.id.action_chat:
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.action_contact:
                                    viewPager.setCurrentItem(2);
                                    break;
                            }
                            return false;
                        }
                    });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (prevMenuItem != null) {
                        prevMenuItem.setChecked(false);
                    } else {
                        bottomNavigationView.getMenu().getItem(0).setChecked(false);
                    }
                    Log.d("page", "onPageSelected: " + position);
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                    prevMenuItem = bottomNavigationView.getMenu().getItem(position);

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            setupViewPager(viewPager);
        }



    private void setupViewPager (ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        callsFragment = new GoogleSearchViewFragment();
        chatFragment = new BrowserFragment();
        contactsFragment = new CallFragment();
        adapter.addFragment(callsFragment);
        adapter.addFragment(chatFragment);
        adapter.addFragment(contactsFragment);
        viewPager.setAdapter(adapter);
    }

}






