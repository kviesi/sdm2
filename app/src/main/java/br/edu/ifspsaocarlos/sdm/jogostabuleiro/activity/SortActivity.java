package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;

public class SortActivity extends Activity {

    public static final String PLAYER_ONE_NAME = "playerOneName";
    public static final String PLAYER_TWO_NAME = "playerTwoName";

    private TextView playerOne;
    private TextView playerTwo;

    private EditText playerOneEditText;
    private EditText playerTwoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        playerOne = (TextView) findViewById(R.id.player1input);
        playerTwo = (TextView) findViewById(R.id.player2input);

        playerOneEditText = (EditText) findViewById(R.id.player1edit);
        playerTwoEditText = (EditText) findViewById(R.id.player2edit);
    }

    //Usuario seleciona sortear com dados
    public void onChooseDice(View v) {
        Intent dicesIntent = new Intent(this, DiceActivity.class);
        if (tryPutExtras(dicesIntent)) {
            startActivity(dicesIntent);
        }
    }

    //Usuario seleciona sortear com jokempo
    public void onChooseJokenpo(View v) {
        Intent jokenpoIntent = new Intent(this, JokenpoActivity.class);
        if (tryPutExtras(jokenpoIntent)) {
            startActivity(jokenpoIntent);
        }
    }

    //Recupera os dados do input dos nomes do jogadores, verifica se possuem valor e caso sim,
    //coloca o valor nos extras da intent de sorteio.
    public boolean tryPutExtras(Intent intent) {
        String playerTwoName = playerTwoEditText.getText().toString();
        String playerOneName = playerOneEditText.getText().toString();

        boolean hasError = false;

        if (isBlank(playerOneName)) {
            displayError(playerOne, R.string.nameRequired);
            hasError = true;
        } else {
            resetLabel(playerOne, R.string.playerOne);
        }

        if (isBlank(playerTwoName)) {
            displayError(playerTwo, R.string.nameRequired);
            hasError = true;
        } else {
            resetLabel(playerTwo, R.string.playerTwo);
        }

        if (hasError) {
            return false;
        }

        intent.putExtra(PLAYER_ONE_NAME, playerOneName);
        intent.putExtra(PLAYER_TWO_NAME, playerTwoName);
        return true;
    }

    //Mostra mensagem de erro caso nao seja informado valor para campo texto.
    private void displayError(TextView textView, int messageID) {
        if (!textView.getText().toString().contains("-")) {
            textView.setText(textView.getText() + " - " + getResources().getString(messageID));
            textView.setTextColor(Color.RED);
        }
    }

    //Volta a label para estado inicial
    private void resetLabel(TextView textView, int labelID) {
        textView.setText(getResources().getString(labelID));
        textView.setTextColor(Color.BLACK);
    }

    //Verifica se nao eh nulo ou vazio apenas com espaco.
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
