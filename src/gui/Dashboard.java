package gui;

import entities.Customer;
import entities.GiftStore;
import entities.GiftStoreException;
import entities.Item;
import entities.ItemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dashboard extends JPanel
{
	private GiftStore kustore;
	private HashMap<Item, AdminItemCard> items;
	private JLabel topimg, welcome, adderror, admin;
	private JPanel toppane, leftpane, innerpane;
	private JScrollPane itempane;
	private JTextField searchbar, addname, addprice, addstock,addactors, addyear, addgenre, addruntime, adddirector, addstudio, addproducers;
	private JButton submititem, logout, deletecust, deleteitem, viewReceipts;
	private JToggleButton additem;
	private JComboBox<String> customerBox, itemBox;
	
	public Dashboard(GiftStore store)
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
		admin = new JLabel("ADMIN", JLabel.CENTER);
		admin.setFont(new Font("Calibri", Font.BOLD, 27));
		admin.setForeground(Color.RED);
		admin.setOpaque(false);
		
		innermost.setLayout(new GridLayout(1, 3, 10, 10));
		innermost.add(welcome);
		innermost.add(admin);
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
		
		additem = new JToggleButton("Add Movie");
		additem.setForeground(Color.WHITE);
		additem.setBackground(new Color(38, 77, 152));
		addname = new JTextField();
		addname.setEditable(false);
		addprice = new JTextField();
		addprice.setEditable(false);
		addstock = new JTextField();
		addstock.setEditable(false);
		
		addactors = new JTextField();
		addactors.setEditable(false);
		addyear = new JTextField();
		addyear.setEditable(false);
		addgenre = new JTextField();
		addgenre.setEditable(false);
		addruntime = new JTextField();
		addruntime.setEditable(false);
		adddirector = new JTextField();
		adddirector.setEditable(false);
		addstudio = new JTextField();
		addstudio.setEditable(false);
		addproducers = new JTextField();
		addproducers.setEditable(false);
	
		adderror = new JLabel();
		adderror.setForeground(Color.RED);
		submititem = new JButton("Submit Movie");
		submititem.setEnabled(false);
		submititem.setForeground(Color.WHITE);
		submititem.setBackground(new Color(38, 77, 152));
	
		customerBox = new JComboBox<String>();
		deletecust = new JButton("Delete Customer");
		deletecust.setForeground(Color.WHITE);
		deletecust.setBackground(Color.RED);
		customerBox.setEditable(false);
		customerBox.addItem("- Select User -");
		for(Customer c : kustore.getCustomers())
			customerBox.addItem(c.getUsername());
		
		itemBox = new JComboBox<String>();
		deleteitem = new JButton("Delete Movie");
		deleteitem.setForeground(Color.WHITE);
		deleteitem.setBackground(Color.RED);
		itemBox.setEditable(false);
		itemBox.addItem("- Select Item -");
		for(Item c : kustore.getItems())
			itemBox.addItem(c.getItemName());
		
		viewReceipts = new JButton("View Receipts");
		viewReceipts.setForeground(Color.BLACK);
		viewReceipts.setBackground(new Color(183, 210, 244));
		
		inner.setLayout(new GridLayout(31, 1, 4, 4));
		inner.add(customerBox);
		inner.add(deletecust);
		inner.add(new JLabel());
		inner.add(itemBox);
		inner.add(deleteitem);
		inner.add(new JLabel());
		inner.add(viewReceipts);
		inner.add(new JLabel());
		inner.add(additem);
		inner.add(new JLabel("Movie Title"));
		inner.add(addname);
		inner.add(new JLabel("Price"));
		inner.add(addprice);
		inner.add(new JLabel("Total copies"));
		inner.add(addstock);
		
		inner.add(new JLabel("Actors"));
		inner.add(addactors);
		inner.add(new JLabel("Year of Release"));
		inner.add(addyear);
		inner.add(new JLabel("Genre"));
		inner.add(addgenre);
		inner.add(new JLabel("Running time"));
		inner.add(addruntime);
		inner.add(new JLabel("Director"));
		inner.add(adddirector);
		inner.add(new JLabel("Movie Studio"));
		inner.add(addstudio);
		inner.add(new JLabel("Producer(s)"));
		inner.add(addproducers);
		
		
		inner.add(adderror);
		inner.add(submititem);
		
		leftpane = new JPanel();
		leftpane.setPreferredSize(new Dimension(352, 0));
		leftpane.setBorder(BorderFactory.createLineBorder(new Color(38, 77, 152)));
		leftpane.setLayout(null);
		leftpane.add(outer);
		outer.setBounds(1, 1, 350, 715);
		
		additem.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					if(additem.isSelected())
					{
						submititem.setEnabled(true);
						addname.setEditable(true);
						addprice.setEditable(true);
						addstock.setEditable(true);
						
					
						addactors.setEditable(true);
						addyear.setEditable(true);
						addgenre.setEditable(true);
						addruntime.setEditable(true);
						adddirector.setEditable(true);
						addstudio.setEditable(true);
						addproducers.setEditable(true);
					}
					else
					{
						submititem.setEnabled(false);
						addname.setEditable(false);
						addname.setText("");
						addprice.setEditable(false);
						addprice.setText("");
						addstock.setEditable(false);
						addstock.setText("");
						
					
						addactors.setEditable(false);
						addactors.setText("");
						addyear.setEditable(false);
						addyear.setText("");
						addgenre.setEditable(false);
						addgenre.setText("");
						addruntime.setEditable(false);
						addruntime.setText("");
						adddirector.setEditable(false);
						adddirector.setText("");
						addstudio.setEditable(false);
						addstudio.setText("");
						addproducers.setEditable(false);
						addproducers.setText("");
						
						adderror.setText("");
					}
				}
			});
		addname.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{addprice.requestFocus();}
			});
		addprice.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{addstock.requestFocus();}
			});
		
		
		
		addstock.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addactors.requestFocus();}
		});
		addactors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addyear.requestFocus();}
		});
		addyear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addgenre.requestFocus();}
		});
		addgenre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addruntime.requestFocus();}
		});
		addruntime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{adddirector.requestFocus();}
		});
		adddirector.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addstudio.requestFocus();}
		});
		addstudio.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addproducers.requestFocus();}
		});
		addproducers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{addactors.requestFocus();}
		});
		
		
		addproducers.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{submititem.requestFocus();}
			});
		submititem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						String iname = addname.getText();
						double iprice = Double.parseDouble(addprice.getText());
						int istock = Integer.parseInt(addstock.getText());
						
						String ititle = addactors.getText();
						String iyear = addyear.getText();
						String igenre = addgenre.getText();
						String iruntime = addruntime.getText();
						String idirector = adddirector.getText();
						String istudio = addstudio.getText();
						String iproducers = addproducers.getText();
						
						
						BufferedImage chosen = (new ImageSelector()).getImage();
						
						kustore.registerItem(iname, iprice, istock, chosen, ititle, iyear, igenre, iruntime, idirector, istudio, iproducers);
						
						items.clear();
						int vertical = kustore.getItems().size() / 4 + 1;
						if(vertical < 3)
							vertical = 3;
						
						innerpane.removeAll();
						innerpane.setLayout(new GridLayout(vertical, 4, 4, 4));
						
						for(Item i : kustore.getItems())
						{
							AdminItemCard card = new AdminItemCard(i, kustore);
							items.put(i, card);
							innerpane.add(card);
						}
						for(int i = 0; i < 4 * vertical - kustore.getItems().size(); i++)
							innerpane.add(new JLabel());
						innerpane.setPreferredSize(new Dimension(1020, 320 * vertical));
						innerpane.setOpaque(false);	
						
						revalidate();
						repaint();
						
						itemBox.addItem(iname);
						
						submititem.setEnabled(false);							
						addname.setEditable(false);
						addname.setText("");
						addprice.setEditable(false);
						addprice.setText("");
						addstock.setEditable(false);
						addstock.setText("");
						
						
						addactors.setEditable(false);
						addactors.setText("");
						addyear.setEditable(false);
						addyear.setText("");
						addgenre.setEditable(false);
						addgenre.setText("");
						addruntime.setEditable(false);
						addruntime.setText("");
						adddirector.setEditable(false);
						adddirector.setText("");
						addstudio.setEditable(false);
						addstudio.setText("");
						addproducers.setEditable(false);
						addproducers.setText("");
						
						additem.setSelected(false);
					}
					catch (GiftStoreException err1)
					{
						adderror.setText(err1.getMessage());
					}
					catch (ItemException err2)
					{
						adderror.setText(err2.getMessage());
					}
					catch(NumberFormatException err3)
					{
						adderror.setText("Please enter valid numerical values");
					}
					catch(IOException err4)
					{
						adderror.setText("Your image file does not exist."); 
					}
					catch(IllegalArgumentException err5)
					{
						// user has not selected an image
						adderror.setText("Please select an image");
					}
					catch(NullPointerException err6)
					{
						// user has selected something other than image file
						adderror.setText("Please select an image file");
					}
				}
			});
		deletecust.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(!customerBox.getSelectedItem().equals("- Select User -"))
					{
						String[] op = {"Yes", "Cancel"};
						String chosen = (String) customerBox.getSelectedItem();
						String msg = "Are you sure you want to delete " + chosen + "'s account?";
						int ch = JOptionPane.showOptionDialog(null, msg, "Confirm", -1, 2, null, op, op[1]);
	
						if(ch == 0)
						{
							kustore.deleteCustomer(chosen);
							customerBox.removeItem(chosen);
						}
						
						customerBox.setSelectedItem("- Select User -");
					}
				}
			});
		deleteitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!itemBox.getSelectedItem().equals("- Select Item -"))
				{
					String[] op = {"Yes", "Cancel"};
					String chosen = (String) itemBox.getSelectedItem();
					String msg = "Are you sure you want to delete \"" + chosen + "\" from KU Gift Shop?";
					int ch = JOptionPane.showOptionDialog(null, msg, "Confirm", -1, 2, null, op, op[1]);
	
					if(ch == 0)
					{
						Item toRemove = kustore.findItem(chosen);
						innerpane.remove(items.get(toRemove));
						innerpane.add(new JLabel());
						revalidate();
						repaint();
						
						items.remove(toRemove);
						kustore.deleteItem(chosen);
						itemBox.removeItem(chosen);
					}
					
					itemBox.setSelectedItem("- Select Item -");
				}
			}
		});
		viewReceipts.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JFileChooser choose = new JFileChooser("src\\entities\\data\\receipts");
					JFrame chooseFrame = new JFrame();
					chooseFrame.setIconImage((new ImageIcon("src\\gui\\data\\kulogo.png")).getImage());
					
					choose.setDialogTitle("Open Receipt");
					choose.setPreferredSize(new Dimension(1000, 563));
					choose.showOpenDialog(chooseFrame);
					
					try 
					{
						File selected = choose.getSelectedFile();
						Scanner inFile = new Scanner(new FileReader(selected));
						
						String message = "";
						while(inFile.hasNext())
							message += inFile.nextLine() + "\n";
						
						if(selected.getAbsolutePath().endsWith(".txt"))
							new ReceiptFrame(message);	
						
						inFile.close();
					} 
					catch (FileNotFoundException err1) 
					{
						// do nothing
					}
					catch (NullPointerException err3)
					{
						// do nothing
					}
				}
			});
	}
	
	private void setItemPane()
	{
		innerpane = new JPanel();
		JPanel outer = new JPanel();
		
		items = new HashMap<Item, AdminItemCard>();
		int vertical = kustore.getItems().size() / 4 + 1;
		if(vertical < 3)
			vertical = 3;
		
		innerpane.setLayout(new GridLayout(vertical, 4, 4, 4));
		for(Item i : kustore.getItems())
		{
			AdminItemCard card = new AdminItemCard(i, kustore);
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