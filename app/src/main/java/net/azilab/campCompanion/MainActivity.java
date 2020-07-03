package net.azilab.campCompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.Requester;
import net.azilab.campCompanion.maps.MapFragment;
import net.azilab.campCompanion.maps.MapHelper;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private static MapHelper mapHelper;
    private Button addSpot;
    private Button searchSpot;

    private List<Spot> spotLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapHelper = new MapHelper(MainActivity.this, (MapFragment) fragmentManager.findFragmentById(R.id.maps));

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
                Intent intent = new Intent(MainActivity.this, AddSpotActivity.class);
                if(mapHelper.getSelectedMarker() != null) {
                    double markerLatitude =  mapHelper.getSelectedMarker().getPosition().latitude;
                    double markerLongitude =  mapHelper.getSelectedMarker().getPosition().longitude;
                    intent.putExtra("selectedLatitude",markerLatitude);
                    intent.putExtra("selectedLongitude",markerLongitude);
                }
                startActivity(intent);
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