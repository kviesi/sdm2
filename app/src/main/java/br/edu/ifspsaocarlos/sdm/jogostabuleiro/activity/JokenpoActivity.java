package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Jokenpo;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Historic;

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

            //armazena vencedor no historico
            Historic.store(new Historic.RankItem(playerOneName, playerTwoName, Historic.RankItem.Type.JOKEMPO));

            setPlayerOneWinner();
        } else {

            //armazena vencedor no historico
            Historic.store(new Historic.RankItem(playerTwoName, playerOneName, Historic.RankItem.Type.JOKEMPO));

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