import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BAccount implements Account, Bank{
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	int accountNumber = 1; 
	
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return accountNumber;
	}

	@Override
	public double deposit(Account account, double amount) {
		try{
			double currentBalance = getBalance(account.getNumber());
			
			ps = con.prepareStatement("update Account set balance=? where account=?");
			ps.setDouble(1, amount+currentBalance);
			ps.setInt(2, account.getNumber());
			
			if(ps.executeUpdate() == 1)
				System.out.println("Deposit "+amount);
			else
				System.out.println("Account not found");
			
			return getBalance(account.getNumber());
			
		}catch(SQLException e){
			
		}
		return 0;
	}

	@Override
	public double withdraw(Account account, double amount) {
		try{
			double currentBalance = getBalance(account.getNumber());
			
			ps = con.prepareStatement("update Account set balance=? where account=?");
			ps.setDouble(1, currentBalance - amount);
			ps.setInt(2, account.getNumber());
			
			if(ps.executeUpdate() == 1)
				System.out.println("Deposit "+amount);
			else
				System.out.println("Account not found");
			
			return getBalance(account.getNumber());
			
		}catch(SQLException e){
			
		}
		return 0;
	}
	
	double getBalance(int account) throws SQLException {
		ps = con.prepareStatement("select * from Account where account = ?");
		ps.setInt(1, account);
		rs = ps.executeQuery();
		double balance = 0;
		if(rs.next())
			balance = rs.getDouble("balance");
		return balance;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://db4free.net/xeno_lee", "xeno_lee", "123456");
		BAccount ba = new BAccount();
		
		System.out.println(ba.deposit(ba, 2));
		System.out.println(ba.withdraw(ba, 1));
	}
}