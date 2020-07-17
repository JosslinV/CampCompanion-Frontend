package net.azilab.campCompanion.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.model.Spot;

import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotViewHolder> {
    private List<Spot> spots;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SpotAdapter(List<Spot> spots) {
        this.spots = spots;
    }


    @Override
    public SpotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.spot_tab, parent, false);

        return new SpotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotViewHolder holder, int position) {
        holder.updateWithSpot(this.spots.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return spots.size();
    }
}
