// The "ShapeClass" class.
import java.awt.*;
import hsa.Console;

public abstract class ShapeClass
{
    protected Color iColour;
    protected int iHeight, iWidth, iCentreX, iCentreY;
    public abstract void draw (Console c);
    public abstract void draw (Graphics c);
    public ShapeClass ()
    {
	iColour = Color.red;
	iHeight = 100;
	iWidth = 50;
	iCentreX = 150;
	iCentreY = 200;
    }


    public ShapeClass (Color clr)
    {
	iColour = clr;
	iHeight = 100;
	iWidth = 50;
	iCentreX = 150;
	iCentreY = 200;
    }


    public ShapeClass (Color clr, int h, int w, int x, int y)
    {
	iColour = clr;
	iHeight = h;
	iWidth = w;
	iCentreX = x;
	iCentreY = y;
    }




    public void setColour (Color newColor)
    {
	iColour = newColor;
    }


    public Color getColour ()
    {
	return iColour;
    }


    public void setHeight (int h)
    {
	iHeight = h;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public void setWidth (int w)
    {
	iWidth = w;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public void setCenter (int x, int y)
    {
	iCentreX = x;
	iCentreY = y;
    }


    public int getCentreX ()
    {
	return iCentreX;
    }


    public int getCentreY ()
    {
	return iCentreY;
    }


    public void erase (Console c)
    {
	Color prevclr = iColour;
	iColour = Color.white;
	draw (c);
	iColour = prevclr;
    }


    public void erase (Graphics c)
    {
	Color prevclr = iColour;
	iColour = Color.white;
	draw (c);
	iColour = prevclr;
    }


    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }
} // ShapeClass class
