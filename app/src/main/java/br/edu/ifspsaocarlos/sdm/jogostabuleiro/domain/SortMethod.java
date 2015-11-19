package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import java.util.Random;

/**
 * Created by viesi on 15/11/15.
 */
public abstract class SortMethod<T> {

    private static final int NOT_INITIALIZED = -1;

    private static int lastRandom = NOT_INITIALIZED;

    //Recupera um valor randomico para sorteio
    public final T play() {
        int newRandom = NOT_INITIALIZED;
        if (isInitialized()) { //Caso ja tenha sido jogado outras vezes
            newRandom = getNewNumberWithoutRepeat(); //garante que nao venha valores repetidos em sequencia
        } else {
            newRandom = getNewNumber(); // recupera um numero novo
        }
        store(newRandom); //armazena na memoria para verificar repeticao
        return getValueBy(newRandom); //chama metodo abstrato onde cada classe filha vai retornar um valor
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

    //Calcula numero randomico de acordo com preferencias de cada classe filha
    private int getNewNumber() {
        int random = new Random().nextInt(maxNumberRandom() + 1);
        int minRandom = minNumberRandom();
        if (random <= minRandom) {
            random = minRandom;
        }
        int maxRandom = maxNumberRandom();
        if (random > maxRandom) {
            random = maxRandom;
        }
        return random;
    }

    //Retorna tipo generico para numero sorteado
    protected abstract T getValueBy(int random);

    //Random maximo que podera ser retornado
    protected abstract int maxNumberRandom();

    //Random minimo que poderia ser retornado
    protected abstract int minNumberRandom();
}
