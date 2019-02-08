// The "SuitClass" class.
import java.awt.*;
import hsa.Console;

public abstract class SuitClass extends ShapeClass
{
    
    public SuitClass (Color clr)
    {
	super (clr);
	setHeight (100);
    }


    public SuitClass (Color clr, int h, int w, int x, int y)
    {
	super (clr, h, w, x, y);
	setHeight (h);
    }

    public void setHeight (int h)
    {
	if (h > 0)
	{
	    super.setHeight (h);
	    super.setWidth (h * 4 / 5);
	}
    }


    public void setWidth (int w)
    {
	if (w > 0)
	{
	    super.setHeight (w * 5 / 4);
	    super.setWidth (w);
	}
    }
} // SuitClass class
