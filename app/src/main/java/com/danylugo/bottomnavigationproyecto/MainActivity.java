package com.danylugo.bottomnavigationproyecto;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.danylugo.bottomnavigationproyecto.Fragments.AliadosFragment;
import com.danylugo.bottomnavigationproyecto.Fragments.PersonajeFragment;
import com.danylugo.bottomnavigationproyecto.Fragments.VillanosFragment;
import com.danylugo.bottomnavigationproyecto.Model.Spider;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView mMain_nav;
    public FrameLayout mMain_frame;

    private AliadosFragment aliadosFragment;
    private PersonajeFragment personajeFragment;
    private VillanosFragment villanosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bottomNavService();
        getDatos();
    }

    private void bottomNavService(){
        mMain_frame = findViewById(R.id.main_frame);
        mMain_nav = findViewById(R.id.btmNavigationView);

        personajeFragment = new PersonajeFragment();
        aliadosFragment = new AliadosFragment();
        villanosFragment = new VillanosFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, personajeFragment);
        fragmentTransaction.commit();

        mMain_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.itemPersonaje:
                        mMain_nav.setItemBackgroundResource(R.color.colorPersonaje);
                        setFragment(personajeFragment);
                        return true;

                    case R.id.itemAlly:
                        mMain_nav.setItemBackgroundResource(R.color.colorAliados);
                        setFragment(aliadosFragment);
                        return true;

                    case R.id.itemVillain:
                        mMain_nav.setItemBackgroundResource(R.color.colorVillano);
                        setFragment(villanosFragment);
                        return true;

                    default:
                        return false;

                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    private void getDatos(){
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("stringId");

        Bundle args = new Bundle();
        args.putString("spiderID",id);

        personajeFragment.setArguments(args);
        villanosFragment.setArguments(args);
        aliadosFragment.setArguments(args);

    }

}