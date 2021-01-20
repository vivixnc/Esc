// Vivian Chen Lam

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.JComponent; 
import javax.swing.JFrame; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import javax.swing.ImageIcon; 
import java.applet.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.BorderFactory;

public class Escape extends JComponent implements KeyListener, MouseListener, MouseMotionListener 
{ 
    //instance variables 
    private int WIDTH, HEIGHT;
    private int score, highscore;
    private double level;
    private String pract;
    private AudioClip music;
    private Font titleFont, pressFont, buttonFont;
    private ImageIcon start, bunny1;
    private Image startScreen, bunny;
    private Player p;
    private Portal p1;
    private boolean pass, fail, isPract;
    private Opp[] tutorial, empty, 
                  lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7, lvl8, lvl9, lvl10,
                  lvl11, lvl12, lvl13, lvl14, lvl15, lvl16, lvl17, lvl18, lvl19, lvl20;
    String[] levelOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                             "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String[] options = {"go to practice menu", "go back to homescreen"};
    
    //Default Constructor 
    public Escape() 
    { 
        //initializing instance variables 
        WIDTH = 700; 
        HEIGHT = 500;
        level = 21;
        score = 0;
        highscore = 0;
        music = Applet.newAudioClip(this.getClass().getResource("track.wav"));
        titleFont = new Font("Monospaced", Font.PLAIN, 75);
        pressFont = new Font("Monospaced", Font.PLAIN, 20);
        buttonFont = new Font("Monospaced", Font.PLAIN, 18);
        start = new ImageIcon(Escape.class.getResource("start screen.gif"));
        startScreen = start.getImage();
        bunny1 = new ImageIcon(Escape.class.getResource("bunny_back.gif"));
        bunny = bunny1.getImage();
        p = new Player(0, 430, 50);
        p1 = new Portal(590, 20, 85);
        pass = false;
        fail = false;
        isPract = false;
        empty = new Opp[0];
        tutorial = p.tutorial();
        lvl1 = p.lvl1();
        lvl2 = p.lvl2();
        lvl3 = p.lvl3();
        lvl4 = p.lvl4();
        lvl5 = p.lvl5();
        lvl6 = p.lvl6();
        lvl7 = p.lvl7();
        lvl8 = p.lvl8();
        lvl9 = p.lvl9();
        lvl10 = p.lvl10();
        lvl11 = p.lvl11();
        lvl12 = p.lvl12();
        lvl13 = p.lvl13();
        lvl14 = p.lvl14();
        lvl15 = p.lvl15();
        lvl16 = p.lvl16();
        lvl17 = p.lvl17();
        lvl18 = p.lvl18();
        lvl19 = p.lvl19();
        lvl20 = p.lvl20();
        
        //Setting up the GUI
        music.loop();
        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Escape");
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));
        gui.setResizable(false); 
        gui.getContentPane().add(this);
        // add buttons and other apis here
        gui.pack();
        gui.setLocationRelativeTo(null); 
        gui.setVisible(true); 
        gui.addKeyListener(this);
        gui.addMouseListener(this); 
        gui.addMouseMotionListener(this);
        // JOptionPane edits
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.font", buttonFont);
        UIManager.put("Button.foreground", Color.CYAN);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.CYAN);
        UIManager.put("OptionPane.messageFont", pressFont);
        UIManager.put("Button.border", BorderFactory.createEtchedBorder(Color.CYAN, Color.BLUE));
        
    } 
    
    public int distance(int x1, int y1, int x2, int y2)
    {
        return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
    
    public void keyPressed(KeyEvent e)  
    { 
        //getting the key pressed 
        int key = e.getKeyCode();
        System.out.println(key);
        if(key == 38 && p.getY() > -10)
            p.setY(p.getY() - 10);
        if(key == 40 && p.getY() + p.getDiam() < 490)
            p.setY(p.getY() + 10);
        if(key == 37 && p.getX() > -10)
        {
            p.setX(p.getX() - 10);
            ImageIcon i = new ImageIcon(Escape.class.getResource("bunny_left.gif"));
            Image ii = i.getImage();
            p.setImageIcon(i);
            p.setImage(ii);
        }
        if(key == 39 && p.getX() + p.getDiam() < 710)
        {
            p.setX(p.getX() + 10);
            ImageIcon i = new ImageIcon(Escape.class.getResource("bunny_right.gif"));
            Image ii = i.getImage();
            p.setImageIcon(i);
            p.setImage(ii);
        }
        if(level == 0 && key == 84)
            level = 0.1;
        if(level == 0 && key == 83)
            level = 1;
        if(level == 0 && key == 75)
            level = 0.2;
        if(level == 0 && key == 80)
        {
            isPract = true;
            int levelChoice = JOptionPane.showOptionDialog(null, "Pick a level", "PRACTICE",
            JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION,
            null, levelOptions, levelOptions[0]);
            level = levelChoice + 1;
        }
        if((level == -1 || level == 0.2 || level == 21) && key == 72)
        {
            level = 0;
            fail = false;
            resetLevels();
            p.setX(0);
            p.setY(430);
        }
    }    
    
    public void resetLevels()
    {
        lvl1 = p.lvl1();
            lvl2 = p.lvl2();
            lvl3 = p.lvl3();
            lvl4 = p.lvl4();
            lvl5 = p.lvl5();
            lvl6 = p.lvl6();
            lvl7 = p.lvl7();
            lvl8 = p.lvl8();
            lvl9 = p.lvl9();
            lvl10 = p.lvl10();
            lvl11 = p.lvl11();
            lvl12 = p.lvl12();
            lvl13 = p.lvl13();
            lvl14 = p.lvl14();
            lvl15 = p.lvl15();
            lvl16 = p.lvl16();
            lvl17 = p.lvl17();
            lvl18 = p.lvl18();
            lvl19 = p.lvl19();
            lvl20 = p.lvl20();
    }
    
    public void drawNewLevel(Opp[] o, Graphics2D g)
    {
        Font f = new Font("Monospaced", Font.PLAIN, 50);
        if(level != 0.1)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setFont(f);
            if(level > 0 && level < 10)
                g.setColor(Color.CYAN);
            else if(level > 9 && level < 20)
                g.setColor(Color.MAGENTA);
            else
                g.setColor(Color.RED);
            g.drawString("LEVEL " + (int)level, WIDTH / 2 - 120, HEIGHT / 2);
            /*try
            {
                //Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);*/
        }
        p.drawSelf(g);
        p1.drawSelf(g);
        for(int i = 0; i < o.length; i++)
        {
            o[i].drawSelf(g);
            //System.out.println("drew");
        }
    }
    
    public void actNewLevel(Opp[] o)
    {
        for(int i = 0; i < o.length; i++)
            o[i].act();
        if(pass && isPract)
        {
            resetLevels();
            p.setX(0);
            p.setY(430);
            int choice = JOptionPane.showOptionDialog(null, "you passed!! now what?", "PRACTICE",
            JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION,
            null, options, options[0]);
            if(choice == 0)
            {
                int levelChoice = JOptionPane.showOptionDialog(null, "Pick a level", "PRACTICE",
                JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION,
                null, levelOptions, levelOptions[0]);
                level = levelChoice + 1;
            }
            else
            {
                isPract = false;
                level = 0;
                p.setX(0);
                p.setY(430);
            }
        }
        else if(p.detectCollisions(o) && isPract)
        {
            resetLevels();
            p.setX(0);
            p.setY(430);
            int choice = JOptionPane.showOptionDialog(null, "you failed :( now what?", "PRACTICE",
            JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION,
            null, options, options[0]);
            if(choice == 0)
            {
                int levelChoice = JOptionPane.showOptionDialog(null, "Pick a level", "PRACTICE",
                JOptionPane.DEFAULT_OPTION, JOptionPane.CLOSED_OPTION,
                null, levelOptions, levelOptions[0]);
                level = levelChoice + 1;
            }
            else
            {
                isPract = false;
                level = 0;
                p.setX(0);
                p.setY(430);
            }
        }
        else if(pass)
        {
            nextLevel();
        }
        else if(level > 0.1 && p.detectCollisions(o))
        {
            score = (int)level;
            level = -1;
            fail = true;
        }
    }
    
    public void nextLevel()
    {
        if(level != 0.1 && !isPract)
            level++;
        else if(level == 0.1 && pass)
            level = 0;
        if(level < 10)
        {
            p.setX(0);
            p.setY(430);
        }
        else
        {
            p.setX(0);
            p.setY(450);
        }
        pass = false;
    }
    
    public void terminate(Opp[] level)
    {
        /*if(!isPract)
        {
            for(int i = 0;i < level.length;i++)
                level[i] = null;
        }*/
        level = null;
    }
    
    public void key(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 500);
        ImageIcon c = new ImageIcon(Escape.class.getResource("cat_right.gif"));
        ImageIcon d = new ImageIcon(Escape.class.getResource("dog_right.gif"));
        ImageIcon r = new ImageIcon(Escape.class.getResource("ram_right.gif"));
        ImageIcon p = new ImageIcon(Escape.class.getResource("pig_right.gif"));
        ImageIcon s = new ImageIcon(Escape.class.getResource("spikes_up.png"));
        ImageIcon s2 = new ImageIcon(Escape.class.getResource("spike_ball.png"));
        Image cat = c.getImage();
        Image dog = d.getImage();
        Image ram = r.getImage();
        Image pig = p.getImage();
        Image spikes = s.getImage();
        Image spikeball = s2.getImage();
        g.drawImage(cat, 70, 40, 60, 60, null);
        g.drawImage(dog, 75, 130, 50, 50, null);
        g.drawImage(ram, 75, 220, 50, 50, null);
        g.drawImage(pig, 75, 310, 50, 50, null);
        g.drawImage(spikes, 75, 400, 60, 20, null);
        g.drawImage(spikeball, 140, 380, 50, 50, null);
        g.setFont(pressFont);
        g.setColor(Color.blue);
        g.drawString(": CAT - only moves in vertical directions", 130, 75);
        g.setColor(Color.GRAY);
        g.drawString(": DOG - moves only in horizontal directions", 130, 160);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(": RAM - moves freely", 130, 250);
        g.setColor(Color.orange);
        g.drawString(": PIG - follows you (creepy, watch out)", 130, 340);
        g.setColor(Color.PINK);
        g.drawString(": SPIKES - static, avoid", 195, 415);
        g.setColor(Color.MAGENTA);
        g.drawString("**press h to go back to the homescreen", 10, 480);
    }
    
    public void winScreen(Graphics2D g)
    {
        highscore = 20;
        ImageIcon w = new ImageIcon(Escape.class.getResource("winscreen.gif"));
        Image win = w.getImage();
        g.drawImage(win, 0, 0, 700, 500, null);
        ImageIcon b = new ImageIcon(Escape.class.getResource("bunny_right.gif"));
        Image bunny = b.getImage();
        g.drawImage(bunny, 0, 300, 120, 150, null);
        Font f = new Font("Monospaced", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("YOU PASSED ALL 20 LEVELS!!!! :D", 20, 40);
        g.drawString("but...this bunny might be stuck in a portal...(forever?)", 20, 70);
        g.drawString("well, the scenery's pretty and free of enemies, so,", 20, 100);
        g.drawString("still a W if you ask me ¯\\_(ツ)_/¯", 20, 130);
        g.drawString("p.s. please don't tell the bunny :(", 20, 160);
        g.drawString("press h to turn back time and go back to the homescreen", 20, 470);
    }
    
    public void failScreen(Graphics2D g)
    {
        if(score - 1 > highscore)
            highscore = score - 1;
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 500);
        ImageIcon r = new ImageIcon(Escape.class.getResource("rain.gif"));
        Image rain = r.getImage();
        g.drawImage(rain, 0, 0, 700, 500, null);
        Font f = new Font("Monospaced", Font.PLAIN, 25);
        g.setFont(f);
        g.setColor(Color.RED);
        g.drawString("you died :(", 270, 85);
        g.setColor(Color.WHITE);
        g.drawString("score: " + (score - 1), 280, 115);
        g.drawString("highscore: " + highscore, 260, 145);
        ImageIcon b = new ImageIcon(Escape.class.getResource("ghost_bunny.png"));
        Image bunny = b.getImage();
        g.drawImage(bunny, 170, 90, 350, 400, null);
        g.drawString("press h to go back to homescreen", 110, 400);
    }
    
    public void paintComponent(Graphics g) 
    { 
        Graphics2D g2d = (Graphics2D)(g);
        // load screen here
        if(level == 0 || level > 50 || level < -1)
        {
            g2d.drawImage(startScreen, -155, -160, 1000, 1200, null);
            g2d.drawImage(bunny, 280, 210, 150, 200, null);
            g.setFont(titleFont);
            g.setColor(Color.MAGENTA);
            g.drawString("ESCAPE", WIDTH / 2 - 130, 70);
            g.setColor(Color.CYAN);
            g.setFont(pressFont);
            g.drawString("press t for a tutorial", WIDTH / 2 - 125, 95);
            g.drawString("press s to start", WIDTH / 2 - 90, 120);
            g.drawString("press p to practice", WIDTH / 2 - 110, 145);
            g.drawString("press k for the character key", WIDTH / 2 - 170, 170);
        }
        else if(level == 0.1)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.MAGENTA);
            g.setFont(pressFont);
            g.drawString("use arrow keys to move around", 5, 20);
            g.drawString("avoid the moving enemy and spikes", 5, 40);
            g.drawString("goal: reach the portal", 5, 60);
            g.drawString("enemies gain different abilities as you progress", 5, 80);
            g.drawString("pass all 20 levels, good luck!!:)", 5, 100);
            g.drawLine(30, 350, 30, 420);
            g.drawLine(30, 420, 20, 410);
            g.drawLine(30, 420, 40, 410);
            g.drawString("this is you", 20, 330);
            g.drawLine(635, 105, 635, 165);
            g.drawLine(635, 105, 625, 115);
            g.drawLine(635, 105, 645, 115);
            g.drawString("portal", 605, 185);
            p.drawSelf(g2d);
            p1.drawSelf(g2d);
            drawNewLevel(tutorial, g2d);
        }
        else if(level == 0.1)
        {
            if(p.detectCollisions(tutorial))
                drawNewLevel(tutorial, g2d);
        }
        else if(level == 0.2)
            key(g2d);
        else if(level == 1)
            drawNewLevel(lvl1, g2d);
        else if(level == 2)
            drawNewLevel(lvl2, g2d);
        else if(level == 3)
            drawNewLevel(lvl3, g2d);
        else if(level == 4)
            drawNewLevel(lvl4, g2d);
        else if(level == 5)
            drawNewLevel(lvl5, g2d);
        else if(level == 6)
            drawNewLevel(lvl6, g2d);
        else if(level == 7)
            drawNewLevel(lvl7, g2d);
        else if(level == 8)
            drawNewLevel(lvl8, g2d);
        else if(level == 9)
            drawNewLevel(lvl9, g2d);
        else if(level == 10)
            drawNewLevel(lvl10, g2d);
        else if(level == 11)
            drawNewLevel(lvl11, g2d);
        else if(level == 12)
            drawNewLevel(lvl12, g2d);
        else if(level == 13)
            drawNewLevel(lvl13, g2d);
        else if(level == 14)
            drawNewLevel(lvl14, g2d);
        else if(level == 15)
            drawNewLevel(lvl15, g2d);
        else if(level == 16)
            drawNewLevel(lvl16, g2d);
        else if(level == 17)
            drawNewLevel(lvl17, g2d);
        else if(level == 18)
            drawNewLevel(lvl18, g2d);
        else if(level == 19)
            drawNewLevel(lvl19, g2d);
        else if(level == 20)
            drawNewLevel(lvl20, g2d);
        else if(level == 21)
            winScreen(g2d);
        else if(fail && !isPract)
            failScreen(g2d);
    } 

    public void loop() 
    {
        pass = p1.pass(p);
        if(level < 10)
            p.setDiam(50);
        else if(level > 9)
            p.setDiam(40);
        if(level == 0.1)
        {
            actNewLevel(tutorial);
            if(p.detectCollisions(tutorial))
            {
                p.setX(0);
                p.setY(430);
                nextLevel();
            }
            if(level == 0.1 && pass)
            {
                level = 0;
                p.setX(0);
                p.setY(430);
            }
        }
        else if(level == 1)
        {
            terminate(tutorial);
            actNewLevel(lvl1);
        }
        else if(level == 2)
        {
            terminate(lvl1);
            actNewLevel(lvl2);
        }
        else if(level == 3)
        {
            terminate(lvl2);
            actNewLevel(lvl3);
        }
        else if(level == 4)
        {
            terminate(lvl3);
            actNewLevel(lvl4);
        }
        else if(level == 5)
        {
            terminate(lvl4);
            actNewLevel(lvl5);
        }
        else if(level == 6)
        {
            terminate(lvl5);
            actNewLevel(lvl6);
        }
        else if(level == 7)
        {
            terminate(lvl6);
            actNewLevel(lvl7);
        }
        else if(level == 8)
        {
            terminate(lvl7);
            actNewLevel(lvl8);
        }
        else if(level == 9)
        {
            terminate(lvl8);
            actNewLevel(lvl9);
        }
        else if(level == 10)
        {
            terminate(lvl9);
            actNewLevel(lvl10);
        }
        else if(level == 11)
        {
            terminate(lvl10);
            actNewLevel(lvl11);
        }
        else if(level == 12)
        {
            terminate(lvl11);
            actNewLevel(lvl12);
        }
        else if(level == 13)
        {
            terminate(lvl12);
            actNewLevel(lvl13);
        }
        else if(level == 14)
        {
            terminate(lvl13);
            actNewLevel(lvl14);
        }
        else if(level == 15)
        {
            terminate(lvl14);
            actNewLevel(lvl15);
        }
        else if(level == 16)
        {
            terminate(lvl15);
            actNewLevel(lvl16);
        }
        else if(level == 17)
        {
            terminate(lvl16);
            actNewLevel(lvl17);
        }
        else if(level == 18)
        {
            terminate(lvl17);
            actNewLevel(lvl18);
        }
        else if(level == 19)
        {
            terminate(lvl18);
            actNewLevel(lvl19);
        }
        else if(level == 20)
        {
            terminate(lvl19);
            actNewLevel(lvl20);
        }
        
        repaint(); 

    } 
    public void keyTyped(KeyEvent e)  
    { 
    } 
    public void keyReleased(KeyEvent e)  
    { 
    } 
    public void mousePressed(MouseEvent e) 
    { 
    } 
    public void mouseReleased(MouseEvent e) 
    { 
    } 
    public void mouseClicked(MouseEvent e) 
    { 
    } 
    public void mouseEntered(MouseEvent e) 
    {
    } 
    public void mouseExited(MouseEvent e) 
    { 
    } 
    public void mouseMoved(MouseEvent e) 
    { 
    } 
    public void mouseDragged(MouseEvent e) 
    { 
    } 
    public void start(final int ticks){ 
        Thread gameThread = new Thread(){ 
            public void run(){
                while(true){ 
                    loop(); 
                    try{ 
                        Thread.sleep(1000 / ticks); 
                    }catch(Exception e){ 
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }; 
        gameThread.start(); 
    } 
 
    public static void main(String[] args) 
    { 
        Escape g = new Escape(); 
        g.start(60);
    } 
}