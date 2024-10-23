package com.example.livrariacdf.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.livrariacdf.fragments.DevFragment;
import com.example.livrariacdf.fragments.HomeFragment;
import com.example.livrariacdf.fragments.HqFragment;
import com.example.livrariacdf.fragments.SciFiFragment;

public class PagerAdapter extends FragmentStateAdapter {


    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // TODO: IMPLEMENTAR
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new DevFragment();
            case 2:
                return new SciFiFragment();
            case 3:
                return new HqFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
