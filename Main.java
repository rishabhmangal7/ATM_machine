package Atm_Machine;

import java.util.Scanner;

public class Main {
	long acc_no;
	int pin;

	Main(Scanner sc) {
		System.out.println("Welcome To ATM Machine !\n");
		System.out.println("Enter your Account Number");
		acc_no = sc.nextLong();
		System.out.println("Enter your Account Pin ");
		pin = sc.nextInt();

	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Main object = new Main(sc);
		Information obj = new Information();
		boolean flag = obj.check(object.acc_no, object.pin);
		if (flag) {
			int choice;
			do {
				System.out.println("Select The Operation You Want To Access:");
				System.out.print(
						" Type 1: View All Information\n Type 2: Deposite Funds \n Type 3: Withdraw Funds \n Type 4: Check Bank balance \n Type 5: Change Pin  \n Type 6: Exit \n Choice: ");
				choice = sc.nextInt();
				System.out.println("\n");
				obj.operation(object.acc_no, choice);
			} while (choice >= 1 && choice < 6);
		}
	}

}
