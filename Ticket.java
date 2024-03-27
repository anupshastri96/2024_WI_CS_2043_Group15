public class Ticket {

   private Double price;
   private int seatNum;
   private int row;
   private boolean isPremium;
   private int id;
   
   public Ticket (Double priceIn, int seatNumIn, int rowIn, boolean isPremiumIn, int idIn) {
      price = priceIn;
      seatNum = seatNumIn;
      row = rowIn;
      isPremium = isPremiumIn;
      id = idIn;
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
}