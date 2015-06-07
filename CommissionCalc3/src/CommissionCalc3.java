
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Simple Commission Calculation Program Part 3 Samuel Tollefson PRG/420 January
 * 12, 2015 William Davis
 */
public class CommissionCalc3 {

    public static void main(String[] args) {
        // Initiate highest earner
        String inputTest;
        String highestEarner = null;
        double highestTotalComp = 0;

        // Create DecimalFormat objects to format dollar and percent output.
        DecimalFormat dollar = new DecimalFormat("#,##0.00");
        DecimalFormat percent = new DecimalFormat("#0%");

        // Number of Employees
        int numEmployees;

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Get number of employees.
        System.out.println("How many employees are you evaluating?");
        inputTest = keyboard.nextLine();

        // Determine whether it is valid.
        while (!isValidNumber1(inputTest) | !isValidString(inputTest)) {
            if (!isValidString(inputTest)) {
                System.err.println("This is not a valid entry.");
                System.err.println();
                System.err.println("How many employees are you evaluating?");
                inputTest = keyboard.nextLine();
            }
            if (!isValidNumber1(inputTest)) {
                System.err.println("This program is designed "
                        + "to compare multiple employees.");
                System.err.println();
                System.err.println("Please enter a number larger than one.");
                inputTest = keyboard.nextLine();
            }
        }

        // Convert to integer.
        numEmployees = Integer.parseInt(inputTest);

        // Create an Employee array.
        Employee[] employees = new Employee[numEmployees];

        // Call the getEmployees method to get data for each employee.
        getEmployees(employees);

        System.out.println("You entered the following:");

        System.out.println();

        // Display the data that the user entered.
        for (int index = 0; index < employees.length; index++) {
            System.out.println("Employee " + (index + 1));
            System.out.println("Name: "
                    + employees[index].getFullName());
            System.out.println("Salary: $"
                    + dollar.format(employees[index].getSalary()));
            System.out.println("Annual sales: $"
                    + dollar.format(employees[index].getAnnualSales()));
            System.out.println("Total commission: $"
                    + dollar.format(employees[index].getTotalCommis()));
            System.out.println("Total compensation: $"
                    + dollar.format(employees[index].getTotalComp()));
            System.out.println();

            // Set highest earner and highest total compensation
            if (employees[index].getTotalComp() > highestTotalComp) {
                highestTotalComp = employees[index].getTotalComp();
                highestEarner = employees[index].getFullName();
            }
        }

        // Display highest earner and highest total compensation
        System.out.println("Highest earner is " + highestEarner
                + " with a total compensation of $"
                + dollar.format(highestTotalComp));
        System.out.println();

        /**
         * Initiate array to calculate sales needed to meet the total
         * compensation of the highest earner.
         */
        for (int index = 0; index < employees.length; index++) {
            // Exclude highest earner
            if (employees[index].getTotalComp() != highestTotalComp) {
                // Continue testing data until total comp. reaches highest.
                while (employees[index].getNeededSales() < highestTotalComp) {
                    // Increment sales +1 (refer to Employee object)
                    employees[index].setNeededSales();

                    // Display needed sales when calculation ends.
                    if (employees[index].getNeededSales() >= highestTotalComp) {
                        System.out.println("In order for "
                                + employees[index].getFullName()
                                + " to reach " + highestEarner
                                + "'s total compensation, sales would need to "
                                + "be increased by $"
                                + dollar.format(employees[index].getDiffer()
                                        - employees[index].getAnnualSales()));
                    }
                }
            }
        }

        // Gives user the option to view projected income
        System.out.println("\nWould you like to view projected income "
                + "for an employee? (Y or N) ");

        String inputLine; // To hold a line of input
        char choice; // To hold the user's choice
        inputLine = keyboard.nextLine(); // Store user input 

        // Test user input.
        while (!"y".equals(inputLine)) {
            System.err.println("That is not a valid entry");
            System.err.println();
            System.err.println("Would you like to view projected income "
                    + "for an employee? (Y or N) ");
            inputLine = keyboard.nextLine(); // Store user input 
        }

        choice = inputLine.charAt(0); // Get the first char.

        // Test the char to initiate the table.
        if (Character.toUpperCase(choice) == 'Y') {

            // Get a employee number from the user.
            System.out.println("Please enter an employee number or -1 to quit.");
            inputTest = keyboard.nextLine();

            // Determine whether it is valid.
            while ((!"-1".equals(inputTest))
                    && !isValidNumber2(inputTest) | !isValidString(inputTest)) {
                if (!isValidNumber2(inputTest) & isValidString(inputTest)) {
                    System.err.println("That employee number does not exist.");
                    System.err.println();
                    System.err.println("Please enter an employee number or -1 to quit.");
                    inputTest = keyboard.nextLine();
                }
                if (!isValidString(inputTest) & isValidNumber2(inputTest)) {
                    System.err.println("That is not a valid number.");
                    System.err.println();
                    System.err.println("Please enter an employee number or -1 to quit.");
                    inputTest = keyboard.nextLine();
                }
            }

            // Create an employee number variable and convert to integer.
            int employeeNumber = Integer.parseInt(inputTest);

            // Test for user input
            while (employeeNumber != -1) {

//********* projected compensation table ***************************************
                employeeNumber = employeeNumber - 1;

                // Create a reset for annual sales.
                employees[employeeNumber].setAnnualSalesFinal();

                System.out.println();

                System.out.println(employees[employeeNumber].getFullName());

                // Display the table headings
                System.out.println("Total sales\t\t\t\tTotal compensation");
                System.out.println("----------------------------------------"
                        + "------------------");

                // Declare controlExit variable 
                double controlExit = employees[employeeNumber].getAnnualSales() * 1.5;

                // Innitiate loop for projected compensation table
                while (controlExit >= employees[employeeNumber].getAnnualSales()) {
                    if (employees[employeeNumber].getAnnualSales()
                            >= employees[employeeNumber].getSalesTarget()) {
                        System.out.println("$"
                                + (dollar.format(employees[employeeNumber].getAnnualSales())
                                + "\t\t\t\t$"
                                + (dollar.format(employees[employeeNumber].getTotalComp()))));

                        // increment annual sales
                        employees[employeeNumber].setAnnualSales(employees[employeeNumber].getAnnualSales()
                                + 5000);
                    } else if (employees[employeeNumber].getAnnualSales()
                            >= employees[employeeNumber].getSalesTarget()
                            * employees[employeeNumber].getIncentStart()) {
                        System.out.println("$"
                                + (dollar.format(employees[employeeNumber].getAnnualSales())
                                + "\t\t\t\t$"
                                + (dollar.format(employees[employeeNumber].getTotalComp()))));

                        // increment annual sales
                        employees[employeeNumber].setAnnualSales(employees[employeeNumber].getAnnualSales()
                                + 5000);

                        // Sales target benchmark declaration
                        if (employees[employeeNumber].getAnnualSales()
                                >= employees[employeeNumber].getSalesTarget()) {
                            System.out.println("\nSales target hit, commission is "
                                    + "multiplied by set acceleration factor.");
                        }
                    } else {
                        System.out.println("$" + (dollar.format(employees[employeeNumber].getAnnualSales())
                                + "\t\t\t\t$"
                                + (dollar.format(employees[employeeNumber].getTotalComp()))));

                        // increment annual sales
                        employees[employeeNumber].setAnnualSales(employees[employeeNumber].getAnnualSales() + 5000);

                        // Commission benchmark declaration
                        if (employees[employeeNumber].getAnnualSales()
                                >= employees[employeeNumber].getSalesTarget()
                                * employees[employeeNumber].getIncentStart()) {
                            System.out.println("\nSales have reached "
                                    + percent.format(employees[employeeNumber].getIncentStart())
                                    + " of sales target. Commission is added to pay.");
                        }
                    }
                }
                // Reset annual sales to employee's annual sales
                employees[employeeNumber].resetAnnualSales();

                // Prompt user to view another employee's 
                // projected total compensation
                System.out.println("Please enter an employee number or -1 to quit.");
                inputTest = keyboard.nextLine();

                // Determine whether it is valid.
                while ((!"-1".equals(inputTest))
                        && !isValidNumber2(inputTest) | !isValidString(inputTest)) {
                    if (!isValidNumber2(inputTest) & isValidString(inputTest)) {
                        System.err.println("That employee number does not exist.");
                        System.err.println();
                        System.err.println("Please enter an employee number or -1 to quit.");
                        inputTest = keyboard.nextLine();
                    }
                    if (!isValidString(inputTest) & isValidNumber2(inputTest)) {
                        System.err.println("That is not a valid number.");
                        System.err.println();
                        System.err.println("Please enter an employee number or -1 to quit.");
                        inputTest = keyboard.nextLine();
                    }
                }

                // Create an employee number variable and convert to integer.
                employeeNumber = Integer.parseInt(inputTest);
            }
        }
    }
//* end of projected compensation table ****************************************

