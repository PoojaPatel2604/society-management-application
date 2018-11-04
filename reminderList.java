package com.example.dell.miniproject;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class reminderList extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG ="reminderList";
    private String mParam1;
    private String mParam2;

    private List<reminder> list = new ArrayList<>();
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    private reminderAdapter recycler;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reminder");
    private OnFragmentInteractionListener mListener;

    public reminderList() {

    }

    public static reminderList newInstance(String param1, String param2) {
        reminderList fragment = new reminderList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_reminder_list, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_reminder);

        reference.child("Messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    reminder n = dataSnapshot1.getValue(reminder.class);
                    reminder listdata = new reminder(R.drawable.usergreen);
                    String n01 = n.getRemind();
                    listdata.setRemind(n01);
                    list.add(listdata);
                    Log.d(TAG, "onDataChange: " + n.getRemind());
                    recycler = new reminderAdapter(list);
                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);
            }@Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
                    return view;    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
