package model;

public class CartItem {
	
	private int itemId;
	private String name;
	private double price;
	private int quantity;
	
	
	public CartItem() {
	// TODO Auto-generated constructor stub
	}

	public CartItem(int itemId, String name, double price, int quantity) {
	super();
	this.itemId = itemId;
	this.name = name;
	this.price = price;
	this.quantity = quantity;
	
	}

	public CartItem(int menuId, int restaurantId, String itemName, int quantity, double price) {
	    this.itemId = menuId;
	    this.name = itemName;
	    this.price = price;
	    this.quantity = quantity;
	}

	
	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
        return price * quantity;
    }
	
	
	@Override
	public String toString() {
		return "CartItem (itemId=" + itemId + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ")";
	}


}
