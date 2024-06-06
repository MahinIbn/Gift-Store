package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReceiptFrame extends JFrame
{
	private JLabel message;
	
	public ReceiptFrame(String msg)
	{
		super("Receipt: ");
		setIconImage((new ImageIcon("src\\gui\\data\\kulogo.png")).getImage());
		
		String modified = "<html>" + msg.replaceAll("\n", "<br>") + "</html>";
		message = new JLabel(modified);
		add(message);
		
		setSize(350, 420);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}