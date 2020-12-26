package com.dam.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ClockActivity extends AppCompatActivity {

    TextView tvClock;
    TextView tvRest;
    TextView tvPeriodos;
    ImageView ivClock;
    ImageView ivCoffee;
    ImageView ivTable;

    CountDownTimer countDownTimer;
    CountDownTimer countDownTimer2;
    SoundPool sp;
    Resources res;

    int sonidoReproduccion;
    int numRep;
    int contador = 0;
    long duration;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        tvClock = findViewById(R.id.tvClock);
        tvRest = findViewById(R.id.tvRest);
        tvPeriodos = findViewById(R.id.tvPeriodos);
        ivClock = findViewById(R.id.ivClock);
        ivCoffee = findViewById(R.id.ivCoffee);
        ivTable = findViewById(R.id.ivTable);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoReproduccion = sp.load(this, R.raw.alarm, 1);

        numRep = getIntent().getIntExtra("REP", 4);

        updatePeriodosCompletados();
        startClock();

    }

    private void startAnimation() {
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.rotation);
        myAnim.setDuration(5000);
        ivClock.startAnimation(myAnim);
    }

    private void startAnimation2() {
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.translate);
        myAnim.setDuration(5000);
        ivCoffee.startAnimation(myAnim);
    }

    private void startRest() {
        startAnimation2();
        long d = TimeUnit.SECONDS.toMillis(5);
        countDownTimer2 = new CountDownTimer(d, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long t = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                int minutes = (int) (t / 60);
                int seconds = (int) (t % 60);

                String sDuration = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                tvRest.setText(sDuration);

                updatePeriodosCompletados();
            }

            @Override
            public void onFinish() {
                tvRest.setText("00:00");

                if (contador < numRep) {
                    startClock();
                } else {
                    Intent i = new Intent(ClockActivity.this, FinalActivity.class);
                    startActivity(i);
                }
            }
        }.start();

    }

    private void updatePeriodosCompletados() {
        res = getResources();
        String texto = String.format(res.getString(R.string.tv_periodos_completados), contador, numRep);
        tvPeriodos.setText(texto);
    }

    private void startClock() {
        contador++;
        startAnimation();
        duration = TimeUnit.SECONDS.toMillis(5);
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                updateClock();
            }

            @Override
            public void onFinish() {
                tvClock.setText("00:00");
                startRest();
            }
        }.start();
    }

    private void updateClock() {
        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);

        String sDuration = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvClock.setText(sDuration);

        if (time < 1) {
            sp.play(sonidoReproduccion, 1, 1, 1, 0, 0);
        }

    }
}