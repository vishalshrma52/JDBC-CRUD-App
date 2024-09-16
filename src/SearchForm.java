import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SearchForm
{
 JFrame fr=new JFrame("Select Form");
 JLabel la=new JLabel("Enter product id: ");
 JTextField tb=new JTextField();
 JButton bt=new JButton("Show Record");
 PreparedStatement ps;
 JPanel pa=new JPanel();
 JLabel [] lah=new JLabel[3];
 JLabel [] lav=new JLabel[3];
 
 public SearchForm()
 {
   fr.setSize(500,500);
   fr.setLocationRelativeTo(null);
   fr.setResizable(false);
   //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   fr.getContentPane().setBackground(Color.GREEN);
   addComponents();
   dbConnection();
   //fr.setVisible(true);
 }
 
 private void addComponents()
 {
   fr.setLayout(null);
   Font fo=new Font("arial",0,19);
   la.setBounds(50,50,200,30);
   fr.add(la);
   la.setFont(fo);
   la.setForeground(Color.blue);
   tb.setBounds(220,50,200,30);
   fr.add(tb);
   tb.setFont(fo);
   bt.setBounds(175,100,150,30);
   fr.add(bt);
   bt.setForeground(Color.blue);
   bt.setBackground(Color.cyan);
   bt.addActionListener(new ShowListener());
   pa.setBounds(50,150,400,280);
   fr.add(pa);
   pa.setBackground(Color.yellow);
   pa.setVisible(false);
   addShowPanel();
 }
 private void addShowPanel()
 {
   pa.setLayout(null);
   int y=30;
   String [] arr={"name","price","quantity"};
   Font fo=new Font("arial",0,19);
   for(int i=0;i<3;i++)
   {
     lah[i]=new JLabel("product "+arr[i]);
     lah[i].setBounds(20,y,200,30);
     lah[i].setFont(fo);
     lah[i].setForeground(Color.blue);
     pa.add(lah[i]);  
     lav[i]=new JLabel();
     lav[i].setBounds(220,y,250,30);
     lav[i].setFont(fo);
     lav[i].setForeground(Color.magenta);
     pa.add(lav[i]);
     y+=70;
     
   }
 }
 private void dbConnection()
 {
   try
   {
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","mysql");
     String query="select * from Productinfo where pid=?";
     ps=cn.prepareStatement(query);
     System.out.println("Connected........");
   }
   catch(Exception ex)
   {
     System.out.println(ex);
   }
 }
 class ShowListener implements ActionListener
 {
   public void actionPerformed(ActionEvent evt)
   {
     
     try
     {
       ps.setString(1,tb.getText());
       ResultSet rst=ps.executeQuery();
       if(rst.next())
       {
         pa.setVisible(true);
         lav[0].setText(rst.getString(2)); 
         lav[1].setText(rst.getString(3));
         lav[2].setText(rst.getString(4));
       }
       else
       {
         JOptionPane.showMessageDialog(fr,"Record does not exist");
         pa.setVisible(false);
       }
     }
     catch(Exception ex)
     {
       System.out.println(ex);
     }
   }
 }
}