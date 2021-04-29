package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

public class Product{
    private String name; 
    private Float price; 
    private String id; 
    private int sales; 

    public Product(){
        this.id= RandomUtils.getId(); 

    }
    public Product ( String name, Float price ){
        this(); 
        this.name = name; 
        this.price = price; 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSales() {
        return this.sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public String toString (){
        return "Product: " + this.name + " Id: " + this.id + " precio: " + this.price;
    }
}