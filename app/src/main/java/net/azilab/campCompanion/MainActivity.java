package net.azilab.campCompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import net.azilab.campCompanion.maps.MyMapFragment;

public class MainActivity extends AppCompatActivity {

    private MyMapFragment myMapFragment;
    private Button addSpot;
    private Button searchSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.addSpot = findViewById(R.id.addSpot);
        this.searchSpot = findViewById(R.id.searchSpot);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.myMapFragment = (MyMapFragment) fragmentManager.findFragmentById(R.id.fragment_map);
    }

    @Override
    protected void onStart() {
        super.onStart();
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