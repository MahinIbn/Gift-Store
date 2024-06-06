package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class StoreFrame extends JFrame
{
	public StorePanel masterPanel;
	
	public StorePanel getMasterPanel() {
		return masterPanel;
	}


	public StoreFrame()
	{
		super("KU Gift Store");
		setIconImage((new ImageIcon("src\\gui\\data\\kulogo.png")).getImage());
		masterPanel = new StorePanel();
		add(masterPanel);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1000, 563);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new StoreFrame();
	}
}