package br.edu.ifspsaocarlos.sdm.jogostabuleiro.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import br.edu.ifspsaocarlos.sdm.jogostabuleiro.R;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.CountDown;
import br.edu.ifspsaocarlos.sdm.jogostabuleiro.domain.CountDownPlayer;

public class CountDownActivity extends AppCompatActivity {

    private CountDownPlayer player1;
    private CountDownPlayer player2;

    public static final String BACKGROUND_COLOR = "#1c484a";
    public static final String LOSE_BACKGROUND_COLOR = "#FFC4AEB4";
    public static final String LOSE_TEXT = "Perdeu!";

    private static final long START_TIME = 30 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        //Criar Player 1
        CountDown countDownPlayer1 = new CountDown(getStartTime(START_TIME)) {
            @Override
            public void onTick(long millis) {
                CountDownActivity.onTick(this, player1, millis);
            }

            @Override
            public void onFinish() {
                CountDownActivity.onFinish(player1);
            }
        };
        TextView timerPlayer1 = getTextViewPlayer1();
        player1 = new CountDownPlayer(countDownPlayer1, timerPlayer1);

        //Criar Player 2
        CountDown countDownPlayer2 = new CountDown(getStartTime(START_TIME)) {
            @Override
            public void onTick(long millis) {
                CountDownActivity.onTick(this, player2, millis);
            }

            @Override
            public void onFinish() {
                CountDownActivity.onFinish(player2);
            }
        };
        TextView timerPlayer2 = getTextViewPlayer2();
        player2 = new CountDownPlayer(countDownPlayer2, timerPlayer2);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Armazenamos as informações necessárias para recuperar o estado da tela
        //Verifica qual player estava ativo para reiniciá-lo
        if (player1.isActive()) {
            savedInstanceState.putLong("startP1", ((CountDown) player1.getCountDownTimer()).getTimeUntilFinish());
        } else {
            savedInstanceState.putLong("startP2", ((CountDown) player2.getCountDownTimer()).getTimeUntilFinish());
        }
        savedInstanceState.putBoolean("isPlayer1Active", player1.isActive());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        //Recuperamos as informações necessárias para restaurar o estado da tela
        Long startP1 = savedInstanceState.getLong("startP1");
        Long startP2 = savedInstanceState.getLong("startP2");
        boolean isPlayer1Active = savedInstanceState.getBoolean("isPlayer1Active");

        //Restaurar Contador do Jogador 1
        CountDown countDownPlayer1 = new CountDown(getStartTime(getStartTime(startP1))) {
            @Override
            public void onTick(long millis) {
                CountDownActivity.onTick(this, player1, millis);
            }

            @Override
            public void onFinish() {
                CountDownActivity.onFinish(player1);
            }
        };
        player1.setCountDownTimer(countDownPlayer1);

        //Restaurar Contador do Jogador 2
        CountDown countDownPlayer2 = new CountDown(getStartTime(getStartTime(startP2))) {
            @Override
            public void onTick(long millis) {
                CountDownActivity.onTick(this, player2, millis);
            }

            @Override
            public void onFinish() {
                CountDownActivity.onFinish(player2);
            }
        };
        player2.setCountDownTimer(countDownPlayer2);

        //Verifica qual player estava ativo para reiniciá-lo
        if (isPlayer1Active) {
            player1.start();
        } else {
            player2.start();
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Evento disparado quando o jogador 1 clicar na tela
     *
     * @param view
     */
    public void onClickPlayer1(View view) {
        cancel(player1);
        start(player2);
    }

    /**
     * Evento disparado quando o jogador 2 clicar na tela
     *
     * @param view
     */
    public void onClickPlayer2(View view) {
        cancel(player2);
        start(player1);
    }

    /**
     * Metodo responsavel por iniciar a contagem do tempo do jogador
     *
     * @param player - jogador que fara a jogada
     */
    private void start(CountDownPlayer player) {
        player.start();
        TextView view = (TextView) player.getView();
        view.setClickable(true);
        view.setBackgroundColor(Color.parseColor(BACKGROUND_COLOR));
    }

    /**
     * Metodo responsavel por parar a contagem do tempo do jogador
     *
     * @param player - jogador que fez a ultima jogada
     */
    private void cancel(CountDownPlayer player) {
        player.cancel();
        TextView view = (TextView) player.getView();
        view.setText(toHourMinuteAndSecond(START_TIME));
        view.setClickable(false);
    }

    /**
     * Evento disparado quando e atualizado o cronometro eh atualizado
     *
     * @param countDown - countDown
     * @param player    - player a ser notificado
     * @param millis    - tempo restante para conclusao do termometro
     */
    private static void onTick(CountDown countDown, CountDownPlayer player, long millis) {
        //informa o tempo até o fim do cronometro
        countDown.setTimeUntilFinish(millis);

        //Se estiver no final do cronometro, faltando 10seg., a tela ficará piscando para dar um alerta
        if (millis < 11 * 1000) {
            if ((millis / 1000) % 2 == 0) {
                player.getView().setBackgroundColor(Color.RED);
            } else {
                player.getView().setBackgroundColor(Color.parseColor(CountDownActivity.BACKGROUND_COLOR));
            }
        }

        //informar a tela que o tempo se alterou
        ((TextView) player.getView()).setText(CountDownActivity.toHourMinuteAndSecond(millis));
    }

    /**
     * Evento disparado quando conclui o tempo do cronometro
     *
     * @param player - player a ser notificado
     */
    private static void onFinish(CountDownPlayer player) {
        player.getView().setBackgroundColor(Color.parseColor(CountDownActivity.LOSE_BACKGROUND_COLOR));
        ((TextView) player.getView()).setText(LOSE_TEXT);
    }

    /**
     * Método responsável por retornar a textView que armazena o cronometro para o jogador 1
     *
     * @return - textView que armazena o cronometro
     */
    public TextView getTextViewPlayer1() {
        return (TextView) findViewById(R.id.timerPlayer1);
    }

    /**
     * Método responsável por retornar a textView que armazena o cronometro para o jogador 2
     *
     * @return - textView que armazena o cronometro
     */
    public TextView getTextViewPlayer2() {
        return (TextView) findViewById(R.id.timerPlayer2);
    }

    /**
     * Método responsável por retornar o tempo em millis de onde o cronometro deve ser iniciado
     *
     * @param start - millis de onde o tempo deve ser iniciado
     * @return - O tempo informado ou o valor default caso o valor seja nulo
     */
    private long getStartTime(Long start) {
        if (start == null || start == 0) {
            start = START_TIME;
        }
        return start;
    }

    /**
     * Método responsável por formatar o tempo em hh:mi:ss
     *
     * @param millis - tempo em milisegundo
     * @return - tempo formatado em hh:mm:ss
     */
    public static String toHourMinuteAndSecond(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }

}
