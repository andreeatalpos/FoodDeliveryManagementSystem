package ro.tuc.tp.BLL;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader {
    public ArrayList<MenuItem> processInputFile(String filePath) {
        int ok=0;
        ArrayList<MenuItem> products = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<List<String>> items = lines.skip(1).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
            for(List<String> item : items){
                MenuItem p = new BaseProduct(item.get(0),Float.parseFloat(item.get(1)),Integer.parseInt(item.get(2)),Integer.parseInt(item.get(3)),Integer.parseInt(item.get(4)),Integer.parseInt(item.get(5)),Integer.parseInt(item.get(6)));
                for(MenuItem pr :products){
                    if(p.getTitle().equals(pr.getTitle()))
                        ok=1;
                }
                if(ok==0)
                    products.add(p);
                else ok=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in processing the csv file");
        }
        return products;
    }
}
