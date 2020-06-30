package net.azilab.campCompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import net.azilab.campCompanion.maps.MapFragment;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private Button addSpot;
    private Button searchSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.maps);

        this.addSpot = findViewById(R.id.addSpot);
        this.searchSpot = findViewById(R.id.searchSpot);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initActivity();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                init();
            }
        });

    }

    private void initActivity() {
        this.addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "add spot !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        this.searchSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "search spot !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void init() {
        this.mapFragment.addPointOnMap(43.595993,1.259442, "Point au hasard");
    }
}