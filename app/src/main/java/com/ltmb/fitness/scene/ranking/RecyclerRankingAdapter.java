package com.ltmb.fitness.scene.ranking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltmb.fitness.R;
import com.ltmb.fitness.internal.util.CircleTransform;
import com.ltmb.fitness.uimodel.RankingPersonUiModel;
import com.squareup.picasso.Picasso;

import java.util.List;

@FunctionalInterface
interface RecyclerRankingCallback {
    void onItemClick(String id);
}

public class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.ViewHolder> {
    List<RankingPersonUiModel> localDataSet;
    RecyclerRankingCallback cb;

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public RecyclerRankingAdapter(List<RankingPersonUiModel> dataSet, RecyclerRankingCallback cb) {
        this.localDataSet = dataSet;
        this.cb = cb;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ranking_person, viewGroup, false);

        return new ViewHolder(view, this.cb);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        RankingPersonUiModel rankingItem = localDataSet.get(position);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        String avt = rankingItem.getAvt();

        Picasso.get()
                .load(avt)
                .transform(new CircleTransform())
                .into(viewHolder.avt);
        viewHolder.username.setText(rankingItem.getName());
        viewHolder.experience.setText(rankingItem.getExperience() + " KN");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, experience;
        ImageView avt;

        public ViewHolder(@NonNull View itemView, RecyclerRankingCallback cb) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.rankingUserName);
            avt = (ImageView) itemView.findViewById(R.id.rankingItemAvt);
            experience = (TextView) itemView.findViewById(R.id.rankingExperience);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lấy position của item đã click
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        cb.onItemClick("123");
                    }
                }
            });
        }
    }
}
