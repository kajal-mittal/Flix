package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiUrls;
import com.kmdev.flix.models.ResponseCastTVShows;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kajal on 3/24/2017.
 */
public class CastShowsAdapter extends RecyclerView.Adapter<CastShowsAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponseCastTVShows.CastBean> mShowsCastList;

    public CastShowsAdapter(List<ResponseCastTVShows.CastBean> castBeanList) {
        mShowsCastList = castBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_similar_movie, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!TextUtils.isEmpty(mShowsCastList.get(position).getProfile_path())) {
            Picasso.with(holder.itemView.getContext())
                    .load(ApiUrls.IMAGE_PATH_ULTRA + mShowsCastList.get(position).getProfile_path())
                    .placeholder(R.color.grey)   // optional
                    .error(R.color.grey)      // optional
                    .into(holder.image);
        }
        holder.tvName.setText(mShowsCastList.get(position).getName());
        holder.tvCharacter.setText(mShowsCastList.get(position).getCharacter());
        holder.tvRating.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mShowsCastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCharacter;
        ImageView image;
        private TextView tvRating;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_title);
            image = (ImageView) itemView.findViewById(R.id.image);
            tvCharacter = (TextView) itemView.findViewById(R.id.tv_release_date);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);


        }
    }

}



