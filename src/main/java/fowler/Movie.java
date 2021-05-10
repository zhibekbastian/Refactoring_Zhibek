package fowler;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String title;
    private PriceCode priceCode;

    public Movie(String title, PriceCode priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }
    public void setPriceCode(PriceCode priceCode) {
        if(priceCode == null) {
            this.priceCode = PriceCode.REGULAR;
            return;
        }
        this.priceCode = priceCode;
    }
    public String getTitle (){
        return title;
    };
}