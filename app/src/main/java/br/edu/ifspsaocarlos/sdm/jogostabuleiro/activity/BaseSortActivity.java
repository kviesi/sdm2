package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;

/**
 * Created by viesi on 14/11/15.
 */
public abstract class BaseSortActivity extends Activity {

    protected ImageView sortImageViewOne;
    protected ImageView sortImageViewTwo;

    protected TextView playerOneTextView;
    protected TextView playerTwoTextView;

    protected Button playButton;

    protected String playerOneName;
    protected String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_sort);

        sortImageViewOne = (ImageView) findViewById(R.id.faceimageview1);
        sortImageViewTwo = (ImageView) findViewById(R.id.faceimageview2);

        playerOneTextView = (TextView) findViewById(R.id.player1);
        playerTwoTextView = (TextView) findViewById(R.id.player2);

        playButton = (Button) findViewById(R.id.play);

        //recupera dados da tela de selecionar sorteio
        Intent sortIntent = getIntent();
        playerOneName = sortIntent.getCharSequenceExtra(SortActivity.PLAYER_ONE_NAME).toString();
        playerTwoName = sortIntent.getCharSequenceExtra(SortActivity.PLAYER_TWO_NAME).toString();

        this.onClickPlayButton(playButton);
    }


    //mosta empate na tela
    protected void setNoWinner() {
        playerOneTextView.setText(playerOneName);
        playerTwoTextView.setText(playerTwoName);
        playerTwoTextView.setTextColor(Color.GREEN);
        playerOneTextView.setTextColor(Color.GREEN);
    }

    //exibe jogador um como vencedor
    protected void setPlayerOneWinner() {
        winLabel(playerOneTextView, playerOneName);
        loseLabel(playerTwoTextView, playerTwoName);
    }

    //exibe jogador dois como vencedor
    protected void setPlayerTwoWinner() {
        winLabel(playerTwoTextView, playerTwoName);
        loseLabel(playerOneTextView, playerOneName);
    }

    public void onClickPlayButton(View v) {
        clickPlayButtonHandle(v);
        playButton.setText(R.string.playAgain);
    }

    //exibe o nome do jogador com a label de vencedor
    protected void winLabel(TextView textView, String player) {
        textView.setText(player + " " + getResources().getString(R.string.winner));
        textView.setTextColor(Color.BLUE);
    }

    //exibe o nome do jogador com a label de perdedor
    protected void loseLabel(TextView textView, String player) {
        textView.setText(player + " " + getResources().getString(R.string.looser));
        textView.setTextColor(Color.RED);
    }

    //classes filhas herdam e implementam esse metodo (template method)
    protected abstract void clickPlayButtonHandle(View view);

}
