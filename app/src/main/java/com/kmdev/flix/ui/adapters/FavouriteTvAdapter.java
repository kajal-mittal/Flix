package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiUrls;
import com.kmdev.flix.models.ResponseTvDetails;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kajal on 10/23/2016.
 */
public class FavouriteTvAdapter extends RecyclerView.Adapter<FavouriteTvAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponseTvDetails> mResponseTvDetailsList;

    public FavouriteTvAdapter(
            List<ResponseTvDetails> responseTvDetailsList) {
        mResponseTvDetailsList = responseTvDetailsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_popular_movie, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext())
                .load(ApiUrls.IMAGE_PATH_ULTRA + mResponseTvDetailsList.get(position).getPoster_path())
                .placeholder(R.color.photo_placeholder)   // optional
                .error(R.color.photo_placeholder)      // optional
                .into(holder.imageView);
        holder.tvTitle.setText(mResponseTvDetailsList.get(position).getOriginal_name());
        holder.tvRate.setText(String.valueOf(mResponseTvDetailsList.get(position).getVote_average()));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            Date date = simpleDateFormat1.parse(mResponseTvDetailsList.get(position).getFirst_air_date());
            String releaseDate = simpleDateFormat.format(date);
            Date formattedDate = simpleDateFormat.parse(releaseDate);
            holder.tvReleaseDate.setText(simpleDateFormat.format(formattedDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {


        return mResponseTvDetailsList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle, tvReleaseDate, tvRate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            tvRate = (TextView) itemView.findViewById(R.id.tv_rating);

        }
    }


}
