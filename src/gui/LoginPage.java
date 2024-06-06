package gui;

import entities.GiftStore;
import entities.GiftStoreException;
import entities.PersonException;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginPage extends JLayeredPane
{
	private GiftStore kustore;
	private JLabel bg;
	private JTabbedPane options;
	private JPanel customerLogin, customerReg, adminLogin;
	private JTextField loginUser, regName, regUser, regPhone, adminUser;
	private JPasswordField loginPass, regPass, adminPass;
	private JLabel loginError, regError, adminError;
	private JButton loginSubmit, regSubmit, adminSubmit;
	
	public LoginPage(GiftStore store)
	{
		kustore = store;
		setBgLabel();
		setPane();
		setCustomerLogin();
		setCustomerRegister();
		setAdminLogin();
		
		setLayout(null);
		add(options);
		add(bg);
	}
	
	private void setBgLabel()
	{
		bg = new JLabel(new ImageIcon("src\\gui\\data\\backg.jpg"));
		bg.setOpaque(false);
		
		addComponentListener(new ComponentAdapter()
			{
				// scaled background image maintaining aspect ratio
				public void componentResized(ComponentEvent e)
				{
					ImageIcon bgicon = new ImageIcon("src\\gui\\data\\backg.jpg");
					int width = getWidth(), height = getHeight();
					bg.setBounds(0, 0, width, height);
					if(width >= height * 1920 / 1080)
						bg.setIcon(new ImageIcon(bgicon.getImage().getScaledInstance(width, width * 1080 / 1920, Image.SCALE_DEFAULT)));
					else
						bg.setIcon(new ImageIcon(bgicon.getImage().getScaledInstance(height * 1920 / 1080, height, Image.SCALE_DEFAULT)));
				}
			});
	}
	
	private void setPane()
	{
		options = new JTabbedPane();
		customerLogin = new JPanel();
		customerReg = new JPanel();
		adminLogin = new JPanel();
		
		options.addTab("Login", customerLogin);
		options.addTab("Register", customerReg);
		options.addTab("Admin", adminLogin);
		options.setOpaque(false);
		
		addComponentListener(new ComponentAdapter()
			{
				// scales JTabbedPane maintaining relative position to background image
				public void componentResized(ComponentEvent e)
				{
					int width = getWidth(), height = getHeight();
					if(width >= height * 1920 / 1080)
						options.setBounds(width/2 - width*175/1536, height/2 - width * 3/32, width*175/768, width*35/128);
					else
						options.setBounds(width/2 - height*162/801, height/3, height*324/801, height*216/445);
				} 
			});
		
		options.addChangeListener(new ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
					Component p = options.getSelectedComponent();
					if (p == customerLogin)
						loginUser.requestFocus();
					else if (p == customerReg)
						regUser.requestFocus();
					else if (p == adminLogin)
						adminUser.requestFocus();
					else
						options.requestFocus();
				}
			});
	}

	private void setCustomerLogin()
	{
		JPanel temp = new JPanel();
		
		customerLogin.setLayout(new BorderLayout());
		customerLogin.add(new JLabel("   "), BorderLayout.SOUTH);
		customerLogin.add(new JLabel("   "), BorderLayout.EAST);
		customerLogin.add(new JLabel("   "), BorderLayout.WEST);
		customerLogin.add(temp, BorderLayout.CENTER);
		
		loginUser = new JTextField();
		loginPass = new JPasswordField();
		loginError = new JLabel();
		loginError.setForeground(Color.RED);
		loginSubmit = new JButton("Submit");
		loginSubmit.setForeground(Color.BLACK);
		loginSubmit.setBackground(new Color(0,255,255));
		
		temp.setLayout(new GridLayout(10, 1));
		temp.add(new JLabel("Username"));
		temp.add(loginUser);
		temp.add(new JLabel("Password"));
		temp.add(loginPass);
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(loginError);
		temp.add(loginSubmit);
		
		loginUser.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{loginPass.requestFocus();}
			});
		loginPass.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						kustore.customerLogin(loginUser.getText(), new String(loginPass.getPassword()));
						((StorePanel) getParent()).setShoppingPage();
						((CardLayout) getParent().getLayout()).show(getParent(), "Shopping Page");
					}
					catch (GiftStoreException err)
					{
						loginError.setText(err.getMessage());
					}
				}
			});
		loginSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					kustore.customerLogin(loginUser.getText(), new String(loginPass.getPassword()));
					((StorePanel) getParent()).setShoppingPage();
					((CardLayout) getParent().getLayout()).show(getParent(), "Shopping Page");
				}
				catch (GiftStoreException err)
				{
					loginError.setText(err.getMessage());
				}
			}
		});
	}
	
	private void setCustomerRegister()
	{
		JPanel temp = new JPanel();
		
		customerReg.setLayout(new BorderLayout());
		customerReg.add(new JLabel("   "), BorderLayout.SOUTH);
		customerReg.add(new JLabel("   "), BorderLayout.EAST);
		customerReg.add(new JLabel("   "), BorderLayout.WEST);
		customerReg.add(temp, BorderLayout.CENTER);
		
		regUser = new JTextField();
		regPass = new JPasswordField();
		regName = new JTextField();
		regPhone = new JTextField();
		regError = new JLabel();
		regError.setForeground(Color.RED);
		regSubmit = new JButton("Submit");
		regSubmit.setForeground(Color.BLACK);
		regSubmit.setBackground(new Color(0,255,255));
		
		temp.setLayout(new GridLayout(10, 1));
		temp.add(new JLabel("Username"));
		temp.add(regUser);
		temp.add(new JLabel("Password"));
		temp.add(regPass);
		temp.add(new JLabel("Name"));
		temp.add(regName);
		temp.add(new JLabel("Phone Number"));
		temp.add(regPhone);
		temp.add(regError);
		temp.add(regSubmit);
		
		regUser.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{regPass.requestFocus();}
			});
		regPass.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{regName.requestFocus();}
			});
		regName.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{regPhone.requestFocus();}
			});
		regPhone.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					kustore.registerCustomer(regUser.getText(), new String(regPass.getPassword()), regName.getText(), regPhone.getText());
					((StorePanel) getParent()).setShoppingPage();
					((CardLayout) getParent().getLayout()).show(getParent(), "Shopping Page");
				}
				catch (GiftStoreException err)
				{
					regError.setText(err.getMessage());
				}
				catch (PersonException err)
				{
					regError.setText(err.getMessage());
				}
			}
		});
		regSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					kustore.registerCustomer(regUser.getText(), new String(regPass.getPassword()), regName.getText(), regPhone.getText());
					((StorePanel) getParent()).setShoppingPage();
					((CardLayout) getParent().getLayout()).show(getParent(), "Shopping Page");
				}
				catch (GiftStoreException err)
				{
					regError.setText(err.getMessage());
				}
				catch (PersonException err)
				{
					regError.setText(err.getMessage());
				}
			}
		});
	}
	
	private void setAdminLogin()
	{
		JPanel temp = new JPanel();
		
		adminLogin.setLayout(new BorderLayout());
		adminLogin.add(new JLabel("   "), BorderLayout.SOUTH);
		adminLogin.add(new JLabel("   "), BorderLayout.EAST);
		adminLogin.add(new JLabel("   "), BorderLayout.WEST);
		adminLogin.add(temp, BorderLayout.CENTER);
		
		adminUser = new JTextField();
		adminPass = new JPasswordField();
		adminError = new JLabel();
		adminError.setForeground(Color.RED);
		adminSubmit = new JButton("Submit");
		adminSubmit.setForeground(Color.BLACK);
		adminSubmit.setBackground(new Color(0,255,255));
		
		temp.setLayout(new GridLayout(10, 1));
		temp.add(new JLabel("Username"));
		temp.add(adminUser);
		temp.add(new JLabel("Password"));
		temp.add(adminPass);
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(new JLabel());
		temp.add(adminError);
		temp.add(adminSubmit);
		
		
		adminUser.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{adminPass.requestFocus();}
			});
		adminPass.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						kustore.adminLogin(adminUser.getText(), new String(adminPass.getPassword()));
						((StorePanel) getParent()).setDashboard();
						((CardLayout) getParent().getLayout()).show(getParent(), "Dashboard");
					}
					catch (GiftStoreException err)
					{
						adminError.setText(err.getMessage());
					}
				}
			});
		adminSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					kustore.adminLogin(adminUser.getText(), new String(adminPass.getPassword()));
					((StorePanel) getParent()).setDashboard();
					((CardLayout) getParent().getLayout()).show(getParent(), "Dashboard");
				}
				catch (GiftStoreException err)
				{
					adminError.setText(err.getMessage());
				}
			}
		});
	}
}