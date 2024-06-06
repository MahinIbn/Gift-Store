package gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageSelector extends JFileChooser
{
	private final JFrame frame;
	private BufferedImage img;
	
	public ImageSelector() throws IOException, IllegalArgumentException, NullPointerException
	{
		frame = new JFrame();
		frame.setIconImage((new ImageIcon("src\\gui\\data\\kulogo.png")).getImage());
		
		setDialogTitle("Open Image");
		setPreferredSize(new Dimension(1000, 563));
		showOpenDialog(frame);
		
		try
		{
			img = ImageIO.read(getSelectedFile());
		}
		catch (IOException err1)
		{
			throw err1;
		}
		catch (IllegalArgumentException err2) // occurs when user does not select a file
		{
			throw err2;
		}
		if (img == null)
			throw new NullPointerException(); // img cannot be null
	}
	
	public BufferedImage getImage() {return img;}
}