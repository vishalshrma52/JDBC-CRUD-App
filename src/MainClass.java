import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainClass 
{
	JFrame fr=new JFrame("MENU");
	JButton [] bt=new JButton[5];
	InsertRecordForm insert=new InsertRecordForm();
	ProductListForm list=new ProductListForm();
	UpdateRecordForm update=new UpdateRecordForm();
	DeleteRecordForm delete=new DeleteRecordForm();
	SearchForm srch=new SearchForm();
	public MainClass() 
	{
		fr.setSize(500,500);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		fr.getContentPane().setBackground(Color.GREEN);
		fr.setLayout(null);
		addbuttons();
		fr.setVisible(true);
	}
	private void addbuttons() 
	{
		int y=70;
		String [] str= {"Insert","Show","Update","Delete","Search"};
		Font fo=new Font("arial",0,19);
		MenuListener listener =new MenuListener();
		for(int i=0;i<5;i++) 
		{
			bt[i]=new JButton(str[i]+" Record");
			bt[i].setBounds(150,y,200,30);
			bt[i].setFont(fo);
			bt[i].setBackground(Color.cyan);
			bt[i].setForeground(Color.blue);
			bt[i].addActionListener(new MenuListener());
			fr.add(bt[i]);
			y+=70;
		}
		bt[1].setText("Show All Record");
	}
	class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			JButton bb=(JButton)evt.getSource();
			if(bb==bt[0])//insert button clicked
			   insert.fr.setVisible(true);
			else if(bb==bt[1])//show list button clicked
			   list.fr.setVisible(true);
			else if(bb==bt[2])//update button clicked
			   update.fr.setVisible(true);
			else if(bb==bt[3])//delete button clicked
			   delete.fr.setVisible(true);
			else if(bb==bt[4])//select button clicked
			   srch.fr.setVisible(true);
		}
	}
	public static void main(String[] args) 
	{
		new MainClass();
	}
}
