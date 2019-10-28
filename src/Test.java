import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Test extends JFrame implements KeyListener {

    public Test() {
       this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
       this.setUndecorated(true);
       this.setBackground(new Color(0, 0, 0, 1));
       this.setVisible(true);
       this.addKeyListener(this);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Test test = new Test();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = e.getKeyText(e.getKeyCode());
        
        if(key.equals("Delete"))
            System.exit(-1);
        System.out.println("Pressed " + key);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("Released " + e);

    }
}