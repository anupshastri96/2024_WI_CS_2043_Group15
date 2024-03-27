import java.io.*;
import java.util.*;
public class Event
{
	private String name;
	private String description;
	private Venue venue;
	private Schedule schedule;
	private boolean isOpen;
	private ArrayList<Ticket> ticketsSold;

	public Event(String nameIn, String descIn, Venue venIn, Schedule schIn)
	{
		name = nameIn;
		description = descIn;
		venue = venIn;
		schedule = schIn;

		isOpen = true;
		ticketsSold = new ArrayList<Ticket>();
	}

	public String getEventName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Venue getVenue()
	{
		return venue;
	}

	public Schedule getSchedule()
	{
		return schedule;
	}

	public void addTicket(Ticket newT)
	{
		ticketsSold.add(newT);
	}

	public void removeTicket(Ticket removeT)
	{
		int count = 0;
		Ticket temp;
		while(count<ticketsSold.size())
		{
			temp = ticketsSold.get(count);
			if(temp.getID() == removeT.getID())
			{
				ticketsSold.remove(count);
				count = ticketsSold.size();
			}
			count++;
		}
	}

	public void closeEvent()
	{
		isOpen=false;
	}
	public boolean openingStatus()
	{
		return isOpen;
	}
}