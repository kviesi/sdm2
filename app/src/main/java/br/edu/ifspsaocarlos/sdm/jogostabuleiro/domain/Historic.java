package br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by viesi on 26/11/15.
 */
public class Historic {

    private static final int LIMIT = 50;

    private static final ArrayList<RankItem> rankMemory = new ArrayList<>();

    public static void store(RankItem rankResult) {
        if(rankResult != null && rankMemory.size() <= LIMIT) {
            rankMemory.add(rankResult);
        }
    }

    public static Collection<RankItem> getAllRank() {
        return rankMemory;
    }

    public static void clear() {
        rankMemory.clear();
    }

    public static class RankItem {

        private String nameWinner;
        private String nameLooser;
        private Type type;

        public RankItem(String nameWinner, String nameLooser, Type type) {
            this.nameWinner = nameWinner;
            this.nameLooser = nameLooser;
            this.type = type;
        }

        public String getNameWinner() {
            return nameWinner;
        }

        public String getNameLooser() {
            return nameLooser;
        }

        public Type getType() {
            return type;
        }

        public enum Type {
            JOKEMPO,
            DICES;

        }

    }


}
