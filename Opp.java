// Vivian Chen Lam

import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.Image; 
import javax.swing.ImageIcon;

public class Opp
{
    private String type;
    private int x, y, vx, vy, diam;
    private ImageIcon op;
    private Image opp;
    private Player p;
    
    public Opp(String t, int a, int b, int c, int d, Player pl)
    {
        type = t;
        x = a;
        y = b;
        diam = d;
        p = pl;
        if(type.equals("cat"))
        {
            op = new ImageIcon(Escape.class.getResource("cat.gif"));
            vx = 0;
            vy = c;
        }
        else if(type.equals("dog"))
        {
            if(c > 0)
                op = new ImageIcon(Escape.class.getResource("dog_right.gif"));
            else
                op = new ImageIcon(Escape.class.getResource("dog_left.gif"));
            vx = c;
            vy = 0;
        }
        else if(type.equals("ram"))
        {
            if(c > 0)
                op = new ImageIcon(Escape.class.getResource("ram_right.gif"));
            else
                op = new ImageIcon(Escape.class.getResource("ram_left.gif"));
            vx = c;
            vy = c;
        }
        else if(type.equals("pig"))
        {
            if(c > 0)
                op = new ImageIcon(Escape.class.getResource("pig_right.gif"));
            else
                op = new ImageIcon(Escape.class.getResource("pig_left.gif"));
            vx = c;
            vy = c;
        }
        else if(type.equals("spikes up"))
            op = new ImageIcon(Escape.class.getResource("spikes_up.png"));
        else if(type.equals("spikes down"))
            op = new ImageIcon(Escape.class.getResource("spikes_down.png"));
        else if(type.equals("spikes left"))
            op = new ImageIcon(Escape.class.getResource("spikes_left.png"));
        else if(type.equals("spikes right"))
            op = new ImageIcon(Escape.class.getResource("spikes_right.png"));
        else if(type.equals("spike ball"))
            op = new ImageIcon(Escape.class.getResource("spike_ball.png"));
        opp = op.getImage();
    }
    
    public void drawSelf(Graphics2D g)
    {
        if(this.getType().equals("spikes up") || this.getType().equals("spikes down"))
            g.drawImage(opp, x, y, diam, 20, null);
        else if(this.getType().equals("spikes left") || this.getType().equals("spikes right"))
            g.drawImage(opp, x, y, 20, diam, null);
        else
            g.drawImage(opp, x, y, diam, diam, null);
    }
    
    public int getCenterX()
    {
        if(type.equals("spikes left") || type.equals("spikes right"))
            return x + 10;
        return x + diam / 2;
    }
    
    public int getCenterY()
    {
        if(type.equals("spikes up") || type.equals("spikes down"))
            return y + 10;
        return y + diam / 2;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getVx()
    {
        return vx;
    }
    
    public int getVy()
    {
        return vy;
    }
    
    public int getDiam()
    {
        return diam;
    }
    
    private void setType(String t)
    {
        type = t;
    }
    
    public void setX(int newX)
    {
        x = newX;
    }
    
    public void setY(int newY)
    {
        y = newY;
    }
    
    private void setVx(int newV)
    {
        vx = newV;
    }
    
    private void setVy(int newV)
    {
        vy = newV;
    }
    
    private void setDiam(int d)
    {
        diam = d;
    }
    
    private void setImageIcon(ImageIcon i)
    {
        op = i;
    }
    
    private void setImage(Image i)
    {
        opp = i;
    }
    public void act()
    {
        if(type.equals("cat"))
        {
            if(y + vy > 500 - diam || y + vy < 0)
                vy *= -1;
            y += vy;
        }
        if(type.equals("dog"))
        {
            if(x + vx > 700 - diam || x + vx < 0)
            {
                vx *= -1;
                // make character flip
                if(vx > 0)
                {
                    ImageIcon i = new ImageIcon(Escape.class.getResource("dog_right.gif"));
                    Image ii = i.getImage();
                    this.setImageIcon(i);
                    this.setImage(ii);
                }
                else
                {
                    ImageIcon i = new ImageIcon(Escape.class.getResource("dog_left.gif"));
                    Image ii = i.getImage();
                    this.setImageIcon(i);
                    this.setImage(ii);
                }
            }
            
            x += vx;
        }
        if(type.equals("ram"))
        {
            x += vx;
            y += vy;
            if(x + vx <= 0 || x + vx + diam >= 700)
                vx *= -1;
            if(y + vy <= 0 || y + vy + diam >= 500)
                vy *= -1;
            if(vx > 0)
            {
                ImageIcon i = new ImageIcon(Escape.class.getResource("ram_right.gif"));
                Image ii = i.getImage();
                this.setImageIcon(i);
                this.setImage(ii);
            }
            else
            {
                ImageIcon i = new ImageIcon(Escape.class.getResource("ram_left.gif"));
                Image ii = i.getImage();
                this.setImageIcon(i);
                this.setImage(ii);
            }
        }
        if(type.equals("pig"))
        {
            if(p.getX() > this.getX())
                x += vx;
            else
                x -= vx;
            if(p.getY() > this.getY())
                y += vy;
            else
                y -= vy;
            if(vx > 0)
            {
                ImageIcon i = new ImageIcon(Escape.class.getResource("pig_right.gif"));
                Image ii = i.getImage();
                this.setImageIcon(i);
                this.setImage(ii);
            }
            else
            {
                ImageIcon i = new ImageIcon(Escape.class.getResource("pig_left.gif"));
                Image ii = i.getImage();
                this.setImageIcon(i);
                this.setImage(ii);
            }
        }
        
        
    }
    
    public int distance(int x1, int y1, int x2, int y2)
    {
        return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
    
    

}