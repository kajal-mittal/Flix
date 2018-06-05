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
import com.kmdev.flix.models.ResponseCastMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kajal on 3/20/2017.
 */
public class CastMovieAdapter extends RecyclerView.Adapter<CastMovieAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponseCastMovies.CastBean> mMovieCastList;

    public CastMovieAdapter(List<ResponseCastMovies.CastBean> castBeanList) {
        mMovieCastList = castBeanList;
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
        if (!TextUtils.isEmpty(mMovieCastList.get(position).getProfile_path())) {
            Picasso.with(holder.itemView.getContext())
                    .load(ApiUrls.IMAGE_PATH_ULTRA + mMovieCastList.get(position).getProfile_path())
                    .placeholder(R.color.grey)   // optional
                    .error(R.color.grey)      // optional
                    .into(holder.image);
        }
        holder.tvName.setText(mMovieCastList.get(position).getName());
        holder.tvCharacter.setText(mMovieCastList.get(position).getCharacter());
        holder.tvRating.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mMovieCastList.size();
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


