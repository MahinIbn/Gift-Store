package entities;

public class GiftStoreException extends Exception
{
	public GiftStoreException(String strMessage)
	{super(strMessage);}
}

class InvalidCredentials extends GiftStoreException
{
	public InvalidCredentials()
	{super("Incorrect Username or Password.");}
}

class CustomerUsernameTaken extends GiftStoreException
{
	public CustomerUsernameTaken()
	{super("This username is already in use.");}
}

class ItemNameTaken extends GiftStoreException
{
	public ItemNameTaken()
	{super("This item name is already in use.");}
}