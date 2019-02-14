package com.example.vianelis.project4.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.vianelis.project4.Database.DBAdaptor;
import com.example.vianelis.project4.MainActivity;
import com.example.vianelis.project4.R;

public class Player extends Fragment implements AdapterView.OnItemClickListener {

    private DBAdaptor playerDB;
    private MainActivity playerMain;
    private ListView playerListOne;
    private ListView playerListTwo;
    private TextView playerOneTitle;
    private TextView playerTwoTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        playerListOne = (ListView) view.findViewById(R.id.player_one_list_view);
        playerListTwo = (ListView) view.findViewById(R.id.player_two_list_view);
        playerOneTitle = (TextView) view.findViewById(R.id.player_one_title);
        playerTwoTitle = (TextView) view.findViewById(R.id.player_two_title);

        playerListOne.setOnItemClickListener(this);
        playerListTwo.setOnItemClickListener(this);
        playerDB = new DBAdaptor(getActivity());
        playerMain = ((MainActivity) getActivity());
        loadPlayerData();
        return view;
    }

    private void loadPlayerData() {
        Cursor c = playerDB.getAllRows();
        String[] fromFieldNames = new String[] {"name"};
        int[] toViewIds = new int[] {R.id.playerName};
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(this.getActivity(), R.layout.fragment_player_item, c, fromFieldNames, toViewIds);
        playerListOne.setAdapter(myCursorAdaptor);
        playerListTwo.setAdapter(myCursorAdaptor);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.player_one_list_view:
                String firstItem = ((TextView) view.findViewById(R.id.playerName)).getText().toString();
                playerOneTitle.setText(String.format("Player 1: %s", firstItem));
                playerMain.setPlayer(1, firstItem);
                break;
            case R.id.player_two_list_view:
                String secondItem = ((TextView) view.findViewById(R.id.playerName)).getText().toString();
                playerTwoTitle.setText(String.format("Player 2: %s", secondItem));
                playerMain.setPlayer(2, secondItem);
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
