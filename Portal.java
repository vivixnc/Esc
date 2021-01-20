import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.Image; 
import javax.swing.ImageIcon; 

public class Portal
{
    private int x, y, diam;
    private ImageIcon p;
    private Image port;
    
    public Portal(int a, int b, int d)
    {
        x = a;
        y = b;
        diam = d;
        p = new ImageIcon(Escape.class.getResource("portal.gif"));
        port = p.getImage();
    }
    
    public void drawSelf(Graphics2D g)
    {
        g.drawImage(port, x, y, diam, diam, null);
    }
    
    public int getCenterX()
    {
        return x + diam / 2 + 18;
    }
    
    public int getCenterY()
    {
        return y + diam / 2 - 5;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getDiam()
    {
        return diam;
    }
    
    private void setX(int newX)
    {
        x = newX;
    }
    
    private void setY(int newY)
    {
        y = newY;
    }
    
    public void setDiam(int d)
    {
        diam = d;
    }
    
    public int distance(int x1, int y1, int x2, int y2)
    {
        return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
    
    public boolean pass(Player p)
    {
        if(distance(p.getCenterX(), p.getCenterY(), this.getCenterX(), this.getCenterY()) <= p.getDiam() / 2 + this.getDiam() / 2)
            return true;
        return false;
    }
    
    public void teleport()
    {
        // at least 50px way from player
    }
}
