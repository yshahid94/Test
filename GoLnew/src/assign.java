import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class assign extends JApplet implements MouseListener, ActionListener, ChangeListener
{
    int pr_xCord = 10, pr_yCord = 10, ms_xCord = 10, ms_yCord = 10, Size = 40;
	String pr_string = "", style = "";
	JButton Bigger, Smaller;
	JSlider bar;
    JLabel pr_label, ps_label;
    JTextField pr_text, ps_text;
	Graphics G2;
	
	public Polygon ResizeY(int PolySize, int xCord, int yCord)
	//pre: Size and x,y co-ordinates are set to the desirable values
	//post: Resizes and locates the Y polygon making it ready to be drawn in the paint method
	{
    int i = PolySize / 3;
    int[] xy = {0,i,i,i*2,i*2,i*3,i*3,i*2,i*2,i,i,0};
    int[] yy = {0,0,i*2,i*2,0,0,i*3,i*3,i*5,i*5,i*3,i*3};
	
	int tempyy[] = new int[12], tempxy[]  = new int[12];
			for(int j = 0; j <= xy.length-1; j++)
  				{
        			tempxy[j] = xy[j] + xCord;
   				}
   			for(int j = 0; j <= yy.length-1; j++)
  				{
       				tempyy[j] = yy[j] + yCord ;
   				}
				
	Polygon PolyY = new Polygon(tempxy, tempyy, tempxy.length);
	return PolyY;
	}
	public Polygon ResizeS(int PolySize, int xCord, int yCord)
	//pre: Size and x,y co-ordinates are set to the desirable values
	//post: Resizes and locates the S polygon making it ready to be drawn in the paint method
	{
    int i = PolySize / 3;
    int[] xs = {0,i*3,i*3,i,i,i*3,i*3,0,0,i*2,i*2,0};
    int[] ys = {0,0,i*1,i*1,i*2,i*2,i*5,i*5,i*4,i*4,i*3,i*3};
	
	int tempys[] = new int[12], tempxs[] = new int[12];
  	for(int j = 0; j <= xs.length-1; j++)
  		{
       		tempxs[j] = xs[j] + xCord;
   		}
   	for(int j = 0; j <= ys.length-1; j++)
  		{
      			tempys[j] = ys[j] + yCord;
   		}
	Polygon PolyS = new Polygon(tempxs, tempys, tempxs.length);
	return PolyS;
	}	
	
	public void Print(char character)
	{
	if (character == 'y' || character == 'Y')
				{
					//Draws Y
					G2.fillPolygon(ResizeY(Size, pr_xCord, pr_yCord));
				}
			
			else if (character == 's' || character == 'S')
				{
					//Draws S
					G2.fillPolygon(ResizeS(Size, pr_xCord, pr_yCord));
				}	
			else
				{
					G2.drawString(Character.toString(character),pr_xCord,pr_yCord + ((Size/3)*5));
				}
	}
	
    public void init()
    //pre:
    //post: sets Layout of applet, adds text-fields, buttons and action listeners for them, a bar and a changelistener for it, labels and mouse listeners to applet
    {
		
		
		setLayout(new FlowLayout());
		
        pr_label = new JLabel("Enter Text");
        add(pr_label);
        
        pr_text = new JTextField(10);
        add(pr_text);
        pr_text.addActionListener(this);
        addMouseListener(this);
        
        ps_label = new JLabel("Enter Style");
        add(ps_label);
        
        ps_text = new JTextField(5);
        add(ps_text);
        ps_text.addActionListener(this);
        addMouseListener(this);
		
		Smaller = new JButton("Smaller");
        Bigger = new JButton("Bigger");
        add(Smaller);
        add(Bigger);
        Smaller.addActionListener(this);
        Bigger.addActionListener(this);
		
		bar = new JSlider(JSlider.HORIZONTAL);
		bar.setValue(50);
		add(bar);
		bar.addChangeListener(this);
		
    }
         
    public void start()
    //pre:
    //post: sets size and background colour
    {
        setSize(400,400);
        setBackground(Color.white);
    }

    public void paint(Graphics g)
    //pre: g is valid graphics object
    //post: draws to square or circle at recorded mouse location to applet window
    {
		G2 = getGraphics();
		super.paint(g);
		G2.setFont(new Font("Verdana", Font.BOLD, Size*2));
        G2.setColor(Color.black);
        
        pr_yCord = ms_yCord;
        pr_xCord = ms_xCord;
		
		////////////SQUARE\\\\\\\\\\\\\
		
        if (style.equals("s") || style.equals("S"))
        {
			int charperline1, charperline2, charperline3, charperline4, M1 = 0, M3 = 0;
			charperline2 = pr_string.length()/4;
			charperline4 = charperline2;
			
			if (pr_string.length()%4 == 3)
				{
					charperline1 = 1 + charperline2;
					charperline3 = 1 + charperline2;
					charperline4 = 1 + charperline2;
					M3 = 1;
				}
			else if (pr_string.length()%4 == 2)
				{
					charperline1 = 1 + charperline2;
					charperline3 = 1 + charperline2;
				}
			else if (pr_string.length()%4 == 1)
				{
					charperline1 = 1 + charperline2;
					charperline3 = charperline2;
					M1 = 1;
				}
			else	
				{
					charperline1 = charperline2;
					charperline3 = charperline2;
				}
				
			String string1, string2, string3, string4;
			
			string1 = pr_string.substring(0,charperline1);
			string2 = pr_string.substring(charperline1, charperline1 + charperline2 );
			string3 = pr_string.substring(charperline1 + charperline2, charperline1 + charperline2 + charperline3 );
			string4 = pr_string.substring(charperline1 + charperline2 + charperline3, pr_string.length() );
			
			///Debug
			//showStatus(string1 + " " + string2 + " " + string3 + " " + string4 + " = " + Integer.toString(charperline1) + " " + Integer.toString(charperline2) + " " + Integer.toString(charperline3) + " " + Integer.toString(charperline4));
			///Debug
			
			for (int k = 0; k < charperline1; k++)
			{
				Print(string1.charAt(k));
			
				pr_xCord += ((Size/3)*5);
			}
			
			pr_yCord += (((Size/3)*5)/2)*M3;
			
			for (int k = 0; k < charperline2; k++)
			{
				Print(string2.charAt(k));
			
				pr_yCord += ((Size/3)*5);				
			}
			
			pr_xCord -= (((Size/3)*5)/2)*M1;
			
			for (int k = 0; k < charperline3; k++)
			{
				Print(string3.charAt(k));
			
				pr_xCord -= ((Size/3)*5);
				pr_yCord += (((Size/3)*5)/2)*M3;
				M3 = 0;	
			}
			
			for (int k = 0; k < charperline4; k++)
			{
				Print(string4.charAt(k));
				pr_yCord -= ((Size/3)*5);
				pr_xCord -= (((Size/3)*5)/2)*M1;
				M1 = 0;
			}
			
			showStatus("Square style applied");
        }
        ////////////SQUARE\\\\\\\\\\\\\
        
		////////////ZIG ZAG\\\\\\\\\\\\\
		
		else if	(style.equals("z") || style.equals("Z"))
		{
			int j = 1;
		
			for (int i = 0; i < pr_string.length(); i++)
			{
				Print(pr_string.charAt(i));
				if (j == 1)
				{
					
					pr_yCord += ((Size/3)*5);
					pr_xCord += ((Size/3)*5);
					j = 2;
				}
				else
				{
				
					pr_yCord -= ((Size/3)*5);
					pr_xCord += ((Size/3)*5);
					j = 1;
				}
			}
			showStatus("Zig Zag style applied");
		}
		////////////ZIG ZAG\\\\\\\\\\\\\
		
		
		else if (style.equals("i") || style.equals("I") || style.equals("d") || style.equals("D") || style.equals("h") || style.equals("H"))
		
			for (int i = 0; i < pr_string.length(); i++)
			{
				Print(pr_string.charAt(i));
				
				if	(style.equals("i") || style.equals("I"))
				{
					pr_xCord += ((Size/3)*5);
					pr_yCord += ((Size/3)*5)+5;
					showStatus("Diagonal style applied");
				}
				else if (style.equals("d") || style.equals("D"))
				{
					pr_yCord += ((Size/3)*5)+5;
					showStatus("Vertical style applied");
				}
				else if (style.equals("h") || style.equals("H"))
				{
					pr_xCord += ((Size/3)*5);
					showStatus("Horizontal style applied");
				}
		} //For Loop
		else
			{
				showStatus("Please use either I, D or H for Diagonal, Vertical or Horizontal");
			}
	}// Paint
	
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e)
	//pre: event driven by LMB being Pressed
    //post: get cursor location, repaint
	{
		ms_xCord = e.getX();
        ms_yCord = e.getY();
		repaint();
	}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
	public void stateChanged(ChangeEvent e)
	{
		Size = bar.getValue();
		repaint();
	}
    public void actionPerformed(ActionEvent e) //This piece of code is ran when enter is pressed in text1
    //pre: event driven by any component action
    //post: determines square or circle drawn on text-field contents
    {
		if ((e.getSource() == this.Bigger) && (Size < 40))
			{
				Size += 10;
			}
		else if ((e.getSource() == this.Smaller) && (Size > 20))
			{
				Size -= 10;
			}
		else if (e.getSource() == this.pr_text)
			{
			pr_string = pr_text.getText();
			}
		else if (e.getSource() == this.ps_text)
			{
			style = ps_text.getText();
			}
		repaint();
    }
}