package com.bignerdranch.android.myreceipts;

import java.util.Date;
import java.util.UUID;

public class Receipt {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mShopName;
    private String mComment;
    private String mLocationLat;
    private String mLocationLon;

    public Receipt() {
        this(UUID.randomUUID());
    }


    public Receipt(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getLocationLat() {
        return mLocationLat;
    }

    public void setLocationLat(String locationLat) {
        mLocationLat = locationLat;
    }

    public String getLocationLon() {
        return mLocationLon;
    }

    public void setLocationLon(String locationLon) {
        mLocationLon = locationLon;
    }


    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
