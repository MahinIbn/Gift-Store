package gui;

import entities.GiftStore;
import entities.Item;
import entities.Customer;
import entities.PersonException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ShoppingPage extends JPanel
{
	private GiftStore kustore;
	private HashMap<Item, CustomerItemCard> items;
	private JLabel topimg, welcome, changeerror;
	private JPanel toppane, leftpane, innerpane;
	private JScrollPane itempane;
	private JTextField searchbar, changeuser, changephone;
	private JPasswordField changepass;
	private JButton savechanges, logout, gotocart, deleteacc, filter, display;
	private JToggleButton changedata;
	
	private JComboBox<String> field, itemBox;
	
	public ShoppingPage(GiftStore store)
	{
		kustore = store;
		setTopPane();
		setLeftPane();
		setItemPane();
		
		setLayout(new BorderLayout());
		add(toppane, BorderLayout.NORTH);
		add(leftpane, BorderLayout.WEST);
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
		
		searchbar = new JTextField();
		searchbar.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		logout = new JButton("Logout?");
		logout.setForeground(Color.WHITE);
		logout.setBackground(new Color(38, 77, 152));
		welcome = new JLabel("<html>Welcome, <br>" + kustore.getCurrentLogin().getUsername() + "</html>", JLabel.CENTER);
		
		gotocart = new JButton();
		Image img2 = new ImageIcon("src\\gui\\data\\cartlogo.png").getImage();
		gotocart.setIcon(new ImageIcon(img2.getScaledInstance(90, 80, Image.SCALE_DEFAULT)));
		gotocart.setOpaque(false);
		gotocart.setContentAreaFilled(false);
		gotocart.setBorderPainted(false);
		
		
		innermost.setLayout(new GridLayout(1, 3, 10, 10));
		innermost.add(welcome);
		innermost.add(gotocart);
		innermost.add(logout);
		innermost.setOpaque(false);
		
		inner.setLayout(new BorderLayout());
		inner.add(searchbar, BorderLayout.CENTER);
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
		
		searchbar.addActionListener(new SearchBarListener());
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
		gotocart.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					((StorePanel) getParent()).setCartPage();
					((CardLayout) getParent().getLayout()).show(getParent(), "Cart Page");
				}
			});
	}
	
	private void setLeftPane()
	{
		JPanel inner = new JPanel(), outer = new JPanel();
		
		outer.setLayout(new BorderLayout());
		outer.add(new JLabel("   "), BorderLayout.NORTH);
		outer.add(new JLabel("   "), BorderLayout.SOUTH);
		outer.add(new JLabel("   "), BorderLayout.EAST);
		outer.add(new JLabel("   "), BorderLayout.WEST);
		outer.add(inner, BorderLayout.CENTER);
		
		changedata = new JToggleButton("Change Information?");
		changedata.setForeground(Color.WHITE);
		changedata.setBackground(new Color(38, 77, 152));
		changeuser = new JTextField();
		changeuser.setEditable(false);
		changepass = new JPasswordField();
		changepass.setEditable(false);
		changephone = new JTextField();
		changephone.setEditable(false);
		changeerror = new JLabel();
		changeerror.setForeground(Color.RED);
		savechanges = new JButton("Submit");
		savechanges.setEnabled(false);
		savechanges.setForeground(Color.WHITE);
		savechanges.setBackground(new Color(38, 77, 152));
		deleteacc = new JButton("Delete my Account?");
		deleteacc.setForeground(Color.WHITE);
		deleteacc.setBackground(Color.RED);
		
		
		field = new JComboBox<String>();
		field.setEditable(false);
		field.addItem("- Filter By -");
		field.addItem("Year of Release");
		field.addItem("Genre");
		field.addItem("Movie Studio");
		
		itemBox = new JComboBox<String>();
		itemBox.setEditable(false);
		
		
		
		filter = new JButton("Filter");
		filter.setForeground(Color.WHITE);
		filter.setBackground(Color.BLACK);
		
		display = new JButton("Display Movies");
		display.setForeground(Color.WHITE);
		display.setBackground(Color.BLACK);
		
		filter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				itemBox.removeAllItems();
				if (field.getSelectedItem().equals("Year of Release")) {
					itemBox.addItem("- Select Year of Release -");
					for(Item c : kustore.getItems())
						itemBox.addItem(c.getYear());}
				if (field.getSelectedItem().equals("Genre")) {
					itemBox.addItem("- Select Genre -");
					for(Item c : kustore.getItems())
						itemBox.addItem(c.getGenre());}
				if (field.getSelectedItem().equals("Movie Studio")) {
					itemBox.addItem("- Select Movie Studio -"); 
					for(Item c : kustore.getItems())
						itemBox.addItem(c.getStudio());} 
				
			}
		});
			
		display.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				String s = null;
				
				if (field.getSelectedItem().equals("Year of Release")) {
					for(Item c : kustore.getItems())
						if (c.getYear().equals(itemBox.getSelectedItem()))
						{s=c.getItemName();}
							}
				
				if (field.getSelectedItem().equals("Genre")) {
					for(Item c : kustore.getItems())
						if (c.getGenre().equals(itemBox.getSelectedItem()))
						{s=c.getItemName();}
							}
				if (field.getSelectedItem().equals("Movie Studio")) {
					for(Item c : kustore.getItems())
						if (c.getStudio().equals(itemBox.getSelectedItem()))
						{s=c.getItemName();}
							}
				
				
				
				ArrayList<Item> search = kustore.searchItem(s);
				int vertical = search.size() / 4 + 1;
				if(vertical < 3)
					vertical = 3;
				
				innerpane.removeAll();
				innerpane.setLayout(new GridLayout(vertical, 4, 4, 4));
				
				for(Item i : search)
					innerpane.add(items.get(i));
				for(int i = 0; i < 4 * vertical - search.size(); i++)
					innerpane.add(new JLabel());
				
				innerpane.setPreferredSize(new Dimension(1020, 320 * vertical));
				innerpane.setOpaque(false);
				
				revalidate();
				repaint();
				
				
			}
		});
		
		inner.setLayout(new GridLayout(19, 1, 4, 4));
		inner.add(changedata);
		inner.add(new JLabel());
		inner.add(new JLabel("Change Username"));
		inner.add(changeuser);
		inner.add(new JLabel("Change Password"));
		inner.add(changepass);
		inner.add(new JLabel("Change Phone Number"));
		inner.add(changephone);
		inner.add(new JLabel());
		inner.add(changeerror);
		inner.add(savechanges);
		
		inner.add(new JLabel());
		inner.add(field);
		inner.add(filter);
		inner.add(new JLabel());
		inner.add(itemBox);
		inner.add(display);
		inner.add(new JLabel());
		
		inner.add(deleteacc);
		
		leftpane = new JPanel();
		leftpane.setPreferredSize(new Dimension(352, 0));
		leftpane.setBorder(BorderFactory.createLineBorder(new Color(38, 77, 152)));
		leftpane.setLayout(null);
		leftpane.add(outer);
		outer.setBounds(1, 1, 350, 575);
		
		changedata.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					if(changedata.isSelected())
					{
						savechanges.setEnabled(true);
						changeuser.setEditable(true);
						changepass.setEditable(true);
						changephone.setEditable(true);
					}
					else
					{
						savechanges.setEnabled(false);
						changeuser.setEditable(false);
						changeuser.setText("");
						changepass.setEditable(false);
						changepass.setText("");
						changephone.setEditable(false);
						changephone.setText("");
						changeerror.setText("");
					}
				}
			});
		changeuser.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{changepass.requestFocus();}
			});
		changepass.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{changephone.requestFocus();}
			});
		changephone.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{savechanges.requestFocus();}
			});
		savechanges.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						if(!changeuser.getText().isBlank())
						{
							kustore.getCurrentLogin().changeUsername(changeuser.getText());
			
						}
						if(!(new String(changepass.getPassword())).isBlank())
						{
							kustore.getCurrentLogin().changePassword(new String(changepass.getPassword()));
						}
						if(!changephone.getText().isBlank())
						{
							((Customer) kustore.getCurrentLogin()).changePhoneNum(changephone.getText());
						}
						
						if(!(changeuser.getText().isBlank() && (new String(changepass.getPassword())).isBlank() && changephone.getText().isBlank()))
						{
							kustore.updateCustomers();
							welcome.setText("<html>Welcome, <br>" + kustore.getCurrentLogin().getUsername() + "</html>");
							savechanges.setEnabled(false);
							changeuser.setEditable(false);
							changeuser.setText("");
							changepass.setEditable(false);
							changepass.setText("");
							changephone.setEditable(false);
							changephone.setText("");
							changedata.setSelected(false);
						}
					}
					catch (PersonException err)
					{
						changeerror.setText(err.getMessage());
					}
				}
			});
		deleteacc.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String[] op = {"Yes", "Cancel"};
					String msg = "Are you sure you want to delete your account?";
					int ch = JOptionPane.showOptionDialog(null, msg, "Confirm", -1, 2, null, op, op[1]);

					if(ch == 0)
					{
						kustore.deleteMyAccount();
						((StorePanel) getParent()).setLoginPage();
						((CardLayout) getParent().getLayout()).show(getParent(), "Login Page");
					}
				}
			});
	}
	
	private void setItemPane()
	{
		innerpane = new JPanel();
		JPanel outer = new JPanel();
		
		items = new HashMap<Item, CustomerItemCard>();
		int vertical = kustore.getItems().size() / 4 + 1;
		if(vertical < 3)
			vertical = 3;
		
		innerpane.setLayout(new GridLayout(vertical, 4, 4, 4));
		for(Item i : kustore.getItems())
		{
			CustomerItemCard card = new CustomerItemCard(i, kustore);
			items.put(i, card);
			innerpane.add(card);
		}
		for(int i = 0; i < 4 * vertical - kustore.getItems().size(); i++)
			innerpane.add(new JLabel());
		innerpane.setPreferredSize(new Dimension(1020, 320 * vertical));
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
	
	private class SearchBarListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ArrayList<Item> search = kustore.searchItem(searchbar.getText());
			int vertical = search.size() / 4 + 1;
			if(vertical < 3)
				vertical = 3;
			
			innerpane.removeAll();
			innerpane.setLayout(new GridLayout(vertical, 4, 4, 4));
			
			for(Item i : search)
				innerpane.add(items.get(i));
			for(int i = 0; i < 4 * vertical - search.size(); i++)
				innerpane.add(new JLabel());
			
			innerpane.setPreferredSize(new Dimension(1020, 320 * vertical));
			innerpane.setOpaque(false);
			
			revalidate();
			repaint();
		}
	}
}