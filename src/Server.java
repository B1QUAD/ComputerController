import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Inet4Address;

class Server extends JFrame implements KeyListener, MouseListener{
    public static Adrian adrian = new Adrian(MouseInfo.getPointerInfo().getLocation(), -1);

    public Server() {
        this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 1));
        this.setVisible(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws AWTException, IOException {
        Robot robot = new Robot();
        ServerSocket serverSocket = new ServerSocket(6969);
        //shows the ip address the client has to connect to (hopefully)
        //not 100% sure about this but it worked with my ipv4
        InetAddress ipv4 = Inet4Address.getLocalHost();
        System.out.println(ipv4.getHostAddress().trim());

        Socket socket = serverSocket.accept();

        Server server = new Server();
        

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        Point point1 = MouseInfo.getPointerInfo().getLocation();
        Point point2 = null;

        try {
            // Adrian adrian = new Adrian(MouseInfo.getPointerInfo().getLocation(), new int[]{}, new int[]{});
            while(true) {
                point2 = point1;
                point1 = server.getMousePosition();
                Server.adrian = new Adrian(point1, adrian.getKeyPressed());

                if(!point1.equals(point2))
                    output.writeObject(adrian);
                
                robot.delay(10);
            }
        }
        catch(Exception e) {
            System.out.println("Client closed the connection like a wussy.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//         String key = e.getKeyText(e.getKeyCode());
        
//         if(key.equals("Delete"))
//             System.exit(-1);
//         adrian = new Adrian(adrian.getMouse(), e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
//         adrian = new Adrian(adrian.getMouse(), -1);
        String key = e.getKeyText(e.getKeyCode());
        
        if(key.equals("Delete"))
            System.exit(-1);
        adrian = new Adrian(adrian.getMouse(), e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
