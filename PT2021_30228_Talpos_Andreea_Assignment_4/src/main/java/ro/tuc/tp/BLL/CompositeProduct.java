package ro.tuc.tp.BLL;
import java.util.ArrayList;
public class CompositeProduct extends MenuItem {
    String name;
    float price;
    private ArrayList<MenuItem> product;
    public CompositeProduct(String name, ArrayList<MenuItem> products){
        this.name=name;
        product=products;
    }
    public int computePrice(){
        int suma=0;
        for(MenuItem item: product){
            suma+=item.computePrice();
        }
        price=suma;
        super.setPrice(suma);
        return suma;
    }
    public float computeRating(){
        float rating=0;
        for(MenuItem item: product){
            rating+=item.computeRating();
        }
        return rating/product.size();
    }

    public void addItem(MenuItem newProduct){
        product.add(newProduct);
    }
    public  int computeCalories(){
        int calories=0;
        for(MenuItem item:product){
            calories+=item.computeCalories();
        }
        return calories;
    }
    public  int computeProteins(){
        int proteins=0;
        for(MenuItem item: product){
            proteins+=item.computeProteins();
        }
        return proteins;
    }
    public  int computeFats(){
        int fats=0;
        for(MenuItem item: product){
            fats+=item.computeFats();
        }
        return fats;
    }
    public int computeSodium(){
        int sodium=0;
        for(MenuItem item: product){
            sodium+=item.computeSodium();
        }
        return sodium;
    }
    public ArrayList<MenuItem> getProduct() {
        return product;
    }
    public String toString(){
        return "Title: "+name + ", price:" + computePrice() + ", rating:" +computeRating()+ ", calories:"+computeCalories()+", proteins:" +computeProteins() +", fats:"+computeFats() + ", sodium:"+computeSodium()+"\n";
    }
}
