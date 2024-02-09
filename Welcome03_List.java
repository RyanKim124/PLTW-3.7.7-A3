/*
 * Arrays of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   public static void main(String[] args) {
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
             "station/station_id", "station/state",
             "station/latitude", "station/longitude");
      System.out.println("Total stations: " + allstns.size());

      int stateStations = 0;

      WeatherStation southest = allstns.get(0);
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      System.out.println("Stations in " + state);
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
            stateStations++;
         }

         if (ws.getLat() < southest.getLat())
         {
            southest = ws;
         }
      }

      System.out.println("Southest Weather Station: " + southest.getId() + ": " + southest.getName() + " " + southest.getLat());


      
      System.out.println("Number of stations in " + state + ": " + stateStations);
   }
}