    /**
     * The getItems method accepts an Employee array as an argument. The user
     * enters data for each element.
     */
    private static void getEmployees(Employee[] array) {
        String inputTest;
        String firstName;
        String lastName;
        double salary;
        double annualSales;

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Prompt user to enter employee data.
        System.out.println("Enter data for " + array.length
                + " employees.");

        // Get data for the array.
        for (int index = 0; index < array.length; index++) {
            // Get an employee first name.
            System.out.println("\nEnter the first name of employee "
                    + (index + 1) + ": ");
            firstName = keyboard.nextLine();

            // Get an employee last name.
            System.out.println("Enter the last name of employee "
                    + (index + 1) + ": ");
            lastName = keyboard.nextLine();

            // Get employee salary.
            System.out.println("Enter the salary for employee "
                    + (index + 1) + ": ");
            inputTest = keyboard.nextLine();

            // Determine if it is valid.
            while (!isValidString(inputTest)) {
                System.err.println("That is not a valid number.");
                System.err.println();
                System.err.println("Enter the salary for employee "
                        + (index + 1) + ": ");
                inputTest = keyboard.nextLine();
            }

            // Convert to double
            salary = Double.parseDouble(inputTest);

            // Get employee sales.
            System.out.println("Enter the annual sales for employee "
                    + (index + 1) + ": ");
            inputTest = keyboard.nextLine();

            // Determine if it is valid.
            while (!isValidString(inputTest)) {
                System.err.println("That is not a valid number.");
                System.err.println();
                System.err.println("Enter the annual sales for employee "
                        + (index + 1) + ": ");
                inputTest = keyboard.nextLine();
            }

            // Convert to double.
            annualSales = Double.parseDouble(inputTest);

            // the data and store the object in the array.
            array[index] = new Employee(firstName, lastName, salary, annualSales);
        }
    }

