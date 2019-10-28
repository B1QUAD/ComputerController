import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Listeners extends JFrame implements Runnable, KeyListener, MouseListener {
    public static JFrame frame = new JFrame();

    public Listeners() {
        Server server = new Server();
        // this.setSize(100, 100);
        this.setLocationRelativeTo(null);
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        frame.setUndecorated(true);
        // frame.setBackground(new Color(0, 0, 0, 1)); //this is to make it invisible
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Server.adrian.keyPressed = -1;
    }

    @Override
    public void run() {
        Server server = new Server();
        Server.adrian = new Adrian(Server.adrian.mouseLocation, -1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Server server = new Server();
        // adrian = new Adrian(adrian.getMouse(), -1);
        String key = e.getKeyText(e.getKeyCode());

        if (key.equals("NumPad-7"))
            System.exit(-1);
        Server.adrian = new Adrian(server.adrian.mouseLocation, e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Server server = new Server();
        // left-click will be stored in Adrian with a keyPressed = 1000
        // right-click will be stored in Adrian with a keyPressed = 1001
        // middle-click will be stored in Adrian with a keyPressed = 1002

        if (e.getButton() == MouseEvent.BUTTON1) {
            Server.adrian = new Adrian(server.adrian.mouseLocation, 1000); // left click
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            Server.adrian = new Adrian(server.adrian.mouseLocation, 1001); // right click
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            Server.adrian = new Adrian(server.adrian.mouseLocation, 1002); // middle click
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
