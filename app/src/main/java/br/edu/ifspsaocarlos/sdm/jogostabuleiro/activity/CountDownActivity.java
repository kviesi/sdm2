package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;

public class CountDownActivity extends AppCompatActivity {

    private CountDown countDownPlayer1;
    private CountDown countDownPlayer2;

    private TextView timerPlayer1;
    private TextView timerPlayer2;

    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;
    private final String backgroudColor = "#1c484a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        timerPlayer1 = (TextView) findViewById(R.id.timerPlayer1);
        timerPlayer2 = (TextView) findViewById(R.id.timerPlayer2);

        countDownPlayer1 = new CountDown(startTime, interval, timerPlayer1);
        countDownPlayer2 = new CountDown(startTime, interval, timerPlayer2);
    }

    public void onClickPlayer1(View view) {
        timerPlayer1.setText(toHourMinuteAndSecond(startTime));
        timerPlayer1.setClickable(false);
        countDownPlayer1.cancel();
        timerPlayer2.setClickable(true);
        timerPlayer2.setBackgroundColor(Color.parseColor(backgroudColor));
        countDownPlayer2.start();
    }

    public void onClickPlayer2(View view) {
        timerPlayer2.setText(toHourMinuteAndSecond(startTime));
        timerPlayer2.setClickable(false);
        countDownPlayer2.cancel();
        timerPlayer1.setClickable(true);
        timerPlayer1.setBackgroundColor(Color.parseColor(backgroudColor));
        countDownPlayer1.start();
    }

    private static String toHourMinuteAndSecond(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }


    public class CountDown extends CountDownTimer {

        private TextView timerPlayer;
        private Color lastColor;

        public CountDown(long startTime, long interval, TextView timerPlayer) {
            super(startTime, interval);
            this.timerPlayer = timerPlayer;
        }

        @Override
        public void onFinish() {
            timerPlayer.setBackgroundColor(Color.BLUE);
            this.timerPlayer.setText("PERDEU!");
        }

        @Override
        public void onTick(long millis) {
            if (millis < 11 * 1000) {
                if ((millis / 1000) % 2 == 0) {
                    timerPlayer.setBackgroundColor(Color.RED);
                } else {
                    timerPlayer.setBackgroundColor(Color.parseColor("#333333"));
                }
            }
            this.timerPlayer.setText(toHourMinuteAndSecond(millis));
        }
    }

}
