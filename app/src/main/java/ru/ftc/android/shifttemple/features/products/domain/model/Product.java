package ru.ftc.android.shifttemple.features.products.domain.model;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:40
 */

public final class Product {

    private String id;
    private String name;
    private String author;
    private String[] genre;
    private String pages;
    private boolean isAvailable;

    //days
    private int shelfTime;
    private int count = 1;//TODO hard

    //TODO add shelf time
    public Product(String name, String author, String pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getShelfTime(){
        return shelfTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getPages() {
        return pages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
