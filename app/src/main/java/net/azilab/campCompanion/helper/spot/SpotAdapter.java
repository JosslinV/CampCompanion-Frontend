package net.azilab.campCompanion.helper.spot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.SpotInfoActivity;
import net.azilab.campCompanion.model.Spot;

import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotViewHolder> {
    private List<Spot> spots;
    private Activity origin;

    public SpotAdapter(List<Spot> spots, Activity origin) {
        this.spots = spots;
        this.origin = origin;
    }


    @Override
    public SpotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.spot_tab, parent, false);
        return new SpotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotViewHolder holder, int position) {
        final Spot spotRelated = this.spots.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(origin, SpotInfoActivity.class);
                intent.putExtra("spot", spotRelated);
                origin.startActivity(intent);
            }
        });

        holder.updateWithSpot(spotRelated);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return spots.size();
    }
}
