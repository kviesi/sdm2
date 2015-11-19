package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

/**
 * Created by viesi on 16/11/15.
 */
public class Jokenpo extends SortMethod<Jokenpo.Item> {

    public static Jokenpo newInstance() {
        return new Jokenpo();
    }

    @Override
    protected Item getValueBy(int random) {
        if (random == 0) {
            return Item.ROCK;
        } else if (random == 1) {
            return Item.PAPER;
        } else if (random == 2) {
            return Item.SCISSORS;
        }
        throw new IllegalArgumentException("Item not mapped.");
    }

    @Override
    protected int minNumberRandom() {
        return 0;
    }

    @Override
    protected int maxNumberRandom() {
        return 2;
    }

    //Gestos do jogo
    public enum Item {

        ROCK{
            @Override
            public boolean win(Item o) {
                if(o == SCISSORS) {
                    return true;
                }
                return false;
            }
        },
        PAPER{
            @Override
            public boolean win(Item o) {
                if(o == ROCK) {
                    return true;
                }
                return false;
            }
        },
        SCISSORS{
            @Override
            public boolean win(Item o) {
                if(o == PAPER) {
                    return true;
                }
                return false;
            }
        };

        //Dado um item, verifica se o atual ganha dele.
        public abstract boolean win(Item o);

    }

}
