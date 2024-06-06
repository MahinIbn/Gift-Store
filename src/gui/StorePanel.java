package gui;

import entities.GiftStore;
import javax.swing.JPanel;
import java.awt.CardLayout;

public  class StorePanel extends JPanel
{
	private GiftStore kustore;
	private LoginPage login;
	private ShoppingPage shop;
	private CartPage cartpage;
	private Dashboard dashboard;
	private MovieInfo movieinfo;

	public StorePanel()
	{		
		setLayout(new CardLayout());
		kustore = new GiftStore();
		
		setLoginPage();
	}
	
	public void setLoginPage()
	{
		login = new LoginPage(kustore);
		add(login, "Login Page");
	}
	
	public void setShoppingPage()
	{
		shop = new ShoppingPage(kustore);
		add(shop, "Shopping Page");
	}
	
	public void setCartPage()
	{
		cartpage = new CartPage(kustore);
		add(cartpage, "Cart Page");
	}
	public void setDashboard()
	{
		dashboard = new Dashboard(kustore);
		add(dashboard, "Dashboard");
	}
	
	public void setMovieInfo()
	{
		movieinfo = new MovieInfo(kustore);
		add(movieinfo,  "Movie Info");
	}
	
	public GiftStore getStore() {return kustore;}
	public LoginPage getLoginPage() {return login;}
	public ShoppingPage getShoppingPage() {return shop;}
	public CartPage getCartPage() {return cartpage;}
	public Dashboard getDashboard() {return dashboard;}
	
	public MovieInfo getMovieInfo() {return movieinfo;};
}
