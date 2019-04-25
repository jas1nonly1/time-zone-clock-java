import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


class Timer extends JFrame implements ActionListener,Runnable
{
  JTextField hrs_textfield;
  JTextField mins_textfield;
  JTextField secs_textfield;
  JLabel hrs_label;
  JLabel mins_label;
  JLabel secs_label;
  JButton start_button;
  JButton pause_button;
  JButton resume_button;
  JButton stop_button;
  Thread thread;

  public Timer()
  {
    setLayout(null);

    JLabel label1 = new JLabel("Enter the hours");
    hrs_textfield = new JTextField();
    JLabel label2 = new JLabel("Enter the minutes");
    mins_textfield = new JTextField();
    JLabel label3 = new JLabel("Enter the seconds");
    secs_textfield = new JTextField();
    start_button = new JButton("Start");
    hrs_label = new JLabel("00");
    JLabel label4 = new JLabel(":");
    mins_label = new JLabel("00");
    JLabel label5 = new JLabel(":");
    secs_label = new JLabel("00");
    pause_button = new JButton("Pause");
    resume_button = new JButton("Resume");
    stop_button = new JButton("Stop");

    start_button.addActionListener(this);
    pause_button.addActionListener(this);
    resume_button.addActionListener(this);
    stop_button.addActionListener(this);

    label1.setBounds(30,30,100,30);
    hrs_textfield.setBounds(150,30,100,30);
    label2.setBounds(30,80,100,30);
    mins_textfield.setBounds(150,80,100,30);
    label3.setBounds(30,130,120,30);
    secs_textfield.setBounds(150,130,100,30);
    start_button.setBounds(180,170,100,30);
    hrs_label.setBounds(150,250,50,30);
    label4.setBounds(210,250,10,30);
    mins_label.setBounds(230,250,50,30);
    label5.setBounds(290,250,10,30);
    secs_label.setBounds(310,250,50,30);
    pause_button.setBounds(80,280,100,30);
    resume_button.setBounds(200,280,100,30);
    stop_button.setBounds(320,280,100,30);

    add(label1);add(hrs_textfield);add(label2);add(mins_textfield);add(label3);add(secs_textfield);add(start_button);add(hrs_label);add(label4);
    add(mins_label);add(label5);add(secs_label);add(pause_button);add(resume_button);add(stop_button);

    setTitle("Timer");
    setSize(600,600);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource() == start_button)
    {
       thread = new Thread(this);
       thread.start();
    }
    else if(ae.getSource() == pause_button)
    {
	thread.suspend();
    }
    else if(ae.getSource() == resume_button)
    {
	thread.resume();
    }
    else if(ae.getSource() == stop_button)
    {
	thread.stop();
	hrs_label.setText("00");
	mins_label.setText("00");
	secs_label.setText("00");
    }
  }

  public void run()
  {
    int hrs = Integer.parseInt(hrs_textfield.getText());
    int mins = Integer.parseInt(mins_textfield.getText());
    int secs = Integer.parseInt(secs_textfield.getText());


    if(mins>60 || secs>60)
    {
	JOptionPane.showMessageDialog(this,"Invalid time..!!");
    }
    else
    {
      Font font = new Font(Font.SERIF,Font.BOLD,20);
      hrs_label.setFont(font);
      mins_label.setFont(font);
      secs_label.setFont(font);
     
      hrs_label.setText(hrs_textfield.getText());
      mins_label.setText(mins_textfield.getText());
      secs_label.setText(secs_textfield.getText());

     for(int s = secs ; s>=0 ; s--)
     {
       secs_label.setText(String.valueOf(s));
       if(s == 0 && (mins!=0 || hrs!=0))
       {
	 s = 60;
       }
       try
       {
	  Thread.sleep(1000);
       }
       catch(InterruptedException ex)
       {
	 System.err.println(ex.getMessage());
       }

       if(s == 60)
       {
	 mins--;
	 if(mins>=0)
	 {
	    mins_label.setText(String.valueOf(mins));
	 }
	 if(mins == -1 && hrs!=0)
	 {
	   hrs--;
	   mins = 59;
	   mins_label.setText(String.valueOf(mins));
	   hrs_label.setText(String.valueOf(hrs));
	 }
       }
    }

    Clip clip = null;
    try 
      {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("railroad_crossing_bell_alarm.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
	Thread.sleep(1000);
      } 
      catch(Exception ex) 
      {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
      }
     JOptionPane.showMessageDialog(this,"Time up..!!!");
     clip.stop();
   }
  }

  public static void main(String[] ar)
  {
    new Timer();
  }
}