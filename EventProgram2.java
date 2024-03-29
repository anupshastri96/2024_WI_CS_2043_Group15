import java.util.Scanner;
import java.util.*;
public class EventProgram
{
	public static void main(String[] args)
	{

		//TEST EVENTS! REMOVE LATER

		//
		String password = "password123";
		boolean loop = true;
		ArrayList<Event> eventList = new ArrayList<Event>();
		char in;
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you an attendee (A) or staff (S)?");
		char ch = sc.next().charAt(0);
		char ch2 = Character.toUpperCase(ch);

		if((ch2!='S')&&(ch2!='A'))
		{
			while((ch2!='S')&&(ch2!='A'))
			{
				System.out.println("Invalid input. Please enter A or S.");
				ch = sc.next().charAt(0);
				ch2 = Character.toUpperCase(ch);
			}
		}

		if(ch2=='A')
		{
			while(loop==true)
			{
				System.out.println("What would you like to do?\n1) Purchase ticket\n2) Remove ticket" +
						"\n3) View ticket information\n4) Upgrade/downgrade ticket\n5 Close program");
				in = sc.next().charAt(0);
				if(in=='1')
				{
					System.out.println("Test 1\n");
				}
				else if(in=='2')
				{
					System.out.println("Test 2\n");
				}
				else if(in=='3')
				{
					System.out.println("Test 3\n");
				}
				else if(in=='4')
				{
					System.out.println("Test 4\n");
				}
				else if(in=='5')
				{
					loop=false;
				}
				else
				{
					System.out.println("Invalid input. Please type a number corresponding to a command.\n");
				}
			}
		}

		if(ch2=='S')
		{
			System.out.println("Please enter the password.");
			String p = sc.nextLine();
			p = sc.nextLine();
			if(p.equals(password)==false)
			{
				System.out.println("Incorrect password. 4 tries left.");
				p = sc.next();
				for(int i =0;(i<3)&&(p.equals(password)==false);i++)
				{
					System.out.println("Incorrect password. " + (3-i) + " tries left.");
					p = sc.next();
				}
			}
			if(p.equals(password))
			{
				System.out.println("Password accepted.\n");
				while(loop==true)
				{
					System.out.println("What would you like to do?\n1)Register an event\n2)Close an event\n3)Reschedule an event" +
							"\n4)Close program");
					in = sc.next().charAt(0);
					if(in=='1')
					{
						System.out.println("\nPlease enter the name of your event, provide a short description, the venue where the event will be held, and the schedule of your event.");
						System.out.print("Name of event: ");
						sc.nextLine();
						String eventName = sc.nextLine();
						
						System.out.print("Short description: ");
						String description = sc.nextLine();
						
						System.out.print("\nMoving onto the venue you wish to hold your event at. \nPlease provide the name of the location: ");
						String venueLocation = sc.nextLine();
						System.out.print("The floor your event will be held on: ");
						int venueFloor = sc.nextInt();
						
						System.out.print("The room number: ");
						int venueRoom = sc.nextInt();
						sc.nextLine();
						
						System.out.print("The opening time of your chosen venue: ");
						String venueOpening = sc.nextLine();
						
						System.out.print("The closing time of your chosen venue: ");
						String venueClosing = sc.nextLine();
						Venue venue = new Venue (venueLocation, venueFloor, venueRoom, venueOpening, venueClosing);
						
						System.out.print("\nNow, please enter the schedule of your event. \nThe date your event will be held on: ");
						String scheduleDate = sc.nextLine();
						
						System.out.print("The starting time of your event: ");
						String scheduleStart = sc.nextLine();
						
						System.out.print("The time your event will end: ");
						String scheduleEnd = sc.nextLine();
						
						Schedule schedule = new Schedule (scheduleDate, scheduleStart, scheduleEnd);
						
						Event event = new Event (eventName, description, venue, schedule);
						System.out.println("\nPlease make sure that the following information is correct.\nName of event: " + event.getEventName() + "\nDescription: " + event.getDescription());
						System.out.println("Location name: " + venue.getLocation() + "\nFloor " + venue.getFloor() + ", room " + venue.getRoomNum() + "\nVenue opens at " + venue.getOpeningTime() + ", closes at " + venue.getClosingTime());
						System.out.println("Event date: " + schedule.getDate() + "\nDuration: " + schedule.getDuration());
						
						System.out.println("\nDoes the information above look correct? (y or n)");
						String answer = sc.nextLine();
						
						if (answer.equals("y")) {
						   System.out.println("Perfect! Your event has been booked. Thank you for booking with us! Your designated event number is given below, please use it when you want to close your event, or to change any details of the event.");
						   System.out.println("Your event number: " + event.getEventNumber());
						   System.out.println("\n");
						   eventList.add(event);
						}
						
						else if(answer.equals("n")) {
						   System.out.println("Please re-enter your booking information.");
						   System.out.println("\n");
						}
						
						else {
						   System.out.println("Incorrect format: please enter y or n.");
						   answer = sc.nextLine();
						   
						   if (answer.equals("y")) {
						      System.out.println("Perfect! Your event has been booked. Thank you for booking with us! Your designated event number is given below, please use it when you want to close your event, or to change any details of the event.");
						      System.out.println("Your event number: " + event.getEventNumber());
						      System.out.println("\n");
						      eventList.add(event);
						   }
						   
						   else if(answer.equals("n")) {
						      System.out.println("Please re-enter your booking information.");
						      System.out.println("\n");
						   }
						}
						
						
					}
					else if(in=='2')
					{
						System.out.println("Please enter your event number.");
						sc.nextLine();
						int eventNum = sc.nextInt();
						
						Iterator<Event> iterator = eventList.iterator();
						boolean found = false;
						
						while (iterator.hasNext()) {
						   Event event = iterator.next();
						   
						   if (event.getEventNumber() == eventNum) {
						      found = true;
						      System.out.println("Are you sure you would like to close " + event.getEventName() + "? (y or n)");
						      sc.nextLine();
						      String answer = sc.nextLine();
						      
						      if (answer.equals("y")) {
						         System.out.println("Closing " + event.getEventName() + "...");
						         event.closeEvent();
						         iterator.remove();
						         System.out.println("Event closed.");
						         System.out.println("\n");
						      }
						      
						      else if (answer.equals("n")) {
						         System.out.println("Please try again and re-enter your event number.");
						         System.out.println("\n");
						      }
						      
						      else {
						         System.out.println("Incorrect format: please enter y or n.");
						         answer = sc.nextLine();
						   
						         if (answer.equals("y")) {
						            System.out.println("Closing " + event.getEventName() + "...");
						            event.closeEvent();
						            iterator.remove();
						            System.out.println("Event closed.");
						            System.out.println("\n");
						         }
						   
						         else if(answer.equals("n")) {
						            System.out.println("Please try again and re-enter your event number.");
						            System.out.println("\n");
						         }
						     
						      }
						      
						   }
						}
						
						if (!found) {
						   System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   System.out.println("\n");
						}
						
					}
					else if(in=='3')
					{
						System.out.println("Please enter your event number.");
						sc.nextLine();
						int eventNum = sc.nextInt();
						
						
						boolean found = false;
						Iterator<Event> iterator = eventList.iterator();
						
						while(iterator.hasNext()) {
						   Event event = iterator.next();
						   
						
				                   if(event.getEventNumber() == eventNum) {
				                      found = true;
				                      System.out.print("\nNow input the new scheduling information. \nNew date: ");
						      sc.nextLine();
						      String newDate = sc.nextLine();
						
						      System.out.print("New start time: ");
						      String newStart = sc.nextLine();
						
						      System.out.print("New end time: ");
						      String newEnd = sc.nextLine();
						
						      Schedule newSchedule = new Schedule (newDate, newStart, newEnd);
				                      event.changeSchedule(newDate, newStart, newEnd);
				                      System.out.println("\nYour event schedule has been changed. Please check the information below, and make sure it is correct. Otherwise, please try again.");
				                System.out.print("\nNew date: " + event.getSchedule().getDate() + "\nNew duration: " + event.getSchedule().getDuration());
				                System.out.println("\n");
				                   }
				                   
				                   
				                }
				                
				                
				                if (!found) {
						   System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   System.out.println("\n");
						}
				                
						
					}
					else if(in=='4')
					{
						loop=false;
					}
					else
					{
						System.out.println("Invalid input. Please type a number corresponding to a command.\n");
					}
				}
			}
			else
			{
				System.out.println("Incorrect password. 0 tries left. CLosing program.");
			}
		}
		System.out.println("Goodbye.");
	}
}
