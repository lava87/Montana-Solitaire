// The "Hand" class.
import java.awt.*;
import hsa.Console;

public class Hand extends DeckClass
{
    private int distHor;
    public Hand ()
    {
	super (Color.red, 100, 300, 300);
	distHor = 25;
    }


    public Hand (Color clr, int h, int x, int y, int d)
    {
	super (clr, h, x, y);
	if (d > 0)
	{
	    distHor = d;
	}
    }


    public void setDist (int d)
    {
	if (d > 0)
	{
	    distHor = d;
	}
    }


    public int getDist ()
    {
	return distHor;
    }


    public void standardize ()
    {
	if (!isEmpty ())
	{
	    for (int i = 0 ; i < getCardCount () ; i++)
	    {
		((CardClass) deck.elementAt (i)).setFill (super.getColour ());
		((CardClass) deck.elementAt (i)).setHeight (super.getHeight ());
		((CardClass) deck.elementAt (i)).setCenter (super.getCentreX () + i * distHor, super.getCentreY ());
	    }
	}
    }

    public int getActualXPos(int cardNum){
	return super.getCentreX () + cardNum * distHor;
    }

    public void draw (Graphics c)
    {
	standardize ();
	
	for (int i = 0 ; i < getCardCount () ; i++)
	{
	    
	    if (getCard (i).getFace ().equals ("A"))
	    {
		c.setColor (super.getColour ());
		c.drawRect (super.getCentreX () - super.getWidth () / 2+ i*distHor, super.getCentreY () - super.getHeight () / 2, super.getWidth (), super.getHeight ());
	    }
	    else
	    {
		getCard (i).draw (c);
	    }
	}
    }


    public void draw (Console c)
    {
	standardize ();
	for (int i = 0 ; i < getCardCount (); i++)
	{
	    if (getCard (i).getFace ().equals ("A"))
	    {
		c.setColor (super.getColour ());
		c.drawRect (super.getCentreX () - super.getWidth () / 2, super.getCentreY () - super.getHeight () / 2, super.getWidth (), super.getHeight ());
	    }
	    else
	    {
		getCard (i).draw (c);
	    }
	}
    }
} // Hand class
