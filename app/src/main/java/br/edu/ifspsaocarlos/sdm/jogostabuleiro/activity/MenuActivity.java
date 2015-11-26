package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Historic;

/**
 * Created by gustavo on 17/11/15.
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
    }

    public void onClickCount(View view) {
        Intent iCount = new Intent(this, CountDownActivity.class);
        startActivity(iCount);
    }

    public void onClickSort(View view) {
        Intent iCount = new Intent(this, SortActivity.class);
        startActivity(iCount);
    }

    @Override
    protected void onDestroy() {
        Historic.clear();
        super.onDestroy();
    }
}
