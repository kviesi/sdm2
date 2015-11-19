package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Jokenpo;

//Responsavel pelo jokenpo
public class JokenpoActivity extends BaseSortActivity {

    public void clickPlayButtonHandle(View v) {
        //Recupera uma gesto aleatorio para cada jogador
        Jokenpo.Item itemOne = Jokenpo.newInstance().play();
        Jokenpo.Item itemTwo = Jokenpo.newInstance().play();

        //mostra imagem de acordo com gesto sorteado
        sortImageViewOne.setImageResource(getImageIDBy(itemOne));
        sortImageViewTwo.setImageResource(getImageIDBy(itemTwo));

        //verifica quem ganhou ou empatou
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