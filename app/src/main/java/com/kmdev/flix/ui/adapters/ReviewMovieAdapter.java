package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmdev.flix.R;
import com.kmdev.flix.models.ResponseMovieReview;

import java.util.List;

/**
 * Created by Kajal on 10/16/2016.
 */
public class ReviewMovieAdapter extends RecyclerView.Adapter<ReviewMovieAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponseMovieReview.ReviewBean> mReviewBeanList;

    public ReviewMovieAdapter(List<ResponseMovieReview.ReviewBean> reviewBeanList) {
        mReviewBeanList = reviewBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_review_movie, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       /* Picasso.with(holder.itemView.getContext())
                .load(ApiUrls.IMAGE_PATH_ULTRA+mReviewBeanList.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)   // optional
                .error(R.mipmap.ic_launcher)      // optional
                .into(holder.imageView);
        holder.tvTitle.setText(mReviewBeanList.get(position).getOriginal_title());
        holder.tvReleaseDate.setText(mReviewBeanList.get(position).getRelease_date());*/
        holder.tvAuthor.setText(mReviewBeanList.get(position).getAuthor());
        holder.tvContent.setText(mReviewBeanList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mReviewBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent, tvAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);

        }
    }

}
