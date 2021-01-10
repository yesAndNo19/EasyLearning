package com.example.android.EasyLearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements linker {
  Button login,todo,quiz;
  ArrayList<UserData> userData;
  boolean Doctor=true;
    TextView name;
    private String userNmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        name = findViewById(R.id.username_name);
        userData = new ArrayList<UserData>();

        login = findViewById(R.id.btn_login);
        todo = findViewById(R.id.btn_todo);
        quiz = findViewById(R.id.btn_quiz);


        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                intent.putExtra("d",Doctor);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(Main2Activity.this).inflate(R.layout.login_layout,null);

                final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this) ;
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view);
                alertDialog.show();

                Button login = view.findViewById(R.id.make_login);
                Button signUp = view.findViewById(R.id.make_signup);
                Button ok = view.findViewById(R.id.make_ok);

                final CheckBox checkBox = view.findViewById(R.id.check_doctor);
                 final  TextView determine =(TextView) view.findViewById(R.id.determine_login_signup);
                final TextView fill = view.findViewById(R.id.error_textview_login);

                final EditText usernmaeBox = view.findViewById(R.id.user_name_edit_box);
                final EditText passwordBox = view.findViewById(R.id.password_edit_box);

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        determine.setText("Login");

                        checkBox.setEnabled(false);
                    }
                });
                signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        determine.setText("Sign up");
                        checkBox.setEnabled(true);
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {


                        linker linky =(linker) Main2Activity.this;
                        if(usernmaeBox.getText().length()<=0 ||passwordBox.getText().length()<=0){
                            fill.setVisibility(View.VISIBLE);

                            return;
                        }
                        Log.v("luuuuuuuuuuuu","ooo");
                        if(determine.getText().charAt(0)== 'L'){
                            int uy =0;
                           for(int i=0;i<userData.size();i++ ){

                               if((userData.get(i).getUser_name()).equals(usernmaeBox.getText().toString())){

                                   if(userData.get(i).getPass().equals(passwordBox.getText().toString())){

                                       linky.link(userData.get(i).isDoctor(),userData.get(i).getUser_name());
                                       uy =  1;

                                   }
                               }


                           }

                           if(uy==0){
                               fill.setVisibility(View.VISIBLE);
                               return;
                           }

                        }
                        else{
                            String user =usernmaeBox.getText().toString();
                            String pass =passwordBox.getText().toString();
                            boolean d =checkBox.isChecked();
                            userData.add(new UserData(user,pass,d));
                            linky.link(d,user);

                        }

                     alertDialog.dismiss();

                    }
                });







            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,ToDoList.class);
                startActivity(intent);            }
        });




    }

    @Override
    public void link(boolean doctor, String username) {
        userNmae =username;
        Doctor =doctor;
        name.setText(username);
        name.setVisibility(View.VISIBLE);
        quiz.setEnabled(true);

        if(!doctor){
            todo.setEnabled(true);

        }

    }




}
interface  linker{
    void link(boolean doctor, String username);



}
