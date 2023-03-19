package userInterFace;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Message
{
  private static final Font MESSAGE_FONT = new Font("Hack NF", Font.BOLD, 15);
  private String message;
  private int xPosition;
  private int yPosition;
  private Background background;

  public Message(String message)
  {
    this.setMessage(message);
    this.background = new Background(30, 20);
    this.xPosition = 0;
    this.yPosition = 0;
  }

  public void draw(Graphics2D aGraphics2D, int aWidth, int aHeight)
  {

    int xMargin = 20;
    int yMargin = 20;

    // Center the text
    FontMetrics fm = aGraphics2D.getFontMetrics();
    int textW = fm.stringWidth(message) * 2;
    int textH = fm.getAscent();
    int textX = (aWidth - textW) / 2;
    int textY = (aHeight + textH) / 2;

    aGraphics2D.translate(textX, textY - textH);


    this.background.paint(aGraphics2D, -xMargin, -yMargin, textW + (xMargin * 2), textH + (yMargin * 2));

    aGraphics2D.setColor(Color.BLACK);
    aGraphics2D.setFont(MESSAGE_FONT);
    aGraphics2D.drawString(message, 0, textH);
  }

  public int getXPosition()
  {
    return xPosition;
  }

  public void setXPosition(int xPosition)
  {
    this.xPosition = xPosition;
  }

  public int getYPosition()
  {
    return yPosition;
  }

  public void setYPosition(int yPosition)
  {
    this.yPosition = yPosition;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

}
