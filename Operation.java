package Atm_Machine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Operation {
	static Scanner sc = new Scanner(System.in);

	public static void information(ResultSet rs) throws Exception {
		System.out.println("Account type : " + rs.getString(1) + "\n" + "Account Number: " + rs.getLong(2) + "\n"
				+ "Password: " + rs.getInt(3) + "\n" + "Balence: " + rs.getFloat(4) + "INR." + "\n\n");
	}

	public static void deposite(ResultSet rs, Statement st, float balence, long acc_no) {
		try {
			System.out.print("Enter Amount You Want To Deposite : ");
			float amount = sc.nextFloat();
			if (amount < 0) {
				System.out.println("\n Wrong Amount Enter !!!!!!!!!!\n\n");
			} else {
				float new_amount = balence + amount;
				int count = st.executeUpdate(
						"UPDATE machine SET balence=" + new_amount + " WHERE Account_Number=" + acc_no + "");
				rs = st.executeQuery("SELECT balence FROM machine WHERE Account_Number=(" + acc_no + ") ");
				if (rs.next()) {
					balence = rs.getFloat(1);
				}
				System.out.println("\n You Have SuccessFully Credited " + amount + " INR." + "\n"
						+ "Your New Updated Balence is :" + balence + " INR." + "\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void withdraw(float balence, Statement st, long acc_no, ResultSet rs, int pin) throws Exception {
		System.out.print("Enter Your Account pin : ");
		int p = sc.nextInt();
		if (p == pin) {
			System.out.print("Enter Amount You Want To Withdraw : ");
			float w_amount = sc.nextFloat();
			if (w_amount < 0) {
				System.out.println("\n Wrong Amount Enter !!\n Please Enter Correct Amount\n\n");
			} else {
				float Update_amount = balence - w_amount;
				if (Update_amount < 0) {
					System.out.println("\n You Have Insufficient Balence !!!\n\n");
				} else {
					int count1 = st.executeUpdate(
							"UPDATE machine SET balence=" + Update_amount + " WHERE Account_Number=" + acc_no + "");
					rs = st.executeQuery("SELECT balence FROM machine WHERE Account_Number=(" + acc_no + ") ");
					if (rs.next()) {
						balence = rs.getFloat(1);
					}
					System.out.println("\n You Have SuccessFully Debited " + w_amount + " INR." + "\n"
							+ "Now Avilable Balence is :" + balence + " INR." + "\n\n");
				}
			}
		} else {
			System.out.println("\n Incorrect Pin!! \n\n");
		}

	}

	public static void checkBalence(int pin, float balence) {
		System.out.print("Enter Your Account Pin : ");
		int u_pin = sc.nextInt();
		if (u_pin == pin) {
			System.out.println("\n Your Account Balence is : " + balence + " INR." + "\n\n");
		} else {
			System.out.println("\n Incorrect Pin!! \n\n");
		}
	}

	public static void changePin(int pin, Statement st, long acc_no) throws Exception {
		System.out.print("Enter Your Old Pin :");
		int old = sc.nextInt();
		if (old == pin) {
			System.out.print("\n\n Enter New 4 Digit Pin : ");
			int new_pin = sc.nextInt();
			if (new_pin >= 1000 && new_pin <= 9999) {
				System.out.print("\n\n Enter Confirm Pin : ");
				int con_pin = sc.nextInt();
				if (new_pin == con_pin) {
					int count2 = st.executeUpdate(
							"UPDATE machine SET password=" + new_pin + " WHERE Account_Number=" + acc_no + "");
					System.out.println("\n Your Pin Has Been Changed !!\n\n ");
				} else {
					System.out.println("\n Incorrect Pin , Sorry!!\n\n");
				}
			} else {
				System.out.println("\n Please Enter 4 Digit Pin !! \n\n");
			}
		} else {
			System.out.println("\n Incorrect Pin!! \n\n");
		}
	}

	public static void exite() {
		System.out.println(" Thank You! \n Have A Good Day!!!!!!!!!");
	}

}
