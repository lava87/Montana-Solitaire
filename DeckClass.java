// The "DeckClass" class.
import java.awt.*;
import hsa.Console;
import java.util.*;

public class DeckClass extends ShapeClass
{
    protected Vector deck = new Vector ();
    private String FaceValues = "A23456789TJQK";

    public DeckClass ()
    {
	super (Color.red, 100, 70, 300, 300);

    }


    public DeckClass (Color clr, int h, int x, int y)
    {
	super (clr, h, (int) (h * 0.7), x, y);

    }


    public DeckClass (String standard, Color clr, int h, int x, int y)
    {
	super (clr, h, (int) (h * 0.7), x, y);
	for (int i = 0 ; i < 13 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {

		CardClass cc = new CardClass (FaceValues.substring (i, i + 1), j, false);
		deck.addElement (cc);
		// System.out.println(j);
	    }
	}
    }


    public boolean isEmpty ()
    {
	return deck.isEmpty ();
    }


    public int getCardCount ()
    {
	deck.trimToSize ();
	if (deck.isEmpty ())
	{
	    return 0;
	}
	return deck.capacity ();
    }


    public void shuffle ()
    {
	if (!isEmpty ())
	{
	    for (int i = 1 ; i < getCardCount () * 15 ; i++)
	    {
		addTop (getAndRemove ((int) (Math.random () * getCardCount ())));
	    }
	}
    }


    public void shuffleSpecific (int c1, int c2)  //shuffles card between the address range including the two cards given in the range
    {
	if (!isEmpty () && c1 != c2)
	{
	    for (int i = c1 ; i < c2 + 1 ; i++)
	    {
		addAt (getAndRemove ((int) (Math.random () * c2 + c1)), i);
	    }
	}
    }


    public void draw (Console c)
    {
	standardize ();
	if (isEmpty ())
	{
	    c.setColour (super.getColour ());
	    c.drawRect (super.getCentreX () - super.getWidth () / 2, super.getCentreY () - super.getHeight () / 2, super.getWidth (), super.getHeight ());
	}
	else if (!isEmpty ())
	{
	    getTop ().draw (c);
	}
	//draw stuff
    }


    public void erase (Console c)
    {
	if (getCardCount () > 0)
	{

	    getTop ().erase (c);
	}
	else
	{
	    Color prevclr = iColour;
	    iColour = Color.white;
	    draw (c);
	    iColour = prevclr;
	}
    }


    public void draw (Graphics g)
    {
	standardize ();
	if (isEmpty ())
	{
	    setColour (super.getColour ());
	    g.drawRect (super.getCentreX () - super.getWidth () / 2, super.getCentreY () - super.getHeight () / 2, super.getWidth (), super.getHeight ());
	}
	else if (!isEmpty ())
	{
	    getTop ().draw (g);
	}
	//draw stuff
    }


    public void erase (Graphics g)
    {
	if (getCardCount () > 0)
	{

	    getTop ().erase (g);
	}
	else
	{
	    Color prevclr = iColour;
	    iColour = Color.white;
	    draw (g);
	    iColour = prevclr;
	}
    }


    public void addTop (CardClass cc)
    {
	deck.add (0, cc);
    }


    public void addBot (CardClass cc)
    {
	deck.addElement (cc);
    }


    public void addAt (CardClass cc, int num)
    {
	if (num >= 0 && num < getCardCount ())
	{
	    deck.add (num, cc);
	}
	else if (num >= getCardCount ())
	{
	    addBot (cc);
	}
    }


    public void removeTop ()
    {
	deck.remove (0);
    }


    public void removeBot ()
    {
	deck.remove (getCardCount () - 1);
    }


    public void removeAt (int num)
    {
	deck.remove (num);
    }


    public void removeAll ()
    {
	while (!isEmpty ())
	{
	    removeTop ();
	}
    }


    public void flipAll ()
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    ((CardClass) deck.elementAt (i)).flip ();
	}
    }


    public void flipTop ()
    {
	((CardClass) deck.elementAt (0)).flip ();
    }


    public CardClass getCard (int num)
    {
	if (!isEmpty () && num >= 0 && num <= getCardCount ())
	{
	    return (CardClass) deck.elementAt (num);
	}
	else
	{
	    return (CardClass) getTop ();
	}
    }


    public CardClass getAndRemove (int num)
    {
	if (!isEmpty () && num >= 0 && num <= getCardCount ())
	{
	    return (CardClass) deck.remove (num);
	}
	else
	{
	    return (CardClass) deck.remove (0);
	}
    }


    public CardClass getTop ()
    {
	return (CardClass) getCard (0);
    }


    public CardClass getBot ()
    {
	return getCard (getCardCount ());
    }


    public void swap (int a, int b)
    {
	if (a >= 0 && b >= 0 && a < getCardCount () && b < getCardCount ())
	{
	    Collections.swap (deck, a, b);
	}
    }


    public void standardize ()
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    ((CardClass) deck.elementAt (i)).setFill (super.getColour ());
	    ((CardClass) deck.elementAt (i)).setHeight (super.getHeight ());
	    ((CardClass) deck.elementAt (i)).setCenter (super.getCentreX (), super.getCentreY ());
	}

    }


    public boolean checkFace (int card1, int card2)  //checks if the face value of the second card is one greater than the first card, returns true if they are consecutive and in order
    {
	if (card1 != card2)
	{
	    for (int i = 0 ; i < FaceValues.length ()-1 ; i++)
	    {
		if (getCard (card1).getFace ().equals (FaceValues.substring (i, i+1)))
		{
		    if (getCard (card2).getFace ().equals (FaceValues.substring (i + 1, i+2)))
		    {
			return true;
		    }
		    else
		    {
			return false;
		    }
		}
	    }

	}
	return false;
    }
} // DeckClass class
