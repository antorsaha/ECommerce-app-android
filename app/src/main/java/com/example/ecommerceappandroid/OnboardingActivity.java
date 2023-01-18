package com.example.ecommerceappandroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ecommerceappandroid.adapters.ViewPagerAdapter;

import java.util.Objects;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {

    //Views
    Button nextButton;
    Button loginButton;
    Button skipButton;
    LinearLayout indicator;
    TextView[] dots;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initialize views
        viewPager = findViewById(R.id.vp_onb_viewpager);
        nextButton = findViewById(R.id.btn_onb_next);
        loginButton = findViewById(R.id.btn_onb_login);
        skipButton = findViewById(R.id.btn_onb_skip);
        indicator = findViewById(R.id.lr_indicator);

        nextButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        skipButton.setOnClickListener(this);

        if (viewPager != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(this);
            viewPager.setAdapter(adapter);
        }

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setupIndicator(position);
                if (position == 2) {
                    loginButton.setVisibility(View.VISIBLE);
                    nextButton.setText(R.string.signup);
                } else {
                    loginButton.setVisibility(View.GONE);
                    nextButton.setText(R.string.next);
                }

            }
        });

    }

    /**
     * it setup the the viewpager position dot indicator
     * this function calls by registerOnPageChangeCallback onPageSelected
     * it does not work
     *
     * @param position is viewPager current item position sent by onPageSelected
     */
    public void setupIndicator(int position) {
        dots = new TextView[3];
        indicator.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#826"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.white, getApplicationContext().getTheme()));
            }
            indicator.addView(dots[i]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.brown, getApplicationContext().getTheme()));
        }
    }

    /**
     * get viewPager current item position
     *
     * @return current item position
     */
    private int getItem() {
        return viewPager.getCurrentItem();
    }


    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btn_onb_next:
                int position = getItem();
                if (getItem() < 2) {
                    viewPager.setCurrentItem(getItem() + 1, true);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.btn_onb_login:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_onb_skip:
                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
}