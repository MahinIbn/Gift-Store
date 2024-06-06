package gui;

import entities.GiftStore;
import entities.Item;
import entities.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MovieInfoCard extends JFrame{
	protected GiftStore kustore;
	protected Item item;
	protected JLabel itemimage, title, actors, year, genre, runtime, director, studio, producer;
	
	public MovieInfoCard(Item i)
	{
		setTitle("Movie Details");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout (1,1));
		
		JPanel outer = new JPanel();
		JPanel 	inner = new JPanel();
		
		item=i;
		//kustore=store;
		
		itemimage = new JLabel("", JLabel.CENTER);
		Image img = (new ImageIcon(item.getImagePath())).getImage();
		if(170 > 125 * img.getWidth(null) / img.getHeight(null))
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(400, 200, Image.SCALE_SMOOTH)));
		else
			itemimage.setIcon(new ImageIcon(img.getScaledInstance(400, 200 , Image.SCALE_SMOOTH)));
		itemimage.setOpaque(true);
		
		
		
		
		title = new JLabel(""+ item.getItemName() , JLabel.CENTER);
		title.setFont(new Font("Calibri", Font.PLAIN, 20));
		actors = new JLabel("Actors: " +item.getActors(), JLabel.CENTER);
		actors.setFont(new Font("Calibri", Font.PLAIN, 15));
		year = new JLabel("Year: "+item.getYear(), JLabel.CENTER);
		year.setFont(new Font("Calibri", Font.PLAIN, 15));
		genre= new JLabel("Genre: "+ item.getGenre(), JLabel.CENTER);
		genre.setFont(new Font("Calibri", Font.PLAIN, 15));
		runtime = new JLabel("Runtime: "+ item.getRuntime(), JLabel.CENTER);
		runtime.setFont(new Font("Calibri", Font.PLAIN, 15));
		director = new JLabel("Director: " + item.getDirector(), JLabel.CENTER);
		director.setFont(new Font("Calibri", Font.PLAIN, 15));
		studio = new JLabel("Studio: "+ item.getStudio(),JLabel.CENTER);
		studio.setFont(new Font("Calibri", Font.PLAIN, 15));
		producer = new JLabel("Producers: "+ item.getProducers(), JLabel.CENTER);
		producer.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		inner.setLayout(new GridLayout(8, 1, 8, 8));
		inner.add(title);
		inner.add(actors);
		inner.add(year);
		inner.add(genre);
		inner.add(runtime);
		inner.add(director);
		inner.add(studio);
		inner.add(producer);
		inner.setOpaque(false);
		
		outer.setLayout(new GridLayout(1, 2, 8, 8));
		outer.add(itemimage);
		outer.add(inner);
		outer.setOpaque(false);
		
		setLayout(new BorderLayout());
		add(new JLabel("   "), BorderLayout.NORTH);
		add(new JLabel("   "), BorderLayout.SOUTH);
		add(new JLabel("   "), BorderLayout.EAST);
		add(new JLabel("   "), BorderLayout.WEST);
		add(outer, BorderLayout.CENTER);
		
		
		//setBorder(BorderFactory.createLineBorder(Color.WHITE));
		//setBackground(new Color(183, 210, 244));
		//setOpaque(true);
		
		pane.add(outer);
		pane.setBackground(new Color(183, 210, 244));
		setVisible(true);
		setSize(800,300);
		
	}
	
}
