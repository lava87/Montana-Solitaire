// The "Main" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    Graphics g;
    Graphics g2;

    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment ();
    GraphicsDevice device = env.getDefaultScreenDevice ();
    GraphicsConfiguration config = device.getDefaultConfiguration ();
    Image screenImage = config.createCompatibleImage (1300, 600, Transparency.TRANSLUCENT);

    Button bQuit = new Button ("Quit");
    Button bNew = new Button ("New");
    Button bUndo = new Button ("Undo");
    Button bInfo = new Button ("Info");
    Choice cDiff = new Choice ();
    Choice cSuit = new Choice ();
    Choice cFace = new Choice ();

    Button bShuffle = new Button ("Shuffle");
    Button bSearch = new Button ("Search");
    Label labelAction = new Label ("Shuffles left:");
    TextField textFieldAction = new TextField (1);

    boolean startGame;
    boolean moving;

    int numShuffle;
    DeckClass d;
    DeckClass dM; //moving card
    Hand aHands[] = new Hand [4];

    int hMove; //the hand in which the card is currently moving (which hand (0-4))
    int cMove; //the card that is currently moving (which card (array element))

    int[] cShuff = new int [4]; //the number of cards that are in order per hand


    public void init ()
    {
	g = getGraphics ();
	g2 = screenImage.getGraphics ();

	setSize (1300, 600); //set the size of the applet
	setBackground (new Color (220, 250, 220));
	add (bQuit); //add buttons
	add (bNew);
	// add (bUndo);
	add (bInfo);
	add (bShuffle);
	add (labelAction);
	add (textFieldAction);

	cDiff.add ("Easy"); //choices for drop down thing
	cDiff.add ("Normal");
	cDiff.add ("Hard");
	add (cDiff);

	add (bSearch);

	cSuit.add ("Diamond");
	cSuit.add ("Club");
	cSuit.add ("Heart");
	cSuit.add ("Spade");
	add (cSuit);

	cFace.add ("2");
	cFace.add ("3");
	cFace.add ("4");
	cFace.add ("5");
	cFace.add ("6");
	cFace.add ("7");
	cFace.add ("8");
	cFace.add ("9");
	cFace.add ("T");
	cFace.add ("J");
	cFace.add ("Q");
	cFace.add ("K");
	add (cFace);

	addMouseListener (this); //listen for mouse actions
	addMouseMotionListener (this);

	hMove = -1;
	cMove = -1;

	startGame = false;
	moving = false;
	textFieldAction.setText ("3");
	numShuffle = 3; //two shuffles to start with at first

	initDeck ();
	initFoundation ();
	repaint (g);
	for (int i = 0 ; i < 4 ; i++)
	{
	    cShuff [i] = -1;
	}
    } // init method


    private void initFoundation ()
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    aHands [i] = new Hand (Color.red, 90, 100, 100 + 110 * i, 90);
	}

    }


    private void initNewFoundation ()
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 13 ; j++)
	    {
		if (!d.isEmpty ())
		{
		    deal (d, i, j, 6 - i);
		}
	    }
	}
    }


    private void initDeck ()
    {
	d = new DeckClass ("a", Color.red, 90, 100, 550);
	d.shuffle ();
	dM = new DeckClass ();
    }


    private void drawDeck (Graphics g)
    {
	if (!startGame)
	{
	    d.draw (g);
	}
    }


    private void drawFoundation (Graphics g)
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    aHands [i].draw (g);
	}
    }


    private void drawMovingDeck (Graphics g)
    {
	if (!dM.isEmpty ())
	{
	    dM.draw (g);
	}
    }


    private void deal (DeckClass curD, int CardI, int CardJ, int steps)
    {
	dM.addTop (curD.getAndRemove (0));
	dM.setCenter (curD.getCentreX (), curD.getCentreY ());
	slide (aHands [CardI].getActualXPos (CardJ), aHands [CardI].getCentreY (), steps, g);
	aHands [CardI].addBot (dM.getAndRemove (0));
    }


    private void search (int sSuit, int sFace)
    {
	String FaceValues = "A23456789TJQK";
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 13 ; j++)
	    {
		if (aHands [i].getCard (j).getSuit () == sSuit && aHands [i].getCard (j).getFace ().equals (FaceValues.substring (sFace, sFace + 1)))
		{
		    //then the right card is found
		    g.setColor (Color.blue);
		    g.drawRect (aHands [i].getCard (j).getCentreX () - (int) (aHands [i].getCard (j).getWidth () / 2), aHands [i].getCard (j).getCentreY () - (int) (aHands [i].getCard (j).getHeight () / 2), aHands [i].getCard (j).getWidth (), aHands [i].getCard (j).getHeight ());
		  
		}
	    }
	}
    }


    private void slide (int nX, int nY, int steps, Graphics g)
    {
	int delay = 1;

	if (steps == 0) //can't be zero steps to a destination
	{
	    steps = 1;
	}
	double xDist = nX - dM.getCentreX ();
	double yDist = nY - dM.getCentreY ();

	double stepX = xDist / steps;
	double stepY = yDist / steps;

	double cX = dM.getCentreX ();
	double cY = dM.getCentreY ();

	if (!dM.getTop ().getFace ().equals ("A"))
	{
	    for (int i = 1 ; i <= steps ; i++)
	    {
		moving = true;
		cX += stepX;
		cY += stepY;

		dM.setCenter ((int) cX, (int) cY);

		if (i == steps) //snap to final position in case of rounding errors
		{
		    dM.setCenter (nX, nY);
		}

		repaint (g);
		dM.delay (0);
	    }

	}

	moving = false;
    }


    public boolean checkWin ()
    {
	if (startGame)
	{
	    checkNumOrder ();
	    for (int i = 0 ; i < 4 ; i++)
	    {
		if (cShuff [i] != 12)
		{
		    return false;
		}
	    }
	    return true;
	}
	return false;
    }


    public void checkNumOrder ()
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 13 ; j++)
	    {
		if (!aHands [i].getTop ().getFace ().equals ("2"))
		{
		    cShuff [i] = 0;
		    break;
		}

		if (j != 0)
		{
		    if (aHands [i].getCard (j).getSuit () != aHands [i].getCard (j - 1).getSuit ())
		    {
			cShuff [i] = j;
			break;
		    }

		    if (!aHands [i].checkFace (j - 1, j))
		    {
			cShuff [i] = j;
			break;
		    }
		}
		if (j == 12)
		{
		    cShuff [i] = j;
		    break;
		}

	    }
	}
    }


    public void checkAndShuffle ()
    {
	if (startGame && !checkWin ())
	{
	    DeckClass dShuff = new DeckClass ();
	    DeckClass aceTemp = new DeckClass ();
	    //checkNumOrder(); redundant
	    for (int i = 0 ; i < 4 ; i++)
	    {
		int start = cShuff [i];
		for (int j = start ; j < 13 ; j++)
		{
		    if (aHands [i].getCard (start).getFace ().equals ("A"))
		    {
			aceTemp.addBot (aHands [i].getAndRemove (start));
		    }
		    else
		    {
			dShuff.addBot (aHands [i].getAndRemove (start));
		    }
		}
	    }
	    dShuff.shuffle ();
	    for (int k = 0 ; k < 4 ; k++)
	    {
		//System.out.println(aHands[k].getCardCount());
		boolean addAce = true;
		for (int l = 0 ; l < aHands [k].getCardCount () ; l++)
		{
		    if (aHands [k].getCard (l).getFace ().equals ("A"))
		    {
			addAce = false;
		    }
		}
		if (addAce)
		{
		    aHands [k].addBot (aceTemp.getAndRemove (0));
		}
		while (true)
		{
		    if (aHands [k].getCardCount () == 13)
		    {
			break;
		    }
		    aHands [k].addBot (dShuff.getAndRemove (0));
		}
	    }

	    //if (cShuff [i] != 0)
	    //{
	    //aHands [i].shuffleSpecific (cShuff [i] + 2, 12); //need to figure out how to shuffle between hands
	    //if cShuff [i] == 11 then it will not run because you cant shuffle the same card, so I need to figure out how to shuffle between hands
	    //make it so that ace is always in the spot j+1
	    //}

	}
    }


    public void actionPerformed (ActionEvent e)
    {

    }


    public boolean action (Event e, Object o)
    {
	if (e.target instanceof Button)
	{
	    if (e.target == bSearch && startGame)
	    {
		search (cSuit.getSelectedIndex (), cFace.getSelectedIndex () + 1);
	    }
	}


	if (cDiff.getSelectedIndex () == 1)
	{
	    if (numShuffle > 2)
	    {
		numShuffle = 2;
		textFieldAction.setText ("2");
	    }
	}
	else if (cDiff.getSelectedIndex () == 2)
	{
	    if (numShuffle > 1)
	    {
		numShuffle = 1;
		textFieldAction.setText ("1");
	    }
	}

	if (e.target instanceof Button)
	{
	    if (e.target == bShuffle)
	    {
		if (cDiff.getSelectedIndex () == 0) //lf easy
		{

		    if (numShuffle == 3)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("2");
			numShuffle--;
			repaint (g);
		    }
		    else if (numShuffle == 2)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("1");
			numShuffle--;
			repaint (g);
		    }
		    else if (numShuffle == 1)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("0");
			numShuffle--;
			repaint (g);
		    }
		    else
		    {
			numShuffle = 0;
		    }
		}
		else if (cDiff.getSelectedIndex () == 1) //lf normal
		{

		    if (numShuffle == 2)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("1");
			numShuffle--;
			repaint (g);
		    }
		    else if (numShuffle == 1)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("0");
			numShuffle--;
			repaint (g);
		    }
		    else
		    {
			numShuffle = 0;
		    }
		}
		else //if hard
		{
		    if (numShuffle == 1)
		    {
			checkAndShuffle ();
			textFieldAction.setText ("0");
			numShuffle--;
			repaint (g);
		    }
		    else
		    {
			numShuffle = 0;
		    }

		}
	    }
	    if (e.target == bQuit)
	    {

		System.exit (0);

	    }
	    if (e.target == bInfo)
	    {
		System.out.println ("Instructions");
		System.out.println ("The objective is to order all the cards in horizontal rows from Two to King, left to right, in the same suit.");
		System.out.println ("An empty spot may be filled with a card that is the same suit and one rank higher than the card to the left.");
		System.out.println ("The most left spot may only be filled with a Two.");
		System.out.println ("Nothing may be placed to the right of a King.");
		System.out.println ("Clicking the shuffle button will randomly rearrange all the cards on the tableau that are not already in order.");
		System.out.println ("There is a limit to how many times you can reshuffle.");
	    }
	    if (e.target == bNew)
	    {
		if (!startGame)
		{
		    initNewFoundation ();
		    startGame = true;
		    repaint (g);
		}
		else if (startGame)
		{
		    init ();
		    initNewFoundation ();
		    startGame = true;
		    repaint (g);
		}
	    }
	}

	return true;
    }


    public void mouseClicked (MouseEvent e)  //click and release mouse
    {
	if (startGame)
	{
	}
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)  // just pressing without letting go
    {

	if (startGame && cMove < 0 && hMove < 0)
	{
	    for (int i = 0 ; i < 4 ; i++)
	    {
		for (int j = 0 ; j < 13 ; j++)
		{
		    if (aHands [i].getCard (j).isInside (e.getX (), e.getY ()) && !aHands [i].getCard (j).getFace ().equals ("A"))
		    {
			if (!dM.isEmpty () && hMove < 0 && cMove < 0)
			{
			    dM.removeAll ();
			}
			cMove = j;
			hMove = i;
			dM.addTop (aHands [i].getCard (j));
			aHands [i].removeAt (j);
			CardClass tempAce = new CardClass ("A");
			aHands [i].addAt (tempAce, j);
		    }
		}
	    }
	}
    }



    public void mouseReleased (MouseEvent e)
    {
	if (startGame)
	{
	    if (hMove >= 0 && cMove >= 0)
	    {
		boolean exitFor = false;
		for (int i = 0 ; i < 4 ; i++)
		{
		    for (int j = 0 ; j < 13 ; j++)
		    {
			if (aHands [i].getCard (j).isInside (e.getX (), e.getY ()))
			{
			    if (aHands [i].getCard (j).getFace ().equals ("A"))
			    {
				boolean isValidMove = false;

				if (j != 0)
				{
				    dM.addBot (aHands [i].getAndRemove (j));
				    aHands [i].addAt (dM.getAndRemove (0), j);
				    if (aHands [i].checkFace (j - 1, j) && aHands [i].getCard (j).getSuit () == aHands [i].getCard (j - 1).getSuit ())
				    {
					isValidMove = true;
				    }
				    dM.addBot (aHands [i].getAndRemove (j));
				    aHands [i].addAt (dM.getAndRemove (0), j);
				}
				else if (dM.getTop ().getFace ().equals ("2")) //if j is 0 by default
				{
				    isValidMove = true;
				}
				if (isValidMove)
				{
				    aHands [hMove].removeAt (cMove);
				    DeckClass dTemp = new DeckClass ();
				    if (hMove == i)
				    {
					aHands [hMove].addAt (dM.getAndRemove (0), cMove);

					int cMoveTEMP;
					int jTEMP;
					if (cMove > j)
					{
					    cMoveTEMP = cMove;
					    jTEMP = j;
					}
					else
					{
					    cMoveTEMP = j;
					    jTEMP = cMove;
					}

					for (int k = 0 ; k < jTEMP ; k++)
					{
					    dTemp.addTop (aHands [i].getAndRemove (0)); //first half of the deck
					}
					DeckClass dTemp2 = new DeckClass ();
					dTemp2.addTop (aHands [i].getAndRemove (0)); //empty card
					DeckClass dTemp3 = new DeckClass ();
					for (int k = 0 ; k < cMoveTEMP - jTEMP - 1 ; k++)
					{
					    dTemp3.addTop (aHands [i].getAndRemove (0)); //second half of the deck
					}
					dTemp2.addAt (aHands [i].getAndRemove (0), 1); //card to be swapped

					aHands [i].addTop (dTemp2.getAndRemove (0));
					for (int k = 0 ; k < cMoveTEMP - jTEMP - 1 ; k++)
					{
					    aHands [i].addTop (dTemp3.getAndRemove (0)); //add second half of deck
					}
					aHands [i].addTop (dTemp2.getAndRemove (0));
					for (int k = 0 ; k < jTEMP ; k++)
					{
					    aHands [i].addTop (dTemp.getAndRemove (0)); //add first half of deck
					}

				    }
				    else
				    {
					dTemp.addTop (aHands [i].getAndRemove (j));
					aHands [hMove].addAt (dTemp.getTop (), cMove);
					aHands [i].addAt (dM.getCard (0), j);
				    }
				    dM.removeAll ();
				    repaint ();
				    exitFor = true;
				    break; //this break statement only exits one loop
				}
			    }
			    else //else if not a match, move back to original pos
			    {
				aHands [hMove].removeAt (cMove);
				aHands [hMove].addAt (dM.getAndRemove (0), cMove);
				repaint ();
				exitFor = true;
				break;
			    }
			}
			else if (i == 3 && j == 12)
			{ //else if not on any card, move back to original pos
			    aHands [hMove].removeAt (cMove);
			    aHands [hMove].addAt (dM.getAndRemove (0), cMove);
			    repaint ();
			    break;
			}
		    }
		    if (exitFor)
		    {
			break;
		    }
		}
	    }
	    cMove = -1;
	    hMove = -1;
	}
    }


    public void mouseDragged (MouseEvent e)
    {
	if (startGame && cMove >= 0 && hMove >= 0)
	{
	    dM.setCenter (e.getX (), e.getY ());
	    repaint (g);
	}
    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public void repaint (Graphics g)
    {
	paint (g);
    }


    public void paint (Graphics g)
    {
	if (startGame == false && !moving)
	{
	    drawDeck (g2);
	    g.drawImage (screenImage, 0, 0, this);
	    screenImage.flush ();
	}
	else if (moving)
	{
	    g2.setColor (new Color (220, 250, 220));
	    g2.fillRect (0, 0, 1300, 600);
	    drawFoundation (g2);
	    drawDeck (g2);
	    drawMovingDeck (g2);

	    g.drawImage (screenImage, 0, 0, this);
	    screenImage.flush ();
	}
	else
	{
	    g2.setColor (new Color (220, 250, 220));
	    g2.fillRect (0, 0, 1300, 600);
	    drawFoundation (g2);
	    drawMovingDeck (g2);

	    g.drawImage (screenImage, 0, 0, this);
	    screenImage.flush ();
	    if (checkWin ())
	    {
		g.setFont (new Font ("ComicSans", Font.PLAIN, 80));
		g.setColor (Color.red);
		g.drawString ("YOU WON!", 1300 / 2 - 200, 300);
	    }
	}


    } // paint method
} // Main class


