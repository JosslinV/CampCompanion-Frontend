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
import net.azilab.campCompanion.maps.MapHelper;

public class MainActivity extends AppCompatActivity {

    private MapHelper mapHelper;
    private Button addSpot;
    private Button searchSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapHelper = new MapHelper((MapFragment) fragmentManager.findFragmentById(R.id.maps));

        this.addSpot = findViewById(R.id.addSpot);
        this.searchSpot = findViewById(R.id.searchSpot);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initActivity();

        mapHelper.initMap();
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
}