// Vivian Chen Lam

import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.Image; 
import javax.swing.ImageIcon; 

public class Player
{
    private int x, y, v, diam;
    private ImageIcon p;
    private Image player;
    
    public Player(int a, int b, int d)
    {
        p = new ImageIcon(Escape.class.getResource("bunny_right.gif"));
        player = p.getImage();
        x = a;
        y = b;
        v = 10;
        diam = d;
    }
    
    public void drawSelf(Graphics2D g)
    {
        g.drawImage(player, x, y, diam, diam, null);
    }
    
    public int getCenterX()
    {
        return x + diam / 2;
    }
    
    public int getCenterY()
    {
        return y + diam / 2;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getV()
    {
        return v;
    }
    
    public int getDiam()
    {
        return diam;
    }
    
    public void setX(int newX)
    {
        x = newX;
    }
    
    public void setY(int newY)
    {
        y = newY;
    }
    
    private void setV(int newV)
    {
        v = newV;
    }
    
    public void setDiam(int d)
    {
        diam = d;
    }
    
    public void setImageIcon(ImageIcon i)
    {
        p = i;
    }
    
    public void setImage(Image i)
    {
        player = i;
    }
    
    public int distance(int x1, int y1, int x2, int y2)
    {
        return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
    
    public boolean detectCollision(Opp o)
    {
        int oX = o.getX();
        int oY = o.getY();
        int oD = o.getDiam();
        int centerX = this.getCenterX();
        int centerY = this.getCenterY();
        
        if(o.getType().equals("spikes right") || o.getType().equals("spikes left"))
        {
            for(int i = oX; i < oX + 15; i++)
            {
                for(int j = oY; j < oY + oD; j++)
                {
                    if(distance(centerX, centerY, i, j) < diam / 2 - 7)
                        return true;
                }
            }
        }
        else if(o.getType().equals("spikes up") || o.getType().equals("spikes down"))
        {
            for(int i = oX; i < oX + oD; i++)
            {
                for(int j = oY; j < oY + 15; j++)
                {
                    if(distance(centerX, centerY, i, j) < diam / 2 - 7)
                        return true;
                }
            }
        }
        else
        {
            for(int i = oX; i < oX + oD; i++)
            {
                for(int j = oY; j < oY + oD; j++)
                {
                    if(distance(centerX, centerY, i, j) < diam / 2 - 10)
                        return true;
                }
            }
        }
        /*if(o.getType().equals("spikes right") || o.getType().equals("spikes left"))
        {
            if(distance(this.getCenterX(), this.getCenterY(), o.getCenterX(), o.getCenterY()) <= ((this.getDiam() + 20) / 2))
                return true;
        }
        else if(distance(this.getCenterX(), this.getCenterY(), o.getCenterX(), o.getCenterY()) <= ((this.getDiam() + o.getDiam()) / 2) - 18)
            return true;*/
        return false;
    }
    
    public boolean detectCollisions(Opp[] opps)
    {
        for(int i = 0; i < opps.length; i++)
        {
            System.out.println("detecting");
            if(this.detectCollision(opps[i]))
            {
                System.out.println("collision");
                return true;
            }
        }
        return false;
    }
    
    public Opp[] tutorial()
    {
        Opp[] tutorial = {new Opp("cat", 350, 250, 5, 60, this), new Opp("spikes down", 350, 0, 0, 60, this),
                          new Opp("spikes up", 350, 470, 0, 60, this)};
        return tutorial;
    }
    
    public Opp[] lvl1()
    {
        Opp[] l1 = {new Opp("cat", 100, (int)(Math.random() * 440), -4, 50, this),
                    new Opp("cat", 300, (int)(Math.random() * 440), 4, 50, this),
                    new Opp("cat", 500, (int)(Math.random() * 440), -4, 50, this)};
        return l1;
    }
    
    public Opp[] lvl2()
    {
        Opp[] l2 = {new Opp("cat", 100, (int)(Math.random() * 440), -4, 50, this), new Opp("cat", 200, (int)(Math.random() * 440), 4, 50, this),
                    new Opp("cat", 300, (int)(Math.random() * 440), -4, 50, this), new Opp("cat", 400, (int)(Math.random() * 440), 4, 50, this),
                    new Opp("cat", 500, (int)(Math.random() * 440), -4, 50, this), new Opp("cat", 600, (int)(Math.random() * 440), 4, 50, this),
                    new Opp("cat", 660, (int)(Math.random() * 440), 4, 50, this),
                    new Opp("spikes left", 680, 220, 0, 60, this)};
        return l2;
    }
    
    public Opp[] lvl3()
    {
        Opp[] l3 = {new Opp("dog", (int)(Math.random() * 640), 50, -5, 50, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 50, this),
                    new Opp("dog", (int)(Math.random() * 640), 250, -5, 50, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 50, this),
                    new Opp("spikes up", 250, 470, 0, 60, this), new Opp("spikes up", 350, 470, 0, 60, this), new Opp("spikes up", 450, 470, 0, 60, this)};
        return l3;
    }
    
    public Opp[] lvl4()
    {
        Opp[] l4 = {new Opp("cat", 100, (int)(Math.random() * 440), -5, 50, this), new Opp("cat", 300, (int)(Math.random() * 440), 5, 50, this),
                    new Opp("cat", 500, (int)(Math.random() * 440), -5, 50, this), new Opp("dog", (int)(Math.random() * 640), 50, 5, 50, this),
                    new Opp("dog", (int)(Math.random() * 640), 150, 5, 50, this), new Opp("dog", (int)(Math.random() * 640), 250, 5, 50, this),
                    new Opp("dog", (int) (Math.random() * 640), 350, 5, 50, this)};
        return l4;
    }
    
    public Opp[] lvl5()
    {
        Opp[] l5 = {new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 3, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), -3, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 3, 50, this)};
        return l5;
    }
    
