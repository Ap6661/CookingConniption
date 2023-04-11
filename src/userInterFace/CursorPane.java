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
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import engine.Item;

public class CursorPane extends JComponent
{
  private static final long serialVersionUID = 1L;

  private Point point = new Point();
  private Item item;
  private static int width = 64;
  private static int height = 64;

  private JLabel holder = new JLabel()
  {
    private static final long serialVersionUID = 1L;

    @Override
    public void paintComponent(Graphics aGraphics)
    {
      if (item != null)
        aGraphics.drawImage(item.getImage(), 0, 0, width, height, null);
    }
  };

  public CursorPane(Container aContainer)
  {
    setEnabled(false);
    setLayout(null);
    add(holder);

    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener()
    {
      public void eventDispatched(AWTEvent aAWTEvent)
      {
        MouseEvent tempMouseEvent = (MouseEvent) aAWTEvent;
        point = (SwingUtilities.convertPoint((Component) aAWTEvent.getSource(), tempMouseEvent.getPoint(), aContainer));
        point.translate(20, 20);
        holder.setBounds(point.x, point.y, width, height);
        repaint();
      }
    }, AWTEvent.MOUSE_MOTION_EVENT_MASK);
  }

  public void setItem(Item aItem)
  {
    item = aItem;
    repaint();
  }
}