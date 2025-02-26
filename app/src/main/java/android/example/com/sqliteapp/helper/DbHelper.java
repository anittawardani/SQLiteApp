package android.example.com.sqliteapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="digitaltalent.db";
    public static final String TABLE_SQLite="sqlite";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ADDRESS="address";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_SQLite+" ("
                +COLUMN_ID+" INTEGER PRIMARY KEY autoincrement, "
                +COLUMN_NAME+" TEXT NOT NULL, "
                +COLUMN_ADDRESS+" TEXT NOT NULL"+" )";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SQLite );
        onCreate(db);
    }

    public ArrayList<HashMap<String,String>> getAllData(){
        ArrayList<HashMap<String, String>> worldList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_SQLite;
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map=new HashMap<>();
                map.put(COLUMN_ID,cursor.getString(0));
                map.put(COLUMN_NAME,cursor.getString(1));
                map.put(COLUMN_ADDRESS,cursor.getString(2));
                worldList.add(map);
            }while (cursor.moveToNext());
        }
        database.close();

        return worldList;
    }

    public void insert(String name, String address){
        SQLiteDatabase database=this.getWritableDatabase();
        String queryValues="INSERT INTO "+TABLE_SQLite+" (name, address) "
                +"VALUES ('"+name+"', '"+address+"') ";
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String address){
        SQLiteDatabase database=this.getWritableDatabase();
        String updateQuery="UPDATE "+TABLE_SQLite+" SET "
                +COLUMN_NAME+"='"+name+"', "
                +COLUMN_ADDRESS+"='"+address+"'"
                +" WHERE "+COLUMN_ID+"="+"'"+id+"'";
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database=this.getWritableDatabase();
        String deleteQuery="DELETE FROM "+TABLE_SQLite+" WHERE "
                +COLUMN_ID+"='"+id+"'";
        database.execSQL(deleteQuery);
        database.close();

    }
}
