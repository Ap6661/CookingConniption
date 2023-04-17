package userInterFace;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TextRenderer
{
  private static final Font FONT = new Font("Hack NF", Font.BOLD, 15);

  public static void draw(Graphics2D aGraphics2D, String text, int width, int height, int size)
  {
    Color oldPaint = aGraphics2D.getColor();
    aGraphics2D.setColor(Color.black);

    Font tempFont = FONT.deriveFont((float) size);
    aGraphics2D.setFont(tempFont);

    // Calculate "Center"
    FontMetrics tempFontMetrics = aGraphics2D.getFontMetrics();
    int textW = tempFontMetrics.stringWidth(text);
    int textH = tempFontMetrics.getAscent();
    int textX = (width - textW) / 2;
    int textY = (height + textH) / 2;

    aGraphics2D.drawString(text, textX, textY);
    aGraphics2D.setColor(oldPaint);
  }

  public static void draw(Graphics2D aGraphics2D, String text, int width, int height)
  {
    draw(aGraphics2D, text, width, height, 15);
  }

  public static void draw(Graphics aGraphics, String text, int width, int height)
  {
    draw((Graphics2D) aGraphics, text, width, height);
  }

  public static void draw(Graphics aGraphics, String text, int width, int height, int size)
  {
    draw((Graphics2D) aGraphics, text, width, height, size);
  }

  public static Rectangle2D getBounds(Graphics2D aGraphics2D, String text, int size)
  {
    Font tempFont = FONT.deriveFont((float) size);
    aGraphics2D.setFont(tempFont);
    FontMetrics tempFontMetrics = aGraphics2D.getFontMetrics();
    return tempFontMetrics.getStringBounds(text, aGraphics2D);

  }

  public static Rectangle2D getBounds(Graphics2D aGraphics2D, String text)
  {
    return getBounds(aGraphics2D, text, 15);
  }

}
