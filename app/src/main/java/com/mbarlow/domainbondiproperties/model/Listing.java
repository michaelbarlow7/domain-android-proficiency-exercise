
package com.mbarlow.domainbondiproperties.model;

public class Listing {

    private String agencyLogoUrl;
    private int bathrooms;
    private int bedrooms;
    private int carspaces;
    private String displayPrice;
    private String displayableAddress;
    private String truncatedDescription;
    private String retinaDisplayThumbUrl;
    private String secondRetinaDisplayThumbUrl;
    private int isElite;

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
    public int getIsElite() {
        return isElite;
    }

    /**
     * @param isElite The IsElite
     */
    public void setIsElite(int isElite) {
        this.isElite = isElite;
    }

}