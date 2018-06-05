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
import com.kmdev.flix.models.ResponsePersonMovie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kajal on 2/12/2017.
 */
public class PersonMovieCreditAdapter extends RecyclerView.Adapter<PersonMovieCreditAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponsePersonMovie.CastBean> mPersonMovieList;

    public PersonMovieCreditAdapter(List<ResponsePersonMovie.CastBean> reviewBeanList) {
        mPersonMovieList = reviewBeanList;
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
        if (!TextUtils.isEmpty(mPersonMovieList.get(position).getPoster_path())) {
            Picasso.with(holder.itemView.getContext())
                    .load(ApiUrls.IMAGE_PATH_ULTRA + mPersonMovieList.get(position).getPoster_path())
                    .placeholder(R.color.photo_placeholder)   // optional
                    .error(R.color.photo_placeholder)      // optional
                    .into(holder.imageView);
            holder.tvTitle.setText(mPersonMovieList.get(position).getOriginal_title());
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
            try {
                if (!TextUtils.isEmpty(mPersonMovieList.get(position).getRelease_date())) {
                    Date date = simpleDateFormat1.parse(mPersonMovieList.get(position).getRelease_date());
                    String releaseDate = simpleDateFormat.format(date);
                    Date formattedDate = simpleDateFormat.parse(releaseDate);
                    holder.tvReleaseDate.setText(simpleDateFormat.format(formattedDate));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tvRate.setVisibility(View.GONE);
        }
        // holder.tvRate.setText(String.valueOf(mPersonMovieList.get(position).getCharacter()));
    }

    @Override
    public int getItemCount() {
        return mPersonMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvReleaseDate, tvRate;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            tvRate = (TextView) itemView.findViewById(R.id.tv_rating);

        }
    }

}

