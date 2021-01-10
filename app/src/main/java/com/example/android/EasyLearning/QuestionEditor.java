package com.example.android.EasyLearning;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionEditor extends AppCompatActivity {

    EditText quizQuestion ;
    EditText firstAnswer ;
    EditText secondAnswer ;
    EditText thirdAnswer ;
    EditText fourthAnswer ;
    RadioGroup radioGroup;
    Button button;
    Button delete;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_question);
        intializeComponets();



        if(getIntent().hasExtra("n")){
            delete.setEnabled(true);
            setComponets();

        }


        delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       ConractClass.deletRow(QuestionEditor.this,ConractClass.COLUMNID,getIntent().getExtras().getString("n"));
        Intent intent = new Intent(QuestionEditor.this,MainActivity.class);
        startActivity(intent);
    }
});

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              if( isNotEmpty() ){
               updateOrInsertInDataBase();
               Intent intent= new Intent(QuestionEditor.this,MainActivity.class);
               startActivity(intent);           }
              else{
                  Toast.makeText(QuestionEditor.this,"Fill Data",Toast.LENGTH_SHORT).show();
              }

           }
       });


    }

    private void setComponets() {
        quizQuestion.setText(getIntent().getExtras().getCharSequence("m"));
        firstAnswer.setText(getIntent().getExtras().getCharSequence("p"));

        secondAnswer.setText(getIntent().getExtras().getCharSequence("q"));
        thirdAnswer.setText(getIntent().getExtras().getCharSequence("r"));
        fourthAnswer.setText(getIntent().getExtras().getCharSequence("s"));

    }

    private void intializeComponets() {
        quizQuestion = (EditText) findViewById(R.id.editor_window_question);
        firstAnswer = (EditText) findViewById(R.id.first_radio_editbox);
        secondAnswer = (EditText) findViewById(R.id.second_radio_editbox);
        thirdAnswer = (EditText) findViewById(R.id.third_radio_editbox);
        fourthAnswer = (EditText) findViewById(R.id.fourth_radio_radio_editbox);
        radioGroup =(RadioGroup) findViewById(R.id.radio_group_in_editor);
        button = (Button) findViewById(R.id.submit_button);
        delete = (Button) findViewById(R.id.delete);


    }


    private boolean isNotEmpty(){
        if(firstAnswer.getText().length()==0 ||
                secondAnswer.getText().length()==0 ||
                thirdAnswer.getText().length()==0 ||
                fourthAnswer.getText().length()==0 ||
                quizQuestion.getText().length()==0 ||
                radioGroup.getCheckedRadioButtonId()==-1

            )
        {  return false;}


        return  true;
    }

    private void updateOrInsertInDataBase() {
        helperClass helper = new helperClass(this);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConractClass.COLUMN_MAINQ,quizQuestion.getText().toString());
        contentValues.put(ConractClass.ANS1,firstAnswer.getText().toString());
        contentValues.put(ConractClass.ANS2,secondAnswer.getText().toString());
        contentValues.put(ConractClass.ANS3,thirdAnswer.getText().toString());
        contentValues.put(ConractClass.ANS4,fourthAnswer.getText().toString());
        contentValues.put(ConractClass.RIGHTANS,radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId())));

        if(getIntent().hasExtra("n")){
            String p =getIntent().getExtras().getString("n");
            Log.v("lllllllllll",p);

           int n=sqLiteDatabase.update(ConractClass.TABLE_NAME,contentValues, ConractClass.COLUMNID + "=?" ,new String[]{getIntent().getExtras().getString("n")});
            Log.v("lllllooooo",String.valueOf(n));
        }

       else {

            sqLiteDatabase.insert(ConractClass.TABLE_NAME, null, contentValues);

        }


    }
}
