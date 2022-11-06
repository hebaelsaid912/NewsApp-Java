package com.hebaelsaid.android.newsapp.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hebaelsaid.android.newsapp.R;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class BindingHolder {
    @BindingAdapter({"app:setImageGlideUri","app:bindProgressItem"})
    public static void setImageURL(ImageView view, String uri, ProgressBar progressBar) {
        Glide.with(view.getContext())
                .load(uri)
                .placeholder(R.drawable.image_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        view.setImageResource(R.drawable.image_placeholder);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(view);
    }
    @BindingAdapter("app:getTimeAgo")
    public static void getTimeAgo(TextView view, String time) {
        OffsetDateTime date = OffsetDateTime.parse(time);
       long timeAgo = date.toEpochSecond();
        if (timeAgo < 1000000000000L) {
            timeAgo *= 1000;
        }
        long now = System.currentTimeMillis();
        if (timeAgo > now || timeAgo <= 0) {
            view.setText(time);
        }
        long diff = now - timeAgo;
        Log.d("BindingHolder", "getTimeAgo: "+diff);
         if (diff < DateUtils.MINUTE_IN_MILLIS) {
             view.setText("just now");
        } else if (diff < 2 * DateUtils.MINUTE_IN_MILLIS) {
             view.setText("a minute ago");
        } else if (diff < 50 * DateUtils.MINUTE_IN_MILLIS) {
             view.setText((diff / DateUtils.MINUTE_IN_MILLIS) + " minutes ago");
        } else if (diff < 90 * DateUtils.MINUTE_IN_MILLIS) {
             view.setText("an hour ago");
        } else if (diff < 24 * DateUtils.HOUR_IN_MILLIS) {
             view.setText((diff / DateUtils.HOUR_IN_MILLIS) + " hours ago");
        } else if (diff < 48 * DateUtils.HOUR_IN_MILLIS) {
             view.setText("yesterday");
        } else {
             view.setText((diff / DateUtils.DAY_IN_MILLIS) + " days ago");
        }

    }


}
