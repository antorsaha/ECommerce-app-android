package com.example.ecommerceappandroid.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ecommerceappandroid.fragments.Onboarding1Fragment;
import com.example.ecommerceappandroid.fragments.Onboarding2Fragment;
import com.example.ecommerceappandroid.fragments.Onboarding3Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    int fragmentCount = 3;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Onboarding1Fragment();
                break;
            case 1:
                fragment = new Onboarding2Fragment();
                break;
            case 2:
                fragment = new Onboarding3Fragment();
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getItemCount() {
        return fragmentCount;
    }
}
