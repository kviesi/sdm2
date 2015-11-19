package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import java.util.Random;

/**
 * Created by viesi on 14/11/15.
 */
public class Dice extends SortMethod<Dice.Face> {

    public static Dice newInstance() {
        return new Dice();
    }

    @Override
    protected Face getValueBy(int random) {
        return Face.getBy(random);
    }

    @Override
    protected int maxNumberRandom() {
        return 6;
    }

    @Override
    protected int minNumberRandom() {
        return 1;
    }

    //Representa a face de um dado
    public enum Face {

        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6);

        private int number;

        private Face(int number) {
            this.number = number;
        }

        public static Face getBy(int number) {
            if (number == 1) {
                return ONE;
            } else if (number == 2) {
                return TWO;
            } else if (number == 3) {
                return THREE;
            } else if (number == 4) {
                return FOUR;
            } else if (number == 5) {
                return FIVE;
            } else if (number == 6) {
                return SIX;
            } else {
                throw new IllegalArgumentException("Face not exist to number.");
            }
        }

        public int number() {
            return number;
        }

        //dado uma face verifica se a atual eh maior que ela.
        public boolean win(Face o) {
            return this.number() > o.number();
        }
    }

}
