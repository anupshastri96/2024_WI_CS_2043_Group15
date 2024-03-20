public class Venue {

   private String location;
   private int floor;
   private int roomNum;
   private String openingTime;
   private String closingTime;
   
   public Venue (String locationIn, int floorIn, int roomNumIn, String openingTimeIn, String closingTimeIn) {
      location = locationIn;
      floor = floorIn;
      roomNum = roomNumIn;
      openingTime = openingTimeIn;
      closingTime = closingTimeIn;
   }
   
   
   public String getLocation () {
      return location;
   }
   
   
   public int getFloor () {
      return floor;
   }
   
   
   public int getRoomNum () {
      return roomNum;

   }
   
   
   public String getOpeningTime () {
      return openingTime;
   }
   
   
   public String getClosingTime () {
      return closingTime;
   }
   
   
   public void changeVenue (String newLocation, int newFloor, int newRoom) {
      location = newLocation;
      floor = newFloor;
      roomNum = newRoom;
   }
   
   
   
}
