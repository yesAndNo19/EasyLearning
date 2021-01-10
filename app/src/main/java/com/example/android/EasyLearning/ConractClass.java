package com.example.android.EasyLearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public final class   ConractClass {



        /*public static final  String TABLE_CONTENT_AUTHORITY="com.example.petsapp";*/

        /*public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + TABLE_CONTENT_AUTHORITY);*/


       /* public static final  String TABLE_PATH="pets";

        public static final Uri theFinalUri = Uri.withAppendedPath(BASE_CONTENT_URI,TABLE_PATH);


        public static final  String TABLE_PLACE_URI="content://com.example.petsapp/pets";


*/      public  static int totalNumOfRows=0;

        public   static  final  String TABLE_NAME="QDATA";

        public   static  final  String COLUMNID="_id";

        public    static  final  String COLUMN_MAINQ="mainq";

        public   static  final  String ANS2="anssec";

        public   static  final  String ANS3="ansthird";

        public   static  final  String ANS4="ansfourth";

        public   static  final  String ANS1="ansfirst";

        public   static  final  String RIGHTANS="rightans";




        public static  final String CREATE_TABLE_Q_DATA="CREATE TABLE "+ TABLE_NAME +"("+COLUMNID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                COLUMN_MAINQ +" TEXT NOT NULL,"+ANS1+" TEXT NOT NULL,"+
                ANS2+" TEXT NOT NULL,"+ANS3+" TEXT ,"+ANS4 +" TEXT ,"+ RIGHTANS +" INTEGER NOT NULL"+ ");";

public static  void deletRow(Context context,String whichColumn, String valueToBeDeleted){
 helperClass helper = new helperClass(context);
 SQLiteDatabase liteDatabase = helper.getWritableDatabase();

 liteDatabase.delete(ConractClass.TABLE_NAME,whichColumn +"=?",new String[]{valueToBeDeleted});
 }
}
