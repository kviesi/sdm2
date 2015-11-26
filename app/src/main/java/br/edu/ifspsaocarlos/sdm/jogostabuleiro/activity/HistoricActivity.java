package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.app.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.Historic;

public class HistoricActivity extends Activity {

    private ListView historicListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        historicListView = (ListView) findViewById(R.id.historicListView);

        this.populeHistoric();
     }

    //Popula lista com dados do historico gravado.
    public void populeHistoric() {

        Collection<Historic.RankItem> items = Historic.getAllRank();

        String[] historic = null;
        if(items.size() == 0) {
            historic = new String[1];
            historic[0] = getResources().getString(R.string.historic_empty);
        } else {
            ArrayList<String> itemsRank = new ArrayList<>();
            for (Historic.RankItem rankItem : items) {
                itemsRank.add(createMessage(rankItem));
            }
            historic  = itemsRank.toArray(new String[itemsRank.size()]);
        }

        historicListView.setAdapter(new ArrayAdapter<String>(this, R.layout.historic_item, historic));
    }

    //Cria mensagem para lista do historico.
    private String createMessage(Historic.RankItem rankItem) {
        return rankItem.getNameWinner() + " " + getResources().getString(R.string.historic_win) + " " + rankItem.getNameLooser() + " " + getResources().getString(R.string.historic_in) + " " + getSortBy(rankItem.getType());
    }

    //Recupera nome do tipo de sorteio.
    private String getSortBy(Historic.RankItem.Type type) {
        if(type == Historic.RankItem.Type.DICES) {
            return getResources().getString(R.string.historic_dices);
        } else if(type == Historic.RankItem.Type.JOKEMPO) {
            return getResources().getString(R.string.historic_jokempo);
        }
        return getResources().getString(R.string.historic_sort);
    }

}
