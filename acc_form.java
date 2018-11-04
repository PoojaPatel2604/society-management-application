package com.example.dell.miniproject;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class acc_form extends Fragment {

    private  static final String TAG="acc_form";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FirebaseDatabase database;
    DatabaseReference ref;
    private FirebaseAuth auth;
    Button b1;
    acc_details ad;
    public EditText accname,accflat,acccontact,accamount,accchequeno,accdate;
    public String ownersname,ownersflatno,ownerscontact,ownersamount,ownerschequeno,ownersdate;

    private OnFragmentInteractionListener mListener;

    public acc_form() {
            }

    public static acc_form newInstance(String param1, String param2) {
        acc_form fragment = new acc_form();
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

        View view=inflater.inflate(R.layout.fragment_acc_form, container, false);

        auth = FirebaseAuth.getInstance();

        accname=(EditText)view.findViewById(R.id.owner_name);
        accflat=(EditText)view.findViewById(R.id.owner_flatno);
        acccontact=(EditText)view.findViewById(R.id.owner_contact);
        accamount=(EditText)view.findViewById(R.id.amount);
        accchequeno=(EditText)view.findViewById(R.id.cheque_no);
        accdate=(EditText)view.findViewById(R.id.payment_date);
        b1=(Button)view.findViewById(R.id.submit_form);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Maintenance");
        ad=new acc_details(R.drawable.usergreen);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ownersname=(accname).getText().toString().trim();
                ownersflatno=(accflat).getText().toString().trim();
                ownerscontact=(acccontact).getText().toString().trim();
                ownersamount=(accamount).getText().toString().trim();
                ownerschequeno=(accchequeno).getText().toString().trim();
                ownersdate=(accdate).getText().toString().trim();

                ad.setOwnersname(accname.getText().toString());
                ad.setOwnnersflatno(accflat.getText().toString());
                ad.setOwnerscontact(acccontact.getText().toString());
                ad.setOwnerschequeno(accchequeno.getText().toString());
                ad.setOwnersamount(accamount.getText().toString());
                ad.setOwnersdate(accchequeno.getText().toString());

                DatabaseReference newRef = ref.child("Records");
                newRef.push().setValue(ad);

                if (TextUtils.isEmpty(ownersname)) {
                    Toast.makeText(getActivity(), "Enter Owner's Name !", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(ownersflatno)) {
                    Toast.makeText(getActivity(), "Enter Owner's Flat No !", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(ownerscontact)) {
                    Toast.makeText(getActivity(), "Enter Owner's Contact No !", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(ownersamount)) {
                    Toast.makeText(getActivity(), "Enter Amount !", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(ownerschequeno)) {
                    Toast.makeText(getActivity(), "Enter Cheque No !", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(ownersdate)) {
                    Toast.makeText(getActivity(), "Enter Date !", Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(getActivity(), "Details submitted ", Toast.LENGTH_LONG).show();

            }
        });
        return view;
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
       void onFragmentInteraction(Uri uri);
    }
}
