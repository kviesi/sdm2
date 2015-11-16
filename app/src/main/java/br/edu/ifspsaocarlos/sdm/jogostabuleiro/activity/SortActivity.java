package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;

public class SortActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
    }

    public void onChooseDice(View v) {
        Intent dicesIntent = new Intent(this, DiceActivity.class);
        startActivity(dicesIntent);
    }
}
