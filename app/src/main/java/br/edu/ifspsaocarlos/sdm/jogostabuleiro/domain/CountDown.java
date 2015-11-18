package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity.CountDownActivity;

/**
 * Created by gustavo on 18/11/15.
 */
public class CountDown extends CountDownTimer {

    public static final long START_TIME = 30 * 1000;

    private TextView timerPlayer;
    private Color lastColor;

    private long timeUntilFinish;

    public static CountDown newInstance(Long start, TextView textView) {
        CountDown countDown = null;
        if (start == null || start == 0) {
            countDown = new CountDown(CountDown.START_TIME, textView);
        } else {
            countDown = new CountDown(start, textView);
        }
        return countDown;
    }

    private CountDown(long startTime, TextView timerPlayer) {
        super(startTime, 1 * 1000);
        this.timerPlayer = timerPlayer;
    }

    @Override
    public void onFinish() {
        timerPlayer.setBackgroundColor(Color.parseColor(CountDownActivity.LOSE_BACKGROUND_COLOR));
        this.timerPlayer.setText("PERDEU!");
    }

    @Override
    public void onTick(long millis) {
        timeUntilFinish = millis;
        if (millis < 11 * 1000) {
            if ((millis / 1000) % 2 == 0) {
                timerPlayer.setBackgroundColor(Color.RED);
            } else {
                timerPlayer.setBackgroundColor(Color.parseColor(CountDownActivity.BACKGROUND_COLOR));
            }
        }
        this.timerPlayer.setText(CountDownActivity.toHourMinuteAndSecond(millis));
    }

    public void setTimerPlayer(TextView timerPlayer) {
        this.timerPlayer = timerPlayer;
    }

    public long getTimeUntilFinish() {
        return timeUntilFinish;
    }
}
