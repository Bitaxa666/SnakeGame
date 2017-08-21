import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 8/2/17.
 */
public class MainWindow extends JFrame
{
    public MainWindow()
    {
        setTitle("My Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
        /*if(GameField.gameColor ==1)
        {
            setBackground(Color.WHITE);
        }*/

    }

    public static void main(String[] args)
    {
        MainWindow newWindow = new MainWindow();

    }
}
