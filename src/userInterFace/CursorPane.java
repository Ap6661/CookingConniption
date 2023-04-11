package userInterFace;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import engine.Item;

public class CursorPane extends JComponent
{
  private static final long serialVersionUID = 1L;

  private Point point = new Point();
  private Item item;

  public CursorPane(Container aContainer)
  {
    setVisible(true);
    setEnabled(false);

    long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK;
    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener()
    {
      public void eventDispatched(AWTEvent aAWTEvent)
      {
        MouseEvent tempMouseEvent = (MouseEvent) aAWTEvent;
        setPoint(SwingUtilities.convertPoint((Component) aAWTEvent.getSource(), tempMouseEvent.getPoint(), aContainer));
        repaint();
      }
    }, eventMask);
  }

  protected void paintComponent(Graphics aGraphics)
  {
    if (point != null)
      if (item != null)
        aGraphics.drawImage(item.getImage(), (int) point.getX(), (int) point.getY(), 52, 52, null);
  }

  public void setPoint(Point aPoint)
  {
    point = aPoint;
  }

  public void setItem(Item aItem)
  {
    item = aItem;
    repaint();
  }

}
