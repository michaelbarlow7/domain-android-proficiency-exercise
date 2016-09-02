
package com.mbarlow.domainbondiproperties.model;

import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;

/**
 * A POJO representing data for Listings. It is used both by Gson to deserialise data and
 * Sugarrecord to store the data in an SQLite database.
 *
 * In hindsight I probably should've called this "Properties" or something less confusing :/
 */
@Table
public class Listing {

    @SerializedName("AdId")
    Long id;
    String agencyLogoUrl;
    int bathrooms;
    int bedrooms;
    int carspaces;
    String displayPrice;
    String displayableAddress;
    String truncatedDescription;
    String retinaDisplayThumbUrl;
    String secondRetinaDisplayThumbUrl;
    int isElite;

    // Default constructor necessary for SugarRecord
    public Listing(){

    }

    /**
     * @return The agencyLogoUrl
     */
    public String getAgencyLogoUrl() {
        return agencyLogoUrl;
    }

    /**
     * @param agencyLogoUrl The AgencyLogoUrl
     */
    public void setAgencyLogoUrl(String agencyLogoUrl) {
        this.agencyLogoUrl = agencyLogoUrl;
    }

    /**
     * @return The bathrooms
     */
    public int getBathrooms() {
        return bathrooms;
    }

    /**
     * @param bathrooms The Bathrooms
     */
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * @return The bedrooms
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * @param bedrooms The Bedrooms
     */
    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    /**
     * @return The carspaces
     */
    public int getCarspaces() {
        return carspaces;
    }

    /**
     * @param carspaces The Carspaces
     */
    public void setCarspaces(int carspaces) {
        this.carspaces = carspaces;
    }

    /**
     * @return The displayPrice
     */
    public String getDisplayPrice() {
        return displayPrice;
    }

    /**
     * @param displayPrice The DisplayPrice
     */
    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    /**
     * @return The displayableAddress
     */
    public String getDisplayableAddress() {
        return displayableAddress;
    }

    /**
     * @param displayableAddress The DisplayableAddress
     */
    public void setDisplayableAddress(String displayableAddress) {
        this.displayableAddress = displayableAddress;
    }

    /**
     * @return The truncatedDescription
     */
    public String getTruncatedDescription() {
        return truncatedDescription;
    }

    /**
     * @param truncatedDescription The TruncatedDescription
     */
    public void setTruncatedDescription(String truncatedDescription) {
        this.truncatedDescription = truncatedDescription;
    }

    /**
     * @return The retinaDisplayThumbUrl
     */
    public String getRetinaDisplayThumbUrl() {
        return retinaDisplayThumbUrl;
    }

    /**
     * @param retinaDisplayThumbUrl The RetinaDisplayThumbUrl
     */
    public void setRetinaDisplayThumbUrl(String retinaDisplayThumbUrl) {
        this.retinaDisplayThumbUrl = retinaDisplayThumbUrl;
    }

    /**
     * @return The secondRetinaDisplayThumbUrl
     */
    public String getSecondRetinaDisplayThumbUrl() {
        return secondRetinaDisplayThumbUrl;
    }

    /**
     * @param secondRetinaDisplayThumbUrl The SecondRetinaDisplayThumbUrl
     */
    public void setSecondRetinaDisplayThumbUrl(String secondRetinaDisplayThumbUrl) {
        this.secondRetinaDisplayThumbUrl = secondRetinaDisplayThumbUrl;
    }

    /**
     * @return The isElite
     */
    public boolean isElite() {
        return isElite != 0;
    }

    /**
     * @param isElite The IsElite
     */
    public void setIsElite(boolean isElite) {
        this.isElite = isElite ? 1 : 0;
    }

    public long getAdId(){
        return this.id;
    }

    public void setAdId(long adId){
        this.id = adId;
    }

}
