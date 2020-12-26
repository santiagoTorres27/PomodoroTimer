package com.dam.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class FinalActivity extends AppCompatActivity {

    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        img1 = findViewById(R.id.img1);

        Glide.with(this)
                .load(R.drawable.img2)
//                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
//                .placeholder(R.drawable.bg2)
                .into(img1);
    }

    public void restart(View view) {
        Intent i = new Intent(FinalActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void goInformation(View view) {
        Intent i = new Intent(this, InformationActivity.class);
        startActivityForResult(i, 1);
    }
}