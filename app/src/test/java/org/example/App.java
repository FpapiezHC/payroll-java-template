package org.example;

import java.util.Scanner;

public class App {
    private static final double HOURLY_RATE = 16.78;
    private static final double OVERTIME_RATE = HOURLY_RATE * 1.5;
    private static final double SOCIAL_SECURITY_TAX = 0.06;
    private static final double FEDERAL_INCOME_TAX = 0.14;
    private static final double STATE_INCOME_TAX = 0.05;
    private static final double UNION_DUES = 10.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hoursWorked = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int dependents = scanner.nextInt();
        if (dependents < 0) {
            dependents = 0; /
        }

        int lifeInsurancePlan = 0;
        while (true) {
            System.out.println("Which life insurance plan do you want to select?\n");
            System.out.println("  (1) no plan");
            System.out.println("  (2) single plan");
            System.out.println("  (3) married plan");
            System.out.println("  (4) married with children plan");
            System.out.print("\nYour choice: ");
            lifeInsurancePlan = scanner.nextInt();

            if (lifeInsurancePlan == 1) break;
            if (lifeInsurancePlan == 2) break;
            if (lifeInsurancePlan == 3) break;
            if (lifeInsurancePlan == 4) {
                if (dependents > 0) break;
                System.out.println("\nSorry! You need at least one child to select that plan.\n");
            } else {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        }

        double regularHours = Math.min(40, hoursWorked);
        double overtimeHours = Math.max(0, hoursWorked - 40);
        double grossPay = (regularHours * HOURLY_RATE) + (overtimeHours * OVERTIME_RATE);
        double socialSecurity = grossPay * SOCIAL_SECURITY_TAX;
        double federalIncomeTax = grossPay * FEDERAL_INCOME_TAX;
        double stateIncomeTax = grossPay * STATE_INCOME_TAX;
        double insurance = dependents >= 3 ? 35.00 : 15.00;
        double lifeInsurance = 0;

        switch (lifeInsurancePlan) {
            case 1: lifeInsurance = 0.00; break;
            case 2: lifeInsurance = 5.00; break;
            case 3: lifeInsurance = 10.00; break;
            case 4: lifeInsurance = 15.00; break;
        }

        double totalDeductions = socialSecurity + federalIncomeTax + stateIncomeTax + UNION_DUES + insurance + lifeInsurance;

        double netPay = grossPay - totalDeductions;

        System.out.println("\nPayroll Stub:\n");
        System.out.printf("   Hours:   %.2f\n", hoursWorked);
        System.out.printf("    Rate:   %.2f $/hr\n", HOURLY_RATE);
        System.out.printf("   Gross:   $ %.2f\n\n", grossPay);
        System.out.printf("  SocSec:   $ %.2f\n", socialSecurity);
        System.out.printf("  FedTax:   $ %.2f\n", federalIncomeTax);
        System.out.printf("   StTax:   $ %.2f\n", stateIncomeTax);
        System.out.printf("   Union:   $ %.2f\n", UNION_DUES);
        System.out.printf("     Ins:   $ %.2f\n", insurance);
        System.out.printf(" LifeIns:   $ %.2f\n\n", lifeInsurance);
        System.out.printf("     Net:   $ %.2f\n", netPay);

        System.out.println("\nThank you for using the Payroll Program!");
        scanner.close();
    }
}
