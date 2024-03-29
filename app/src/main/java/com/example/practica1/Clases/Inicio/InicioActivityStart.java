package com.example.practica1.Clases.Inicio;

import android.os.Bundle;

import com.example.practica1.Clases.Inicio.ui.gallery.GalleryFragment;
import com.example.practica1.Clases.Inicio.ui.home.HomeFragment;
import com.example.practica1.Clases.Inicio.ui.logout.LogoutFragment;
import com.example.practica1.Clases.Inicio.ui.manage.ManageFragment;
import com.example.practica1.Clases.Inicio.ui.send.SendFragment;
import com.example.practica1.Clases.Inicio.ui.share.ShareFragment;
import com.example.practica1.Clases.Inicio.ui.slideshow.SlideshowFragment;
import com.example.practica1.Clases.LoginActivity;
import com.example.practica1.R;
import com.example.practica1.Sessions.UserSession;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.content.Intent;
import static java.lang.Thread.sleep;

public class InicioActivityStart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    UserSession session;

    LoginActivity la = new LoginActivity();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_start);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new UserSession(this);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*try {
            sleep(3000);
            //la.checkConnection();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }*/

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        /*NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_categories, R.id.nav_products,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new HomeFragment()).commit();
                break;

            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new GalleryFragment()).commit();
                break;

            case R.id.nav_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new SlideshowFragment()).commit();
                break;

            case R.id.nav_tools:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new ManageFragment()).commit();
                break;

            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new ShareFragment()).commit();
                break;

            case R.id.nav_send:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new SendFragment()).commit();
                break;

            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new LogoutFragment()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedFragment(item.getItemId());
        return true;



    }

    private void displaySelectedFragment(int menuItemID){
        Fragment selectedFragment = null;
        if (menuItemID == R.id.nav_logout){
            session.logoutUser();
            finish();
        }
        else {

            switch (menuItemID){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.nav_categories:
                    selectedFragment = new GalleryFragment();
                    break;

                case R.id.nav_products:
                    selectedFragment = new SlideshowFragment();
                    break;

                case R.id.nav_tools:
                    selectedFragment = new ManageFragment();
                    break;

                case R.id.nav_share:
                    selectedFragment = new ShareFragment();
                    break;

                case R.id.nav_send:
                    selectedFragment = new SendFragment();
                    break;

                case R.id.nav_logout:
                    selectedFragment = new LogoutFragment();
                    break;
            }

            getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, selectedFragment)
                    .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_enter_anim)
                    .commit();

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio_activity_start, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override*/
    /*public boolean onNavigationItemSelectedListener(MenuItem item){
        int id = item.getItemId();
        Fragment logout = null;
        boolean activity = false;

        if(id == R.id.nav_categories) {
            logout = new GalleryFragment();
            activity = true;


        }

        if(activity == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, logout).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }*/



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment frag = null;
        boolean activity = false;

        if(id == R.id.nav_categories) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new GalleryFragment()).commit();

            frag = new GalleryFragment();
            activity = true;

        }
        else if(id == R.id.nav_products){
            frag = new SlideshowFragment();
            activity = true;
        }

        if(activity == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, frag).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    /*@Override
    public void onFragmentInteraction(Url url){

    }*/

}