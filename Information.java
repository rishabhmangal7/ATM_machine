package Atm_Machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Information extends Operation {

	public static String Driver = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/ATM?characterEncoding=utf8";
	public static String UserName = "root";
	public static String password = "5540";

	public boolean check(long acc_no, int pin) throws Exception {
		boolean flag = false;
		Class.forName(Driver);
		Connection con = DriverManager.getConnection(URL, UserName, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT Password FROM machine WHERE Account_Number=(" + acc_no + ") ");
		if (rs.next()) {
			if (rs.getInt(1) == pin) {
				System.out.println("\n Congratulations for successfully login !!\n\n");
				flag = true;
			} else {
				System.out.println("Incorrect Password !!!!!!");
			}

		} else {
			System.out.println("Incorrect Account Number !!!!!");
		}

		return flag;
	}

	public void operation(long acc_no, int choice) throws Exception {
		String account_type = "";
		int pin = 0;
		float balance = 0;

		Class.forName(Driver);
		Connection con = DriverManager.getConnection(URL, UserName, password);
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("SELECT Account_type,Account_Number,Password,balance FROM machine WHERE Account_Number=("
						+ acc_no + ") ");
		if (rs.next()) {
			account_type = rs.getString(1);
			pin = rs.getInt(3);
			balance = rs.getFloat(4);
		}
		switch (choice) {
		case 1:
			information(rs);
			break;
		case 2:
			deposite(rs, st, balance, acc_no);
			break;
		case 3:
			withdraw(balance, st, acc_no, rs, pin);
			break;
		case 4:
			checkbalance(pin, balance);
			break;
		case 5:
			changePin(pin, st, acc_no);
			break;
		case 6:
			exite();
			break;
		default:
			System.out.println("Plaese Enter Correct Operation !");
		}
	}
}
