package net.azilab.campCompanion.helper.log;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.azilab.campCompanion.R;
import net.azilab.campCompanion.SpotInfoActivity;
import net.azilab.campCompanion.helper.spot.SpotViewHolder;
import net.azilab.campCompanion.model.Log;
import net.azilab.campCompanion.model.Spot;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
    private List<Log> logs;
    private Activity origin;

    public LogAdapter(List<Log> logs, Activity origin) {
        this.logs = logs;
        this.origin = origin;
    }


    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.log_tab, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        final Log logRelated = this.logs.get(position);

        holder.updateWithLog(logRelated);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return logs.size();
    }
}
