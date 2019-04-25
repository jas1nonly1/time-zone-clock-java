import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class TimeZoneClock extends JFrame implements ActionListener
{
  JButton worldclock_button;
  JButton alarm_button;
  JButton stopwatch_button;
  JButton timer_button;
  JLabel title;

  public TimeZoneClock() 
  {
    setLayout(null);
	
	title = new JLabel("Time Zone Clock");
	title.setForeground(Color.blue);
	title.setFont(new Font("Serif", Font.BOLD, 26));
    worldclock_button = new JButton("World Clock");
    alarm_button = new JButton("Alarm");
    stopwatch_button = new JButton("Stopwatch");
    timer_button = new JButton("Timer");
	
	title.setBounds(200,150,200,200);
    worldclock_button.setBounds(50,120,200,50);
    alarm_button.setBounds(300,120,200,50);
    stopwatch_button.setBounds(50,350,200,50);
    timer_button.setBounds(300,350,200,50);

    worldclock_button.addActionListener(this);
    alarm_button.addActionListener(this);
    stopwatch_button.addActionListener(this);
    timer_button.addActionListener(this);
	
	add(title);
    add(worldclock_button);
    add(alarm_button);
    add(stopwatch_button);
    add(timer_button);

    setSize(600,600);
    setTitle("Time Zone Clock");
    setVisible(true);

  }

  public void actionPerformed(ActionEvent ae)
  {
     if(ae.getSource() == worldclock_button)
     {
	new WorldClock();
     }
     else if(ae.getSource() == alarm_button)
     {
	new Alarm();
     }
     else if(ae.getSource() == stopwatch_button)
     {
	new Stopwatch();
     }
     else if(ae.getSource() == timer_button)
     {
	new Timer();
     }
  }

  public static void main(String[] ar)
  {
    new TimeZoneClock();
  }
}  