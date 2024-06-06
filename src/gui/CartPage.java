package gui;

import entities.GiftStore;
import entities.Item;
import entities.ItemException;
import entities.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class CartPage extends JPanel
{
	private GiftStore kustore;
	private HashMap<Item, CartItemCard> cartitems;
	private JLabel topimg, welcome, yourcart, gross, vat, total;
	private JPanel toppane, rightpane, innerpane;
	private JScrollPane itempane;
	private JTextArea address;
	private JButton savechanges, logout, gotoshop, checkout;
	
	public CartPage(GiftStore store)
	{
		kustore = store;
		
		setTopPane();
		setRightPane();
		setItemPane();
		
		setLayout(new BorderLayout());
		add(toppane, BorderLayout.NORTH);
		add(rightpane, BorderLayout.EAST);
		add(itempane, BorderLayout.CENTER);
	}

	private void setTopPane() 
	{
		JPanel inner = new JPanel(), outer = new JPanel(), innermost = new JPanel();
		
		outer.setLayout(new BorderLayout());
		outer.add(new JLabel("   "), BorderLayout.NORTH);
		outer.add(new JLabel("   "), BorderLayout.SOUTH);
		outer.add(new JLabel("   "), BorderLayout.EAST);
		outer.add(new JLabel("   "), BorderLayout.WEST);
		outer.add(inner, BorderLayout.CENTER);
		
		
		topimg = new JLabel();
		Image img1 = new ImageIcon("src\\gui\\data\\kugiftlogo.png").getImage();
		topimg.setIcon(new ImageIcon(img1.getScaledInstance(308, 80, Image.SCALE_DEFAULT)));
		
		yourcart = new JLabel("Your Cart", JLabel.CENTER);
		yourcart.setFont(new Font("Calibri", Font.BOLD, 50));
		
		logout = new JButton("Logout?");
		logout.setForeground(Color.WHITE);
		logout.setBackground(new Color(38, 77, 152));
		welcome = new JLabel("<html>Welcome, <br>" + kustore.getCurrentLogin().getUsername() + "</html>", JLabel.CENTER);
		
		gotoshop = new JButton("to Shop?");
		gotoshop.setForeground(Color.WHITE);
		gotoshop.setBackground(new Color(38, 77, 152));
				
		
		innermost.setLayout(new GridLayout(1, 3, 10, 10));
		innermost.add(welcome);
		innermost.add(gotoshop);
		innermost.add(logout);
		innermost.setOpaque(false);
		
		inner.setLayout(new BorderLayout());
		inner.add(yourcart, BorderLayout.CENTER);
		topimg.setPreferredSize(new Dimension(342, 0));
		inner.add(topimg, BorderLayout.WEST);
		innermost.setPreferredSize(new Dimension(342, 0));
		inner.add(innermost, BorderLayout.EAST);
		inner.setOpaque(false);
		
		toppane = new JPanel();
		toppane.setPreferredSize(new Dimension(0, 102));
		toppane.setBorder(BorderFactory.createLineBorder(new Color(38, 77, 152)));
		toppane.setBackground(new Color(183, 210, 244));
		toppane.setLayout(null);
		toppane.add(outer);
		outer.setOpaque(false);
		outer.setBounds(1, 1, 1534, 100);
		
		logout.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String[] op = {"Yes", "Cancel"};
					String msg = "Are you sure you want to logout?";
					int ch = JOptionPane.showOptionDialog(null, msg, "Confirm", -1, 2, null, op, op[1]);
	
					if(ch == 0)
					{
						kustore.logout();
						((StorePanel) getParent()).setLoginPage();
						((CardLayout) getParent().getLayout()).show(getParent(), "Login Page");
					}
				}
			});
		gotoshop.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					((StorePanel) getParent()).setShoppingPage();
					((CardLayout) getParent().getLayout()).show(getParent(), "Shopping Page");
				}
			});
	}

	private void setRightPane() 
	{
		JPanel outer = new JPanel(), inner = new JPanel();
		JPanel innermost1 = new JPanel(), innermost2 = new JPanel();
		double sum = ((Customer) kustore.getCurrentLogin()).computeTotal();
		
		outer.setLayout(new BorderLayout());
		outer.add(new JLabel("   "), BorderLayout.NORTH);
		outer.add(new JLabel("   "), BorderLayout.SOUTH);
		outer.add(new JLabel("   "), BorderLayout.EAST);
		outer.add(new JLabel("   "), BorderLayout.WEST);
		outer.add(inner, BorderLayout.CENTER);
		
		savechanges = new JButton("Save Cart");
		savechanges.setForeground(Color.BLACK);
		savechanges.setBackground(new Color(183, 210, 244));
		gross = new JLabel(String.format("%.2f AED", sum), JLabel.CENTER);
		gross.setFont(new Font("Calibri", Font.PLAIN, 24));
		vat = new JLabel(String.format("%.2f AED", 0.05 * sum), JLabel.CENTER);
		vat.setFont(new Font("Calibri", Font.PLAIN, 24));
		total = new JLabel(String.format("%.2f AED", 1.05 * sum), JLabel.CENTER);
		total.setFont(new Font("Calibri", Font.PLAIN, 24));
		address = new JTextArea();
		address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		address.setLineWrap(true);
		checkout = new JButton("Finish");
		checkout.setForeground(Color.WHITE);
		checkout.setBackground(new Color(38, 77, 152));
		if(((Customer) kustore.getCurrentLogin()).getCart().size() == 0)
			checkout.setEnabled(false);
		
		JLabel grosslabel = new JLabel("Subtotal: ", JLabel.RIGHT);
		grosslabel.setFont(new Font("Calibri", Font.BOLD, 24));
		JLabel vatlabel = new JLabel(" + VAT: ", JLabel.RIGHT);
		vatlabel.setFont(new Font("Calibri", Font.BOLD, 24));
		JLabel totallabel = new JLabel("Total: ", JLabel.RIGHT);
		totallabel.setFont(new Font("Calibri", Font.BOLD, 24));
		JLabel addresslabel = new JLabel("Enter address: ");
		addresslabel.setVerticalAlignment(JLabel.BOTTOM);
		
		innermost2.setLayout(new GridLayout(3, 2, 6, 6));
		innermost2.add(grosslabel);
		innermost2.add(gross);
		innermost2.add(vatlabel);
		innermost2.add(vat);
		innermost2.add(totallabel);
		innermost2.add(total);
		
		innermost1.setLayout(new GridLayout(4, 1, 6, 6));
		innermost1.add(savechanges);
		innermost1.add(checkout);
		innermost1.add(new JLabel());
		innermost1.add(addresslabel);
		
		inner.setLayout(new GridLayout(3, 1, 6, 6));
		inner.add(innermost2);
		inner.add(innermost1);
		inner.add(address);
		
		rightpane = new JPanel();
		rightpane.setPreferredSize(new Dimension(352, 0));
		rightpane.setBorder(BorderFactory.createLineBorder(new Color(38, 77, 152)));
		rightpane.setLayout(null);
		rightpane.add(outer);
		outer.setBounds(1, 1, 350, 525);
		
		savechanges.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Customer c = (Customer) kustore.getCurrentLogin();
					ArrayList<Item> toRemove = new ArrayList<Item>();
					
					for(CartItemCard card : cartitems.values())
					{
						try 
						{
							c.changeQuantity(card.getItem(), Integer.parseInt(card.getAmount().getText()));
							card.getError().setText(" ");
						} 
						catch (ItemException err1)
						{
							card.getError().setText(err1.getMessage());
							card.getAmount().setText("" + c.getCart().get(card.getItem()));
						}
						catch (NumberFormatException err2) 
						{
							card.getError().setText("Please enter a number.");
							card.getAmount().setText("" + c.getCart().get(card.getItem()));
						}
						
						if(card.getDelete().isSelected() || card.getAmount().getText().equals("0"))
						{
							innerpane.remove(card);
							toRemove.add(card.getItem());
							c.removeItem(card.getItem());
							innerpane.add(new JLabel());
						}
					}
					for(Item i : toRemove)
						cartitems.remove(i);
					
					if(cartitems.size() == 0)
						checkout.setEnabled(false);
					
					double sum = c.computeTotal();
					gross.setText(String.format("%.2f AED", sum));
					vat.setText(String.format("%.2f AED", 0.05 * sum));
					total.setText(String.format("%.2f AED", 1.05 * sum));
					
					revalidate();
					repaint();
				}
			});
		checkout.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{					
					String[] op = {"Yes", "Cancel"};
					String msg = "Are you sure you want to checkout? Please save your cart and enter your address.";
					int ch = JOptionPane.showOptionDialog(null, msg, "Confirm", -1, 2, null, op, op[1]);
	
					if(ch == 0)
					{
						try 
						{
							new ReceiptFrame(kustore.checkout(address.getText()));
							
							for(CartItemCard card : cartitems.values())
							{
								innerpane.remove(card);
								innerpane.add(new JLabel());
							}
							
							cartitems.clear();
							checkout.setEnabled(false);
							
							double sum = ((Customer) kustore.getCurrentLogin()).computeTotal();
							gross.setText(String.format("%.2f AED", sum));
							vat.setText(String.format("%.2f AED", 0.05 * sum));
							total.setText(String.format("%.2f AED", 1.05 * sum));
							
							revalidate();
							repaint();
						} 
						catch (FileNotFoundException err) 
						{
							// do nothing, file will be created if not found
						}
					}
				}
			});
	}

	private void setItemPane() 
	{
		
		innerpane = new JPanel();
		JPanel outer = new JPanel();
		
		
		cartitems = new HashMap<Item, CartItemCard>();
		int vertical = kustore.getItems().size() / 2 + 1;
		if(vertical < 5)
			vertical = 5;
		
		innerpane.setLayout(new GridLayout(vertical, 2, 8, 8));
		for(Item i : ((Customer) kustore.getCurrentLogin()).getCart().keySet())
		{
			CartItemCard card = new CartItemCard(i, kustore);
			cartitems.put(i, card);
			innerpane.add(card);
		}
		for(int i = 0; i < 2 * vertical - ((Customer) kustore.getCurrentLogin()).getCart().size(); i++)
			innerpane.add(new JLabel());
			
		innerpane.setPreferredSize(new Dimension(1020, 170 * vertical));
		innerpane.setOpaque(false);		
		
		outer.setLayout(new BorderLayout());
		outer.add(new JLabel("   "), BorderLayout.NORTH);
		outer.add(new JLabel("   "), BorderLayout.EAST);
		outer.add(new JLabel("   "), BorderLayout.WEST);
		outer.add(new JLabel("   "), BorderLayout.SOUTH);
		outer.add(innerpane, BorderLayout.CENTER);
		outer.setOpaque(false);
		
		itempane = new JScrollPane();
		itempane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		itempane.getViewport().setBackground(new Color(38, 77, 152));
		itempane.setViewportView(outer);
	}
}