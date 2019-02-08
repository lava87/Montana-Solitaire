// The "DiamondClass" class.
import java.awt.*;
import hsa.Console;

public class DiamondClass extends SuitClass
{
    public DiamondClass ()
    {
	super (Color.red);
    }


    public DiamondClass (int h, int x, int y)
    {
	super (Color.red, h, h, x, y);
    }


    public void draw (Console c)
    {
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX;
	iPointsY [1] = iCentreY - iHeight / 2;
	iPointsX [2] = iCentreX + iWidth / 2;
	iPointsY [2] = iCentreY;
	iPointsX [3] = iCentreX;
	iPointsY [3] = iCentreY + iHeight / 2;

	// draw the diamond using methods available from the Console object (c)
	c.setColor (iColour);

	c.fillPolygon (iPointsX, iPointsY, 4);

    }

    public void draw (Graphics g)
    {
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX;
	iPointsY [1] = iCentreY - iHeight / 2;
	iPointsX [2] = iCentreX + iWidth / 2;
	iPointsY [2] = iCentreY;
	iPointsX [3] = iCentreX;
	iPointsY [3] = iCentreY + iHeight / 2;

	// draw the diamond using methods available from the Console object (c)
	g.setColor (iColour);
	g.fillPolygon (iPointsX, iPointsY, 4);

    }
} // DiamondClass class
