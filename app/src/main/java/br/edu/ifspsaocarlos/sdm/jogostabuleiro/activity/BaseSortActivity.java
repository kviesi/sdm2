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

    protected String playerOneName;
    protected String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_sort);

        faceImageViewOne = (ImageView) findViewById(R.id.faceimageview1);
        faceImageViewTwo = (ImageView) findViewById(R.id.faceimageview2);

        playerOneTextView = (TextView) findViewById(R.id.player1);
        playerTwoTextView = (TextView) findViewById(R.id.player2);

        playButton = (Button) findViewById(R.id.play);

        //playerOneName = savedInstanceState.getString(SortActivity.PLAYER_ONE_NAME);
        //playerTwoName = savedInstanceState.getString(SortActivity.PLAYER_TWO_NAME);

        this.onClickPlayButton(playButton);
    }

    protected void setNoWinner() {
        playerOneTextView.setText(playerOneName);
        playerTwoTextView.setText(playerTwoName);
        playerTwoTextView.setTextColor(Color.GREEN);
        playerOneTextView.setTextColor(Color.GREEN);
    }

    protected void setPlayerOneWinner() {
        playerOneTextView.setText(playerOneName + " venceu!");
        playerOneTextView.setTextColor(Color.BLUE);
        playerTwoTextView.setText(playerTwoName + " perdeu!");
        playerTwoTextView.setTextColor(Color.RED);
    }

    protected void setPlayerTwoWinner() {
        playerTwoTextView.setText(playerTwoName + " venceu!");
        playerTwoTextView.setTextColor(Color.BLUE);
        playerOneTextView.setText(playerOneName + " perdeu!");
        playerOneTextView.setTextColor(Color.RED);
    }

    public void onClickPlayButton(View v) {
        clickPlayButtonHandle(v);
        playButton.setText(R.string.playAgain);
    }

    protected abstract void clickPlayButtonHandle(View view);

}
