package com.dxtdkwt.zzh.appframework.ipctest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log

//CRUD操作，防止SQL注入和权限控制等  MediaStore
class BookProvider : ContentProvider() {

    private lateinit var mDb: SQLiteDatabase

    init {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE)
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE)
    }

    companion object {
        const val TAG = "BookProvider";
        const val AUTHORITY = "com.dxtdkwt.zzh.appframework.ipctest"
        val BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book")
        val USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user")

        const val BOOK_URI_CODE = 0
        const val USER_URI_CODE = 1

        val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    }

    fun getTableName(uri: Uri): String? {
        val tableName: String? = null
        when (sUriMatcher.match(uri)) {
            BOOK_URI_CODE -> DbOpenHelper.BOOK_TABLE_NAME
            USER_URI_CODE -> DbOpenHelper.USER_TALBE_NAME
        }
        return tableName;
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "delete")
        val table = getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI:" + uri)
        }
        val count = mDb.delete(table, selection, selectionArgs)
        if (count > 0) {
            context!!.contentResolver.notifyChange(uri, null)
        }
        return count
    }

    override fun getType(uri: Uri): String? {
        Log.d(TAG, "getType")
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "insert")
        val table = getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI:" + uri)
        }
        mDb.insert(table, null, values)
        context!!.contentResolver.notifyChange(uri, null)
        return uri
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate,current thread:" + Thread.currentThread().name)
        initProviderData()
        return false
    }

    private fun initProviderData() {
        mDb = DbOpenHelper(context).writableDatabase
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME)
        mDb.execSQL("delete from " + DbOpenHelper.USER_TALBE_NAME)
        mDb.execSQL("insert into book values(3,'Android');")
        mDb.execSQL("insert into book values(4,'Ios');")
        mDb.execSQL("insert into book values(5,'Html5');")
        mDb.execSQL("insert into user values(1,'jake',1);")
        mDb.execSQL("insert into user values(2,'jasmine',0);")

    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        Log.d(TAG, "query,current thread:" + Thread.currentThread().name)
        val table = getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI:" + uri)
        }

        return mDb.query(table, projection, selection, selectionArgs, null, sortOrder, null)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        Log.d(TAG, "update")
        val table = getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI:" + uri)
        }
        val row = mDb.update(table, values, selection, selectionArgs)
        if (row > 0) {
            context!!.contentResolver.notifyChange(uri, null)
        }
        return row
    }
}
