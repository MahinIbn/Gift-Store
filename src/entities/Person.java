package entities;

public abstract class Person 
{
	private String username, password;
	private final String realname;
	
	public Person(String user, String pass, String real) throws PersonException
	{
		if(!user.contains(" ") && (user.length() >= 3 && user.length() <= 25))
			username = user;
		else
			throw new InvalidUsername();

		if(!pass.contains(" ") && (pass.length() >= 3 && pass.length() <= 25))
			password = pass;
		else
			throw new InvalidPassword();
		
		realname = real;
	}
	
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getRealName() {return realname;}
	
	public void changeUsername(String user) throws InvalidUsername 
	{
		if(!user.contains(" ") && (user.length() >= 3 && user.length() <= 25))
			username = user;
		else
			throw new InvalidUsername();
	}
	public void changePassword(String pass) throws InvalidPassword
	{
		if(!pass.contains(" ") && (pass.length() >= 3 && pass.length() <= 25))
			password = pass;
		else
			throw new InvalidPassword();
	}
}