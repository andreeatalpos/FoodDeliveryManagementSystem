package ro.tuc.tp.BLL;
public class BaseProduct extends MenuItem{
    private String title;
    private float rating;
    private int calories;
    private int proteins;
    private int fat;
    private int sodium;
    private int price;
    public BaseProduct(String title,float rating, int calories, int proteins, int fat, int sodium, int price){
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.proteins=proteins;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }
    @Override
    public boolean equals(Object product){
        MenuItem p = (MenuItem) product;
        if(this.title.equals(p.getTitle()))
            return true;
        return false;
    }

    public int getHashCode(){
        return this.getHashCode();
    }
    @Override
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setRating(String rating) {
        this.rating = Float.parseFloat(rating);
    }
    public void setCalories(String calories) {
        this.calories = Integer.parseInt(calories);
    }
    public void setProteins(String proteins) {
        this.proteins = Integer.valueOf(proteins);
    }
    public void setFat(String fat) {
        this.fat = Integer.valueOf(fat);
    }
    public void setSodium(String sodium) {
        this.sodium = Integer.valueOf(sodium);
    }
    public void setPrice(String price) {
        this.price = Integer.valueOf(price);
    }

    public int computePrice(){
        return price;
    }

    public  float computeRating(){
        return rating;
    }
    public  int computeCalories(){
        return calories;
    }
    public  int computeProteins(){
        return proteins;
    }
    public  int computeFats(){
        return fat;
    }
    public int computeSodium(){
        return sodium;
    }
    public String toString(){
        return "Title: "+title + ", price:" + price + ", rating:" +rating+ ", calories:"+calories+", proteins:" +proteins +", fats:"+fat + ", sodium:"+sodium+"\n";
    }
}
