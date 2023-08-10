package com.example.ktlab6_ph26503;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ktlab6_ph26503.Adapter.Adapter_SP;
import com.example.ktlab6_ph26503.DAO.DAO_SP;
import com.example.ktlab6_ph26503.DTO.SP;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class ServiceFragment extends Fragment {
    Context context;
    private RecyclerView rcv_list_sp;
    private FloatingActionButton fab_add_sp;
    ArrayList<SP> listSP;
    DAO_SP dao_sp;
    Adapter_SP adapter_sp;
    String TAG = "zzzzzzzzzzzz";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        context = getContext();
        ((AppCompatActivity)getActivity ()).getSupportActionBar ().setTitle ("Quản lý dịch vụ");

        dao_sp = new DAO_SP(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        rcv_list_sp = view.findViewById(R.id.rcv_list_service);
        rcv_list_sp.setLayoutManager(new LinearLayoutManager(getActivity()));
        dao_sp= new DAO_SP(context);
        dao_sp.opend();
        listSP = new ArrayList<>();
        listSP = dao_sp.selectAll();
        adapter_sp = new Adapter_SP(listSP, dao_sp);
        rcv_list_sp.setAdapter(adapter_sp);
        fab_add_sp = view.findViewById(R.id.fab_add_sp);

        fab_add_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View viewdialog = inflater.inflate(R.layout.dialog_add_sp, null);
                dialog.setView(viewdialog);
                TextInputLayout input_name, input_price, input_date;
                input_name = viewdialog.findViewById(R.id.input_sp_ADDSP);
                input_price = viewdialog.findViewById(R.id.input_price_ADDSP);
                input_date = viewdialog.findViewById(R.id.input_dateADDSP);

                AlertDialog alertDialog = dialog.create();

                MaterialButton btn_add_dialog = viewdialog.findViewById(R.id.btn_ADDSP);

                btn_add_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SP sp = new SP();
                        sp.setContent(input_name.getEditText().getText().toString());
                        sp.setPrice(Integer.parseInt(input_price.getEditText().getText().toString()));
                        sp.setDate(input_date.getEditText().getText().toString());


                        long ketqua = dao_sp.AddSP(sp);
                        if (ketqua > 0) {
                            listSP.clear();
                            listSP.addAll(dao_sp.selectAll());
                            adapter_sp.notifyDataSetChanged();

                            alertDialog.dismiss();

                            Toast.makeText(getActivity(), "Them moi thanh cong" + ketqua, Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getActivity(), "Them moi that bai" + ketqua, Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();

                listSP = dao_sp.selectAll();

                adapter_sp = new Adapter_SP(listSP, dao_sp);
                rcv_list_sp.setAdapter(adapter_sp);
            }
        });
    }


}