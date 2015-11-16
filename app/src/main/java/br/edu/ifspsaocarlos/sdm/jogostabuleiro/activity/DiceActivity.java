package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Dice;

public class DiceActivity extends BaseSortActivity {

    private ImageView faceImageViewOne;
    private ImageView faceImageViewTwo;

    private TextView playerOneTextView;
    private TextView playerTwoTextView;

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        faceImageViewOne = (ImageView) findViewById(R.id.faceimageview1);
        faceImageViewTwo = (ImageView) findViewById(R.id.faceimageview2);

        playerOneTextView = (TextView) findViewById(R.id.player1);
        playerTwoTextView = (TextView) findViewById(R.id.player2);

        playButton = (Button) findViewById(R.id.playbutton);

        this.onClickPlayButton(playButton);
    }

    public void onClickPlayButton(View v) {
        Dice.Face playOneFace = Dice.newInstance().play();
        Dice.Face playTwoFace = Dice.newInstance().play();

        faceImageViewOne.setImageResource(getImageIDBy(playOneFace));
        faceImageViewTwo.setImageResource(getImageIDBy(playTwoFace));

        if(playOneFace.number() > playTwoFace.number()) {
            playerOneTextView.setText("Jogador 1 venceu!");
            playerOneTextView.setTextColor(Color.BLUE);
            playerTwoTextView.setText("Jogador 2 perdeu!");
            playerTwoTextView.setTextColor(Color.RED);
        } else if(playOneFace.number() < playTwoFace.number()) {
            playerOneTextView.setText("Jogador 1 perdeu!");
            playerOneTextView.setTextColor(Color.RED);
            playerTwoTextView.setText("Jogador 2 venceu!");
            playerTwoTextView.setTextColor(Color.BLUE);
        } else {
            playerOneTextView.setText("Jogador 1");
            playerTwoTextView.setText("Jogador 2");
            playerTwoTextView.setTextColor(Color.BLUE);
            playerOneTextView.setTextColor(Color.BLUE);
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
