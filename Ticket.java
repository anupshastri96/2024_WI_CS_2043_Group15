public class Ticket {

   private Double price;
   private int seatNum;
   private int row;
   private boolean isPremium;
   private int id;
   private static int count = 1000;
   
   public Ticket (Double priceIn, int seatNumIn, int rowIn, boolean isPremiumIn) {
      price = priceIn;
      seatNum = seatNumIn;
      row = rowIn;
      isPremium = isPremiumIn;
      id = ++count;
   }   
   
   public Double getPrice () {
      return price;
   }

   public int getSeat () {
      return seatNum;
   }

   public int getRow () {
      return row;
   }

   public boolean getType () {
      return isPremium;
   }

   public int getID () {
      return id;
   }

	public void changeType()
	{
		if(isPremium==true)
		{
			isPremium=false;
		}
		else
		{
			isPremium=true;
		}
	}
}