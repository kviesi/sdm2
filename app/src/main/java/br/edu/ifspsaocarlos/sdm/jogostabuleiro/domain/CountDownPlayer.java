package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by gustavo on 18/11/15.
 */
public class CountDownPlayer {

    private CountDownTimer countDownTimer;
    private View view;
    private boolean isActive = Boolean.FALSE;

    public CountDownPlayer(CountDownTimer countDownTimer, View view) {
        this.countDownTimer = countDownTimer;
        this.view = view;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public View getView() {
        return view;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        isActive = Boolean.TRUE;
        countDownTimer.start();
    }

    public void cancel() {
        isActive = Boolean.FALSE;
        countDownTimer.cancel();
    }
}
