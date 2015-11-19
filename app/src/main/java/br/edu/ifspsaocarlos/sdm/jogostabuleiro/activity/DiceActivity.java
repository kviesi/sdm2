package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Dice;

//Responsavel pelos sorteio com dados
public class DiceActivity extends BaseSortActivity {

    public void clickPlayButtonHandle(View v) {
        //Recupera uma face do lado aleatoria para cada jogador
        Dice.Face playOneFace = Dice.newInstance().play();
        Dice.Face playTwoFace = Dice.newInstance().play();

        //exibe uma imagem de acordo com a face sorteada
        sortImageViewOne.setImageResource(getImageIDBy(playOneFace));
        sortImageViewTwo.setImageResource(getImageIDBy(playTwoFace));

        //verifica quem ganhou ou empatou
        if (playOneFace == playTwoFace) {
            setNoWinner();
        } else if (playOneFace.win(playTwoFace)) {
            setPlayerOneWinner();
        } else {
            setPlayerTwoWinner();
        }
    }

    private int getImageIDBy(Dice.Face face) {
        if (face == Dice.Face.ONE) {
            return R.drawable.faceone;
        } else if (face == Dice.Face.TWO) {
            return R.drawable.facetwo;
        } else if (face == Dice.Face.THREE) {
            return R.drawable.facethee;
        } else if (face == Dice.Face.FOUR) {
            return R.drawable.facefour;
        } else if (face == Dice.Face.FIVE) {
            return R.drawable.facefive;
        } else if (face == Dice.Face.SIX) {
            return R.drawable.facesix;
        } else {
            throw new IllegalArgumentException("Face image not mapped.");
        }
    }

}
