package com.kmdev.flix.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmdev.flix.R;
import com.kmdev.flix.models.ResponseVideo;

import java.util.List;

/**
 * Created by Kajal on 10/16/2016.
 */
public class VideoMovieAdapter extends RecyclerView.Adapter<VideoMovieAdapter.ViewHolder> {
    private ViewHolder mViewHolder;
    private List<ResponseVideo.VideoBean> mVideoBeanList;

    public VideoMovieAdapter(List<ResponseVideo.VideoBean> videoBeanList) {
        mVideoBeanList = videoBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_video_movie, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       /* Picasso.with(holder.itemView.getContext())
                .load(ApiUrls.IMAGE_PATH_ULTRA+mVideoBeanList.get(position).get())
                .placeholder(R.mipmap.ic_launcher)   // optional
                .error(R.mipmap.ic_launcher)      // optional
                .into(holder.imageView);
        holder.tvTitle.setText(mVideoBeanList.get(position).getOriginal_title());
        holder.tvReleaseDate.setText(mVideoBeanList.get(position).getRelease_date());*/
        holder.tvName.setText(mVideoBeanList.get(position).getName());
        holder.tvType.setText(mVideoBeanList.get(position).getType());


    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);


        }
    }

}

