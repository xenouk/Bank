import java.sql.*;

public class BasicAccount {
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://db4free.net/xeno_lee", "xeno_lee", "123456");

		System.out.println(getBalance(1));
		//addAccount("Li", "London", 1, 5);
		System.out.println("New Balance "+deposit(1, 5));
		System.out.println("New Balance "+withdraw(1, 2));
		//createTable();
		
		if (rs != null) {
			rs.close();
		}

		if (st != null) {
			st.close();
		}

		if (con != null) {
			con.close();
		}

		if (ps != null) {
			ps.close();
		}
	}
	
	static void createTable() throws SQLException{
		ps = con.prepareStatement("create table if not exists BasicAccount("
				+ "ID int not null auto_increment primary key,"
				+ "Name varchar(16) not null,"
				+ "Town varchar(16) not null,"
				+ "Age int not null,"
				+ "Balance double not null default 0)");
		ps.execute();
	}

	static double getBalance(int account) throws SQLException {
		ps = con.prepareStatement("select * from Account where account = ?");
		ps.setInt(1, account);
		rs = ps.executeQuery();
		double balance = 0;
		if(rs.next())
			balance = rs.getDouble("balance");
		return balance;
	}
	
	static void addAccount(String name, String town, int age, double balance) throws SQLException {
		ps = con.prepareStatement("insert into Account (name,town,age,balance) value (?,?,?,?)");
		ps.setString(1, name);
		ps.setString(2, town);
		ps.setInt(3, age);
		ps.setDouble(4, balance);
		ps.execute();
	}
	
	static double deposit(int account, double amount) throws SQLException{
		double currentBalance = getBalance(account);
		
		ps = con.prepareStatement("update Account set balance=? where account=?");
		ps.setDouble(1, amount+currentBalance);
		ps.setInt(2, account);
		
		if(ps.executeUpdate() == 1)
			System.out.println("Deposit "+amount);
		else
			System.out.println("Account not found");
		
		return getBalance(account);
	}
	
	static double withdraw(int account, double amount) throws SQLException{
		double currentBalance = getBalance(account);
		
		ps = con.prepareStatement("update Account set balance=? where account=?");
		ps.setDouble(1, currentBalance-amount);
		ps.setInt(2, account);
		
		if(ps.executeUpdate() == 1)
			System.out.println("Withdraw "+amount);
		else
			System.out.println("Account not found");
		
		return getBalance(account);
	}
}
