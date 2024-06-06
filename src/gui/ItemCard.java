package gui;

import entities.GiftStore;
import entities.Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class ItemCard extends JPanel
{
	protected GiftStore kustore;
	public Item item;
	protected JLabel itemimage, stock, added, error, AED; 
	protected JButton increment, decrement, save, info;
	protected JTextField amount, itemname, itemprice;
	
	public ItemCard(Item i, GiftStore store)
	{
		JPanel outer = new JPanel(), inner = new JPanel();
		JPanel inner1 = new JPanel(), inner2 = new JPanel(), innermost = new JPanel();
		
	
		item = i;
		kustore = store;
		
		itemimage = new JLabel("", JLabel.CENTER);
		Image img = (new ImageIcon(item.getImagePath())).getImage();
		if(250 > 130 * img.getWidth(null) / img.getHeight(null))
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(130 * img.getWidth(null) / img.getHeight(null), 130, Image.SCALE_DEFAULT)));
		else
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(250, 250 * img.getHeight(null) / img.getWidth(null), Image.SCALE_DEFAULT)));
		itemimage.setOpaque(true);
		
		stock = new JLabel("STOCK", JLabel.CENTER);
		added = new JLabel("ADDED", JLabel.CENTER);
		error = new JLabel(" ", JLabel.CENTER);
		error.setForeground(Color.RED);
		AED = new JLabel("AED");
		
		/*
		 * increment = new JButton("+"); increment.setForeground(Color.WHITE);
		 * increment.setBackground(new Color(38, 77, 152)); increment.setFont(new
		 * Font("Calibri", Font.PLAIN, 17)); decrement = new JButton("-");
		 * decrement.setForeground(Color.WHITE); decrement.setBackground(new Color(38,
		 * 77, 152)); decrement.setFont(new Font("Calibri", Font.PLAIN, 17));
		 */
		save = new JButton("SAVE");
		save.setForeground(Color.WHITE);
		save.setBackground(new Color(38, 77, 152));
		
		
		
		info = new JButton("More Info"); 
		info.setForeground(Color.WHITE);
		info.setBackground(new Color(38, 77, 152));
		 
		amount = new JTextField("1");

		/*
		 * amount.setHorizontalAlignment(JTextField.CENTER);
		 */
		itemname = new JTextField(item.getItemName());
		itemname.setHorizontalAlignment(JTextField.CENTER);
		itemname.setFont(new Font("Calibri", Font.BOLD, 25));
		itemname.setForeground(new Color(38, 77, 152));
		itemprice = new JTextField(item.getPrice() + "");
		itemprice.setHorizontalAlignment(JTextField.RIGHT);
		
		
		/*
		 * innermost.setLayout(new GridLayout(1, 3)); innermost.add(decrement);
		 * innermost.add(amount); innermost.add(increment); innermost.setOpaque(false);
		 */
		
		inner1.setLayout(new GridLayout(1, 4, 8, 8));
		inner1.add(stock);
		inner1.add(added);
		inner1.add(itemprice);
		inner1.add(AED);
		inner1.setOpaque(false);
		
		inner2.setLayout(new GridLayout(1, 2, 4, 4));
		inner2.add(info);
		inner2.add(save);
		inner2.setOpaque(false);
		
		inner.setLayout(new GridLayout(3, 1, 8, 8));
		inner.add(itemname);
		inner.add(inner1);
		inner.add(inner2);
		inner.setOpaque(false);
		
		outer.setLayout(new GridLayout(2, 1, 8, 8));
		outer.add(itemimage);
		outer.add(inner);
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
		
		/* setPlusMinus(); */
		setSaveAction();
		setImageAction();
		
		//setMovieAction();
		
		
	}
	
	/*
	 * public void setPlusMinus() { increment.addActionListener(new ActionListener()
	 * { public void actionPerformed(ActionEvent e) { amount.setText("" +
	 * (Integer.parseInt(amount.getText()) + 1)); } });
	 * decrement.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { if(Integer.parseInt(amount.getText()) > 0)
	 * amount.setText("" + (Integer.parseInt(amount.getText()) - 1)); } }); }
	 */
	
	public Item getItem() {return item;}
	
	public abstract void setSaveAction();
	public abstract void setImageAction();
	
//	public abstract void setMovieAction();
}