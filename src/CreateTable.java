import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable 
{

	public static void main(String[] args)
	{
		try
		  {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","mysql");
		    System.out.println("connected.......");
		    String query="create table productinfo(pid varchar(4) primary key,name varchar(20),price int,quantity int)";
		    Statement st=cn.createStatement();
		    st.execute(query);
		    cn.close();
		    System.out.println("table created.......");
		  }
		  catch(Exception ex)
		  {
		    System.out.println(ex);
		  }

	}

}
