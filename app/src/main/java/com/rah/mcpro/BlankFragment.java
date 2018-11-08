package com.rah.mcpro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class BlankFragment extends Fragment {

    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    EditText username,password;

    public BlankFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_blank, container, false);
        username=(EditText)view.findViewById(R.id.rusername);
        password=(EditText)view.findViewById(R.id.password);
        ImageButton login=(ImageButton)view.findViewById(R.id.submit);
       /* saveLoginCheckBox = (CheckBox)view.findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
*/

        final database database=new database(getActivity());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String user=username.getText().toString();
                        String pass=password.getText().toString();
                        int result=database.login(user,pass);
                        if(result==2) {
                            Toast.makeText(getActivity(),"Login successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Main2Activity.class);
                            startActivity(intent);
                        }
                        else if(result==1){
                            Toast.makeText(getActivity(),"Wrong password",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Please Register Yourself",Toast.LENGTH_SHORT).show();
                        }

            }
        });

        Button register=(Button)view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                Fragment fragment=fm.findFragmentById(R.id.fragment);
                FragmentTransaction ft=fm.beginTransaction();
                BlankFragment2 blankFragment2=new BlankFragment2();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment,blankFragment2);
                ft.commit();
            }
        });
        return view;
    }

}
