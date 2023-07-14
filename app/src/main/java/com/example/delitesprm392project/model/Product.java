package com.example.delitesprm392project.model;

public class Product {
    private int productid;
    private String productname;
    private double productprice;
    private int categoryid;
    private boolean stocking;
    private String imagePath;
    private String productDes;

    public Product() {
    }

    public Product(int productid, String productname, double productprice, int categoryid, boolean stocking, String imagePath, String productDes) {
        this.productid = productid;
        this.productname = productname;
        this.productprice = productprice;
        this.categoryid = categoryid;
        this.stocking = stocking;
        this.imagePath = imagePath;
        this.productDes = productDes;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public boolean isStocking() {
        return stocking;
    }

    public void setStocking(boolean stocking) {
        this.stocking = stocking;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }
}
