// The "CardClass" class.
import java.awt.*;
import hsa.Console;

public class CardClass extends ShapeClass
{
    private String FaceValue;
    private int Suit; //1=diamond,2=club,3=heart,4=spade
    private boolean isFlipped;
    private Color FillColour;
    private Color temp;

    public CardClass ()
    {
	super (Color.red, 200, (int) (200 * 0.7), 400, 200);
	FillColour = Color.white;
	FaceValue = "6";
	Suit = 1;
	isFlipped = false;
    }
    
    public CardClass (String face)
    {
	super (Color.red, 200, (int) (200 * 0.7), 400, 200);
	FillColour = Color.white;
	FaceValue = face;
	Suit = 1;
	isFlipped = false;
    }

    public CardClass (String face, int suit, int height, int x, int y)
    {
	super (Color.red, height, (int) (height * 0.7), x, y);

	FillColour = Color.white;
	Suit = suit;
	isFlipped = false;
	FaceValue = face;

	setSuit (suit);
    }


    public CardClass (String face, int suit, boolean flipped)
    {
	super (Color.red, 200, (int) (200 * 0.7), 400, 200);

	FillColour = Color.orange;
	Suit = suit;
	isFlipped = flipped;
	FaceValue = face;

	setSuit (suit);
    }


    public void draw (Console c)
    {
	//     if (!isFlipped)
	//     {
	//         c.setColor (FillColour);
	//         c.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	//         //SUIT
	//         if (Suit == 1)
	//         {
	//             DiamondClass z = new DiamondClass ((int) (iHeight / 4), iCentreX, iCentreY);
	//             if (iColour == Color.white)
	//             {
	//                 z.erase (c);
	//             }
	//             else if (iColour != Color.white)
	//             {
	//                 z.draw (c);
	//             }
	//         }
	//         else if (Suit == 2)
	//         {
	//             ClubClass z = new ClubClass ((int) (iHeight / 4), iCentreX, iCentreY);
	//             if (iColour == Color.white)
	//             {
	//                 z.erase (c);
	//             }
	//             else if (iColour != Color.white)
	//             {
	//                 z.draw (c);
	//             }
	//         }
	//         else if (Suit == 3)
	//         {
	//             HeartClass z = new HeartClass ((int) (iHeight / 4), iCentreX, iCentreY);
	//             if (iColour == Color.white)
	//             {
	//                 z.erase (c);
	//             }
	//             else if (iColour != Color.white)
	//             {
	//                 z.draw (c);
	//             }
	//         }
	//         else if (Suit == 4)
	//         {
	//             SpadeClass z = new SpadeClass ((int) (iHeight / 4), iCentreX, iCentreY);
	//             if (iColour == Color.white)
	//             {
	//                 z.erase (c);
	//             }
	//             else if (iColour != Color.white)
	//             {
	//                 z.draw (c);
	//             }
	//         }
	//         //FACE LETTER
	//
	//         int fontPoint = 1;
	//
	//         while (true)
	//         {
	//             Font f = new Font ("ComicSans", Font.PLAIN, fontPoint);
	//             FontMetrics fm = Toolkit.getDefaultToolkit ().getFontMetrics (f);
	//             if (fm.getHeight () > iHeight * 0.2)
	//             {
	//                 break;
	//             }
	//             else
	//             {
	//                 fontPoint++;
	//             }
	//         }
	//         System.out.println (fontPoint);
	//
	//         Font f1 = new Font ("ComicSans", Font.PLAIN, fontPoint);
	//
	//         c.setColor (iColour);
	//         c.setFont (f1);
	//         c.drawString (FaceValue, (int) (iCentreX - iWidth / 2 + iWidth * 0.1), (int) (iCentreY - iHeight / 2 + iHeight * 0.2));
	//
	//     }
	//     else if (isFlipped)
	//     {
	//         c.setColor (FillColour);
	//         c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	//     }
    }


