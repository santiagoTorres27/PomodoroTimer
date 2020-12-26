package com.dam.pomodorotimer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] arrayImages = {
            R.drawable.information1,
            R.drawable.information2,
            R.drawable.information3
    };

    public int[] arrayText = {
            R.string.txt_info1,
            R.string.txt_info2,
            R.string.txt_info3
    };

    @Override
    public int getCount() {
        return arrayImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView ivInfo = (ImageView) view.findViewById(R.id.ivInfo);
        TextView tvInfo = (TextView) view.findViewById(R.id.tvText);

        ivInfo.setImageResource(arrayImages[position]);
        tvInfo.setText(arrayText[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
