import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class Server {
    public static volatile Adrian adrian = new Adrian(MouseInfo.getPointerInfo().getLocation(), -1);
    public static volatile ImageIcon screenCapture = new ImageIcon();

    public static void main(String[] args) throws AWTException, IOException {
        Robot robot = new Robot();

        ServerSocket serverSocket = new ServerSocket(6969);

        // shows the ip address the client has to connect to (hopefully)
        // not 100% sure about this but it worked with my ipv4
        InetAddress ipv4 = Inet4Address.getLocalHost();
        System.out.println(ipv4.getHostAddress().trim());

        Socket socket = serverSocket.accept();

        // run thread with the GUI
        Listeners listeners = new Listeners();
        listeners.run();

        // set up object streams to send and receive information from the client
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        Point point1 = MouseInfo.getPointerInfo().getLocation();
        Point point2 = null;

        ImageIcon screen = null;
        JLabel image = new JLabel();
        Listeners.frame.getContentPane().add(image);
        Listeners.frame.setVisible(true);

        try {
            while (true) {
                Server server = new Server();
                point2 = point1;
                point1 = MouseInfo.getPointerInfo().getLocation();
                adrian = new Adrian(point1, adrian.keyPressed);

                output.writeObject(adrian);
                output.flush();
                output.reset();

                screen = (ImageIcon) input.readObject();

                Listeners.frame.getContentPane().remove(image);
                image = new JLabel(screen);
                Listeners.frame.getContentPane().add(image);
                Listeners.frame.pack();
                Listeners.frame.setVisible(true);

                Server.adrian = new Adrian(adrian.mouseLocation, -1);

                robot.delay(1);
            }
        } catch (Exception e) {
            System.out.println("Client closed the connection like a wussy.");
            System.exit(-1);
        }
    }
}
