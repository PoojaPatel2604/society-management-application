package com.example.dell.miniproject;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


public class ownerslist_admin extends Fragment {
    private static final String TAG = "ownerslist_admin";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<regis> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private registerAdapter recycler;
    DatabaseReference reference=FirebaseDatabase.getInstance().getReference("regis");



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private notification_admin.OnFragmentInteractionListener mListener;

    public ownerslist_admin() {

    }

     public static ownerslist_admin newInstance(String param1, String param2) {
        ownerslist_admin fragment = new ownerslist_admin();
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

        View view=inflater.inflate(R.layout.fragment_ownerslist_admin,container,false);

      recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_owner);


      reference.child("Owners").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


              for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
              regis userdetails = dataSnapshot1.getValue(regis.class);
              regis listdata = new regis(R.drawable.usergreen);

              String name=userdetails.getname();
              String contact=userdetails.getcontact();
              String flatno=userdetails.getflatno();


              listdata.setname(name);
              listdata.setflatno(flatno);
              listdata.setcontact(contact);

              list.add(listdata);
              Log.d(TAG, "onDataChange: "+listdata);
          }
              recycler = new registerAdapter(list);

              RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);


          }
          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }

          });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

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
