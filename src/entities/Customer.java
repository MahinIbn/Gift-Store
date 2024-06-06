package entities;

import java.util.HashMap;

public class Customer extends Person
{
	private String phoneNum;
	private HashMap<Item, Integer> cart;
	
	public Customer(String user, String pass, String real, String phone) throws PersonException
	{
		super(user, pass, real);
		
		if(validPhoneNumber(phone))
			phoneNum = phone;
		else
			throw new InvalidPhoneNumber();
		
		cart = new HashMap<Item, Integer>();
	}
	
	public String getPhoneNum() {return phoneNum;}
	public HashMap<Item, Integer> getCart(){return cart;}
	
	public void changePhoneNum(String phone) throws InvalidPhoneNumber
	{
		if(validPhoneNumber(phone))
			phoneNum = phone;
		else
			throw new InvalidPhoneNumber();
	}
	
	// phone number must be 05XXXXXXXX. Other formats not accepted
	public static boolean validPhoneNumber(String phone)
	{
		if(!phone.startsWith("05") || phone.length() != 10)
			return false;
		for(int i = 0; i < phone.length(); i++)
		{
			if(!(Character.isDigit(phone.charAt(i))))
				return false;
		}
		return true;
	}
	
	public void addToCart(Item item, int quantity) throws ItemException
	{
		if(item.getNumStock() > 0)
		{
			boolean ch1 =  (cart.get(item) == null && quantity <= item.getNumStock());
			boolean ch2 = (cart.get(item) != null && (quantity <= (item.getNumStock() - cart.get(item))));
			if(quantity >= 0 && (ch1 || ch2))
			{
				if(quantity > 0)
				{
					if(cart.get(item) != null)
						cart.put(item, cart.get(item) + quantity);
					else
						cart.put(item, quantity);
				}
			}
			else
				throw new InvalidQuantity();
		}
		else
			throw new ItemOutOfStock();
	}
	
	public void changeQuantity(Item item, int quantity) throws ItemException
	{
		if(cart.get(item) != null)
		{
			if(quantity >= 0 && quantity <= item.getNumStock())
			{
				if(quantity > 0)
				{
					cart.put(item, quantity);
				}
				else
					removeItem(item);
			}
			else
				throw new InvalidQuantity();
		}
	}
	
	public void removeItem(Item item)
	{
		cart.remove(item);
	}
	
	public double computeTotal()
	{
		double total = 0;
		for(Item i : cart.keySet())
			total += i.getPrice() * cart.get(i);
		return total;
	}
	
	public void checkout()
	{
		for(Item i : cart.keySet())
			i.buyItem(cart.get(i));
		cart.clear();
	}
}