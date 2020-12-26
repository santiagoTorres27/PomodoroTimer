package com.dam.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {

    ImageView img1;
    Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        slider = findViewById(R.id.slider);

        Glide.with(this)
                .load(R.drawable.img)
//                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
//                .placeholder(R.drawable.bg2)
                .into(img1);
    }

    public void start(View view) {
        int numRep = (int) slider.getValue();

        Intent i = new Intent(this, ClockActivity.class);
        i.putExtra("REP", numRep);
        startActivity(i);
    }

    public void goInformation(View view) {
        Intent i = new Intent(this, InformationActivity.class);
        startActivityForResult(i, 1);
    }
}