package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import android.os.CountDownTimer;

/**
 * Created by gustavo on 18/11/15.
 */
public abstract class CountDown extends CountDownTimer {

    private long timeUntilFinish;

    public CountDown(long startTime) {
        super(startTime, 1 * 1000);
    }

    public long getTimeUntilFinish() {
        return timeUntilFinish;
    }

    public void setTimeUntilFinish(long timeUntilFinish) {
        this.timeUntilFinish = timeUntilFinish;
    }
}
