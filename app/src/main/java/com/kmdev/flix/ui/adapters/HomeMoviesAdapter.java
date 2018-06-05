package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiUrls;
import com.kmdev.flix.RestClient.ConnectionDetector;
import com.kmdev.flix.models.ResponsePopularMovie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kajal on 10/9/2016.
 */
public class HomeMoviesAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private ViewHolder mViewHolder;
    private List<ResponsePopularMovie.PopularMovie> mPopularMovieList;
    private OnRetryListener mOnRetryListener;

    public HomeMoviesAdapter(List<ResponsePopularMovie.PopularMovie> popularMovieList, OnRetryListener onRetryListener) {
        mPopularMovieList = popularMovieList;
        mOnRetryListener = onRetryListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_ITEM:
                View viewItem = inflater.inflate(R.layout.item_popular_movie, parent, false);
                mViewHolder = new ItemViewHolder(viewItem);
                break;
            case VIEW_TYPE_LOADING:

                View viewLoding = inflater.inflate(R.layout.item_loading, parent, false);
                mViewHolder = new LoadingViewHolder(viewLoding);
                break;

        }

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == mPopularMovieList.size()) {
            configureLoadingViewHolder((LoadingViewHolder) holder, position);
        } else {
            configureItemViewHolder((ItemViewHolder) holder, position);
        }


    }

    private void configureItemViewHolder(ItemViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext())
                .load(ApiUrls.IMAGE_PATH_ULTRA + mPopularMovieList.get(position).getPoster_path())
                .placeholder(R.color.photo_placeholder)   // optional
                .error(R.color.photo_placeholder)      // optional
                .into(holder.imageView);
        holder.tvTitle.setText(mPopularMovieList.get(position).getOriginal_title());
        holder.tvRate.setText(String.valueOf(mPopularMovieList.get(position).getVote_average()));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            Date date = simpleDateFormat1.parse(mPopularMovieList.get(position).getRelease_date());
            String releaseDate = simpleDateFormat.format(date);
            Date formattedDate = simpleDateFormat.parse(releaseDate);
            holder.tvReleaseDate.setText(simpleDateFormat.format(formattedDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void configureLoadingViewHolder(final LoadingViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        if (mPopularMovieList.size() == 0) {
            holder.progressBarLoading.setVisibility(View.GONE);
        } else {
            if (ConnectionDetector.isNetworkAvailable(holder.itemView.getContext())) {
                holder.progressBarLoading.setVisibility(View.VISIBLE);
                holder.layoutNetwork.setVisibility(View.GONE);
            } else {
                holder.progressBarLoading.setVisibility(View.GONE);
                holder.layoutNetwork.setVisibility(View.VISIBLE);
                holder.btnRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnRetryListener != null &&
                                ConnectionDetector.isNetworkAvailable(holder.itemView.getContext())) {
                            holder.progressBarLoading.setVisibility(View.VISIBLE);
                            holder.layoutNetwork.setVisibility(View.GONE);

                            mOnRetryListener.onRetry();
                        } else {
                            Toast.makeText(holder.itemView.getContext(),
                                    R.string.internet_connection,
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });


            }
        }

    }

    @Override
    public int getItemCount() {
        return mPopularMovieList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mPopularMovieList.size()) {
            return VIEW_TYPE_LOADING;
        }
        if (getItemCount() == 1) {
            return 99;
        }
        return VIEW_TYPE_ITEM;
    }

    public interface OnRetryListener {
        void onRetry();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle, tvReleaseDate, tvRate;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            tvRate = (TextView) itemView.findViewById(R.id.tv_rating);

        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBarLoading;
        private RelativeLayout layoutNetwork;
        private Button btnRetry;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBarLoading = (ProgressBar) itemView.findViewById(R.id.progress_bar_loading);
            layoutNetwork = (RelativeLayout) itemView.findViewById(R.id.rl_network_error);
            btnRetry = (Button) itemView.findViewById(R.id.btn_retry);
        }
    }


}
