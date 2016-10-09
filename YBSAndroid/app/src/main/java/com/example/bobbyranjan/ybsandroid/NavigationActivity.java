package com.example.bobbyranjan.ybsandroid;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bobbyranjan.ybsandroid.models.Patient;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PatientListFragment.OnListFragmentInteractionListener {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
        setupWindowAnimations();
        setupToolbar();
        setupDrawer();
        hookEvents();
        addFragments();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setSubtitle(R.string.list_of_pregnant_women);
    }


    private void bindControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning back to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT); // Use START if using right - to - left locale
        slideTransition.setDuration(1000);

        getWindow().setReenterTransition(slideTransition);  // When MainActivity Re-enter the Screen
        getWindow().setExitTransition(slideTransition);     // When MainActivity Exits the Screen

        // For overlap of Re Entering Activity
        getWindow().setAllowReturnTransitionOverlap(false);
    }


    private void addFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rlContent, new PatientListFragment(), "Patient List Fragment");
        fragmentTransaction.commit();
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void hookEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListFragmentInteraction(null, Constants.ActionType.AddNewPatient);

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onListFragmentInteraction(Patient patient, Constants.ActionType fragmentType) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(NavigationActivity.this);
        Intent i = null;

        switch (fragmentType) {

            case AddNewPatient:
                i = new Intent(NavigationActivity.this, AddPatientActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.AddNewPatient);
                break;
            case PatientDetails:
                i = new Intent(NavigationActivity.this, ViewPatientActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.PatientDetails);
                i.putExtra(Constants.PATIENT_ID, patient.getId());
                break;
            case AddNewMedicalRecord:
                i = new Intent(NavigationActivity.this, AddPatientActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.AddNewMedicalRecord);
                i.putExtra(Constants.PATIENT_ID, patient.getId());
                break;
        }
        startActivity(i, options.toBundle());

    }
}
