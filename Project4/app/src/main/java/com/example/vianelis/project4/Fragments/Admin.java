package com.example.vianelis.project4.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vianelis.project4.Database.DBAdaptor;
import com.example.vianelis.project4.R;

public class Admin extends Fragment implements View.OnClickListener {

    private DBAdaptor adminDB;
    private EditText adminAdd;
    private EditText adminRemove;
    private TextView adminAddStatus;
    private TextView adminRemoveStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        adminAdd = (EditText) view.findViewById(R.id.admin_add_input);
        adminRemove = (EditText) view.findViewById(R.id.admin_remove_input);
        adminAddStatus = (TextView) view.findViewById(R.id.admin_add_status);
        adminRemoveStatus = (TextView) view.findViewById(R.id.admin_remove_status);
        Button adminAddSubmit = (Button) view.findViewById(R.id.admin_add_submit);
        Button adminRemoveSubmit = (Button) view.findViewById(R.id.admin_remove_submit);

        adminAddSubmit.setOnClickListener((View.OnClickListener) this);
        adminRemoveSubmit.setOnClickListener((View.OnClickListener) this);
        adminDB = new DBAdaptor(getActivity());
        return view;
    }

    public void addPlayer() {
        if (adminAdd.getText().toString().isEmpty()) {
            adminAddStatus.setText(R.string.fragment_admin_status_blank);
        } else {
            boolean isInserted = adminDB.insertData(adminAdd.getText().toString(), "0", "0", "0");
            if (isInserted) {
                adminAddStatus.setText(R.string.fragment_admin_status_success);
            } else {
                adminAddStatus.setText(R.string.fragment_admin_status_failed);
            }
            adminDB.closeDB();
        }
    }

    public void removePlayer() {
        if (adminRemove.getText().toString().isEmpty()) {
            adminRemoveStatus.setText(R.string.fragment_admin_status_blank);
        } else {
            boolean isRemoved = adminDB.deleteData(adminRemove.getText().toString());
            if (isRemoved) {
                adminRemoveStatus.setText(R.string.fragment_admin_status_success);
            } else {
                adminRemoveStatus.setText(R.string.fragment_admin_status_failed);
            }
            adminDB.closeDB();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admin_add_submit:
                addPlayer();
                break;
            case R.id.admin_remove_submit:
                removePlayer();
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
