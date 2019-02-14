package com.example.vianelis.project4.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.vianelis.project4.Database.DBAdaptor;
import com.example.vianelis.project4.R;

public class Scoreboard extends Fragment {

    private DBAdaptor scoreDB;
    private ListView boardList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        boardList = (ListView) view.findViewById(R.id.board_stats);
        scoreDB = new DBAdaptor(getActivity());
        loadBoardData();
        return view;
    }

    private void loadBoardData() {
        Cursor c = scoreDB.getAllRows();
        String[] fromFieldNames = new String[] {"name","wins","losses","ties"};
        int[] toViewIds = new int[] {R.id.boardName, R.id.boardWins, R.id.boardLosses, R.id.boardTies};
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(this.getActivity(), R.layout.fragment_stats, c, fromFieldNames, toViewIds);
        boardList.setAdapter(myCursorAdaptor);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
