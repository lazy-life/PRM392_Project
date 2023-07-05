package com.example.delitesprm392project.model;

public class Product {
    int productid,categoryid;
    double productprice;
    String productname;
    String productDes;
    String imagePath;

    public Product(int productid, int categoryid, double productprice, String productname, String productDes, String imagePath) {
        this.productid = productid;
        this.categoryid = categoryid;
        this.productprice = productprice;
        this.productname = productname;
        this.productDes = productDes;
        this.imagePath = imagePath;
    }

    public Product() {
    }

    public int getProductid() {
        return productid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public double getProductprice() {
        return productprice;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductDes() {
        return productDes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
