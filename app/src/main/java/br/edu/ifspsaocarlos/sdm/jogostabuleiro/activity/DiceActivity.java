package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;


import android.graphics.Color;
import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Dice;

//Responsavel pelos dados
public class DiceActivity extends BaseSortActivity {

    public void onClickPlayButton(View v) {
        Dice.Face playOneFace = Dice.newInstance().play();
        Dice.Face playTwoFace = Dice.newInstance().play();

        faceImageViewOne.setImageResource(getImageIDBy(playOneFace));
        faceImageViewTwo.setImageResource(getImageIDBy(playTwoFace));

        if (playOneFace.number() > playTwoFace.number()) {
            setPlayerOneWinner();
        } else if (playOneFace.number() < playTwoFace.number()) {
            setPlayerTwoWinner();
        } else {
            setNoWinner();;
        }

        playButton.setText("Jogar novamente!");
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
