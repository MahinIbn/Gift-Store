package gui;

import entities.GiftStore;
import entities.Item;
import entities.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CartItemCard extends JPanel
{
	private Item item;
	private GiftStore kustore;
	private JLabel itemimage, name, stock, added, error, price; 
	private JButton increment, decrement;
	private JToggleButton delete;
	private JTextField amount;
	
	public CartItemCard(Item i, GiftStore store)
	{
		JPanel outer = new JPanel();
		JPanel inner1 = new JPanel(), inner2 = new JPanel(), innermost = new JPanel();
		
	
		item = i;
		kustore = store;
		
		itemimage = new JLabel("", JLabel.CENTER);
		Image img = (new ImageIcon(item.getImagePath())).getImage();
		if(170 > 125 * img.getWidth(null) / img.getHeight(null))
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(125 * img.getWidth(null) / img.getHeight(null), 125, Image.SCALE_DEFAULT)));
		else
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(170, 170 * img.getHeight(null) / img.getWidth(null), Image.SCALE_DEFAULT)));
		itemimage.setOpaque(true);
		stock = new JLabel("In Stock: " + item.getNumStock(), JLabel.CENTER);
		stock.setFont(new Font("Calibri", Font.PLAIN, 20));
		added = new JLabel("Added: ", JLabel.CENTER);
		added.setFont(new Font("Calibri", Font.PLAIN, 20));
		error = new JLabel(" ", JLabel.CENTER);
		error.setForeground(Color.RED);
		
		increment = new JButton("+");
		increment.setForeground(Color.WHITE);
		increment.setBackground(new Color(38, 77, 152));
		increment.setFont(new Font("Calibri", Font.PLAIN, 20));
		decrement = new JButton("-");
		decrement.setForeground(Color.WHITE);
		decrement.setBackground(new Color(38, 77, 152));
		decrement.setFont(new Font("Calibri", Font.PLAIN, 20));
		delete = new JToggleButton("Delete?");
		delete.setForeground(Color.WHITE);
		delete.setBackground(Color.RED);
		
		amount = new JTextField("1");
		amount.setHorizontalAlignment(JTextField.CENTER);
		amount.setText("" + ((Customer) kustore.getCurrentLogin()).getCart().get(item));
		name = new JLabel(item.getItemName(), JLabel.CENTER);
		name.setFont(new Font("Calibri", Font.BOLD, 25));
		name.setForeground(new Color(38, 77, 152));
		price = new JLabel(item.getPrice() + " AED", JLabel.CENTER);
		price.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		
		innermost.setLayout(new GridLayout(1, 3));
		innermost.add(decrement);
		innermost.add(amount);
		innermost.add(increment);
		innermost.setOpaque(false);
		
		inner1.setLayout(new GridLayout(3, 1, 8, 8));
		inner1.add(name);
		inner1.add(added);
		inner1.add(stock);
		inner1.setOpaque(false);
		
		inner2.setLayout(new GridLayout(3, 1, 4, 4));
		inner2.add(price);
		inner2.add(innermost);
		inner2.add(delete);
		inner2.setOpaque(false);
		
		outer.setLayout(new GridLayout(1, 3, 8, 8));
		outer.add(itemimage);
		outer.add(inner1);
		outer.add(inner2);
		outer.setOpaque(false);
		
		
		setLayout(new BorderLayout());
		add(new JLabel("   "), BorderLayout.NORTH);
		add(error, BorderLayout.SOUTH);
		add(new JLabel("   "), BorderLayout.EAST);
		add(new JLabel("   "), BorderLayout.WEST);
		add(outer, BorderLayout.CENTER);
		
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setBackground(new Color(183, 210, 244));
		setOpaque(true);
		
		setPlusMinus();
	}
	
	public void setPlusMinus()
	{
		increment.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					amount.setText("" + (Integer.parseInt(amount.getText()) + 1));
				}
			});
		decrement.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Integer.parseInt(amount.getText()) > 0)
					amount.setText("" + (Integer.parseInt(amount.getText()) - 1));
			}
		});
	}
	
	public Item getItem() {return item;}
	public JToggleButton getDelete() {return delete;}
	public JTextField getAmount() {return amount;}
	public JLabel getError() {return error;}
}