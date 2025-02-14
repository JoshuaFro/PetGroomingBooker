package groomingCalendar;

import java.time.DayOfWeek;
import java.time.YearMonth;


public class CalendarTools 
{
	public static void displayCalendar(String monthInput) 
    {
        int month = getMonthNumber(monthInput);		// initializes month with numerical value associated with monthInput
        if (month == -1) 							// -1 is the default value returned if the monthInput is not valid
        {
            System.out.println("Invalid month name. Please try again.");
            return;
        }

        int year = 2025; // change year accordingly
        YearMonth yearMonth = YearMonth.of(year, month);	// creates an object of the year and month e.g February 2025
        int numDays = yearMonth.lengthOfMonth();	// uses the yearMonth object to get the number of days in the month of that year
        DayOfWeek startDayOfWeek = yearMonth.atDay(1).getDayOfWeek(); // gets the day of the week for the first day of the entered month
        															  // e.g. SUNDAY
        int startDayOfWeekValue = startDayOfWeek.getValue(); // converts the string value of the day of the week into its associated int value
        // e.g. MONDAY = 1 and SUNDAY = 7

        System.out.println("\n--- Calendar for " + monthInput + " " + year + " ---");
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        /*
         * This is responsible for printing the calendar. if ((i == 0 && j < startDayOfWeekValue - 1) means 
         * that if we are in the first row and j is less than the startDayOfWeekValue - 1 then that means it 
         * will print blank spaces. For example if the startDayOfWeekValue is 7 (sunday) it will subtract one 
         * to give six, so each time the loop increments it will print a blank space until j is 6. 
         * Meaning that it has finally made it the starting date and will begin printing the days of the month.
         */
        int day = 1;
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                if ((i == 0 && j < startDayOfWeekValue - 1) || day > numDays) 
                {
                    System.out.print("    "); // Empty days
                }
                else 
                {
                    System.out.printf("%3d ", day++);
                }
            }
            System.out.println();
        }
    }

    
    // converts month name to month number
    private static int getMonthNumber(String monthInput) 
    {
        return switch (monthInput.toLowerCase())
        {
            case "january" -> 1;
            case "february" -> 2;
            case "march" -> 3;
            case "april" -> 4;
            case "may" -> 5;
            case "june" -> 6;
            case "july" -> 7;
            case "august" -> 8;
            case "september" -> 9;
            case "october" -> 10;
            case "november" -> 11;
            case "december" -> 12;
            default -> -1; // Invalid month
        };
    }

    
    public static String getTimeSlot(int timeChoice) 
    {
        return getTimeSlotInternal(timeChoice);
    }
    
    
    // gets the time slot based on user choice
    private static String getTimeSlotInternal(int timeChoice) 
    {
        return switch (timeChoice) 
        {
            case 1 -> "9:00 AM";
            case 2 -> "10:00 AM";
            case 3 -> "11:00 AM";
            case 4 -> "12:00 PM";
            case 5 -> "1:00 PM";
            case 6 -> "2:00 PM";
            case 7 -> "3:00 PM";
            case 8 -> "4:00 PM";
            default -> "Invalid time slot";
        };
    }
}
