import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

class WorldClock extends JFrame implements ItemListener
{
  JComboBox countries;
  JLabel time_label;
  JLabel temperature_label;
  JLabel weather_label;
  JLabel label1;
  JLabel label2;
  JLabel label3;

  String[] countries_array = {"Afghanistan","Argentina","Austria","Bangladesh","Bhutan","China","Cuba","Egypt","Germany","Greece","Hong Kong", "Iceland","India","Iran","Iraq","Ireland","Italy","Japan","Kenya","Malaysia","Mauritius","Myanmar","Nepal","Nigeria","Peru","Singapore",
"Switzerland","Thailand","Turkey","UAE"};

  String[] time_zones = {"GMT+4:30","GMT-3:00","GMT+1:00","GMT+6:00","GMT+6:00","GMT+8:00","GMT-5:00","GMT+2:00","GMT+1:00","GMT+2:00","GMT+8:00",       "GMT","GMT+5:30","GMT+3:30","GMT+3:00","GMT","GMT+1:00","GMT+9:00","GMT+3:00","GMT+8:00","GMT+4:00","GMT+6:30","GMT+5:45","GMT+1:00",  
  "GMT-5:00","GMT+8:00","GMT+1:00","GMT+7:00","GMT+2:00","GMT+4:00"};

  String[] temperature = {"2","17","7","25","11","11","21","14","3","7","19","4","16","13","16","3","8","10","20","27","24","24","12","24","23",
  "28","6","29","9","21"};
  

  String[] weather = {"Clear with periodic clouds","Rainy","Cloudy","Fog","Cloudy","Sunny","Rainy","Clear","Clear","Clear with periodic clouds",
  "Light rain showers","Cloudy","Sunny","Cloudy","Widespread dust","Clear","Clear with periodic clouds","Partly sunny","Clear with periodic clouds",
  "Sunny","Clear","Fog","Fog","Cloudy","Partly cloudy","Mostly cloudy","Cloudy","Mostly sunny","Clear with periodic clouds",
  "Clear with periodic clouds"};

  public WorldClock() 
  {
     setLayout(null);

     JLabel country_label = new JLabel("Select a country");
     countries = new JComboBox(countries_array);
     countries.addItemListener(this);
     time_label = new JLabel();
     temperature_label = new JLabel();
     weather_label = new JLabel();
     label1 = new JLabel();
     label2 = new JLabel();
     label3 = new JLabel();

     country_label.setBounds(30,30,200,30);
     countries.setBounds(250,30,200,30);
     time_label.setBounds(30,100,400,30);
     temperature_label.setBounds(30,150,400,30);
     weather_label.setBounds(30,200,400,30);
     label1.setBounds(30,300,400,30);
     label2.setBounds(30,350,400,30);
     label3.setBounds(30,400,400,30);

     Font font = new Font(Font.SERIF,Font.BOLD,15);
     time_label.setFont(font);
     temperature_label.setFont(font);
     weather_label.setFont(font);
     label1.setFont(font);
     label2.setFont(font);
     label3.setFont(font);

     add(country_label);add(countries);add(time_label);add(temperature_label);add(weather_label);add(label1);add(label2);add(label3);

     setSize(600,600);
     setTitle("Time Zone Clock");
     setVisible(true);

  }

  public void itemStateChanged(ItemEvent ie)
  {
   if(ie.getStateChange() == ItemEvent.SELECTED)
   {
    int index = countries.getSelectedIndex();
    String id = time_zones[index];

    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone(id));
    String new_now = sdf.format(now);
    time_label.setText("The current time in " + countries.getSelectedItem() + " is: " + new_now);

    temperature_label.setText("The temperature in " + countries.getSelectedItem() + " is: " + temperature[index] + " degree celsius");
    weather_label.setText("The weather in " + countries.getSelectedItem() + " is: " + weather[index]);

    sdf = new SimpleDateFormat("HH:mm:ss"); 
    label1.setText("The current time in your country is: " + sdf.format(now));
    sdf.setTimeZone(TimeZone.getTimeZone(id));
    new_now = sdf.format(now);
    label2.setText("The current time in " + countries.getSelectedItem() + " is: " + new_now);

    sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone(id));
    new_now = sdf.format(now);
    int year = Integer.parseInt(new_now.substring(6,10));
    int month = Integer.parseInt(new_now.substring(3,5));
    int date = Integer.parseInt(new_now.substring(0,2));
    int hour = Integer.parseInt(new_now.substring(11,13));
    int min = Integer.parseInt(new_now.substring(14,16));
    int sec = Integer.parseInt(new_now.substring(17));
    Calendar cal = new GregorianCalendar(year,month-1,date,hour,min,sec);
    Date now1 = cal.getTime();
    long diff = (now.getTime() - now1.getTime())/(1000*60);
    diff = Math.abs(diff);
    long diff_hour = diff/60;
    long diff_min = diff%60;
    label3.setText("The time difference is: " + diff_hour + " hours " + diff_min + " minutes "); 
   }

  }

  public static void main(String[] ar)
  {
	new WorldClock();
  }
}