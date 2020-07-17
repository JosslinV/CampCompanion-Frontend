package net.azilab.campCompanion.helper;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.model.Spot;

public class SpotViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public SpotViewHolder(View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.fragment_main_item_title);
    }

    public void updateWithSpot(Spot spot){
        this.textView.setText(spot.getName());
    }
}
