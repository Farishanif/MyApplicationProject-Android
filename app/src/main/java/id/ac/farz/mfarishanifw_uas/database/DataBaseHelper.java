package id.ac.farz.mfarishanifw_uas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import id.ac.farz.mfarishanifw_uas.model.FriendModel;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String friendNames;
    private static String friendSex;
    private static String friendBod;
    private static String friendDetails;
    private static int friendImages;

    public static final String FRIEND_TABLE = "FRIEND_TABLE";
    public static final String COLUMN_FRIEND_NAME = "FRIEND_NAME";
    public static final String COLUMN_FRIEND_BOD = "FRIEND_BOD";
    public static final String COLUMN_FRIEND_SEX = "FRIEND_SEX";
    public static final String COLUMN_FRIEND_DETAIL = "FRIEND_DETAIL";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "friend.db", null, 1);
        //getListData();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + FRIEND_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FRIEND_NAME + " TEXT, " + COLUMN_FRIEND_BOD + " TEXT, " + COLUMN_FRIEND_SEX + " TEXT, " + COLUMN_FRIEND_DETAIL + " TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addOne(FriendModel friendModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FRIEND_NAME, friendModel.getName());
        cv.put(COLUMN_FRIEND_SEX, friendModel.getSex());
        cv.put(COLUMN_FRIEND_BOD, friendModel.getBod());
        cv.put(COLUMN_FRIEND_DETAIL, friendModel.getDetail());

        long insert = db.insert(FRIEND_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int checkEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM FRIEND_TABLE";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }
    public boolean deleteOne(FriendModel friendModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+FRIEND_TABLE+" WHERE "+COLUMN_ID+" = "+ friendModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return  false;
        }

    }

    public ArrayList<FriendModel> getListData() {
        ArrayList<FriendModel> list = new ArrayList<>();

        String queryString = "SELECT * FROM " + FRIEND_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {

            do {
                int friendID = cursor.getInt(0);
                friendNames = cursor.getString(1);
                friendSex = cursor.getString(2);
                friendBod = cursor.getString(3);
                friendDetails = cursor.getString(4);


                FriendModel newFriend = new FriendModel(friendID, friendNames,friendBod, friendSex, friendDetails);
                list.add(newFriend);
            } while (cursor.moveToNext());

        }
        else{

        }
        cursor.close();
        db.close();

        return list;

    }

}
