package bankingAccounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Accounts {
   
	private Connection connection;
	private Scanner scanner;
	
	
	public Accounts(Connection connection, Scanner scanner) {
		super();
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void register() {
		scanner.nextLine();
		System.out.println("Full Name: ");
		String full_name = scanner.nextLine();
		System.out.println("Email: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		if(user_exist(email)) {
			System.out.println("User already Exists for this Email Address!!");
			return ;
		}
		String register_query = "INSERT INTO User(full_name, email, password) VALUES(?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(register_query);
			preparedStatement.setString(1, full_name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			int affectRows = preparedStatement.executeUpdate();
			if (affectRows > 0) {
				System.out.println("Registration successful !!!");
			} else {
				System.out.println("Regsitration Failed !!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public String login() {
		scanner.nextLine();
		System.out.println("Email: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();
		String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(login_query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
}
