package gui;

import entities.GiftStore;
import entities.Item;
import entities.Customer;
import entities.ItemException;
import gui.StorePanel;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;

public class CustomerItemCard extends ItemCard
{
	public CustomerItemCard(Item i, GiftStore store) 
	{
		super(i, store);
		
		
		
		itemname.setEditable(false);
		itemname.setOpaque(false);
		itemprice.setEditable(false);
		itemprice.setOpaque(false);
		save.setText("Add to Cart");
		stock.setText("<html>In Stock:<br>" + item.getNumStock() + "</html>");
		int num = 0;
		try
		{
			num = ((Customer) kustore.getCurrentLogin()).getCart().get(i);
		}
		catch(NullPointerException e)
		{ 
			// nothing, num already set to 0
		}
		added.setText("<html>Added:<br>" + num + "</html>");
		
	
	
	info.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			 MovieInfoCard m = new MovieInfoCard(item);
		}		
			
		});
					
	}			
	

	public void setSaveAction() 
	{
		save.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						//(item,Integer.parseInt(amount.getText()))
						((Customer) kustore.getCurrentLogin()).addToCart(item, 1);
						int quantity = ((Customer) kustore.getCurrentLogin()).getCart().get(item);
						added.setText("<html>Added:<br>" + quantity + "</html>");
						error.setText(" ");
					}
					catch (ItemException err)
					{
						error.setText(err.getMessage());
					}
					catch(NumberFormatException err2)
					{
						error.setText("Please enter a number.");
					}
					//amount.setText("1");
				}
			});
	}
	
	/*
	 * public void setMovieAction() { info.addActionListener(new ActionListener() {
	 * public void actionPerformed(ActionEvent e) {
	 * 
	 * ((StorePanel) getParent()).setMovieInfo(); ((CardLayout)
	 * getParent().getLayout()).show(getParent(), "Movie Info"); } }); }
	 */
	

	public void setImageAction() {/*no action*/}
}