    // This method ensures that the user's input consists of only numbers.
    private static boolean isValidString(String test) {
        boolean goodSoFar = true; // Flag
        char[] array; // To hold the input as an array

        // Is the string the correct length?
        if (test.length() > 8) {
            goodSoFar = false;
        }

        // Convert the string to a char array.
        array = test.toCharArray();

        // Analyze the characters.
        for (int i = 0; i < array.length; i++) {
            if (!Character.isDigit(array[i])) {
                goodSoFar = false;
            }
        }

        // Return the results
        return goodSoFar;
    }

    // This method ensures that the user's input is larger than 1.
    private static boolean isValidNumber1(String test) {
        boolean goodSoFar = true; // Flag

        try {
            int number = Integer.parseInt(test);

            // Is the number larger than one?
            if (number < 2) {
                goodSoFar = false;
            }
            // Catch exceptions.
        } catch (NumberFormatException e) {
        }

        // Return the results
        return goodSoFar;
    }

    // This method ensures that the user's input refers to an employee.
    private static boolean isValidNumber2(String test) {
        boolean goodSoFar = true; // Flag

        try {
            int number = Integer.parseInt(test);

            // Does the entry represent a valid employee?
            if (number != -1) {
                if (number < test.length() - 1) {
                    goodSoFar = false;
                }

                if (number > test.length() + 1) {
                    goodSoFar = false;
                }
            }

            // Catch exceptions.
        } catch (NumberFormatException e) {
        }

        // Return the results
        return goodSoFar;
    }
}
