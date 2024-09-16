import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.sql.*;
public class ProductListForm
{
  JFrame fr=new JFrame("product List");
  JTable ta;
  DefaultTableModel model=new DefaultTableModel();
  public ProductListForm()
  {
    fr.setSize(500,500);
    fr.setLocationRelativeTo(null);
    fr.setResizable(false);
    //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.getContentPane().setBackground(Color.GREEN);
    ta=new JTable(model);
    ta.setForeground(Color.blue);
    ta.setBackground(Color.cyan);
    JScrollPane pa=new JScrollPane(ta);
    fr.add(pa);
    populateData();
    //fr.setVisible(true);
  }
  private void populateData()
  {
    model.addColumn("Product id");
    model.addColumn("Product name");
    model.addColumn("Product price");
    model.addColumn("Product quantity");
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","mysql");
      Statement st=cn.createStatement();
      ResultSet rst=st.executeQuery("select * from productinfo");
      while(rst.next())
      {
        Object [] data={rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)};
        model.addRow(data);
      }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
    //Object [] data={101,"Laptop",45000,10};
    //model.addRow(data);
  }
}