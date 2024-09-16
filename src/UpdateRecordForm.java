import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateRecordForm
{
 JFrame fr=new JFrame("Update Form");
 JLabel la=new JLabel("Enter product id: ");
 JTextField tb=new JTextField();
 JButton bt=new JButton("Get Record");
 PreparedStatement ps,ps1;
 JPanel pa=new JPanel();
 JLabel [] la1=new JLabel[3];
 JTextField [] tb1=new JTextField[3];
 JButton bt1=new JButton("Update Record");
 
 public UpdateRecordForm()
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
   bt.addActionListener(new GetListener());
   pa.setBounds(50,150,400,280);
   fr.add(pa);
   pa.setBackground(Color.yellow);
   pa.setVisible(false);
   addEditPanel();
 }
 private void addEditPanel()
 {
   pa.setLayout(null);
   int y=30;
   String [] arr={"name","price","quantity"};
   Font fo=new Font("arial",0,19);
   for(int i=0;i<3;i++)
   {
     la1[i]=new JLabel("Enter product "+arr[i]);
     la1[i].setBounds(20,y,200,30);
     la1[i].setFont(fo);
     la1[i].setForeground(Color.blue);
     pa.add(la1[i]);  
     tb1[i]=new JTextField();
     tb1[i].setBounds(220,y,250,30);
     tb1[i].setFont(fo);
     pa.add(tb1[i]);
     y+=70;
     
   }
   bt1.setBounds(130,230,150,30);
   pa.add(bt1);
   bt1.setForeground(Color.blue);
   bt1.setBackground(Color.cyan);
   bt1.addActionListener(new UpdateListener());
 }
 private void dbConnection()
 {
   try
   {
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","mysql");
     String query="select * from Productinfo where pid=?";
     ps=cn.prepareStatement(query);
     ps1=cn.prepareStatement("update productinfo set name=?,price=?,quantity=? where pid=?");
     System.out.println("Connected........");
   }
   catch(Exception ex)
   {
     System.out.println(ex);
   }
 }
 class GetListener implements ActionListener
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
         tb1[0].setText(rst.getString(2)); 
         tb1[1].setText(rst.getString(3));
         tb1[2].setText(rst.getString(4));
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
 class UpdateListener implements ActionListener
 {
   public void actionPerformed(ActionEvent evt)
   {
    try
    {
      ps1.setString(1,tb1[0].getText());
      ps1.setString(2,tb1[1].getText());
      ps1.setString(3,tb1[2].getText());
      ps1.setString(4,tb.getText());
      ps1.executeUpdate();
      JOptionPane.showMessageDialog(fr,"Record has been Updated sucessfully");
      pa.setVisible(false);
    }
    catch(Exception ex)
    {
       System.out.println(ex);
    }
   }
 }
}