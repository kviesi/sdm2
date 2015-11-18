package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.CountDown;

public class CountDownActivity extends AppCompatActivity {

    private CountDown countDownPlayer1;
    private CountDown countDownPlayer2;

    private TextView timerPlayer1;
    private TextView timerPlayer2;

    private boolean isPlayer1Active = Boolean.FALSE;

    public static final String BACKGROUND_COLOR = "#1c484a";
    public static final String LOSE_BACKGROUND_COLOR = "#FFC4AEB4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        timerPlayer1 = (TextView) findViewById(R.id.timerPlayer1);
        timerPlayer2 = (TextView) findViewById(R.id.timerPlayer2);

        countDownPlayer1 = CountDown.newInstance(CountDown.START_TIME, timerPlayer1);
        countDownPlayer2 = CountDown.newInstance(CountDown.START_TIME, timerPlayer2);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putLong("startP1", countDownPlayer1.getTimeUntilFinish());
        savedInstanceState.putLong("startP2", countDownPlayer2.getTimeUntilFinish());
        savedInstanceState.putBoolean("isPlayer1Active", isPlayer1Active);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        Long startP1 = savedInstanceState.getLong("startP1");
        Long startP2 = savedInstanceState.getLong("startP2");
        isPlayer1Active = savedInstanceState.getBoolean("isPlayer1Active");

        countDownPlayer1 = CountDown.newInstance(startP1, timerPlayer1);
        countDownPlayer2 = CountDown.newInstance(startP2, timerPlayer2);

        //Verifica qual player estava ativo para reiniciá-lo
        if (isPlayer1Active) {
            countDownPlayer1.start();
        } else {
            countDownPlayer2.start();
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onClickPlayer1(View view) {
        isPlayer1Active = Boolean.FALSE;
        timerPlayer1.setText(toHourMinuteAndSecond(CountDown.START_TIME));
        timerPlayer1.setClickable(false);
        countDownPlayer1.cancel();
        timerPlayer2.setClickable(true);
        timerPlayer2.setBackgroundColor(Color.parseColor(BACKGROUND_COLOR));
        countDownPlayer2.start();
    }

    public void onClickPlayer2(View view) {
        isPlayer1Active = Boolean.TRUE;
        timerPlayer2.setText(toHourMinuteAndSecond(CountDown.START_TIME));
        timerPlayer2.setClickable(false);
        countDownPlayer2.cancel();
        timerPlayer1.setClickable(true);
        timerPlayer1.setBackgroundColor(Color.parseColor(BACKGROUND_COLOR));
        countDownPlayer1.start();
    }

    public static String toHourMinuteAndSecond(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }

}
