/**
* This class holds employee names and values, and  
* calculates total commission, total compensation,
* and sales needed to meet the total compensation 
* earned by the highest earner.
*/

public class Employee
{
    private String 
            firstName,
            lastName;
    private double 
            salary, 
            salesTarget = 120000, //$120,000 sales target
            incentStart = 0.8, //Initiates incentive at 80% of salesTarget
            annualSales,
            differ = annualSales, //Protect integrity of annualSales variable
            annualSalesFinal,
            neededSales;
    
    /**
    * The constructor accepts string arguments that are assigned to 
    * the firstName, laxtName, salary and annualSales fields.
    * @param fName
    * @param lName
    * @param sal
    * @param annSal
    */
    public Employee(String fName, String lName, double sal, double annSal) 
    {
        firstName = fName;
        lastName = lName;
        salary = sal;
        annualSales = annSal;
    }
    
    /**Returns the value stored in
    * the firstName field
    * @return 
    */
    public String getFirstName()
    {
        return firstName;
    }

    /**Returns the value stored in
    * the lastName field
    * @return 
    */
    public String getLastName()
    {
        return lastName;
    }
    
    /**Combines first and last names
    * @return 
    */
    public String getFullName()
    {
        return firstName + " " + lastName;
    }
    
    /**Returns the value stored in
    * the salary field
    * @return 
    */
    public double getSalary()
    {
        return salary;
    }

    /**Sets the annualSales variable
    * for use in the tables
    * @param annSal
    */
    public void setAnnualSales(double annSal)
    {
        annualSales = annSal;
    }
    
    /**Returns the value stored in
    * the annualSales field
    * @return 
    */
    public double getAnnualSales()
    {
        return annualSales;
    }    
    
    /**Returns the value stored in
    * the salesTarget field
    * @return 
    */
    public double getSalesTarget()
    {
        return salesTarget;
    }
      
    /**Sets the incentStart variable
    * for use in the tables
    * @param start
    */   
    public void setIncentStart(double start)
    {
        incentStart = start;
    }
    
    /**Returns the value stored in
    * the incentStart field
    * @return 
    */
    public double getIncentStart()
    {
        return incentStart;
    }

    /**
    * The getTotalCommis method calculates and returns the
    * commission based on the applicable commission 
    * and acceleration factor. Used only to display 
    * total commission to the user.
    * @return 
    */
    public double getTotalCommis()
    {
        double 
        commission, // Holds the commission
        accelFactor; // Holds the acceleration factor

        if (annualSales >= salesTarget)       
        { 
            commission = 0.09;
            accelFactor = 1.25;
        }  
        else if (annualSales >= salesTarget * incentStart)
        { 
            commission = 0.09;
            accelFactor = 1;
        }
        
        else
        {
            commission = 0;
            accelFactor = 1;
        }

        return (commission * annualSales) * accelFactor;
    }
    
    /**
    * The getTotalComp method calculates and returns
    * total compensation based on the applicable commission 
    * and acceleration factor
    * @return 
    */
    public double getTotalComp()
    {
        double 
        commission, // Holds the gross paycommission
        accelFactor; // Holds acceleration factor

        if (annualSales >= salesTarget)       
        { 
            commission = 0.09;
            accelFactor = 1.25;
        }  
        else if (annualSales >= salesTarget * incentStart)
        { 
            commission = 0.09;
            accelFactor = 1;
        }
        
        else
        {
            commission = 0;
            accelFactor = 1;
        }

        return ((commission * annualSales) * accelFactor) + salary;
    }
    
    /**
    * The setNeededSales method calculates and returns
    * the sales needed to meet the total compensation 
    * earned by the highest earner.
    */
    public void setNeededSales()
    {
        double 
        commission, // Holds the gross paycommission
        accelFactor; // Holds acceleration factor
 
        differ = differ + 1;

        if (differ >= salesTarget)       
        { 
            commission = 0.09;
            accelFactor = 1.25;
        }  
        else if (differ >= salesTarget * incentStart)
        { 
            commission = 0.09;
            accelFactor = 1;
        }
        
        else
        {
            commission = 0;
            accelFactor = 1;
        }

        neededSales = ((commission * differ) * accelFactor) + salary;
    }
    
    /**Returns the value stored in the neededSales field
    * Used only to display the amount of sales needed to 
    * meet the total compensation earned by the highest earner.
    * @return 
    */
    public double getNeededSales()
    {
        return neededSales;
    }
    
    /**Returns the value stored in the differ field
    * Used only to display the amount of sales needed to 
    * meet the total compensation earned by the highest earner.
    * @return 
    */
    public double getDiffer()
    {
        return differ;
    }
    
    /** Protects the integrity of annual sales input
    */
    public void setAnnualSalesFinal()
    {
        annualSalesFinal = annualSales;
    }
    
    /** Uses annualSalesFinal to reset annualSales
    */
    public void resetAnnualSales()
    {
        annualSales = annualSalesFinal;
    }
}
