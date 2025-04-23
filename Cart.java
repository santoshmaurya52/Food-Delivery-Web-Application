package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer, CartItem> items;
	
	public Cart() {
		this.items = new HashMap<Integer, CartItem>();
	}
	
	public void addCartItem(CartItem item)
	{
		int itemId = item.getItemId();
		if(items.containsKey(itemId) )
		{
			CartItem existingtem = items.get(itemId);
//			int q1 = existingCartItem.getQuantity();
//			int q2 = item.getQuantity();
			existingtem.setQuantity(existingtem.getQuantity()+item.getQuantity());
			
		}else
		{
			items.put(itemId, item);			
		}
	}

	public void updateCartItem(int itemId, int quantity) 
	{
		if(items.containsKey(itemId))
		{
			if(quantity <=0)
			{
				items.remove(itemId);
			}
			else
			{
				items.get(itemId).setQuantity(quantity);
			}
		}	
	}
	
	public void removeCartItem(int itemId) {
		
		items.remove(itemId);	
	}
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public  double getTotalPrice() {
		return items.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
	}
	
	public void clear() {
		items.clear();
	}	
}
