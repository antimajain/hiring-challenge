package com.example.demo.model.user;

public class Cart {

	private String id;
	private String product;
	private int cost;
	private int count;
	

	public Cart() {
		super();
	}

	public Cart(String id,String product, int cost, int count) {
		super();
		this.id=id;
		this.product = product;
		this.cost = cost;
		this.count = count;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Cart [product=" + product + ", cost=" + cost + ", count=" + count + "]";
	}

	@Override
	public boolean equals(Object obj) {
	    if(obj instanceof Cart) {
	      Cart that = (Cart) obj;
	      return (this.id.equals(that.getId()) &&this.product.equals(that.getProduct()) && this.cost ==(that.getCost()) && this.count==(that.getCount()));
	    } else {
	      return false;
	    }
	  }
}
