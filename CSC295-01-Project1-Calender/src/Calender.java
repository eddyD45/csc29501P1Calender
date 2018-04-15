import java.util.Scanner;

public class Calender {
	
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a year: ");
		int year = sc.nextInt();
		sc.close();
		printCalender(year);
	}


	//used to find the first day of the week for a given month (month) for a given year (year)
	//returns a value 0-6, which represent a day of the week 
	public static int dayOfTheWeek(int month, int year) {
		int day = 1;

		if (month < 3) {
		    month += 12;
		    year -= 1;
		}

		int k = year % 100;
		int j = year / 100;

		// 0 = Saturday, 1 = Sunday, ...
		int dayOfWeek = ((day + (((month + 1) * 26) / 10) + 
		   k + (k / 4) + (j / 4)) + (5 * j)) % 7;
		return dayOfWeek;
	}
	
	//for a given year, determines if it is  a leap year or not. Necessary to determine length of Februaries
	public static boolean isLeapYear (int year) {
		return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
	}
	
	//prints a header for each month
	//header is month followed by year.
	public static void printHeader(int month, int year) {
		StringBuilder header = new StringBuilder();
		
		switch(month) {
			case 1: header.append("January");	 break;
			case 2: header.append("February");	 break;
			case 3: header.append("March"); 	 break;
			case 4: header.append("April"); 	 break;
			case 5: header.append("May"); 		 break;
			case 6: header.append("June"); 		 break;
			case 7: header.append("July"); 		 break;
			case 8: header.append("August");	 break;
			case 9: header.append("September");	 break;
			case 10: header.append("October");	 break;
			case 11: header.append("November");  break;
			case 12: header.append("December");  break;
			default: System.err.println("Month out of bounds");
		}
		
		header.append(" ").append(year);
		
		System.out.println(header);
		
		
	}
	
	//prints Sunday-Saturday at the top, shorthanded to 3 letters, in a specific format
	//then prints out days 1-31/30/29/28 (depending on month and leap year or not for Feb) in correct format and starting on correct day of the week for day 1
	public static void printMonth(int month, int year) {
		printHeader(month,year);
		
		String [] daysOfTheWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s", 
				daysOfTheWeek[0],daysOfTheWeek[1], daysOfTheWeek[2], daysOfTheWeek[3], 
				daysOfTheWeek[4], daysOfTheWeek[5], daysOfTheWeek[6]);
		System.out.println("");
		
		String [] totalDays = daysArray(month, year);
	
		
		
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[0], totalDays[1], totalDays[2], totalDays[3], totalDays[4], totalDays[5], totalDays[6]);
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[7], totalDays[8], totalDays[9], totalDays[10], totalDays[11], totalDays[12], totalDays[13]);
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[14], totalDays[15], totalDays[16], totalDays[17], totalDays[18], totalDays[19], totalDays[20]);
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[21], totalDays[22], totalDays[23], totalDays[24], totalDays[25], totalDays[26], totalDays[27]);
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[28], totalDays[29], totalDays[30], totalDays[31], totalDays[32], totalDays[33], totalDays[34]);
		System.out.printf("%-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", totalDays[35], totalDays[36], totalDays[37], totalDays[38], totalDays[39], totalDays[40], totalDays[41]);
		
	}
	
	//given a month and a year, returns a String array of numbers 1-31/30/29/28 depending on month and leap year or not for Feb
	public static String [] daysArray (int month, int year) {
		String [] daysArray = new String [43];
		int start = 0;
		
		int dayOne = dayOfTheWeek(month, year);			//check out where you want to start so day1 is on the correct day of the week i.e day 1 is on monday
		switch(dayOne) {								//instead of always sunday by default
			case 0: start = 6; break;
			case 1: start = 0; break;
			case 2: start = 1; break;
			case 3: start = 2; break;
			case 4: start = 3; break;
			case 5: start = 4; break;
			case 6: start = 5; break;
		}
		
		int totalDays = monthLength(month, year);
		
		//fill array with days according to length of month
		for(int i = 1; i <= totalDays; i++) {
			daysArray[start] = Integer.toString(i);
			start++;
		}
		for(int i = 0; i < daysArray.length; i++) {				//fill out any nulls with a white space for the printout
			if(daysArray[i] == null) {
				daysArray[i] = " ";
			}
		}
		
		return daysArray;
	}
	//given a month and an year returns the length of the given month
	//used for determining how many days to fill up in the calender month
	public static int monthLength (int month, int year) {
		int totalDays = 0;
		switch(month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				totalDays = 31; break;
			case 4: case 6: case 9: case 11:
				totalDays = 30; break;
			case 2: totalDays = februraryDays(year);
			
		}
		
		return totalDays;
	}
	//specific method that determines what length february has this year
	public static int februraryDays(int year) {
		if(isLeapYear(year)) {
			return 29;
		}
		else return 28;
	}
	
	//prints a whole calender year by looping through the printMonth method 12 times.
	public static void printCalender(int year) {
		for (int i = 1; i <= 12; i++) {
			printMonth(i, year);
		}
	}
	
}
