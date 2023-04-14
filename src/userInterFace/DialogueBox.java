package userInterFace;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DialogueBox extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Message message;

  public DialogueBox()
  {
    super();
    this.setPreferredSize(new Dimension(100, 100));
  }

  public Message speak(String message)
  {
    return speak(new Message(message));
  }

  public Message speak(Message aMessage)
  {
    this.message = aMessage;
    return aMessage;
  }

  public void clear()
  {
    this.message = null;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    Graphics2D aGraphics2D = (Graphics2D) aGraphics;
    if (message != null)
    {
      message.draw(aGraphics2D, getWidth(), getHeight());
    }
  }
}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//