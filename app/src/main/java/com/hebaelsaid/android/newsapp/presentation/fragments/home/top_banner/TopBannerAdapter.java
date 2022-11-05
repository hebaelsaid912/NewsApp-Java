package com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TopBannerAdapter extends FragmentStateAdapter {
    private int pagerListSize = 0;

    public TopBannerAdapter(@NonNull FragmentActivity fragmentActivity, int pagerListSize) {
        super(fragmentActivity);
        this.pagerListSize = pagerListSize;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new TopBannerFragment();
    }

    @Override
    public int getItemCount() {
        return pagerListSize;
    }
}
