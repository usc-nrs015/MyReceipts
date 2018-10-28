package com.bignerdranch.android.myreceipts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.myreceipts.database.ReceiptBaseHelper;
import com.bignerdranch.android.myreceipts.database.ReceiptCursorWrapper;
import com.bignerdranch.android.myreceipts.database.ReceiptDbSchema.ReceiptTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReceiptLab {

    private static ReceiptLab sReceiptLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public  static ReceiptLab get(Context context) {
        if (sReceiptLab == null) {
            sReceiptLab = new ReceiptLab(context);
        }
        return sReceiptLab;
    }

    private ReceiptLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ReceiptBaseHelper(mContext).getWritableDatabase();
    }

    public void addReceipt(Receipt r) {
        ContentValues values = getContentValues(r);

        mDatabase.insert(ReceiptTable.NAME, null, values);
    }

    public void deleteReceipt(Receipt r) {
        String id = r.getId().toString();
        mDatabase.delete(ReceiptTable.NAME, ReceiptTable.Cols.UUID + " = ?", new String[] {id});
    }

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();

        ReceiptCursorWrapper cursor = queryReceipt(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                receipts.add(cursor.getReceipt());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return receipts;
    }

    public Receipt getReceipt(UUID id) {
        ReceiptCursorWrapper cursor = queryReceipt(
                ReceiptTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getReceipt();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Receipt receipt) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, receipt.getPhotoFilename());
    }

    public void updateReceipt(Receipt receipt) {
        String uuidString = receipt.getId().toString();
        ContentValues values = getContentValues(receipt);

        mDatabase.update(ReceiptTable.NAME, values, ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private ReceiptCursorWrapper queryReceipt(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ReceiptTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ReceiptCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Receipt receipt) {
        ContentValues values = new ContentValues();
        values.put(ReceiptTable.Cols.UUID, receipt.getId().toString());
        values.put(ReceiptTable.Cols.TITLE, receipt.getTitle());
        values.put(ReceiptTable.Cols.DATE, receipt.getDate().getTime());
        values.put(ReceiptTable.Cols.SHOP_NAME, receipt.getShopName());
        values.put(ReceiptTable.Cols.COMMENT, receipt.getComment());
        values.put(ReceiptTable.Cols.LOCATION_LAT, receipt.getLocationLat());
        values.put(ReceiptTable.Cols.LOCATION_LON, receipt.getLocationLon());

        return values;
    }
}