    public Opp[] lvl6()
    {
        Opp[] l6 = {new Opp("pig", 600, 60, 2, 50, this)};
        return l6;
    }
    
    public Opp[] lvl7()
    {
        Opp[] l7 = {new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 3, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), -3, 50, this),
                    new Opp("pig", 600, 60, 1, 50, this)};
        return l7;
    }
    
    public Opp[] lvl8()
    {
        Opp[] l8 = {new Opp("cat", (int)(Math.random() * 530) + 100, (int)(Math.random() * 440), -5, 50, this),
                    new Opp("dog", (int)(Math.random() * 640), (int)(Math.random() * 440), 5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 4, 50, this),
                    new Opp("pig", 600, 60, 1, 50, this)};
        return l8;
    }
    
    public Opp[] lvl9()
    {
        Opp[] l9 = {new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), -5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), -5, 50, this),
                    new Opp("ram", (int)(Math.random() * 640), (int)(Math.random() * 440), 5, 50, this),
                    new Opp("pig", 600, 60, 1, 50, this), new Opp("pig", 200, 60, 1, 50, this)};
        return l9;
    }
    
    public Opp[] lvl10()
    {
        Opp[] l10 = {new Opp("ram", (int)(Math.random() * 340) + 100, (int)(Math.random() * 240) + 100, -6, 40, this),
                     new Opp("ram", (int)(Math.random() * 440) + 100, (int)(Math.random() * 340) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 240) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 340) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 440) + 100, (int)(Math.random() * 240) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 340) + 100, (int)(Math.random() * 240) + 100, 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 440) + 100, (int)(Math.random() * 340) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 240) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 340) + 100, (int)(Math.random() * 340) + 100, -6, 40, this),
                     new Opp("ram", (int)(Math.random() * 440) + 100, (int)(Math.random() * 240) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this)};
        return l10;
    }
    
    public Opp[] lvl11()
    {
        Opp[] l11 = {new Opp("dog", (int)(Math.random() * 640), 50, -5, 50, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -5, 50, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, 5, 50, this), new Opp("dog", (int)(Math.random() * 640), 150, -5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, 5, 50, this), new Opp("dog", (int) (Math.random() * 640), 350, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes up", 350, 470, 0, 60, this), new Opp("spikes down", 350, 0, 0, 60, this)};
        return l11;
    }
    
    public Opp[] lvl12()
    {
        Opp[] l12 = {new Opp("dog", (int)(Math.random() * 640), 50, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -5, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("pig", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 1, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this)};
        return l12;
    }
    
    public Opp[] lvl13()
    {
        Opp[] l13 = {new Opp("pig", (int)(Math.random() * 440) + 200, (int)(Math.random() * 240) + 200, 2, 40, this),
                     new Opp("pig", (int)(Math.random() * 440) + 200, (int)(Math.random() * 240) + 200, 2, 40, this),
                     new Opp("pig", (int)(Math.random() * 440) + 200, (int)(Math.random() * 240) + 200, 2, 40, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this)};
        return l13;
    }
    
    public Opp[] lvl14()
    {
        Opp[] l14 = {new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -5, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -5, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 40, this),
                     new Opp("pig", (int)(Math.random() * 440) + 200, (int)(Math.random() * 240) + 200, 1, 40, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this)};
        return l14;
    }
    
    public Opp[] lvl15()
    {
        Opp[] l15 = {new Opp("spike ball", 130, 70, 0, 50, this), new Opp("spike ball", 130, 220, 0, 50, this),
                     new Opp("spike ball", 130, 370, 0, 50, this), new Opp("spike ball", 310, 70, 0, 50, this),
                     new Opp("spike ball", 310, 220, 0, 50, this), new Opp("spike ball", 310, 370, 0, 50, this),
                     new Opp("spike ball", 490, 70, 0, 50, this), new Opp("spike ball", 490, 220, 0, 50, this),
                     new Opp("spike ball", 490, 370, 0, 50, this), 
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -6, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this)};
        return l15;
    }
    
    public Opp[] lvl16()
    {
        Opp[] l16 = {new Opp("spike ball", 110, 70, 0, 50, this), new Opp("spike ball", 110, 220, 0, 50, this),
                     new Opp("spike ball", 110, 370, 0, 50, this), new Opp("spike ball", 290, 70, 0, 50, this),
                     new Opp("spike ball", 290, 220, 0, 50, this), new Opp("spike ball", 290, 370, 0, 50, this),
                     new Opp("spike ball", 470, 70, 0, 50, this), new Opp("spike ball", 470, 220, 0, 50, this),
                     new Opp("spike ball", 470, 370, 0, 50, this), new Opp("spike ball", 620, 220, 0, 50, this),
                     new Opp("spike ball", 620, 370, 0, 50, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, 6, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -5, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, 6, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, 5, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, -6, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, 5, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, -6, 40, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this)};
        return l16;
    }
    
    public Opp[] lvl17()
    {
        Opp[] l17 = {new Opp("spike ball", 110, 70, 0, 50, this), new Opp("spike ball", 110, 220, 0, 50, this),
                     new Opp("spike ball", 110, 370, 0, 50, this), new Opp("spike ball", 290, 70, 0, 50, this),
                     new Opp("spike ball", 290, 220, 0, 50, this), new Opp("spike ball", 290, 370, 0, 50, this),
                     new Opp("spike ball", 470, 70, 0, 50, this), new Opp("spike ball", 470, 220, 0, 50, this),
                     new Opp("spike ball", 470, 370, 0, 50, this), new Opp("spike ball", 620, 220, 0, 50, this),
                     new Opp("spike ball", 620, 370, 0, 50, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -3, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 3, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 3, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 3, 40, this)};
        return l17;
    }
    
    public Opp[] lvl18()
    {
        Opp[] l18 = {new Opp("spike ball", 130, 70, 0, 50, this), new Opp("spike ball", 130, 220, 0, 50, this),
                     new Opp("spike ball", 130, 370, 0, 50, this), new Opp("spike ball", 490, 70, 0, 50, this), 
                     new Opp("spike ball", 490, 220, 0, 50, this), new Opp("spike ball", 490, 370, 0, 50, this),
                     new Opp("spikes right", 0, 130, 0, 60, this), new Opp("spikes right", 0, 270, 0, 60, this),
                     new Opp("spikes left", 680, 130, 0, 60, this), new Opp("spikes left", 680, 270, 0, 60, this),
                     new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 375, 470, 0, 60, this),
                     new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 375, 0, 0, 60, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -6, 40, this), new Opp("dog", (int)(Math.random() * 640), 150, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 250, -6, 40, this), new Opp("dog", (int) (Math.random() * 640), 350, 5, 40, this)};
        return l18;
    }
    
    public Opp[] lvl19()
    {
        Opp[] l19 = {new Opp("spike ball", 110, 70, 0, 50, this), new Opp("spike ball", 110, 220, 0, 50, this),
                     new Opp("spike ball", 110, 370, 0, 50, this), new Opp("spike ball", 290, 70, 0, 50, this),
                     new Opp("spike ball", 290, 220, 0, 50, this), new Opp("spike ball", 290, 370, 0, 50, this),
                     new Opp("spike ball", 470, 70, 0, 50, this), new Opp("spike ball", 470, 220, 0, 50, this),
                     new Opp("spike ball", 470, 370, 0, 50, this), new Opp("spike ball", 620, 220, 0, 50, this),
                     new Opp("spike ball", 620, 370, 0, 50, this),
                     new Opp("spikes right", 0, 10, 0, 60, this), new Opp("spikes right", 0, 110, 0, 60, this), new Opp("spikes right", 0, 210, 0, 60, this),
                     new Opp("spikes right", 0, 310, 0, 60, this),
                     new Opp("spikes left", 680, 10, 0, 60, this), new Opp("spikes left", 680, 110, 0, 60, this), new Opp("spikes left", 680, 210, 0, 60, this),
                     new Opp("spikes left", 680, 310, 0, 60, this), new Opp("spikes left", 680, 410, 0, 60, this),
                     new Opp("spikes up", 100, 470, 0, 60, this), new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 300, 470, 0, 60, this),
                     new Opp("spikes up", 400, 470, 0, 60, this), new Opp("spikes up", 500, 470, 0, 60, this), new Opp("spikes up", 600, 470, 0, 60, this),
                     new Opp("spikes down", 100, 0, 0, 60, this), new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 300, 0, 0, 60, this),
                     new Opp("spikes down", 400, 0, 0, 60, this), new Opp("spikes down", 500, 0, 0, 60, this), new Opp("spikes down", 600, 0, 0, 60, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -6, 40, this), new Opp("dog", (int)(Math.random() * 640), 125, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 200, -6, 40, this), new Opp("dog", (int) (Math.random() * 640), 275, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 350, -6, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 6, 40, this),
                     new Opp("cat", 40, (int)(Math.random() * 440), -5, 40, this), 
                     new Opp("pig", 650, 50, 1, 40, this), new Opp("pig", 300, 50, 1, 40, this)};
        return l19;
    }
    
    public Opp[] lvl20()
    {
        Opp[] l20 = {new Opp("spike ball", 110, 70, 0, 50, this), new Opp("spike ball", 110, 220, 0, 50, this),
                     new Opp("spike ball", 110, 370, 0, 50, this), new Opp("spike ball", 290, 70, 0, 50, this),
                     new Opp("spike ball", 290, 220, 0, 50, this), new Opp("spike ball", 290, 370, 0, 50, this),
                     new Opp("spike ball", 470, 70, 0, 50, this), new Opp("spike ball", 470, 220, 0, 50, this),
                     new Opp("spike ball", 470, 370, 0, 50, this), new Opp("spike ball", 620, 220, 0, 50, this),
                     new Opp("spike ball", 620, 370, 0, 50, this),
                     new Opp("spikes right", 0, 10, 0, 60, this), new Opp("spikes right", 0, 110, 0, 60, this), new Opp("spikes right", 0, 210, 0, 60, this),
                     new Opp("spikes right", 0, 310, 0, 60, this),
                     new Opp("spikes left", 680, 10, 0, 60, this), new Opp("spikes left", 680, 110, 0, 60, this), new Opp("spikes left", 680, 210, 0, 60, this),
                     new Opp("spikes left", 680, 310, 0, 60, this), new Opp("spikes left", 680, 410, 0, 60, this),
                     new Opp("spikes up", 100, 470, 0, 60, this), new Opp("spikes up", 200, 470, 0, 60, this), new Opp("spikes up", 300, 470, 0, 60, this),
                     new Opp("spikes up", 400, 470, 0, 60, this), new Opp("spikes up", 500, 470, 0, 60, this), new Opp("spikes up", 600, 470, 0, 60, this),
                     new Opp("spikes down", 100, 0, 0, 60, this), new Opp("spikes down", 200, 0, 0, 60, this), new Opp("spikes down", 300, 0, 0, 60, this),
                     new Opp("spikes down", 400, 0, 0, 60, this), new Opp("spikes down", 500, 0, 0, 60, this), new Opp("spikes down", 600, 0, 0, 60, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, -5, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 4, 40, this),
                     new Opp("ram", (int)(Math.random() * 540) + 100, (int)(Math.random() * 340) + 100, 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("cat", 100, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 200, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 300, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 400, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 500, (int)(Math.random() * 440), 5, 40, this), new Opp("cat", 600, (int)(Math.random() * 440), -5, 40, this),
                     new Opp("cat", 560, (int)(Math.random() * 440), -5, 40, this), new Opp("cat", 650, (int)(Math.random() * 440), 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 50, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 125, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 200, -5, 40, this), new Opp("dog", (int) (Math.random() * 640), 275, 5, 40, this),
                     new Opp("dog", (int)(Math.random() * 640), 350, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 50, 5, 40, this), 
                     new Opp("dog", (int)(Math.random() * 640), 125, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 200, 5, 40, this), 
                     new Opp("dog", (int) (Math.random() * 640), 275, -5, 40, this), new Opp("dog", (int)(Math.random() * 640), 350, 5, 40, this)};
        return l20;
    }
    
}