import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


class Alarm extends JFrame implements ActionListener
{
  JButton new_button;
  CardLayout card_layout;
  JPanel cards_panel;
  JTextField name_textfield;
  JComboBox hours;
  JComboBox minutes;
  JTextArea things_area;
  JComboBox snooze_time;
  JButton save_button;
  JButton cancel_button;
  JDialog dialog;
  JButton snooze_button;
  JButton off_button;
  MyTask myTask = null;
  int year,month,date,hr,min;
  AudioInputStream audioInputStream;
  Clip clip;

  String[] hours_array = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

  String[] minutes_array = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
  "26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54",
  "55","56","57","58","59"};


  public Alarm()
  {
    JPanel north_panel = new JPanel();
    new_button = new JButton("New Alarm");
    new_button.addActionListener(this);
    north_panel.add(new_button);
    add(north_panel,BorderLayout.NORTH);

    card_layout = new CardLayout();
    cards_panel = new JPanel(card_layout);

    JPanel newalarm_panel = new JPanel();
    newalarm_panel.setLayout(null);
    JLabel name_label = new JLabel("Alarm Name");
    name_textfield = new JTextField();
    JLabel time_label = new JLabel("Choose the time");
    hours = new JComboBox(hours_array);
    minutes = new JComboBox(minutes_array);
    JLabel things_label = new JLabel("List of important things to do:");
    things_area = new JTextArea();
    JLabel snooze_label = new JLabel("Snooze time");
    snooze_time = new JComboBox();
    snooze_time.addItem("5 minutes");snooze_time.addItem("10 minutes");snooze_time.addItem("20 minutes");snooze_time.addItem("30 minutes");
    save_button = new JButton("Save");
    cancel_button = new JButton("Cancel");
    save_button.addActionListener(this);
    cancel_button.addActionListener(this);

    name_label.setBounds(30,30,200,30);
    name_textfield.setBounds(150,30,100,30);
    time_label.setBounds(30,80,100,30);
    hours.setBounds(150,80,100,30);
    minutes.setBounds(270,80,100,30);
    things_label.setBounds(30,130,200,30);
    things_area.setBounds(250,130,300,300);
    snooze_label.setBounds(30,450,100,30);
    snooze_time.setBounds(150,450,100,30);
    save_button.setBounds(200,530,100,30);
    cancel_button.setBounds(350,530,100,30);

    newalarm_panel.add(name_label);newalarm_panel.add(name_textfield);newalarm_panel.add(time_label);newalarm_panel.add(hours);
    newalarm_panel.add(minutes);newalarm_panel.add(things_label);newalarm_panel.add(things_area);newalarm_panel.add(snooze_label);
    newalarm_panel.add(snooze_time);newalarm_panel.add(save_button);newalarm_panel.add(cancel_button);

    JPanel empty_panel = new JPanel();
    cards_panel.add(empty_panel);
    cards_panel.add(newalarm_panel,"New Alarm");

    add(cards_panel);
  
    setSize(700,700);
    setVisible(true);
  }

  public static void main(String ar[])
  {
    new Alarm();
  }

  public void actionPerformed(ActionEvent ae)
  {
	if(ae.getSource() == new_button)
	{
	  card_layout.show(cards_panel,"New Alarm");
	}

	if(ae.getSource() == cancel_button)
	{
	   myTask.cancel();
	   JOptionPane.showMessageDialog(this,"Alarm Canceled..!!");
	}
	   
	
	Calendar cal;
	Date d;
	java.util.Timer timer;

        if(ae.getSource() == save_button)
        {
          String hrs = hours.getSelectedItem().toString();
	  String mins = minutes.getSelectedItem().toString();
        
	  hr = Integer.parseInt(hrs);
          min = Integer.parseInt(mins);

          findYearMonthDate(hr,min);
	   
          myTask = new MyTask();
	  cal = new GregorianCalendar(year,month,date,hr,min);
	  d = cal.getTime();
	  timer = new java.util.Timer();
	  timer.schedule(myTask,d);
	  Date d1 = new Date();
	  long diff = (d.getTime()-d1.getTime()) / (1000*60);
	  long diff_hr = diff/60;
	  long diff_min = diff%60;
	  JOptionPane.showMessageDialog(this,"Alarm set for " + diff_hr + " hours and " + diff_min + " minutes from now");
        }

        
       if(ae.getSource() == snooze_button)
        {
	   dialog.dispose();
	   clip.stop();

	   String snooze = snooze_time.getSelectedItem().toString();
	   String c = snooze.substring(0,1);
	   int snoozetime = Integer.parseInt(c);

           min = min + snoozetime;
           if(min > 59)
           {
		min = min - 60;
	        hr = hr + 1;
           }
           if(hr > 23)
	   {
		hr = 24 - hr;
           }

	   findYearMonthDate(hr,min);
	   myTask = new MyTask();
	   cal = new GregorianCalendar(year,month,date,hr,min);
	   d = cal.getTime();
	   timer = new Timer();
	   timer.schedule(myTask,d);
	}

       if(ae.getSource() == off_button)
       {
	  dialog.dispose();
	  clip.stop();
       }
	   
  }

  class MyTask extends TimerTask
  {
    public void run()
    {
      dialog = new JDialog(Alarm.this,name_textfield.getText());
      JTextArea text_area = new JTextArea(things_area.getText());
      dialog.add(text_area);
      JPanel south_panel = new JPanel();
      snooze_button = new JButton("Snooze");
      off_button = new JButton("Off");
      snooze_button.addActionListener(Alarm.this);
      off_button.addActionListener(Alarm.this);     
      south_panel.add(snooze_button);
      south_panel.add(off_button);
      dialog.add(south_panel,BorderLayout.SOUTH);
      dialog.addWindowListener(new MyWindowAdapter());
      dialog.setSize(400,400);
      dialog.setVisible(true);

      try 
      {
        audioInputStream = AudioSystem.getAudioInputStream(new File("railroad_crossing_bell_alarm.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
	Thread.sleep(45000);
      } 
      catch(Exception ex) 
      {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
      }

    }
  }

  public void findYearMonthDate(int hr,int min)
  {
    
    Date d = new Date();
    SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    String d1 = obj.format(d);
    String[] values = d1.split("-");
    String current_year = values[0];
    String current_month = values[1];
    String current_date = values[2];
    String current_hr = values[3];
    String current_min = values[4];
    int c_year = Integer.parseInt(current_year);
    int c_month = Integer.parseInt(current_month);
    int c_date = Integer.parseInt(current_date);
    int c_hr = Integer.parseInt(current_hr);
    int c_min = Integer.parseInt(current_min);
    year = c_year;
    month = c_month-1;
	   
    boolean leap = checkLeap(c_year);

    if(hr == c_hr)
    {
	if(min > c_min)
		date = c_date;
	else
		date = c_date + 1;
		
    }
    else if(hr > c_hr)
    {
	date = c_date;
    }
    else
    {
	date = c_date + 1;
    }

   if(current_month.equals("01") && date == 32)
   {
	date = 1;
	month = 1;
   }
   else if(current_month.equals("03") && date == 32)
   {
	date = 1;
	month = 3;
   }
   else if(current_month.equals("05") && date == 32)
   {
	date = 1;
	month = 5;
    }
    else if(current_month.equals("07") && date == 32)
    {
	date = 1;
	month = 7;
    }
    else if(current_month.equals("08") && date == 32)
    {
	date = 1;
	month = 8;
    }
    else if(current_month.equals("10") && date == 32)
    {
	date = 1;
	month = 10;
    }
    else if(current_month.equals("12") && date == 32)
    {
	date = 1;
	month = 0;
	year = year + 1;
    }
    else if(current_month.equals("04") && date == 31)
    {
	date = 1;
	month = 4;
    }
    else if(current_month.equals("06") && date == 31)
    {
	date = 1;
	month = 6;
    }
    else if(current_month.equals("09") && date == 31)
    {
	date = 1;
	month = 9;
    }
    else if(current_month.equals("11") && date == 31)
    {
	date = 1;
	month = 11;
    }
    else if(current_month.equals("02") && leap && date == 29)
    {
	date = 1;
        month = 2;
    }
    else if(current_month.equals("02") && date == 28)
    {
	date = 1;
	month = 2;
    }
  }

   public boolean checkLeap(int year)
   {
	boolean flag = false;
	if(year % 100 == 0)
	{
	  if(year % 400 == 0)
	  {
		flag = true;
	  }
	}
	else
	{
	  if(year % 4 == 0)
	  {
		flag = true;
	  }
	}
    return flag;
  }

class MyWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent we)
  {
	dialog.dispose();
	clip.stop();
  }
}

}

    