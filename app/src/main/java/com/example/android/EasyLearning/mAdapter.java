package com.example.android.EasyLearning;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mAdapter extends RecyclerView.Adapter<mAdapter.AnimalHolder> {

    Context context;
    DataLinkerIntent dataLinkerIntent ;
    Cursor cursor;
   static public int totalNum=0;
   static  private  int totalMarks=0;


   public  static int id[];
   public static int getTotal(){





       return  totalMarks;
   }


    public mAdapter( Context context, DataLinkerIntent d) {

        this.context = context;
        dataLinkerIntent=d;
        helperClass helper = new helperClass(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        cursor = database.query(ConractClass.TABLE_NAME,null,null,null,null,null,null);
        totalNum =cursor.getCount();

         totalMarks=0;
        id = new int[totalNum];

    }

    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);


        return new AnimalHolder(view);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        cursor.close();
        Log.v("nooooooo","l");
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalHolder holder, int position) {
/*
        holder.imageView.setImageResource(animalArrayList.get(position).getImage());
*/      cursor.moveToPosition(position);
        holder.textView.setText(cursor.getString(cursor.getColumnIndex(ConractClass.COLUMN_MAINQ)));
        holder.radioButton1.setText(cursor.getString(cursor.getColumnIndex(ConractClass.ANS1)));
        holder.radioButton2.setText(cursor.getString(cursor.getColumnIndex(ConractClass.ANS2)));
        holder.radioButton3.setText(cursor.getString(cursor.getColumnIndex(ConractClass.ANS3)));
        holder.radioButton4.setText(cursor.getString(cursor.getColumnIndex(ConractClass.ANS4)));
        id[position]=cursor.getInt(cursor.getColumnIndex(ConractClass.COLUMNID));


    }



    @Override
    public int getItemCount() {
        return totalNum;
    }
    class AnimalHolder extends RecyclerView.ViewHolder{
        /*ImageView imageView;*/
        TextView textView;
        RadioGroup radioGroup ;
        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;
        RadioButton radioButton4;




        public AnimalHolder(@NonNull final View itemView) {

            super(itemView);
            /*imageView = itemView.findViewById(R.id.image1);*/


            textView =(TextView) itemView.findViewById(R.id.textView);
            radioGroup =itemView.findViewById(R.id.radio_Big);
            radioButton1 = itemView.findViewById(R.id.radio_one);
            radioButton2 = itemView.findViewById(R.id.radio_two);
            radioButton3 = itemView.findViewById(R.id.radio_three);
            radioButton4 = itemView.findViewById(R.id.radio_four);




          if(MainActivity.isDoctor){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
        dataLinkerIntent.goIntent(id[getAdapterPosition()],textView.getText().toString(),
        radioButton1.getText(),
        radioButton2.getText(),
        radioButton3.getText(),
        radioButton4.getText()

        );
                }
            });}

          else{
              radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(RadioGroup group, int checkedId) {

                      cursor.moveToPosition(getAdapterPosition());
                      Log.v("raddddddd",String.valueOf(getAdapterPosition()));
                      int rAns=cursor.getInt(cursor.getColumnIndex(ConractClass.RIGHTANS));
                      Log.v("raddddddd",String.valueOf(rAns));
                      Log.v("raddddddd",String.valueOf(group.getCheckedRadioButtonId()));
                      if(group.indexOfChild(itemView.findViewById(group.getCheckedRadioButtonId())) == rAns) {
                         totalMarks+=2;
                         Log.v("raddddddd",String.valueOf(totalMarks));

                      }
                  }
              });

          }


        }
    }
    interface  DataLinkerIntent
    {

        void goIntent(int id,CharSequence mainQ,CharSequence fAns, CharSequence sAns,CharSequence thAns, CharSequence fourAns);
    }

}


