import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;

class Server {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        ServerSocket serverSocket = new ServerSocket(6969);
        Socket socket = serverSocket.accept();

        //shows the ip address the client has to connect to (hopefully)
        //not 100% sure about this but it worked with my ipv4
        InetAddress ipv4 = Inet4Address.getLocalHost();
        System.out.println(ipv4.getHostAddress().trim());
        

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        try {
            while(true) {
                output.writeObject(MouseInfo.getPointerInfo().getLocation());
                robot.delay(25);
            }
        }
        catch(Exception e) {
            System.out.println("Client closed the connection like a wussy.");
        }
    }
}