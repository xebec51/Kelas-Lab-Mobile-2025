package com.example.praktikum3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedDetailPagerAdapter extends RecyclerView.Adapter<FeedDetailPagerAdapter.ViewHolder> {
    private Context context;
    private List<Feed> feedList;

    public FeedDetailPagerAdapter(Context context, List<Feed> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feedList.get(position);

        holder.username.setText(feed.getUsername());
        holder.captionUsername.setText(feed.getCaption_username());
        holder.caption.setText(feed.getCaption());
        holder.like.setText(feed.getLike());
        holder.comment.setText(feed.getComment());
        holder.share.setText(feed.getShare());

        if (feed.getImagePost() instanceof String) {
            Glide.with(context).load(feed.getImagePost()).into(holder.imagePost);
        } else if (feed.getImagePost() instanceof Integer) {
            holder.imagePost.setImageResource((Integer) feed.getImagePost());
        }

        holder.imageProfile.setImageResource(feed.getImageProfile());
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageProfile;
        ImageView imagePost;
        TextView username, like, comment, share, captionUsername, caption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.cIV_profile);
            imagePost = itemView.findViewById(R.id.iV_postingan);
            username = itemView.findViewById(R.id.tV_username);
            like = itemView.findViewById(R.id.tV_like);
            comment = itemView.findViewById(R.id.tV_comment);
            share = itemView.findViewById(R.id.tV_share);
            captionUsername = itemView.findViewById(R.id.tV_caption_username);
            caption = itemView.findViewById(R.id.tV_caption);
        }
    }
}
