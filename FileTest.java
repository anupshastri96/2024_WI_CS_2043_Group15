import java.io.*;
public class FileTest
{
	public static void main(String[] args) throws IOException
	{
		FileWriter fw = null; 
		BufferedWriter bw = null; 
		PrintWriter pw = null;

		try
		{
			fw = new FileWriter("ticketList.txt", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			//price, then seatnum, then row, then isPremium, then id
			pw.println("12.99,8,4,true,10001,");
			pw.println("6.00,7,7,false,64342,");
			pw.println("100.09,1,1,true,33234,");

			System.out.println("epic");
			pw.flush();
		}
		finally
		{
			try
			{
				pw.close();
				bw.close();
				fw.close();
			}
			catch (IOException io)
			{
				System.out.println("IO error");
			}
		}
	}
}