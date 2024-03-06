package bankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class BankingApp {
     
	private static final String url = "jdbc:mysql://localhost:3306/banking_system";
	
	private static final String username = "root";
	
	private static final String password = "vay#45vdra##" ;
	
	public static void main(String[] args) {
        
        try {
        	 Connection connection = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in);
             User user = new User(connection, scanner);
             Accounts accounts = new Accounts(connection, scanner);
             AccountManager accountManager = new AccountManager(connection, scanner);
			
             String email;
             long account_number;
             
             while(true) {
            	 System.out.println("*****Welcome to Bank Management System*******");
            	 System.out.println();
            	 System.out.println("1. Register");
            	 System.out.println("2. Login");
            	 System.out.println("3. Exit");
            	 
             }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
	}
}
