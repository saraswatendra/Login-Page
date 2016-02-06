import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class Jf
{
	JFrame j=new JFrame();
	JLabel lid=new JLabel("User Id   :");
	JTextField fid=new JTextField(10);
	JLabel lpass=new JLabel("Password  :");
	//JPasswordField fpass=new JPasswordField(10);
	TextField fpass=new TextField(10);
	JLabel linfo=new JLabel("     Welcome  to  SARAS  Database");
	JButton blogin=new JButton("Login");
	JButton bnewaccount=new JButton("Create an Account");
	JButton bforget=new JButton("Forget Password");
	
	Jf()
	{
		
		j.setBounds(500,40,500,500);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(null);		
		ImageIcon image=new ImageIcon("im1.jpg");             
		JLabel l=new JLabel(image);
		int m=image.getIconWidth(); 
		int m1=image.getIconHeight();
		j.setResizable(false);//frame resize nahi hoga		
		l.setBounds(0,0,m,m1);
		j.add(l);
		l.add(linfo);
		l.add(lid);
		l.add(fid);
		l.add(lpass);
		l.add(fpass);
		fpass.setEchoChar('*');
		
		l.add(blogin);
		l.add(bnewaccount);
		l.add(bforget);
		
		ImageIcon image1=new ImageIcon("logo1.png");             
		JLabel limage=new JLabel(image1);
		l.add(limage);
		limage.setBounds(10,50,190,130);
		
		Font f=new Font("ALGERIAN",Font.BOLD,20);
		linfo.setFont(f);
		linfo.setBounds(40,10,420,30);
		lid.setBounds(210,50,70,30);
		fid.setBounds(290,50,150,30);
		lpass.setBounds(210,90,70,30);		
		fpass.setBounds(290,90,150,30);
		blogin.setBounds(210,130,100,50);
		bnewaccount.setBounds(320,130,160,20);
		bforget.setBounds(320,160,160,20);
		blogin.setBackground(Color.green);
		bnewaccount.setBackground(Color.cyan);
		bforget.setBackground(Color.pink);
		
		blogin.addActionListener(new Login());
		bnewaccount.addActionListener(new Account());
		bforget.addActionListener(new Forget());
	}
	
	class Login implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String t=fid.getText();
			//char[] t2=fpass.getPassword();
			String t2=fpass.getText();
				
			try
			{
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","9696030257");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from database where userid='"+t+"' AND password='"+t2+"'");
				rs.next();
				String g=rs.getString("userid");
				String h=rs.getString("password");
				String i=rs.getString("mob");
				String j=rs.getString("dob");
				if(g.equals(t) && h.equals(t2))
				{
				//JOptionPane.showMessageDialog(null,"WoW  !!  You  Are  a  Valid  User");
					JFrame jf1=new JFrame("About Saras");
					jf1.setBounds(500,40,500,500);
					jf1.setVisible(true);
					jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jf1.setLayout(null);
					String ab="\n\nHis Name is Saraswatendra Singh.\nHe is pursuing B.Tech from ABES Engineering College(032) Ghaziabad U.P.\nHe is belong from VARANASI which is also called BANARAS.\nHis Email and Facebook id is  <saraswatendra@gmail.com>\n\n\n \t\t\tTHANK YOU";
					String bc="\n\n\nABOUT YOU:-\n\n\t UserId is < "+g+" >\n\t Password is <"+h+" > \n\t Mobile No is < "+i+" >\n\t Date Of Birth(dd/mm/yyyy) is < "+j+" >\n \n\nABOUT DEVELOPER:-"+ab;
					JTextArea about=new JTextArea(bc);
					jf1.add(about);
					about.setBounds(0,0,500,500);					
					JButton rest=new JButton("ResetPassword");
					about.add(rest);
					rest.setBounds(30,400,150,20);
					Cursor k1=new Cursor(Cursor.HAND_CURSOR);
					rest.setCursor(k1);
					rest.addActionListener(new ResetPassword());
					JButton restmob=new JButton("ResetMobileNo");
					about.add(restmob);
					restmob.setBounds(230,400,150,20);
					Cursor k2=new Cursor(Cursor.HAND_CURSOR);
					restmob.setCursor(k2);
					restmob.addActionListener(new ResetMob());
														
				}	
			}
			catch(Exception ex)
			{
				System.out.print(ex);
				JOptionPane.showMessageDialog(null,"UserId  or  Password  MissMatched !!!  please  Enter  Valid  UserId and Password");
			}	 
		}
	}
	
	class ResetPassword implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
			{
				String name6=JOptionPane.showInputDialog("Enter old Password:");
				String name7=JOptionPane.showInputDialog("Enter New Password:");
				try
				{
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","9696030257");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from database where password='"+name6+"'");
				rs.next();
				String k8=rs.getString("password");
				if(k8.equals(name6))
				{
					st.executeUpdate("update database set password='"+name7+"' where password='"+name6+"'");
					JOptionPane.showMessageDialog(null,"Your  Password  has  been  Reset");
						new Jf();
				}				
				}
				catch(Exception ex)
				{
				System.out.print(ex);
				JOptionPane.showMessageDialog(null,"Please Enter valid DATA .");
				}
					
			}
			
	}
	
	class ResetMob implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
			
				String name8=JOptionPane.showInputDialog("Enter old MobileNo:");
				String name9=JOptionPane.showInputDialog("Enter New MobileNo:");
				try
				{
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","9696030257");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from database where mob='"+name8+"'");
				rs.next();
				String k9=rs.getString("mob");
				if(k9.equals(name8))
				{				
				st.executeUpdate("update database set mob='"+name9+"' where mob='"+name8+"'");				
				JOptionPane.showMessageDialog(null,"Your  Mobile is  Reset . Now Your mobile no is <  "+name9+"  >");
				new Jf();				
				}
				}
				catch(Exception ex)
				{
				System.out.print(ex);
				JOptionPane.showMessageDialog(null,"Please Enter valid DATA.");
				}
			
			}
		}
	
	class Account implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			try
			{
				String name=JOptionPane.showInputDialog("Enter the UserId:");
				String name1=JOptionPane.showInputDialog("Enter the Password:");
				String name2=JOptionPane.showInputDialog("Enter the MobileNo:");
				//int name2=Integer.parseInt(name22);
				String name3=JOptionPane.showInputDialog("Enter the DOB(dd/mm/yyyy):");
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","9696030257");
				Statement st=con.createStatement();
				st.executeUpdate("insert into database values('"+name+"','"+name1+"','"+name2+"','"+name3+"')");
				st.executeUpdate("commit");
				JOptionPane.showMessageDialog(null,"Hi !! Welcome in Our Database.Your userId is  < "+name+" > And Password is < ******* >");
				
							
			}
			catch(Exception ex)
			{
				System.out.print(ex);
				JOptionPane.showMessageDialog(null,"Please Fill All Entry OR choose another UsetId.");
			}
		  
			
			
			
		}
	}
	
	class Forget implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name4=JOptionPane.showInputDialog("Enter the UserId:");
			String name5=JOptionPane.showInputDialog("Enter the DOB(dd/mm/yyyy):");
			try
			{
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","9696030257");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from database where userid='"+name4+"' and dob='"+name5+"'");
				rs.next();
				String z=rs.getString("password");
				JOptionPane.showMessageDialog(null,"Your  Password  is  <  "+z+"  >");
				
							
			}
			catch(Exception ex)
			{
				System.out.print(ex);
				JOptionPane.showMessageDialog(null,"Please Enter valid UserId and DOB(dd/mm/yyyy) .");
			}
			
		}
	}
	
	
	
}



class M
{
	public static void main(String args[])
	{
		new Jf();
	}
}