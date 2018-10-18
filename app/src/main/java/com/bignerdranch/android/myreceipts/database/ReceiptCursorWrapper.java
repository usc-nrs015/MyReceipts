package com.bignerdranch.android.myreceipts.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.myreceipts.Receipt;
import com.bignerdranch.android.myreceipts.database.ReceiptDbSchema.ReceiptTable;

import java.util.Date;
import java.util.UUID;

public class ReceiptCursorWrapper extends CursorWrapper {
    public ReceiptCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Receipt getReceipt() {
        String uuidString = getString(getColumnIndex(ReceiptTable.Cols.UUID));
        String title = getString(getColumnIndex(ReceiptTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ReceiptTable.Cols.DATE));
        String shopName = getString(getColumnIndex(ReceiptTable.Cols.SHOP_NAME));
        String comment = getString(getColumnIndex(ReceiptTable.Cols.COMMENT));
        double locationLat = getDouble(getColumnIndex(ReceiptTable.Cols.LOCATION_LAT));
        double locationLon = getDouble(getColumnIndex(ReceiptTable.Cols.LOCATION_LON));

        Receipt receipt = new Receipt(UUID.fromString(uuidString));
        receipt.setTitle(title);
        receipt.setDate(new Date(date));
        receipt.setShopName(shopName);
        receipt.setComment(comment);
        receipt.setLocationLat(Double.toString(locationLat));
        receipt.setLocationLon(Double.toString(locationLon));

        return receipt;
    }
}
