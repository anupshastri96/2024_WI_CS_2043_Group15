import java.util.*;
import java.io.*;
public class EventProgram
{
	public static void main(String[] args)
	{
		ArrayList<Attendee> aList = new ArrayList<Attendee>();
		Attendee att = new Attendee("John Smith","johnsmith@gmail.com", "abcdefg");
		aList.add(att);
		att = new Attendee("Mary Lastname","marylastname@gmail.com", "uhhhhhhhhh51");
		aList.add(att);

		ArrayList<Ticket> tList = new ArrayList<Ticket>();
		ArrayList<Event> eventList = new ArrayList<Event>();
		Venue v = new Venue("Fredericton Convention  Centre", 2, 120, "10:00AM", "10:00PM", "5x10 Rectangular");
		Schedule sch = new Schedule("September 20, 2024", "2:00PM", "4:00PM");
		Event eTemp = new Event("Bake With Bob", "A comedy show in which Bob tries to bake a cake, except he has no idea how",v,sch, "Bob Bob");
		eventList.add(eTemp);
		sch = new Schedule("September 21, 2024","2:00PM", "4:00PM");
		eTemp = new Event ("World's Worst Rap Battle", "Participants all write a rap for a rap battle, but must run it through Google Translate 20 times, then they must rap the output abomination", v, sch, "Super Mario");
		eventList.add(eTemp);
		Ticket tTemp;
		int index, row, num, eID;
		Double price;
		boolean prem;
		String email, p;
		Attendee aTemp;

		String password = "password123";
		boolean loop = true;
		char in;
		int tID = 1000;

		Scanner sc = new Scanner(System.in);
		System.out.println("Are you an attendee (A) or staff (S)?");
		char ch = sc.next().charAt(0);
		char ch2 = Character.toUpperCase(ch);
		boolean closeProgram = false;
		boolean passKill = false;
		ArrayList<Ticket> allT;
		boolean eCheck, pCheck;
		int attInd = 0;

		if((ch2!='S')&&(ch2!='A'))
		{
			while((ch2!='S')&&(ch2!='A'))
			{
				System.out.println("Invalid input. Please enter A or S.");
				ch = sc.next().charAt(0);
				ch2 = Character.toUpperCase(ch);
			}
		}
		while(closeProgram==false)
		{
			if(ch2=='A')
			{
				System.out.println("Do you already have an account? Y or N");
				in = sc.next().charAt(0);
				in = Character.toUpperCase(in);
				while((in!='Y')&&(in!='N'))
				{
					System.out.println("Invalid input. Please type a Y or N.");
					in = sc.next().charAt(0);
					in = Character.toUpperCase(in);
				}
				if(in=='Y')
				{
					eCheck=false;
					System.out.println("What is your email?");
					email = sc.next();
					for(int i=0;i<aList.size();i++)
					{
						if(email.equals((aList.get(i)).getEmail()))
						{
							attInd = i;
							i = aList.size();
							eCheck=true;
							pCheck=false;
							System.out.println("Please enter the password.");
							p = sc.nextLine();
							p = sc.nextLine();
							if(p.equals((aList.get(attInd)).getPassword())==false)
							{
								System.out.println("Incorrect password. 4 tries left.");
								p = sc.next();
								for(int j =0;(j<3)&&(p.equals((aList.get(attInd)).getPassword())==false);j++)
								{
									System.out.println("Incorrect password. " + (3-j) + " tries left.");
									p = sc.next();
								}
							}
							if(p.equals((aList.get(attInd)).getPassword())==false)
							{
								System.out.println("Incorrect password. 0 tries left. CLosing program.");
								passKill = true;
								closeProgram = true;
								loop = false;
							}
							else
							{
								System.out.println("Password accepted.");
							}
						}
					}
					if(eCheck==false)
					{
						System.out.println("That email is not registered.");
						loop = false;
					}
				}
				else
				{
					System.out.println("What is your full name?");
					sc.nextLine();
					String aName = sc.nextLine();
					System.out.println("What is your email?");
					email = sc.next();
					System.out.println("What is your password?");
					p = sc.next();
					att = new Attendee(aName,email,p);
					aList.add(att);
					attInd = (aList.size()-1);
				}

				while(loop==true)
				{
					System.out.println("\nWhat would you like to do?\n1) Purchase ticket\n2) Remove ticket" +
							"\n3) View ticket information\n4) Upgrade/downgrade ticket\n5) Log out");
					in = sc.next().charAt(0);
					if(in=='1')
					{
						System.out.println("\nWhich event will you be attending?\n");
						for(int i=0;i<eventList.size();i++)
						{
							eTemp = eventList.get(i);
							System.out.println((i+1) + ") " + eTemp.getEventName());
						}
						index = sc.nextInt();
						index = index-1;
						if((index>=0)&&(index<eventList.size()))
						{
							eTemp = eventList.get(index);
							eID = (eventList.get(index)).getEventNumber();
							System.out.println("Do you want premium? Y for yes, N for no.");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);
							while((in!='Y')&&(in!='N'))
							{
								System.out.println("Invalid input. Please type a Y or N.");
								in = sc.next().charAt(0);
								in = Character.toUpperCase(in);
							}
							if(in=='Y')
							{
								prem = true;
							}
							else
							{
								prem = false;
							}
							System.out.println("What row are you in?");
							row = sc.nextInt();
							System.out.println("What column?");
							num = sc.nextInt();
							tTemp = new Ticket(eTemp.getEventName(), num, row, prem, eID);
							tList.add(tTemp);
							(aList.get(attInd)).buyTicket(tTemp);
							(eventList.get(index)).addTicket(tTemp, aList.get(attInd));

						}
						else
						{
							System.out.println("Invalid input.");	
						}
					}
					else if(in=='2')
					{
						System.out.println("\nWhat is the ID of the ticket you wish to cancel?");
						tID = sc.nextInt();
						allT = (aList.get(attInd)).viewTickets();
						for(int j=0;j<allT.size();j++)
						{
							if((allT.get(j)).getID()==tID)
							{
								eID = (allT.get(j)).getEID();
								for(int i=0;i<eventList.size();i++)
								{
									if((eventList.get(i)).getEventNumber()==eID)
									{
										(eventList.get(i)).removeTicket(allT.get(j), aList.get(attInd));
										i = eventList.size();
										System.out.println("The ticket has been removed.");
									}
								}
								j = allT.size();
							}
						}
						(aList.get(attInd)).removeTicket(tID);
					}
					else if(in=='3')
					{
						System.out.println("\nTicket ID, Ticket Type, Event Name, Event ID\n");
						allT = (aList.get(attInd)).viewTickets();
						for(int j=0;j<allT.size();j++)
						{
							System.out.print(allT.get(j).getID() + ", ");
							if(allT.get(j).getType()==true)
							{
								System.out.print("Premium, ");
							}
							else
							{
								System.out.print("Standard, ");
							}
							System.out.println(allT.get(j).getEName() + ", " + allT.get(j).getEID());
						}
					}
					else if(in=='4')
					{
						System.out.println("What is the ID of the ticket you wish to alter?");
						tID = sc.nextInt();
						(aList.get(attInd)).newTicket(tID);
					}
					else if(in=='5')
					{
						loop=false;
						System.out.println("Logging out...");
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
				p = sc.nextLine();
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
						System.out.println("\nWhat would you like to do?\n1)Register an event\n2)Close an event\n3)Reschedule an event" +
							 	"\n4)Add a break to an event\n5)Change venue details\n6)View Attendee List\n7)View Event List\n8)Log out");
						in = sc.next().charAt(0);
						if(in=='1')
						{
							System.out.println("\nPlease enter the name of your event, provide a short description, the venue where the event will be held, and the schedule of your event.");
							System.out.print("Name of event: ");
							sc.nextLine();
							String eventName = sc.nextLine();
						
							System.out.print("Short description: ");
							String description = sc.nextLine();

							System.out.print("Name of speaker: ");
							String speaker = sc.nextLine();
						
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
						
							System.out.print("The way you would like the seating to be arranged (ex: circular tables in a star formation): ");
							String venueSeating = sc.nextLine();
						
							Venue venue = new Venue (venueLocation, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						
							System.out.print("\nNow, please enter the schedule of your event. \nThe date your event will be held on: ");
							String scheduleDate = sc.nextLine();
						
							System.out.print("The starting time of your event: ");
							String scheduleStart = sc.nextLine();
						
							System.out.print("The time your event will end: ");
							String scheduleEnd = sc.nextLine();
						
							Schedule schedule = new Schedule (scheduleDate, scheduleStart, scheduleEnd);
						
							Event event = new Event (eventName, description, venue, schedule, speaker);
							System.out.println("\nPlease make sure that the following information is correct.\nName of event: " + event.getEventName() + "\nDescription: " + event.getDescription());
							System.out.println("Location name: " + venue.getLocation() + "\nFloor " + venue.getFloor() + ", room " + venue.getRoomNum() + "\nSeating arrangement: " + venue.getSeatingType() + "\nVenue opens at " + venue.getOpeningTime() + ", closes at " + venue.getClosingTime());
							System.out.println("Event date: " + schedule.getDate() + "\nDuration: " + schedule.getDuration());
						
							System.out.println("\nDoes the information above look correct? (y or n)");
							String answer = sc.nextLine();
						
							if (answer.equals("y")) 
							{
						   		System.out.println("Perfect! Your event has been booked. Thank you for booking with us! Your designated event number is given below, please use it when you want to close your event, or to change any details of the event.");
						   		System.out.println("Your event number: " + event.getEventNumber());
						   		System.out.println("\n");
						   		eventList.add(event);
							}
						
							else if(answer.equals("n")) 
							{
						   		System.out.println("Please re-enter your booking information.");
						   		System.out.println("\n");
							}
						
							else 
							{
						   		System.out.println("Incorrect format: please enter y or n.");
						   		answer = sc.nextLine();
						   
						   		if (answer.equals("y")) 
								{
						      			System.out.println("Perfect! Your event has been booked. Thank you for booking with us! Your designated event number is given below, please use it when you want to close your event, or to change any details of the event.");
						      			System.out.println("Your event number: " + event.getEventNumber());
						      			System.out.println("\n");
						      			eventList.add(event);
						   		}
						   
						   		else if(answer.equals("n")) 
								{
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
						
							while (iterator.hasNext()) 
							{
						   		Event event = iterator.next();
						   
						   		if (event.getEventNumber() == eventNum) 
								{
						      			found = true;
						      			System.out.println("\nAre you sure you would like to close " + event.getEventName() + "? (y or n)");
						      			sc.nextLine();
						      			String answer = sc.nextLine();
							
						      
						      			if (answer.equals("y")) 
									{
						         			System.out.println("Closing " + event.getEventName() + "...\n");
						         			event.closeEvent();
						         			iterator.remove();
						         			System.out.println("Event closed.");
						         			System.out.println("\n");
						      			}
						      
						      			else if (answer.equals("n")) 
									{
						         			System.out.println("Please try again and re-enter your event number.");
						         			System.out.println("\n");
						      			}
						      
									else 
									{
						         			System.out.println("Incorrect format: please enter y or n.");
						        			answer = sc.nextLine();
						   
						         			if (answer.equals("y")) 
										{
						            				System.out.println("Closing " + event.getEventName() + "...");
						            				event.closeEvent();
						            				iterator.remove();
						            				System.out.println("Event closed.");
						            				System.out.println("\n");
						         			}
						   
						         			else if(answer.equals("n")) 
										{
						            				System.out.println("Please try again and re-enter your event number.");
						            				System.out.println("\n");
						         			}
						     
									}
						      
								}
							}
							if (!found) 
							{
								System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   		System.out.println("\n");
							}
						}

						else if(in=='3')
						{
							System.out.println("\nPlease enter your event number.");
							sc.nextLine();
							int eventNum = sc.nextInt();
						
						
							boolean found = false;
							Iterator<Event> iterator = eventList.iterator();
						
							while(iterator.hasNext()) 
							{
						   		Event event = iterator.next();
						   
						
				                   		if(event.getEventNumber() == eventNum) 
								{
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
				                
				                
				                	if (!found) 
							{
						   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   		System.out.println("\n");
							}
			
						}
					
						else if(in=='4')
						{
							System.out.println("\nPlease enter your event number.");
							sc.nextLine();
							int eventNum = sc.nextInt();
						
							Iterator<Event> iterator = eventList.iterator();
							boolean found = false;
						
							while (iterator.hasNext()) 
							{
						   		Event event = iterator.next();
						   
						   		if (event.getEventNumber() == eventNum) 
								{
						      			found = true;
						      			System.out.println("\nEnter the duration of the break you would like to add to " + event.getEventName() + ". (Ex: 11:00am-11:30am)");
						      			sc.nextLine();
						      			String newBreak = sc.nextLine();
						      
					              			event.getSchedule().addBreak(newBreak);
						      
						      			System.out.println("Break added.\n");
						      
						   		}
						   
						  
							}
						
							if (!found) 
							{
						   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   		System.out.println("\n");
							}	
						}
					
						else if(in=='5')
						{
							boolean looping = true;
							while (looping) 
							{
								System.out.println("\nWelcome to the venue management system. What would you like to do?" + "\n1)Change an event's venue" + "\n2)Change seating arrangement" + "\n3)Back to event menu");
							
						        	char answer = sc.next().charAt(0);
						        
						        	if (answer=='1') 
								{
						        		System.out.println("Please enter your event number.");
									sc.nextLine();
									int eventNum = sc.nextInt();
								
									Iterator<Event> iterator = eventList.iterator();
									boolean found = false;
						
									while (iterator.hasNext()) 
									{
						   		   		Event event = iterator.next();
						   
						   		   		if (event.getEventNumber() == eventNum) 
										{
						      		      			found = true;
						      		      
						      		      			System.out.print("Please enter the name of the new venue: ");
						      		      			sc.nextLine();
						      		      			String venueName = sc.nextLine();
						      		      
						      		      			System.out.print("The new floor your event will be held on: ");
						      		      			int venueFloor = sc.nextInt();
						      		
						      		      
						      		      			System.out.print("The new room your event will be held in: ");
						      		      			int venueRoom = sc.nextInt();
						      		      
						      		      			System.out.print("The opening time of your new venue: ");
						      		      			sc.nextLine();
						      		      			String venueOpening = sc.nextLine();
						      		      
						      		      			System.out.print("The closing time of your new venue: ");
						      		      			String venueClosing = sc.nextLine();
						      		      
						      		      			System.out.print("The way the seating will be arranged: ");
						      		      			String venueSeating = sc.nextLine();
						      		      
						      		      			Venue venue = new Venue (venueName, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						      		      
						      		      			event.getVenue().changeVenue(venueName, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						      		      
						      		      			System.out.println("\nVenue changed. Please check that the information below looks correct. Otherwise, return to the venue menu and try again." + "\nVenue location: " + venue.getLocation() + "\non floor " + venue.getFloor() + ", room " + venue.getRoomNum() + "\nOpens at " + venue.getOpeningTime() + ", closes at " + venue.getClosingTime() + "\nSeating arrangement: " + venue.getSeatingType());
						      		      			System.out.println("\n");
						      		   		}
						      			}
						      		
						      			if (!found) 
									{
						                   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   		   		System.out.println("\n");
									}
						      		      
								
						        	}
						        	else if (answer=='2') 
								{
						        		System.out.println("Please enter your event number.");
									sc.nextLine();
									int eventNum = sc.nextInt();
								
									Iterator<Event> iterator = eventList.iterator();
									boolean found = false;
						
									while (iterator.hasNext()) 
									{
						   		   		Event event = iterator.next();
						   
						   		   		if (event.getEventNumber() == eventNum) 
										{
						      		      			found = true;
						      		      
						      		      			System.out.print("Enter the new way you would like your seats to be arranged: ");
						      		      			sc.nextLine();
						      		      			String newSeating = sc.nextLine();
						      		      
						      		      			event.getVenue().changeSeatingType(newSeating);
						      		      
						      		      
						      		      			System.out.println("Seating arrangement successfully changed.");
						      		   		}
						      			}
						      		
						      			if (!found) 
									{
						                   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.");
						   		   		System.out.println("\n");
									}
						      		      
						      		      
						        	}
						        
						        	else if (answer=='3') 
								{
						        		looping = false;
						        	}
						        
						        
						        	else 
								{
						        		System.out.println("Invalid answer, please type one of the numbers provided.");
						        
						        	}
							
							}
						}

						else if(in=='6')
						{
							System.out.println("Please enter your event number.");
							sc.nextLine();
							int eventNum = sc.nextInt();

							for(int i=0;i<eventList.size();i++)
							{
								if(eventNum==(eventList.get(i)).getEventNumber())
								{
									(eventList.get(i)).showAttendees();
								}
							}
						}

						else if(in=='7')
						{
							System.out.println("\nEvent Name, Event Number\n");
							for(int i=0;i<eventList.size();i++)
							{
								System.out.println((eventList.get(i)).getEventName() + ", " + (eventList.get(i)).getEventNumber());
							}
							System.out.println("Would you like to view the details of an event? Y or N");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);
							while((in!='Y')&&(in!='N'))
							{
								System.out.println("Invalid input. Please type a Y or N.");
								in = sc.next().charAt(0);
								in = Character.toUpperCase(in);
							}
							if(in=='Y')
							{
								System.out.println("Enter the event number.");
								int eventNum = sc.nextInt();
								for(int i=0;i<eventList.size();i++)
								{
									if(eventNum==(eventList.get(i)).getEventNumber())
									{
										System.out.print("\nName: " + (eventList.get(i)).getEventName() + "\nEvent Number: " + (eventList.get(i)).getEventNumber() + "\nSpeaker: " + (eventList.get(i)).getSpeaker() + "\n\nVenue: ");
										v = (eventList.get(i)).getVenue();
										sch = (eventList.get(i)).getSchedule();
										System.out.print(v.getLocation() + "\nFloor: " + v.getFloor() + "\nRoom Number: " + v.getRoomNum() + "\n\nDate: " + sch.getDate() + "\nDuration: " + sch.getDuration() + "\n");
									}
								}
							}
						}
					
						else if(in=='8')
						{
							loop=false;
							System.out.println("Logging out...");
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
					passKill = true;
					closeProgram = true;
				}
			}

			if(passKill==false)
			{
				System.out.println("\nWould you like to log in as a staff member or another attendee? Y or N");
				in = sc.next().charAt(0);
				in = Character.toUpperCase(in);
				while((in!='Y')&&(in!='N'))
				{
					System.out.println("Invalid input. Please type a Y or N.");
					in = sc.next().charAt(0);
					in = Character.toUpperCase(in);
				}
				if(in=='Y')
				{
					loop = true;
					System.out.println("Are you an attendee (A) or staff (S)?");
					ch = sc.next().charAt(0);
					ch2 = Character.toUpperCase(ch);

					if((ch2!='S')&&(ch2!='A'))
					{
						while((ch2!='S')&&(ch2!='A'))
						{
							System.out.println("Invalid input. Please enter A or S.");
							ch = sc.next().charAt(0);
							ch2 = Character.toUpperCase(ch);
						}

					}
				}
				else
				{
					closeProgram = true;
				}
			}
		}
		System.out.println("Goodbye.");
	}
}