package com.example.ktlab6_ph26503;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ktlab6_ph26503.DAO.DAO_SP;

public class InfoFragment extends Fragment {
    Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        context = getContext();
        ((AppCompatActivity)getActivity ()).getSupportActionBar ().setTitle ("Thông tin");


        return view;
    }
}