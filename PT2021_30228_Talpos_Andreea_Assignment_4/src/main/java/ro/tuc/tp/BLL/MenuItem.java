package ro.tuc.tp.BLL;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private float rating;
    private int calories;
    private int proteins;
    private int fat;
    private int sodium;
    private float price;
    public MenuItem(){}
    public MenuItem(String title,float rating, int calories, int proteins, int fat, int sodium, int price){
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.proteins=proteins;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }
    public abstract int computePrice();
    public abstract float computeRating();
    public abstract int computeCalories();
    public abstract int computeProteins();
    public abstract int computeFats();
    public abstract int computeSodium();
    public String getTitle() {
        return title;
    }
    public void setName(String name) {
        this.title = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String toString(){
      return "Title: "+title + ", price:" + price + ", rating:" +rating+ ", calories:"+calories+", proteins:" +proteins +", fats:"+fat + ", sodium:"+sodium+"\n";
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public float getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }
}
