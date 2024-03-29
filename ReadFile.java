import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

public class ReadFile 
	{
  	public static void main(String[] args) 
	{
		int num, row, id;
		Double price;
		boolean prem;
		Ticket t;
		String b;

    		try 
		{
      			File myObj = new File("ticketList.txt");
      			Scanner myReader = new Scanner(myObj);
			myReader.useDelimiter(","); 
			myReader.nextLine();
      			while (myReader.hasNextLine()) 
			{
				price = myReader.nextDouble();
				num = myReader.nextInt();
				row = myReader.nextInt();
				b = myReader.next();
				if(b.equals("true"))
				{
					prem = true;
				}
				else
				{
					prem = false;
				}
				id = myReader.nextInt();
				
				t = new Ticket(price, num, row, prem, id);
				System.out.print("$" + price + " " + num + " " + row + " ");
				if(prem==true)
				{
					System.out.print("premium " + id + "\n");
				}
				else
				{
					System.out.print("regular " + id + "\n");
				}
        			myReader.nextLine();
      			}
      			myReader.close();
    		} 
		catch (FileNotFoundException e) 
		{
      			System.out.println("An error occurred.");
      			e.printStackTrace();
    		}
  	}
}