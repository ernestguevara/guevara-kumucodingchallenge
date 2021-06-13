package com.ernestguevara.kumucodingchallenge.network.retrofit.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Model for the data you get from Location API
 * Also serves as the model for the database for search results
 */
@Entity(tableName = "itunes_items")
public class SearchResult implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "result_id")
    private int resultId;

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("collectionId")
    @Expose
    private int collectionId;
    @SerializedName("trackId")
    @Expose
    private int trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("collectionArtistId")
    @Expose
    private int collectionArtistId;
    @SerializedName("collectionArtistViewUrl")
    @Expose
    private String collectionArtistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private double trackPrice;
    @SerializedName("trackRentalPrice")
    @Expose
    private double trackRentalPrice;
    @SerializedName("collectionHdPrice")
    @Expose
    private double collectionHdPrice;
    @SerializedName("trackHdPrice")
    @Expose
    private double trackHdPrice;
    @SerializedName("trackHdRentalPrice")
    @Expose
    private double trackHdRentalPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private int discCount;
    @SerializedName("discNumber")
    @Expose
    private int discNumber;
    @SerializedName("trackCount")
    @Expose
    private int trackCount;
    @SerializedName("trackNumber")
    @Expose
    private int trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private int trackTimeMillis;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("hasITunesExtras")
    @Expose
    private boolean hasITunesExtras;
    @SerializedName("artistId")
    @Expose
    private int artistId;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;

    public SearchResult() {

    }

    protected SearchResult(Parcel in) {
        wrapperType = in.readString();
        kind = in.readString();
        collectionId = in.readInt();
        trackId = in.readInt();
        artistName = in.readString();
        collectionName = in.readString();
        trackName = in.readString();
        collectionCensoredName = in.readString();
        trackCensoredName = in.readString();
        collectionArtistId = in.readInt();
        collectionArtistViewUrl = in.readString();
        collectionViewUrl = in.readString();
        trackViewUrl = in.readString();
        previewUrl = in.readString();
        artworkUrl30 = in.readString();
        artworkUrl60 = in.readString();
        artworkUrl100 = in.readString();
        collectionPrice = in.readDouble();
        trackPrice = in.readDouble();
        trackRentalPrice = in.readDouble();
        collectionHdPrice = in.readDouble();
        trackHdPrice = in.readDouble();
        trackHdRentalPrice = in.readDouble();
        releaseDate = in.readString();
        collectionExplicitness = in.readString();
        trackExplicitness = in.readString();
        discCount = in.readInt();
        discNumber = in.readInt();
        trackCount = in.readInt();
        trackNumber = in.readInt();
        trackTimeMillis = in.readInt();
        country = in.readString();
        currency = in.readString();
        primaryGenreName = in.readString();
        contentAdvisoryRating = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        hasITunesExtras = in.readByte() != 0;
        artistId = in.readInt();
        artistViewUrl = in.readString();
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public int getCollectionArtistId() {
        return collectionArtistId;
    }

    public void setCollectionArtistId(int collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public void setTrackRentalPrice(double trackRentalPrice) {
        this.trackRentalPrice = trackRentalPrice;
    }

    public double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    public void setCollectionHdPrice(double collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }

    public double getTrackHdPrice() {
        return trackHdPrice;
    }

    public void setTrackHdPrice(double trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }

    public double getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    public void setTrackHdRentalPrice(double trackHdRentalPrice) {
        this.trackHdRentalPrice = trackHdRentalPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public int getDiscCount() {
        return discCount;
    }

    public void setDiscCount(int discCount) {
        this.discCount = discCount;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public boolean isHasITunesExtras() {
        return hasITunesExtras;
    }

    public void setHasITunesExtras(boolean hasITunesExtras) {
        this.hasITunesExtras = hasITunesExtras;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wrapperType);
        dest.writeString(kind);
        dest.writeInt(collectionId);
        dest.writeInt(trackId);
        dest.writeString(artistName);
        dest.writeString(collectionName);
        dest.writeString(trackName);
        dest.writeString(collectionCensoredName);
        dest.writeString(trackCensoredName);
        dest.writeInt(collectionArtistId);
        dest.writeString(collectionArtistViewUrl);
        dest.writeString(collectionViewUrl);
        dest.writeString(trackViewUrl);
        dest.writeString(previewUrl);
        dest.writeString(artworkUrl30);
        dest.writeString(artworkUrl60);
        dest.writeString(artworkUrl100);
        dest.writeDouble(collectionPrice);
        dest.writeDouble(trackPrice);
        dest.writeDouble(trackRentalPrice);
        dest.writeDouble(collectionHdPrice);
        dest.writeDouble(trackHdPrice);
        dest.writeDouble(trackHdRentalPrice);
        dest.writeString(releaseDate);
        dest.writeString(collectionExplicitness);
        dest.writeString(trackExplicitness);
        dest.writeInt(discCount);
        dest.writeInt(discNumber);
        dest.writeInt(trackCount);
        dest.writeInt(trackNumber);
        dest.writeInt(trackTimeMillis);
        dest.writeString(country);
        dest.writeString(currency);
        dest.writeString(primaryGenreName);
        dest.writeString(contentAdvisoryRating);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeByte((byte) (hasITunesExtras ? 1 : 0));
        dest.writeInt(artistId);
        dest.writeString(artistViewUrl);
    }
}
