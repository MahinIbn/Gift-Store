package entities;

public class PersonException extends Exception
{
	public PersonException(String strMessage)
	{super(strMessage);}
}

class InvalidUsername extends PersonException
{
	public InvalidUsername() 
	{super("Username must be between 3 and 25 chars. No spaces.");}
}

class InvalidPassword extends PersonException 
{
	public InvalidPassword() 
	{super("Password must be between 3 and 25 chars. No spaces.");}
}

class InvalidPhoneNumber extends PersonException
{
	public InvalidPhoneNumber()
	{super("Phone no. must be a valid UAE number (05XXXXXXXX).");}
}