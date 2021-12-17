package org.example.dataLayer;

import org.example.businessLayer.BaseProduct;
import org.example.businessLayer.MenuItem;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileReaderCSV {

    /**
     * Method that will read from a file all the available products
     * by reading each line and creating a new product
     *
     * @return a set with all the products from the file
     */
    public HashSet<MenuItem> processInputFile() {

        HashSet<MenuItem> inputList = new LinkedHashSet<>();

        try {
            File inputF = new File("products.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = (HashSet<MenuItem>) br.lines().skip(1).map(mapToItem).collect(Collectors.toSet());
            br.close();
        } catch (IOException e) {
            System.out.println("File products.csv not found");
        }
        return inputList;
    }

    /**
     * Function that creates a new product from the line that was read from the file
     */
    private Function<String, MenuItem> mapToItem = (line) -> {

        String[] p = line.split(",");// a CSV has comma separated lines
        BaseProduct item = new BaseProduct();

        item.setTitle(p[0]);//<-- this is the first column in the csv file
        item.setRating(Double.parseDouble(p[1]));
        item.setCalories(Integer.parseInt(p[2]));
        item.setProtein(Integer.parseInt(p[3]));
        item.setFat(Integer.parseInt(p[4]));
        item.setSodium(Integer.parseInt(p[5]));
        item.setPrice(Integer.parseInt(p[6]));
        return item;
    };

}
