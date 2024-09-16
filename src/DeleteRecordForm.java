import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class DeleteRecordForm
{
 JFrame fr=new JFrame("Delete Form");
 JLabel la=new JLabel("Enter product id: ");
 JTextField tb=new JTextField();
 JButton bt=new JButton("Delete Record");
 PreparedStatement ps;
 
 public DeleteRecordForm()
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
   la.setBounds(20,150,200,30);
   fr.add(la);
   la.setFont(fo);
   la.setForeground(Color.blue);
   tb.setBounds(220,150,200,30);
   fr.add(tb);
   tb.setFont(fo);
   bt.setBounds(175,250,150,30);
   fr.add(bt);
   bt.setForeground(Color.blue);
   bt.setBackground(Color.cyan);
   bt.addActionListener(new DeleteListener());
 }
 private void dbConnection()
 {
   try
   {
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","mysql");
     String query="delete from Productinfo where pid=?";
     ps=cn.prepareStatement(query);
     System.out.println("Connected........");
   }
   catch(Exception ex)
   {
     System.out.println(ex);
   }
 }
 class DeleteListener implements ActionListener
 {
   public void actionPerformed(ActionEvent evt)
   {
     
     try
     {
       ps.setString(1,tb.getText());
       int n=ps.executeUpdate();
       if(n>=1)
          JOptionPane.showMessageDialog(fr,"Product record has been deleted succesfully");
       else
          JOptionPane.showMessageDialog(fr,"Product record does not exist");
     }
     catch(Exception ex)
     {
       System.out.println(ex);
     }
   }
 }
}

 
 