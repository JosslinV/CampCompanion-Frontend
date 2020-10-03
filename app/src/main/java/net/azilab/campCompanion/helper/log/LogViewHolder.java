package net.azilab.campCompanion.helper.log;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.model.Log;
import net.azilab.campCompanion.model.Spot;
import net.azilab.campCompanion.model.User;

public class LogViewHolder extends RecyclerView.ViewHolder {

    TextView userName;
    RatingBar spotNote;

    TextView logTxt;


    public LogViewHolder(View itemView) {
        super(itemView);
        this.userName = itemView.findViewById(R.id.usernameTxt);
        this.spotNote = itemView.findViewById(R.id.spotNote);

        this.logTxt = itemView.findViewById(R.id.logTxt);
    }

    public void updateWithLog(Log log){
        User user = log.getRelatedUser();
        if(user == null) {
            this.userName.setText("Unidentified");
        } else {
            this.userName.setText(user.getUsername());
        }
        this.spotNote.setRating(log.getNote());

        this.logTxt.setText(log.getComment());
    }
}
