package com.example.libraryapp.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private String author;
    private int year;
    private String blurb;
    private Uri coverUri;
    private boolean isLiked;

    public Book(String title, String author, int year, String blurb, Uri coverUri, boolean isLiked) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.coverUri = coverUri;
        this.isLiked = isLiked;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        year = in.readInt();
        blurb = in.readString();
        coverUri = in.readParcelable(Uri.class.getClassLoader());
        isLiked = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeString(blurb);
        dest.writeParcelable(coverUri, flags);
        dest.writeByte((byte) (isLiked ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // enam getters dan satu setter
    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public int getYear() { return year; }

    public String getBlurb() { return blurb; }

    public Uri getCoverUri() { return coverUri; }

    public boolean isLiked() { return isLiked; }

    public void setLiked(boolean liked) { isLiked = liked; }
}
