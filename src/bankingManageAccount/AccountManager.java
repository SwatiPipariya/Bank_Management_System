package bankingManageAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
   
	private Connection connection;
	private Scanner scanner;

	AccountManager(Connection connection, Scanner scanner) {
		this.connection = connection;
		 this.scanner = scanner;
	}
	
	public void credit_money(long account_number) throws SQLException {
		scanner.nextLine();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter Security Pin");
		String security_pin = scanner.nextLine();
		
		try {
			connection.setAutoCommit(false);
			if(account_number != 0) {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? and security_pin = ? ");
				preparedStatement.setLong(1, account_number);
				preparedStatement.setString(2, security_pin);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					String credit_query = "UPDATE Accounts SET balance = balance + ? WHERE account_number = ?";
					PreparedStatement preparedStatement1 = connection.prepareStatement(credit_query);
					preparedStatement.setDouble(1, amount);
					preparedStatement.setLong(1, account_number);
					int rowsAffected = preparedStatement1.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Rs. " +amount+" credited Successfully");
						connection.commit();
						connection.setAutoCommit(true);
						return;
					} else {
						System.out.println("Transaction Failed!");
						connection.rollback();
						connection.setAutoCommit(true);
					}
					
				} else {
					System.out.println("Invalid Security Pin!");
				}
			}
			
		}catch (SQLException e) {
             e.printStackTrace();
		}
		connection.setAutoCommit(true);
		
	}
	
	public void debit_money(long account_number) throws SQLException {
		
	}
	   
	
}
