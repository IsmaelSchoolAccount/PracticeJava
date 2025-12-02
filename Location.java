public class Location {
    private int row, col; 
   
   public Location() 
   { 
      row = 2;
      col = 4;
   } 

   public Location clone(Location loc) 
   { 
      Location loc1 = new Location(); 
      loc1.row = loc.row; 
      loc1.col = loc.col; 
      return loc1;
   }

   public static void main(String args[])
   {
      Location loc1 = new Location();
      Location loc2 = loc1.clone(loc1);
   }
}
