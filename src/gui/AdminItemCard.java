package gui;

import entities.GiftStore;
import entities.Item;
import entities.ItemException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminItemCard extends JPanel {

	protected GiftStore kustore;
	protected Item item;
	protected JLabel itemimage, stock, added, error, AED;
	protected JButton increment, decrement, save, info;
	protected JTextField amount, itemname, itemprice;

	public AdminItemCard(Item i, GiftStore store) {
		JPanel outer = new JPanel(), inner = new JPanel();
		JPanel inner1 = new JPanel(), inner2 = new JPanel(), innermost = new JPanel();

		item = i;
		kustore = store;

		itemimage = new JLabel("", JLabel.CENTER);
		Image img = (new ImageIcon(item.getImagePath())).getImage();
		if (250 > 130 * img.getWidth(null) / img.getHeight(null))
			itemimage.setIcon(new ImageIcon(
					img.getScaledInstance(130 * img.getWidth(null) / img.getHeight(null), 130, Image.SCALE_DEFAULT)));
		else
			itemimage.setIcon(new ImageIcon(
					img.getScaledInstance(250, 250 * img.getHeight(null) / img.getWidth(null), Image.SCALE_DEFAULT)));
		itemimage.setOpaque(true);

		stock = new JLabel("STOCK", JLabel.CENTER);
		added = new JLabel("ADDED", JLabel.CENTER);
		error = new JLabel(" ", JLabel.CENTER);
		error.setForeground(Color.RED);
		AED = new JLabel("AED");

		
		 increment = new JButton("+"); increment.setForeground(Color.WHITE);
		 increment.setBackground(new Color(38, 77, 152)); increment.setFont(new Font("Calibri", Font.PLAIN, 17)); decrement = new JButton("-");
		 decrement.setForeground(Color.WHITE); decrement.setBackground(new Color(38,77, 152)); decrement.setFont(new Font("Calibri", Font.PLAIN, 17));
		 
		save = new JButton("SAVE");
		save.setForeground(Color.WHITE);
		save.setBackground(new Color(38, 77, 152));


		amount = new JTextField("1");

		
		amount.setHorizontalAlignment(JTextField.CENTER);
		
		itemname = new JTextField(item.getItemName());
		itemname.setHorizontalAlignment(JTextField.CENTER);
		itemname.setFont(new Font("Calibri", Font.BOLD, 25));
		itemname.setForeground(new Color(38, 77, 152));
		itemprice = new JTextField(item.getPrice() + "");
		itemprice.setHorizontalAlignment(JTextField.RIGHT);

		
		innermost.setLayout(new GridLayout(1, 3)); innermost.add(decrement);
		innermost.add(amount); innermost.add(increment); innermost.setOpaque(false);
		

		inner1.setLayout(new GridLayout(1, 4, 8, 8));
		inner1.add(stock);
		inner1.add(added);
		inner1.add(itemprice);
		inner1.add(AED);
		inner1.setOpaque(false);

		inner2.setLayout(new GridLayout(1, 2, 4, 4));
		inner2.add(innermost);
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

		setPlusMinus();
		setSaveAction();
		setImageAction();

		stock.setText("In Stock: ");
		stock.setVerticalAlignment(JLabel.BOTTOM);
		added.setText("<html>Sold: <br>" + item.getNumSold() + "</html>");
		save.setText("Save Changes");
		amount.setText("" + item.getNumStock());
	}

	public void setSaveAction() {
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String iname = itemname.getText();
					double iprice = Double.parseDouble(itemprice.getText());
					int istock = Integer.parseInt(amount.getText());

					item.changeItemName(iname);
					item.changeItemPrice(iprice);
					item.changeNumStock(istock);

					kustore.updateItems();

					error.setText(" ");
				} catch (ItemException err1) {
					error.setText(err1.getMessage());
				} catch (NumberFormatException err2) {
					error.setText("Please enter valid numerical values");
				}
				itemname.setText(item.getItemName());
				itemprice.setText("" + item.getPrice());
				amount.setText("" + item.getNumStock());
			}
		});
	}
	
	
	  public void setPlusMinus() { increment.addActionListener(new ActionListener()
	  { public void actionPerformed(ActionEvent e) { amount.setText("" +
	  (Integer.parseInt(amount.getText()) + 1)); } });
	  decrement.addActionListener(new ActionListener() { public void
	  actionPerformed(ActionEvent e) { if(Integer.parseInt(amount.getText()) > 0)
	  amount.setText("" + (Integer.parseInt(amount.getText()) - 1)); } }); }
	 

	public void setImageAction() {
		itemimage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedImage chosen = (new ImageSelector()).getImage();
					item.changeImage(chosen);
					error.setText(" ");

					Image img = (new ImageIcon(item.getImagePath())).getImage();
					if (250 > 130 * img.getWidth(null) / img.getHeight(null))
						itemimage.setIcon(new ImageIcon(img.getScaledInstance(
								130 * img.getWidth(null) / img.getHeight(null), 130, Image.SCALE_DEFAULT)));
					else
						itemimage.setIcon(new ImageIcon(img.getScaledInstance(250,
								250 * img.getHeight(null) / img.getWidth(null), Image.SCALE_DEFAULT)));
				} catch (IOException err1) {
					error.setText("Your image file does not exist.");
				} catch (IllegalArgumentException err2) {
					// do nothing, user has not selected an image
				} catch (NullPointerException err3) {
					// user has selected something other than image file
					error.setText("Please select an image file");
				}
			}
		});
	}

}