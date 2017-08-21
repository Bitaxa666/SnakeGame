import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by user on 8/2/17.
 */
public class GameField extends JPanel implements ActionListener
{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image fence;
    private int appleX;
    private int appleY;
    private int fenceX;
    private int fenceY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    //private int gameScore;
    private boolean gameColor;
    private Timer timer;
    private boolean leftSn = false;
    private boolean rightSn = true;
    private boolean upSn = false;
    private boolean downSn = false;
    private boolean inGame = true;


    public GameField()
    {
        setBackground(Color.black);
        loadImage();
        gameStart();
        addKeyListener(new KeyField());
        setFocusable(true);
        //gameColor = false;

    }

    public void gameStart()
    {
        dots=3;
        for(int i = 0; i<dots; i++)
        {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
        createFence();// создание блока препятствий

    }

    public void createApple()
    {
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }
    public void createFence()
    {
        fenceX = new Random().nextInt(20) * DOT_SIZE;
        fenceY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImage()
    {
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon ifa = new ImageIcon("fence1.png");
        fence = ifa.getImage();
    }

    public void moveMy()
    {
        for(int i = dots; i>0;i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(leftSn)
        {
            x[0] -= DOT_SIZE;
        }
        if(rightSn)
        {
            x[0] += DOT_SIZE;
        }
        if(upSn)
        {
            y[0] -= DOT_SIZE;
        }
        if(downSn)
        {
            y[0] += DOT_SIZE;
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(inGame)
        {
            g.drawImage(apple, appleX, appleY, this);
            g.drawImage(fence, fenceX, fenceY, this);

            for(int i=0;i<dots;i++)
            {
                g.drawImage(dot,x[i], y[i], this);
            }
        } else
        {
            setBackground(Color.WHITE);
            String gameOver = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 26);
            g.setFont(f);
            g.setColor(Color.RED);
            g.drawString(gameOver,75,SIZE/2);
        }
    }

    public void checkApple()
    {
        if(x[0] == appleX && y[0] == appleY)
        {
            dots++;
            createApple();
        }
    }
    public void checkFence()
    {
        if(x[0] == fenceX && y[0] == fenceY)
        {
            inGame=false;
            gameColor = true;
        }
    }

    public void checkWall()
    {
        for(int i=dots; i>0; i--)
        {
            if(i>4 && x[0]==x[i] && y[0] == y[i])
            {
                inGame=false;
               // gameColor = true;
            }
        }
        if(x[0]>SIZE){
            inGame = false;
            //gameColor = true;
        }
        if(x[0]<0){
            inGame = false;
            //gameColor = true;
        }
        if(y[0]>SIZE){
            inGame = false;
            //gameColor = true;
        }
        if(y[0]<0){
            inGame = false;
            //gameColor = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
            checkApple();
            checkWall();
            moveMy();
            checkFence();
        }
        repaint();

    }
    class KeyField extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !rightSn)
            {
                leftSn = true;
                upSn = false;
                downSn = false;
            }
            if(key == KeyEvent.VK_RIGHT && !leftSn)
            {
                rightSn = true;
                upSn = false;
                downSn = false;
            }
            if(key == KeyEvent.VK_UP && !downSn)
            {
                rightSn = false;
                upSn = true;
                leftSn = false;
            }
            if(key == KeyEvent.VK_DOWN && !upSn)
            {
                rightSn = false;
                leftSn = false;
                downSn = true;
            }
        }
    }
}
