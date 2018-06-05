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
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kajal on 2/28/2017.
 */
public class FavouritePeopleAdapter extends RecyclerView.Adapter<FavouritePeopleAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponsePeopleDetails> mResponsePeopleDetailsList;

    public FavouritePeopleAdapter(
            List<ResponsePeopleDetails> responsePeopleDetailsList) {
        mResponsePeopleDetailsList = responsePeopleDetailsList;
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
        if (!TextUtils.isEmpty(mResponsePeopleDetailsList.get(position).getProfile_path())) {
            Picasso.with(holder.itemView.getContext())
                    .load(ApiUrls.IMAGE_PATH_ULTRA + mResponsePeopleDetailsList.get(position).getProfile_path())
                    .placeholder(R.color.photo_placeholder)   // optional
                    .error(R.color.photo_placeholder)      // optional
                    .into(holder.imageView);
        }
        holder.tvTitle.setText(mResponsePeopleDetailsList.get(position).getName());
        holder.tvRate.setText(String.valueOf(mResponsePeopleDetailsList.get(position).getPopularityInt()));
        holder.tvTitle.setPadding(10, 10, 10, 10);
        holder.tvReleaseDate.setVisibility(View.GONE);
      /*  SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            Date date = simpleDateFormat1.parse(mResponsePeopleDetailsList.get(position).get());
            String releaseDate = simpleDateFormat.format(date);
            Date formattedDate = simpleDateFormat.parse(releaseDate);
            holder.tvReleaseDate.setText(simpleDateFormat.format(formattedDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public int getItemCount() {


        return mResponsePeopleDetailsList.size();

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

