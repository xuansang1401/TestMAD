package com.ptit.testmad.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SinhVienProvider extends ContentProvider {
    private SQLiteDatabase db;

    public static final String PROVIDER_NAME = "com.example.provider.College";
    public static final String URI1 = "content://" + PROVIDER_NAME + "/"+ DatabaseHelper.TABLE_1;
    public static final String URI2 = "content://" + PROVIDER_NAME + "/"+ DatabaseHelper.TABLE_2;
    public static final String URI3 = "content://" + PROVIDER_NAME + "/"+ DatabaseHelper.TABLE_3;
    static final int ID_TA1 = 1;
    static final int ID_TA2 = 2;
    static final int ID_TA3 = 3;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, DatabaseHelper.TABLE_1, ID_TA1);
        uriMatcher.addURI(PROVIDER_NAME, DatabaseHelper.TABLE_2, ID_TA2);
        uriMatcher.addURI(PROVIDER_NAME, DatabaseHelper.TABLE_3, ID_TA3);
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//        qb.setTables(DatabaseHelper.TABLE_NAME);

        Cursor c = db.rawQuery(selection, null);
//        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/vnd.students";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID=-1;
        Uri _uri= null;
        switch (uriMatcher.match(uri)){
            case ID_TA1:
                rowID = db.insert(DatabaseHelper.TABLE_1, "", values);
                if (rowID>=0){
                    _uri = ContentUris.withAppendedId(Uri.parse(URI1), rowID);
                }
                break;
            case ID_TA2:
                rowID = db.insert(DatabaseHelper.TABLE_2, "", values);
                if (rowID>=0) {
                    _uri = ContentUris.withAppendedId(Uri.parse(URI2), rowID);
                }
                break;
            case ID_TA3:
                rowID = db.insert(DatabaseHelper.TABLE_3, "", values);
                if (rowID>=0){
                    _uri = ContentUris.withAppendedId(Uri.parse(URI3), rowID);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(_uri, null);
        return _uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = db.delete("SinhVien", selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = db.update("SinhVien", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
