package com.example.vianelis.project4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vianelis.project4.Database.DBAdaptor;
import com.example.vianelis.project4.MainActivity;
import com.example.vianelis.project4.R;

public class Gameboard extends Fragment implements View.OnClickListener {

    private DBAdaptor gameDB;
    private MainActivity gameMain;
    private TextView playerOne;
    private TextView playerTwo;
    private Button playerOneWin;
    private Button playerTwoWin;
    private Button playerTie;
    private ViewPager viewPager;
    private String getPlayerOne;
    private String getPlayerTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gameboard, container, false);

        playerOne = (TextView) view.findViewById(R.id.game_player_one);
        playerTwo = (TextView) view.findViewById(R.id.game_player_two);
        playerOneWin = (Button) view.findViewById(R.id.player_one_win);
        playerTwoWin = (Button) view.findViewById(R.id.player_two_win);
        playerTie = (Button) view.findViewById(R.id.player_tie);

        gameDB = new DBAdaptor(getActivity());
        gameMain = ((MainActivity) getActivity());
        viewPager = (ViewPager) getActivity().findViewById(R.id.container);

        getPlayerOne = gameMain.getPlayer(1);
        getPlayerTwo = gameMain.getPlayer(2);
        loadGameData();

        playerOneWin.setOnClickListener(this);
        playerTwoWin.setOnClickListener(this);
        playerTie.setOnClickListener(this);

        /* Return view. */
        return view;
    }

    public void loadGameData() {

        if (getPlayerOne.equals("none") && getPlayerTwo.equals("none")) {
            playerOne.setText(R.string.fragment_game_player_select_msg);
            playerTwo.setText(R.string.fragment_game_player_select_msg);
        } else {
            playerOne.setText(getPlayerOne);
            playerTwo.setText(getPlayerTwo);
            playerOneWin.setText(String.format("%s Wins!", getPlayerOne));
            playerTwoWin.setText(String.format("%s Wins!", getPlayerTwo));
            playerTie.setText(R.string.fragment_game_player_tie);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onClick(View v) {
        if (!(getPlayerOne.equals("none") && getPlayerTwo.equals("none"))) {
            switch (v.getId()) {
                case R.id.player_one_win:
                    gameDB.updateData(getPlayerOne, true, false, false);
                    gameDB.updateData(getPlayerTwo, false, true, false);
                    gameDB.closeDB();
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.player_two_win:
                    gameDB.updateData(getPlayerOne, false, true, false);
                    gameDB.updateData(getPlayerTwo, true, false, false);
                    gameDB.closeDB();
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.player_tie:
                    gameDB.updateData(getPlayerOne, false, false, true);
                    gameDB.updateData(getPlayerTwo, false, false, true);
                    gameDB.closeDB();
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}
