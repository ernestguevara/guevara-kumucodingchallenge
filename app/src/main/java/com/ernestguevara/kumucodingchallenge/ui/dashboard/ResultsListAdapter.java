package com.ernestguevara.kumucodingchallenge.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ernestguevara.kumucodingchallenge.R;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.MyViewHolder> {

    private static final String TAG = "ResultsListAdapter";

    private static ResultsListAdapter.RecyclerViewClickListener itemListener;
    private ArrayList<SearchResult> resultsList;
    private Context context;

    public ResultsListAdapter(Context context, RecyclerViewClickListener mItemListener) {
        this.resultsList = new ArrayList<>();
        this.context = context;
        this.itemListener = mItemListener;
    }

    @NonNull
    @NotNull
    @Override
    public ResultsListAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //Inflate the layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);

        return new ResultsListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ResultsListAdapter.MyViewHolder holder, int position) {
        //Binds the results to the layout
        Glide.with(context)
                .load(resultsList.get(position).getArtworkUrl100())
                .apply(new RequestOptions().dontTransform().placeholder(R.mipmap.ic_launcher))
                .into(holder.resultImageView);

        holder.resultTitle.setText(resultsList.get(position).getTrackName());
        holder.resultGenre.setText(resultsList.get(position).getPrimaryGenreName());
        holder.resultPrice.setText(context.getResources().getString(R.string.item_price, String.valueOf(resultsList.get(position).getTrackPrice())));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    //sets the data upon change
    public void setData(ArrayList<SearchResult> mResultsList) {
        if (resultsList != null) {
            resultsList.clear();
            resultsList.addAll(mResultsList);
            notifyDataSetChanged();
        } else {
            resultsList = mResultsList;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView resultImageView;
        private TextView resultTitle;
        private TextView resultGenre;
        private TextView resultPrice;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            resultImageView = itemView.findViewById(R.id.search_item_image);
            resultTitle = itemView.findViewById(R.id.search_item_name);
            resultGenre = itemView.findViewById(R.id.search_item_genre);
            resultPrice = itemView.findViewById(R.id.search_item_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.onClick(v, this.getAdapterPosition(), resultsList);
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position, List<SearchResult> mSearchResultList);
    }
}
