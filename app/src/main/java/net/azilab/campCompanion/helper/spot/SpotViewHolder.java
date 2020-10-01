package net.azilab.campCompanion.helper.spot;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.model.Spot;

public class SpotViewHolder extends RecyclerView.ViewHolder {

    ImageView thumbnail;

    TextView campName;
    TextView distance;

    TextView accessNote;
    TextView locationNote;
    TextView commoditiesNote;
    TextView privacyNote;

    public SpotViewHolder(View itemView) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.spot_img_tab);
        this.campName = itemView.findViewById(R.id.camp_name_tab);
        this.distance = itemView.findViewById(R.id.distance_camp_tab);

        this.accessNote = itemView.findViewById(R.id.access_tab);
        this.locationNote = itemView.findViewById(R.id.loc_tab);
        this.commoditiesNote = itemView.findViewById(R.id.comm_tab);
        this.privacyNote = itemView.findViewById(R.id.priv_tab);

    }

    public void updateWithSpot(Spot spot){
        this.campName.setText(spot.getName());
        this.distance.setText("N/A");
        this.thumbnail.setBackgroundResource(R.drawable.camp_thumbnail);

        this.accessNote.setText(String.valueOf(spot.getAccessibilityNote()));
        this.locationNote.setText(String.valueOf(spot.getLocationNote()));
        this.commoditiesNote.setText(String.valueOf(spot.getUtilitiesNote()));
        this.privacyNote.setText(String.valueOf(spot.getPrivacyNote()));
    }
}
