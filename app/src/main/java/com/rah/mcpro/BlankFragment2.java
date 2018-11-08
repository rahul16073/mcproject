package com.rah.mcpro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BlankFragment2 extends Fragment {

    EditText rusername,rpassword,rphone;
    public BlankFragment2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final database database=new database(getActivity());
        View view=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        rusername=(EditText)view.findViewById(R.id.rusername);
        rpassword=(EditText) view.findViewById(R.id.rpassword);
        rphone=(EditText) view.findViewById(R.id.rphone);
        Button register=(Button)view.findViewById(R.id.rregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=rusername.getText().toString();
                String pass=rpassword.getText().toString();
                String phone=rphone.getText().toString();
                if(user.length()<5){
                    Toast.makeText(getActivity(),"username length should be greater than 4",Toast.LENGTH_SHORT).show();
                }
                else if(database.CheckUserRegistration(user)){
                    Toast.makeText(getActivity(),"This username have already been registered",Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<8){
                    Toast.makeText(getActivity(),"password length should be greater than 7",Toast.LENGTH_SHORT).show();
                }
                else if(rphone.length()<10 || rphone.length()>10){
                    Toast.makeText(getActivity(),"phone no. should contain only 10 digits",Toast.LENGTH_SHORT).show();
                }
                else {
                    add(database,user, pass, phone, "false");
                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
    private void add(database database,String user,String password,String phone,String blocked){
        boolean result=database.add(user,password,phone,blocked);
        if(result) {
            Toast.makeText(getActivity(),"Registered",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(),"Failed to Register",Toast.LENGTH_LONG).show();
        }
    }

}
