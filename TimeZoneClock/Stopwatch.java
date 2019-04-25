import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Stopwatch extends JFrame implements ActionListener,Runnable
{
  JTextField textfield_hrs;
  JTextField textfield_mins;
  JTextField textfield_secs;
  JButton button_start;
  JButton button_stop;
  JButton button_pause;
  JButton button_resume; 
  Thread thread;

  public Stopwatch()
  {
    setLayout(null);

    textfield_hrs = new JTextField();
    textfield_mins = new JTextField();
    textfield_secs = new JTextField();
    JLabel label_colon1 = new JLabel(":");
    JLabel label_colon2 = new JLabel(":");
    JLabel label_hrs = new JLabel("Hrs");
    JLabel label_mins = new JLabel("Mins");
    JLabel label_secs = new JLabel("Secs"); 
    button_start = new JButton("Start");
    button_stop = new JButton("Stop");
    button_pause = new JButton("Pause");
    button_resume = new JButton("Resume");

    textfield_hrs.setBounds(50,80,100,30);
    textfield_mins.setBounds(180,80,100,30);
    textfield_secs.setBounds(310,80,100,30);
    label_colon1.setBounds(160,80,50,30);
    label_colon2.setBounds(290,80,50,30);
    label_hrs.setBounds(70,110,50,30);
    label_mins.setBounds(200,110,50,30);
    label_secs.setBounds(330,110,50,30);
    button_start.setBounds(90,180,100,30);
    button_stop.setBounds(90,230,100,30);
    button_pause.setBounds(220,180,100,30);
    button_resume.setBounds(220,230,100,30);

    textfield_hrs.setText("00");
    textfield_mins.setText("00");
    textfield_secs.setText("00");

    button_start.addActionListener(this);
    button_stop.addActionListener(this);
    button_resume.addActionListener(this);
    button_pause.addActionListener(this);

    add(textfield_hrs);
    add(textfield_mins);
    add(textfield_secs);
    add(label_colon1);
    add(label_colon2);
    add(label_hrs);
    add(label_mins);
    add(label_secs);
    add(button_start);
    add(button_stop);
    add(button_pause);
    add(button_resume);

    setTitle("Stopwatch");
    setSize(600,600);
    setVisible(true);

  }

  public void run()
  {
    int secs = 0;
    int mins = 0;
    int hrs = 0;
    textfield_secs.setText("00");
    textfield_mins.setText("00");
    textfield_mins.setText("00");
    while(true)
    {
	for(secs=0 ; secs<=59 ; secs++)
        {
          textfield_secs.setText(String.valueOf(secs));
          try
          {
            Thread.sleep(1000);
          }
          catch(InterruptedException ex)
          {
   
          }
        
        if(secs == 59)
        {
	  if(mins!=59)
	  {
            textfield_mins.setText(String.valueOf(++mins));
	  }
	  else
	  {
	     mins++;
	  }
        }
        if(mins == 60)
        {
           mins=0;
           textfield_mins.setText(String.valueOf(mins));
           textfield_hrs.setText(String.valueOf(++hrs));
           
        }
       }
    }
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getActionCommand().equals("Start"))
    {
       thread = new Thread(this);
        thread.start();
    }
    else if(ae.getActionCommand().equals("Pause"))
    {
        thread.suspend();
    }
    else if(ae.getActionCommand().equals("Resume"))
    {
        thread.resume();
    }
    else if(ae.getActionCommand().equals("Stop"))
    {
        thread.stop();
    }




  }

  public static void main(String ar[])
  {
    new Stopwatch();
  }
}
