package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;

public class CountDownActivity extends AppCompatActivity {

    private CountDown countDownPlayer1;
    private CountDown countDownPlayer2;

    private TextView timerPlayer1;
    private TextView timerPlayer2;

    private final long startTime = 30 * 1000;
    private final String backgroudColor = "#1c484a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        timerPlayer1 = (TextView) findViewById(R.id.timerPlayer1);
        timerPlayer2 = (TextView) findViewById(R.id.timerPlayer2);

        Long startP1 = null;
        Long startP2 = null;
        if (savedInstanceState != null) {
            startP1 = savedInstanceState.getLong("countDownPlayer1");
            startP2 = savedInstanceState.getLong("countDownPlayer2");
        }

        if (startP1 == null) {
            countDownPlayer1 = new CountDown(startTime, timerPlayer1);
            countDownPlayer2 = new CountDown(startTime, timerPlayer2);
        } else {
            countDownPlayer1 = new CountDown(startP1, timerPlayer1);
            countDownPlayer2 = new CountDown(startP2, timerPlayer2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putLong("countDownPlayer1", countDownPlayer1.getTimeUntilFinish());
        savedInstanceState.putLong("countDownPlayer2", countDownPlayer2.getTimeUntilFinish());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Long startP1 = null;
        Long startP2 = null;
        if (savedInstanceState != null) {
            startP1 = savedInstanceState.getLong("countDownPlayer1");
            startP2 = savedInstanceState.getLong("countDownPlayer2");
        }

        if (startP1 == null) {
            countDownPlayer1 = new CountDown(startTime, timerPlayer1);
            countDownPlayer2 = new CountDown(startTime, timerPlayer2);
        } else {
            countDownPlayer1 = new CountDown(startP1, timerPlayer1);
            countDownPlayer2 = new CountDown(startP2, timerPlayer2);
        }

        super.onRestoreInstanceState(savedInstanceState);

//        countDownPlayer1 = (CountDown) savedInstanceState.getSerializable("countDownPlayer1");
//        countDownPlayer2 = (CountDown) savedInstanceState.getSerializable("countDownPlayer1");

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


    public class CountDown extends CountDownTimer implements Serializable {

        private static final long serialVersionUID = 7455734982142331027L;

        private TextView timerPlayer;
        private Color lastColor;

        private long timeUntilFinish;

        public CountDown(long startTime, TextView timerPlayer) {
            super(startTime, 1 * 1000);
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
                    timerPlayer.setBackgroundColor(Color.parseColor(backgroudColor));
                }
            }
            this.timerPlayer.setText(toHourMinuteAndSecond(millis));
            timeUntilFinish = millis;
        }

        public void setTimerPlayer(TextView timerPlayer) {
            this.timerPlayer = timerPlayer;
        }

        public long getTimeUntilFinish() {
            return timeUntilFinish;
        }
    }
}
