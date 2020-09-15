package net.azilab.campCompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import net.azilab.campCompanion.backendCommunicator.LogRequester;
import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.helper.log.LogAdapter;
import net.azilab.campCompanion.model.Log;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SpotInfoActivity extends AppCompatActivity {

    private static Spot spot;

    private ImageView thumbnail;

    private TextView accessibilityNote;
    private TextView locationNote;
    private TextView utilitiesNote;
    private TextView privacyNote;

    private Button displayOnMap;
    private Button addLog;

    private RecyclerView logZone;
    private RecyclerView.LayoutManager layoutManager;
    private LogAdapter logAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spot = (Spot) getIntent().getSerializableExtra("spot");
        setContentView(R.layout.spot_information_view);

        this.thumbnail = findViewById(R.id.spotImage);

        this.accessibilityNote = findViewById(R.id.accessibilityDisplay);
        this.locationNote = findViewById(R.id.locationDisplay);
        this.utilitiesNote = findViewById(R.id.utilitiesDisplay);
        this.privacyNote = findViewById(R.id.privacyDisplay);

        this.displayOnMap = findViewById(R.id.displayMapButton);
        this.addLog = findViewById(R.id.addLog);

        this.logZone = findViewById(R.id.logZone);
        logZone.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        logZone.setLayoutManager(layoutManager);

        LogRequester.getLogForSpot(String.valueOf(spot.getId()), SpotInfoActivity.this, new RequestCallback<JSONArray>() {
            @Override
            public void onDataReceived(JSONArray response) throws JSONException {
                List<Log> lstOfLogs = new ArrayList<>();

                for(int i = 0; i < response.length(); i++) {
                    Log logToAdd = new Gson().fromJson(response.getJSONObject(i).toString(), Log.class);
                    lstOfLogs.add(logToAdd);
                    System.out.println("pouet");
                }
                android.util.Log.println( android.util.Log.INFO, "hello","pouet pouet");

                logAdapter = new LogAdapter(lstOfLogs, SpotInfoActivity.this);
                logZone.setAdapter(logAdapter);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setTitle(spot.getName());

        this.thumbnail.setBackgroundResource(R.drawable.splash_screen);

        this.accessibilityNote.setText(String.valueOf(spot.getAccessibilityNote()));
        this.locationNote.setText(String.valueOf(spot.getLocationNote()));
        this.utilitiesNote.setText(String.valueOf(spot.getUtilitiesNote()));
        this.privacyNote.setText(String.valueOf(spot.getPrivacyNote()));

        this.displayOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotInfoActivity.this, ShowSpotActivity.class);
                intent.putExtra("spotLat", spot.getLatitude());
                intent.putExtra("spotLon", spot.getLongitude());
                startActivity(intent);
            }
        });

        this.addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotInfoActivity.this, AddLogActivity.class);
                intent.putExtra("spot", spot);
                startActivity(intent);
            }
        });

        getLogs();
    }

    private void getLogs() {



    }
}
