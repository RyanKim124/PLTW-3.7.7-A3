import core.data.*;
import java.util.ArrayList;

public class Welcome02_Object {
   public static void main(String[] args) {
      ArrayList<Observation> obs = new ArrayList<Observation>();

      String id1 = "KATL";
      DataSource ds1 = DataSource.connect("http://weather.gov/xml/current_obs/" + id1 + ".xml"); 
      ds1.setCacheTimeout(15 * 60);  
      ds1.load();

      // ds1.printUsageString();

      Observation ob1 = ds1.fetch("Observation", "weather", "temp_f", "wind_degrees", "station_id");
      System.out.println(id1 + ": " + ob1);

      obs.add(ob1);
      
      String id2 = "KSAV";
      DataSource ds2 = DataSource.connect("http://weather.gov/xml/current_obs/" + id2 + ".xml"); 
      ds2.setCacheTimeout(15 * 60);  
      ds2.load();
      
      Observation ob2 = ds2.fetch("Observation", "weather", "temp_f", "wind_degrees", "station_id");
      System.out.println(id2 + ": " + ob2);

      obs.add(ob2);

      String id3 = "KLAX";
      DataSource ds3 = DataSource.connect("http://weather.gov/xml/current_obs/" + id3 + ".xml"); 
      ds3.setCacheTimeout(15 * 60);  
      ds3.load();
      
      Observation ob3 = ds3.fetch("Observation", "weather", "temp_f", "wind_degrees", "station_id");
      System.out.println(id3 + ": " + ob3);

      obs.add(ob3);

      Observation coldest = obs.get(0);
      for (int i = 1; i < obs.size(); i++)
      {
         if (obs.get(i).colderThan(coldest))
            coldest = obs.get(i);
      }

      System.out.println(coldest);
   }
}


/* Represents a weather observation */
class Observation {
   float temp;    // in fahrenheit
   int windDir;   // in degrees
   String description;
   String id;
   
   Observation(String description, float temp, int windDir, String id) {
      this.description = description;
      this.temp = temp;
      this.windDir = windDir;
      this.id = id;
   }
   
   /* determine if the temperature of this observation is colder than 'that's */
   public boolean colderThan(Observation that) {
      return this.temp < that.temp;
   }
   
   /* produce a string describing this observation */
   public String toString() {
      return ("ID: " + this.id + " " + temp + " degrees; " + description + " (wind: " + windDir + " degrees)");
   }
}