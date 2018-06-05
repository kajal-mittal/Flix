package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
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
import com.kmdev.flix.models.ResponsePeople;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kajal on 1/22/2017.
 */
public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private RecyclerView.ViewHolder mViewHolder;
    private List<ResponsePeople.ResultsBean> mPeopleList;
    private boolean mIsKnown;
    private PeopleAdapter.OnRetryListener mOnRetryListener;

    public PeopleAdapter(List<ResponsePeople.ResultsBean> peopleList, boolean isVisibleknown) {
        mPeopleList = peopleList;
        mIsKnown = isVisibleknown;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (!mIsKnown) {
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
        } else {
            View view = inflater.inflate(R.layout.item_similar_movie, parent, false);
            mViewHolder = new ViewHolder(view);

        }
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!mIsKnown) {
            if (position == mPeopleList.size()) {
                configureLoadingViewHolder((LoadingViewHolder) holder, position);
            } else {
                configureItemViewHolder((ItemViewHolder) holder, position);
            }

        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            List<ResponsePeople.ResultsBean.KnownForBean> knownForBeanList = mPeopleList.get(position).getKnown_for();
            for (int i = 0; i < knownForBeanList.size(); i++) {
                Picasso.with(holder.itemView.getContext())
                        .load(ApiUrls.IMAGE_PATH_ULTRA + knownForBeanList.get(i).getPoster_path())
                        .placeholder(R.color.photo_placeholder)   // optional
                        .error(R.color.photo_placeholder)      // optional
                        .into(viewHolder.imageView);

                viewHolder.tvTitle.setText(knownForBeanList.get(i).getOriginal_title());
                viewHolder.tvTitle.setPadding(0, 0, 0, 5);
                viewHolder.tvRate.setText(String.valueOf(knownForBeanList.get(i).getPopularityInt()));
                viewHolder.tvReleaseDate.setVisibility(View.GONE);
            }
         /*   SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
            try {
                Date date = simpleDateFormat1.parse(mPeopleList.get(position).ge);
                String releaseDate = simpleDateFormat.format(date);
                Date formattedDate = simpleDateFormat.parse(releaseDate);
                holder.tvReleaseDate.setText(simpleDateFormat.format(formattedDate));

            } catch (ParseException e) {
                e.printStackTrace();
            }    */

        }
/*

*/


    }

    private void configureItemViewHolder(ItemViewHolder holder, int position) {
        if (!TextUtils.isEmpty(mPeopleList.get(position).getProfile_path())) {
            Picasso.with(holder.itemView.getContext())
                    .load(ApiUrls.IMAGE_PATH_ULTRA + mPeopleList.get(position).getProfile_path())
                    .placeholder(R.color.photo_placeholder)   // optional
                    .error(R.color.photo_placeholder)      // optional
                    .into(holder.imageView);
        }
        holder.tvTitle.setText(mPeopleList.get(position).getName());
        holder.tvTitle.setPadding(15, 15, 15, 15);
        holder.tvRate.setText(String.valueOf(mPeopleList.get(position).getPopularityInt()) + "%");
        holder.tvReleaseDate.setVisibility(View.GONE);

    }

    private void configureLoadingViewHolder(final LoadingViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        if (mPeopleList.size() == 0) {
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
    public int getItemViewType(int position) {
        if (position == mPeopleList.size()) {
            return VIEW_TYPE_LOADING;
        }
        if (getItemCount() == 1) {
            return 99;
        }
        return VIEW_TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return mPeopleList.size() + 1;
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

