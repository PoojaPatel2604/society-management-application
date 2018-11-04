package com.example.dell.miniproject;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class acc_reminder extends Fragment {

    private static final String TAG = "acc_reminder";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int NOTIFICATION_ID = 1;

    private String mParam1;
    private String mParam2;

    private List<reminder> list = new ArrayList<>();
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    private reminderAdapter recycler;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reminder");
    Button b1;

    reminder notification1;
    public EditText adminnotify;
    String n;

    private NotificationManager mNotificationManager;


    private OnFragmentInteractionListener mListener;

    public acc_reminder() {

    }

    public static acc_reminder newInstance(String param1, String param2) {
        acc_reminder fragment = new acc_reminder();
        fragment.setRetainInstance(true);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context
                .NOTIFICATION_SERVICE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acc_reminder, container, false);
        adminnotify = (EditText) view.findViewById(R.id.acc_msg);

        b1 = (Button) view.findViewById(R.id.btn_acc_msg);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_reminder);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Reminder");
        notification1 = new reminder(R.drawable.usergreen);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n = adminnotify.getText().toString().trim();
                notification1.setRemind(adminnotify.getText().toString());
                //getValues();
                DatabaseReference newRef = reference.child("Messages");
                newRef.push().setValue(notification1);

                if(TextUtils.isEmpty(n)){
                    Toast.makeText(getActivity(),"Enter your Message",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Notification sent to all Owners", Toast.LENGTH_LONG).show();
                mNotificationManager.notify(NOTIFICATION_ID, createNotification());
                //Toast.makeText(getActivity(), "Show Notification clicked", Toast.LENGTH_SHORT).show();


            }
        });

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private Notification createNotification() {
        Notification.Builder notificationBuilder = new Notification.Builder(getActivity())
                .setSmallIcon(R.drawable.ic_menu_send)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("1 new Notification ! ")
                .setContentText("Click to view ");

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //push.setClass(getActivity(), SplashPage.class);

        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(getActivity(), 0,
                push, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationBuilder
                .setFullScreenIntent(fullScreenPendingIntent, true);
        return notificationBuilder.build();
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
