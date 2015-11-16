package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import java.util.Random;

/**
 * Created by viesi on 15/11/15.
 */
public abstract class SortMethod<T> {

    private static final int NOT_INITIALIZED = -1;

    private static int lastRandom = NOT_INITIALIZED;

    public final T play() {
        int newRandom = NOT_INITIALIZED;
        if (isInitialized()) {
            newRandom = getNewNumberWithoutRepeat();
        } else {
            newRandom = getNewNumber();
        }
        store(newRandom);
        return getValueBy(newRandom);
    }

    private boolean isInitialized() {
        return lastRandom != NOT_INITIALIZED;
    }

    private int getNewNumberWithoutRepeat() {
        int random = NOT_INITIALIZED;
        do {
            random = getNewNumber();
        } while (random == lastRandom);
        return random;
    }

    private void store(int value) {
        SortMethod.lastRandom = value;
    }

    private int getNewNumber() {
        int random = new Random().nextInt(maxNumberRandom() + 1);
        if (random <= minNumberRandom()) {
            random = minNumberRandom();
        }
        if (random > maxNumberRandom()) {
            random = maxNumberRandom();
        }
        return random;
    }

    protected abstract T getValueBy(int random);

    protected abstract int maxNumberRandom();

    protected abstract int minNumberRandom();
}
