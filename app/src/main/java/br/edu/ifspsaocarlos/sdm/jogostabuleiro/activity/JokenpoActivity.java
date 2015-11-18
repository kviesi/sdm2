package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.graphics.Color;
import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Jokenpo;

//Responsavel pelo jokenpo
public class JokenpoActivity extends BaseSortActivity {

    public void clickPlayButtonHandle(View v) {
        Jokenpo.Item itemOne = Jokenpo.newInstance().play();
        Jokenpo.Item itemTwo = Jokenpo.newInstance().play();

        faceImageViewOne.setImageResource(getImageIDBy(itemOne));
        faceImageViewTwo.setImageResource(getImageIDBy(itemTwo));

        if (itemOne == itemTwo) {
            setNoWinner();
        } else if (itemOne.win(itemTwo)) {
            setPlayerOneWinner();
        } else {
          setPlayerTwoWinner();
        }
    }

    private int getImageIDBy(Jokenpo.Item item) {
        if (item == Jokenpo.Item.PAPER) {
            return R.drawable.jkphand;
        } else if (item == Jokenpo.Item.ROCK) {
            return R.drawable.jkprock;
        } else if (item == Jokenpo.Item.SCISSORS) {
            return R.drawable.jkpscissors;
        }
        throw new IllegalArgumentException("Item image not mapped.");
    }
}