    public void draw (Graphics c)
    {
	if (!isFlipped)
	{
	    temp = iColour;
	    c.setColor (Color.white);
	    c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    //SUIT
		c.setColor (temp);
	    if (Suit == 0)
	    {
		DiamondClass z = new DiamondClass ((int) (iHeight / 4), iCentreX, iCentreY);
		if (iColour == Color.white)
		{
		    z.erase (c);
		}
		else if (iColour != Color.white)
		{
		    z.draw (c);
		}
	    }
	    else if (Suit == 1)
	    {
		// System.out.println (Suit);
		ClubClass z = new ClubClass ((int) (iHeight / 4), iCentreX, iCentreY);
		if (iColour == Color.white)
		{
		    z.erase (c);
		}
		else if (iColour != Color.white)
		{
		    z.draw (c);
		}

	    }
	    else if (Suit == 2)
	    {
		// System.out.println (Suit);
		HeartClass z = new HeartClass ((int) (iHeight / 4), iCentreX, iCentreY);
		if (iColour == Color.white)
		{
		    z.erase (c);
		}
		else if (iColour != Color.white)
		{
		    z.draw (c);
		}

	    }
	    else if (Suit == 3)
	    {
		// System.out.println (Suit);
		SpadeClass z = new SpadeClass ((int) (iHeight / 4), iCentreX, iCentreY);
		if (iColour == Color.white)
		{
		    z.erase (c);
		}
		else if (iColour != Color.white)
		{

		    z.draw (c);
		}

	    }
	    //FACE LETTER

	    int fontPoint = 1;

	    while (true)
	    {
		Font f = new Font ("ComicSans", Font.PLAIN, fontPoint);
		FontMetrics fm = Toolkit.getDefaultToolkit ().getFontMetrics (f);
		if (fm.getHeight () > iHeight * 0.2)
		{
		    break;
		}
		else
		{
		    fontPoint++;
		}
	    }


	    Font f1 = new Font ("ComicSans", Font.PLAIN, fontPoint);

	    c.setColor (iColour);
	    c.setFont (f1);
	    c.drawString (FaceValue, (int) (iCentreX - iWidth / 2 + iWidth * 0.1), (int) (iCentreY - iHeight / 2 + iHeight * 0.2));

	}
	else if (isFlipped)
	{
	    c.setColor (FillColour);
	    c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	}
    }


    public void erase (Console c)
    {
	Color prevclr = iColour;
	Color prevfill = FillColour;
	iColour = Color.white;
	FillColour = Color.white;
	draw (c);
	FillColour = prevfill;
	iColour = prevclr;
    }


    public void erase (Graphics c)
    {
	Color prevclr = iColour;
	Color prevfill = FillColour;
	iColour = Color.white;
	FillColour = Color.white;
	draw (c);
	FillColour = prevfill;
	iColour = prevclr;
    }


    public void setFill (Color f)
    {
	FillColour = f;
    }


    public void setFace (String f)
    {
	String FaceValues = "A23456789TJQK";
	if (FaceValues.indexOf (f) > -1)
	{
	    FaceValue = f;
	}
    }


    public void setSuit (int s)
    {
	if (s >= 0 && s < 4)
	{
	    Suit = s;
	    if (s == 0 || s == 2)
	    {
		setColour (Color.red);
	    }
	    else if (s == 1 || s == 3)
	    {
		setColour (Color.black);
	    }
	}
    }


    public void flip ()
    {
	isFlipped = !isFlipped;
    }


    public void setHeight (int h)
    {
	if (h > 0)
	{
	    super.setHeight (h);
	    super.setWidth ((int) (h * 0.7));
	}
    }


    public void setWidth (int w)
    {
	if (w > 0)
	{
	    super.setHeight ((int) (w / 0.7));
	    super.setWidth (w);
	}
    }


    //gets
    public Color getFill ()
    {
	return FillColour;
    }


    public String getFace ()
    {
	return FaceValue;
    }


    public int getSuit ()
    {
	return Suit;
    }


    public boolean getFlipped ()
    {
	return isFlipped;
    }


    public boolean isInside (int x, int y)
    {
	if (x >= (int) (super.getCentreX () - super.getWidth () / 2) && x <= (int) (super.getCentreX () + super.getWidth () / 2) && y >= (int) (super.getCentreY () - super.getHeight () / 2) && y <= (int) (super.getCentreY () + super.getHeight () / 2))
	{
	    return true;
	}
	else
	{
	    return false;
	}

    }
} // CardClass class
