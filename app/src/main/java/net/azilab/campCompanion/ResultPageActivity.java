package net.azilab.campCompanion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.SpotRequester;
import net.azilab.campCompanion.helper.SpotAdapter;
import net.azilab.campCompanion.model.Spot;
import net.azilab.campCompanion.model.SpotRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ResultPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpotAdapter spotAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_result_view);

        recyclerView = (RecyclerView) findViewById(R.id.spotResultRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SpotRequest request = (SpotRequest) this.getIntent().getSerializableExtra("requestOptions");

        SpotRequester.searchSpot(request,this, new RequestCallback<JSONArray>() {
            @Override
            public void onDataReceived(JSONArray response) throws JSONException {
                List<Spot> lstOfSpot = new ArrayList<>();

                for(int i = 0; i < response.length(); i++) {
                  lstOfSpot.add(new Gson().fromJson(response.getJSONObject(i).toString(), Spot.class));
                }
                spotAdapter = new SpotAdapter(lstOfSpot, ResultPageActivity.this);
                recyclerView.setAdapter(spotAdapter);
            }
        });
    }

}
