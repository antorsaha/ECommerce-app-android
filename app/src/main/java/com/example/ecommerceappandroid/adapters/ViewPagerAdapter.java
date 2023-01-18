package com.example.ecommerceappandroid.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ecommerceappandroid.fragments.Onboarding1Fragment;
import com.example.ecommerceappandroid.fragments.Onboarding2Fragment;
import com.example.ecommerceappandroid.fragments.Onboarding3Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    Context context;
    int fragmentCount = 3;

    public ViewPagerAdapter(Context context, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.context = context;
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
                //set isFirstTime to false in onboardingScreen sharedPreference
                //so that the onboarding screen don't show everytime.
                SharedPreferences onBoarding =
                        context.getSharedPreferences("onboardingScreen", MODE_PRIVATE);
                SharedPreferences.Editor editor = onBoarding.edit();
                editor.putBoolean("firstTime",false);
                editor.apply();

                //showing Onboarding3Fragment to the viewPager
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
