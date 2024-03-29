import java.util.*;
import java.io.*;
public class EventProgram
{
	public static void main(String[] args)
	{
		//TEST LISTS, REMOVE LATER
		ArrayList<Attendee> aList = new ArrayList<Attendee>();
		ArrayList<Ticket> tList = new ArrayList<Ticket>();
		ArrayList<Event> eList = new ArrayList<Event>();
		Venue v = new Venue("uuhhhh", 2, 120, "10:00AM", "10:00PM");
		Schedule sch = new Schedule("September 20", "2:00PM", "4:00PM");
		Event eTemp = new Event("Woah", "e",v,sch);
		eList.add(eTemp);
		sch = new Schedule("September 21","2:00PM", "4:00PM");
		eTemp = new Event ("Huh?", "tt", v, sch);
		eList.add(eTemp);
		Ticket tTemp;
		int index, row, num;
		Double price;
		boolean prem;
		String email;
		Attendee aTemp;

		String password = "password123";
		boolean loop = true;
		char in;
		int tID = 1000;
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
			System.out.println("What is your full name?");
			String aName = sc.next();
			System.out.println("What is your email?");
			email = sc.next();
			Attendee newA = new Attendee(aName,email);

			while(loop==true)
			{
				System.out.println("\nWhat would you like to do?\n1) Purchase ticket\n2) Remove ticket" +
						"\n3) View ticket information\n4) Upgrade/downgrade ticket\n5 Close program");
				in = sc.next().charAt(0);
				if(in=='1')
				{
					System.out.println("Which event will you be attending?\n");
					for(int i=0;i<eList.size();i++)
					{
						eTemp = eList.get(i);
						System.out.println((i+1) + ") " + eTemp.getEventName());
					}
					index = sc.nextInt();
					index = index-1;
					if((index>=0)&&(index<eList.size()))
					{
						eTemp = eList.get(index);
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
							price = 15.0;
						}
						else
						{
							prem = false;
							price = 10.0;
						}
						System.out.println("What row are you in?");
						row = sc.nextInt();
						System.out.println("What column?");
						num = sc.nextInt();
						tTemp = new Ticket(price, num, row, prem);
						tList.add(tTemp);
						newA.buyTicket(tTemp);

					}
					else
					{
						System.out.println("Invalid input.");	
					}
				}
				else if(in=='2')
				{
					System.out.println("What is the ID of the ticket you wish to cancel?");
					tID = sc.nextInt();
					newA.removeTicket(tID);
				}
				else if(in=='3')
				{
					ArrayList<Ticket> allT = newA.viewTickets();
					for(int j=0;j<allT.size();j++)
					{
						System.out.print(allT.get(j).getID() + " ");
						if(allT.get(j).getType()==true)
						{
							System.out.println("Premium");
						}
						else
						{
							System.out.println("Standard");
						}
					}
				}
				else if(in=='4')
				{
					System.out.println("What is the ID of the ticket you wish to alter?");
					tID = sc.nextInt();
					newA.newTicket(tID);
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