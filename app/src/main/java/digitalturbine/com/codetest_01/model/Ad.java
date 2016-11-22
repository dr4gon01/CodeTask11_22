package digitalturbine.com.codetest_01.model;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class Ad {
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    String rating, productName, productThumbnail;


}
