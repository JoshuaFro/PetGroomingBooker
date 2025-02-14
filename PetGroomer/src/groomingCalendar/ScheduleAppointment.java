package groomingCalendar;

import java.util.Scanner;


public class ScheduleAppointment 
{
    private Scanner scanner;
    private boolean running;
    private String[] timeSlots;

    public ScheduleAppointment()
    {
    	this.scanner = new Scanner(System.in);
        this.running = true;
        this.timeSlots = new String[]{
            "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"
        };
    }
    
   
    public void start()
    {
        while (running) 
        {
            int choice = displayMenu();
            if (choice == 1) 
            {
                bookAppointment();
            } 
            else if (choice == 2) 
            {
                running = false;
                System.out.println("Thank you for using the Pet Grooming Booking System. Goodbye!");
            } 
            else 
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    

    private int displayMenu() 
    {
        System.out.println("\nWelcome to the Pet Grooming Booking System!");
        System.out.println("1. Proceed to book an appointment");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }

    
    private void bookAppointment() 
    {
        // Step 1: Ask for the month
        System.out.print("Enter the month you'd like to book (e.g., January, February, etc.): ");
        String monthInput = scanner.nextLine().trim();

        // Display the calendar for the selected month
        CalendarTools.displayCalendar(monthInput);

        // Step 2: Ask for the preferred day
        System.out.print("Enter your preferred day of the month: ");
        int day = scanner.nextInt();
        scanner.nextLine(); 

        // Step 3: Display available times
        System.out.println("Available times for " + monthInput + " " + day + ":");
        for (int i = 0; i < timeSlots.length; i++) 
        {
            System.out.println((i + 1) + ". " + timeSlots[i]);
        }

        System.out.print("Choose a time slot (1-" + timeSlots.length + "): ");
        int timeChoice = scanner.nextInt();
        scanner.nextLine(); 

        // Validate time choice
        while (timeChoice < 1 || timeChoice > timeSlots.length) 
        {
            System.out.print("Invalid choice. Please select a valid time slot (1-" + timeSlots.length + "): ");
            timeChoice = scanner.nextInt();
            scanner.nextLine(); 
        }

        // takes the timeChoice number, subtracts it by 1, and retrieves the string in the corresponding index
        String timeSlot = timeSlots[timeChoice - 1];

        /* Step 4: assign the return value of the getClientDetails() method and the selectServiceType() method to 
         * the clientDetails variable and the selectedService variable so that the values can be passed to the
         * confirmBooking method.
         */
        String[] clientDetails = getClientDetails();
        String selectedService = selectServiceType();

        // Step 5: Confirm the booking
        confirmBooking(clientDetails, selectedService, monthInput, day, timeSlot);
        
        running = false;
    }
    

    private String[] getClientDetails() 
    {
        String[] details = new String[4];

        System.out.print("Enter your name: ");
        details[0] = scanner.nextLine();

        System.out.print("Enter your contact info (phone/email): ");
        details[1] = scanner.nextLine();

        System.out.print("Enter the type of pet (cat/dog): ");
        details[2] = scanner.nextLine();

        System.out.print("Enter your pet's name: ");
        details[3] = scanner.nextLine();


        return new String[]{details[0], details[1], details[2], details[3]};
    }
    
    
    String selectServiceType()
    {
       	String serviceTypes[] = {"Full Bath and Groom", "Bath", "Nail Trim"};

       	System.out.println("\nFinally, select your preferred service type (1-3)");
       	for (int i = 0; i < serviceTypes.length; i++) 
       	{
           	System.out.println((i + 1) + ". " + serviceTypes[i]);
       	}

       	System.out.print("Enter service type: ");
       	int serviceNum = scanner.nextInt();  
       	scanner.nextLine();  

       	// Check if the input is valid
       	if (serviceNum < 1 || serviceNum > serviceTypes.length) 
       	{
           	System.out.println("Invalid choice. Please try again.");
           	return selectServiceType();  // Recursive call to retry
       	}

       	return serviceTypes[serviceNum - 1];	 // Subtract 1 to match the array index
   	}

    
    private void confirmBooking(String[] details, String selectedService, String month, int day, String timeSlot) 
    {
        System.out.println("\n--- Booking Confirmation ---");
        System.out.println("Client: " + details[0]);
        System.out.println("Contact: " + details[1]);
        System.out.println("Pet: " + details[3] + " (" + details[2] + ")");
        System.out.println("Service: " + selectedService);
        System.out.println("Date: " + month + " " + day + " at " + timeSlot);
        System.out.println("Thank you for booking with us!");
    }


}
