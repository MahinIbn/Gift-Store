package entities;

public class ItemException extends Exception 
{
	public ItemException(String strMessage)
	{super(strMessage);}
}

class InvalidItemName extends ItemException
{
	public InvalidItemName() 
	{super("Item name must be between 3 and 35 chars.");}
}

class InvalidPrice extends ItemException
{
	public InvalidPrice() 
	{super("Price must be a positive number.");}
}

class InvalidStockNum extends ItemException
{
	public InvalidStockNum()
	{super("No. in stock must be a non-negative number.");}
}

class InvalidSoldNum extends ItemException
{
	public InvalidSoldNum()
	{super("No. sold must be a non-negative number.");}
}

class InvalidQuantity extends ItemException
{
	public InvalidQuantity()
	{super("Quantity must be positive and less than stock.");}
}

class ItemOutOfStock extends ItemException
{
	public ItemOutOfStock()
	{super("Out of stock.");}
}