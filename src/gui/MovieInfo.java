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


public class MovieInfo extends JPanel {
	private GiftStore kustore;
	private JLabel topimg, welcome, yourcart;
	private JPanel toppane, innerpane;
	private JButton  logout, gotoshop;
	
	public MovieInfo(GiftStore store)
	{
		kustore = store;
		
		setTopPane();
		setInnerPane();
		
		
		setLayout(new BorderLayout());
		add(toppane, BorderLayout.NORTH);
		add(innerpane, BorderLayout.SOUTH);
		
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
		
		welcome = new JLabel("<html>Welcome <br>");
				// + kustore.getCurrentLogin().getUsername() + "</html>", JLabel.CENTER);
		 
		
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

	
	private void setInnerPane() {
		innerpane = new JPanel();
		JPanel outer = new JPanel();
		
		innerpane.setLayout(new GridLayout(1, 1, 8, 8));
		
		Item i = (Item) ((Customer) kustore.getCurrentLogin()).getCart().keySet();
		
		MovieInfoCard card = new MovieInfoCard(i);
		innerpane.add(card);
		innerpane.setOpaque(false);	
		// size not included & link from button 
		
		innerpane.setPreferredSize(new Dimension(1020, 1020));
		innerpane.setOpaque(false);		
		
		
		outer.setLayout(new BorderLayout());
		outer.add(new JLabel("   "), BorderLayout.NORTH);
		outer.add(new JLabel("   "), BorderLayout.EAST);
		outer.add(new JLabel("   "), BorderLayout.WEST);
		outer.add(new JLabel("   "), BorderLayout.SOUTH);
		outer.add(innerpane, BorderLayout.CENTER);
		outer.setOpaque(false);
		
	}
	
	
}
