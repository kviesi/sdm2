package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
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

    protected ImageView faceImageViewOne;
    protected ImageView faceImageViewTwo;

    protected TextView playerOneTextView;
    protected TextView playerTwoTextView;

    protected Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        faceImageViewOne = (ImageView) findViewById(R.id.faceimageview1);
        faceImageViewTwo = (ImageView) findViewById(R.id.faceimageview2);

        playerOneTextView = (TextView) findViewById(R.id.player1);
        playerTwoTextView = (TextView) findViewById(R.id.player2);

        playButton = (Button) findViewById(R.id.play);

        this.onClickPlayButton(playButton);
    }

    protected void setNoWinner() {
        playerOneTextView.setText("Jogador 1");
        playerTwoTextView.setText("Jogador 2");
        playerTwoTextView.setTextColor(Color.BLUE);
        playerOneTextView.setTextColor(Color.BLUE);
    }

    protected void setPlayerOneWinner() {
        playerOneTextView.setText("Jogador 1 venceu!");
        playerOneTextView.setTextColor(Color.RED);
        playerTwoTextView.setText("Jogador 2 perdeu!");
        playerTwoTextView.setTextColor(Color.BLUE);
    }

    protected void setPlayerTwoWinner() {
        playerOneTextView.setText("Jogador 1 perdeu!");
        playerOneTextView.setTextColor(Color.RED);
        playerTwoTextView.setText("Jogador 2 venceu!");
        playerTwoTextView.setTextColor(Color.BLUE);
    }

    public abstract void onClickPlayButton(View v);

}
