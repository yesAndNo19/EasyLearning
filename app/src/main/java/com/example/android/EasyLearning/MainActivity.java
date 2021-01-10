package com.example.android.EasyLearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements  mAdapter.DataLinkerIntent {



    mAdapter adapter;
    RecyclerView recyclerView;
    Button addQuestion ;
    LinearLayout bigLayout ;
   static boolean isDoctor = false;

    @Override
    protected void onPause() {
        super.onPause();
        adapter.onDetachedFromRecyclerView(recyclerView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("d")){
            isDoctor = getIntent().getExtras().getBoolean("d");
        }


        addQuestion =(Button) findViewById(R.id.add_question_button);
        bigLayout = (LinearLayout) findViewById(R.id.recyclerview_linear_layout);

        makeAndSetRecyclerView();


        if(!isDoctor){
            addQuestion.setText("Submit Answer");

        }

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isDoctor){

                    View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main2,null);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this) ;
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setView(view);
                    alertDialog.show();

                    Button cancel = view.findViewById(R.id.cancel_button);
                    Button proceed = view.findViewById(R.id.proceed_button);
                    final CheckBox checkBox = view.findViewById(R.id.check_box_alert);
                    final TextView textView = view.findViewById(R.id.text_view_alert);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    proceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(checkBox.isChecked()){

                                alertDialog.dismiss();
                                recyclerView.removeAllViewsInLayout();
                              
                                int total =mAdapter.getTotal();
                                int qmarks= mAdapter.totalNum;
                                Toast.makeText(MainActivity.this,checkGrade(total,qmarks),Toast.LENGTH_LONG).show();

                            }
                            else {
                                textView.setVisibility(View.VISIBLE);
                            }
                        }
                    });





                }
                else {
                    Intent intent = new Intent(MainActivity.this, QuestionEditor.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void makeAndSetRecyclerView() {

        adapter = new mAdapter( this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    public void goIntent(int id,CharSequence mainQ,CharSequence fAns, CharSequence sAns,CharSequence thAns, CharSequence fourAns) {
        Intent intent = new Intent(MainActivity.this,QuestionEditor.class);
        intent.putExtra("n",String.valueOf(id));
        intent.putExtra("m",mainQ);
        intent.putExtra("p",fAns);
        intent.putExtra("q",sAns);
        intent.putExtra("r",thAns);
        intent.putExtra("s",fourAns);


        startActivity(intent);
    }
    public String checkGrade(int mark, int total) {
        int succesmark= total/2;
        if(mark >= succesmark) {
           return "you succed,yor mark is "+ mark;
        }else {
            return  "you failed,yor mark is "+ mark;
        }
    }


}
