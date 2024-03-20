public class Attendee {

   private String name;
   private String email;
   private Ticket purchasedTicket;
   
   public Attendee (String nameIn, String emailIn) {
      name = nameIn;
      email = emailIn;
   }
   
   
   public String getName () {
      return name;
   }
   
   
   public String getEmail () {
      return email;
   }
   
   
   public void updateEmail (String newEmail) {
      email = newEmail;
   }
   
   
   public void newTicket (Ticket ticket) {
      purchasedTicket = ticket;
   }

 
}
