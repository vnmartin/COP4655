package com.example.vianelis.project2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int[][] Status = new int [3][3];
    private int[] FlatStatus = new int[9];
    private int roundCount;
    private TextView tictactoe;
    private Button b00;
    private Button b01;
    private Button b02;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b20;
    private Button b21;
    private Button b22;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tictactoe = findViewById(R.id.tictactoe);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonID = "b" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.newgame);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v){
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button) v).setText("O");
        }
        else {
            ((Button) v).setText("X");
        }

        roundCount++;

        if (roundCount == 9){
            draw();
        }
        else if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else{
                player2Wins();
            }
        }
        else{
            player1Turn = !player1Turn;
            if(player1Turn){
                tictactoe.setText("Player 1 Turn");
            }
            else {
                tictactoe.setText("Player 2 Turn");
            }
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for(int i = 0; i <3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private void player1Wins() {
        tictactoe.setText("Player 1 Wins!");
        resetBoard();
    }

    @SuppressLint("SetTextI18n")
    private void player2Wins(){
        tictactoe.setText("Player 2 Wins!");
        resetBoard();
    }

    @SuppressLint("SetTextI18n")
    private void draw(){
        tictactoe.setText("Draw!");
        resetBoard();
    }
    private void resetBoard(){
        for(int i = 0; i <3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    @SuppressLint("SetTextI18n")
    private void resetGame() {
        tictactoe.setText("Player 1 Turn");
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString("game", tictactoe.getText().toString());
        outState.putInt("roundCount", roundCount);
        outState.putBoolean("player1Turn", player1Turn);
        outState.putString("b00", b00.getText().toString());
        outState.putString("b01", b01.getText().toString());
        outState.putString("b02", b02.getText().toString());
        outState.putString("b10", b10.getText().toString());
        outState.putString("b11", b11.getText().toString());
        outState.putString("b12", b12.getText().toString());
        outState.putString("b20", b20.getText().toString());
        outState.putString("b21", b21.getText().toString());
        outState.putString("b22", b22.getText().toString());
        for (int i=0, count=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                FlatStatus[count] = Status[i][j];
                count++;
            }
        }
        outState.putIntArray("array", FlatStatus);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);

        roundCount = inState.getInt("roundCount");
        player1Turn = inState.getBoolean("player1Turn");
        tictactoe.setText(inState.getString("tictactoe", ""));
        roundCount = inState.getInt("roundCount");
        b00.setText(inState.getString("b00", ""));
        b01.setText(inState.getString("b01", ""));
        b02.setText(inState.getString("b02", ""));
        b10.setText(inState.getString("b10", ""));
        b11.setText(inState.getString("b11", ""));
        b12.setText(inState.getString("b12", ""));
        b20.setText(inState.getString("b20", ""));
        b21.setText(inState.getString("b21", ""));
        b22.setText(inState.getString("b22", ""));
        FlatStatus = inState.getIntArray("array");
        for (int i=0, count=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                assert FlatStatus != null;
                Status[i][j] = FlatStatus[count];
                count++;
            }
        }
        super.onRestoreInstanceState(inState);
    }
}