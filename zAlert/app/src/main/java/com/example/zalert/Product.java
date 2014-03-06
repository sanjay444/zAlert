package com.example.zalert;

import android.graphics.Bitmap;

public class Product {

	private Bitmap productImage;
	private int productId;
	private String brandname;
	private String productURL;
	private double price;
	private String percentOff ;
	private String imageURL;
	
	
	public Product(Bitmap productImage, int productId, String brandname,
			String productURL, double price, String percentOff, String imageURL) {
		super();
		this.productImage = productImage;
		this.productId = productId;
		this.brandname = brandname;
		this.productURL = productURL;
		this.price = price;
		this.percentOff = percentOff;
		this.imageURL = imageURL;
	}
	
	public Bitmap getProductImage() {
		return productImage;
	}

	public int getProductId() {
		return productId;
	}

	public String getBrandname() {
		return brandname;
	}

	public String getProductURL() {
		return productURL;
	}

	public double getPrice() {
		return price;
	}

	public String getPercentOff() {
		return percentOff;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setProductImage(Bitmap productImage) {
		this.productImage = productImage;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPercentOff(String percentOff) {
		this.percentOff = percentOff;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
