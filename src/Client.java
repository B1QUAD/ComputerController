import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;

/**
 * Property of CASpirations No touchy or else we sue. If this is on your
 * computer you owe us $1000000 million
 */

class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, AWTException {

        // create a robot which is going to move the mouse
        Robot robot = new Robot();

        // print out the address you need to connect to
        InetAddress ip = (Inet4Address) InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());

        // create socket at ipv4 with port 6969 ;)
        // Socket socket = new Socket("10.10.4.183", 6969); // contains the local ip at
        // school
        Socket socket = new Socket("localhost", 6969); // contains your ip

        socket.setTcpNoDelay(true);

        // set up object streams to send and receive information from the server
        OutputStream output = socket.getOutputStream();
        ObjectInputStream input = new ObjectInputStream((socket.getInputStream()));

        Rectangle screenRect = new Rectangle((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        // ImageIcon screen = new ImageIcon(robot.createScreenCapture(screenRect));

        try {
            while(true) {
                // receive input from server and move mouse to point that the server sent
                Adrian serverInfo = (Adrian) input.readObject();
                BufferedImage screen = robot.createScreenCapture(screenRect);
                // System.out.println("about to send " + screen);
                ImageIO.write(screen, "PNG", output);
                // System.out.println("Sent " + screen);
                // output.flush();
                // System.out.println("Flushed output");
            
                // System.out.println(serverInfo); // prints out the current mouse location and
                // keys pressed

                // move the mouse to the location of the server
                robot.mouseMove(serverInfo.mouseLocation.x, serverInfo.mouseLocation.y);

                // press the keys that the "server" is pressing
                if (serverInfo.keyPressed > 0 && serverInfo.keyPressed < 1000) {
                    robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(serverInfo.keyPressed));
                    robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(serverInfo.keyPressed));
                }

                // click what the "server" is clicking
                if (serverInfo.keyPressed >= 1000) {
                    switch (serverInfo.keyPressed) {
                    case 1000:
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 1001:
                        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                        break;
                    case 1002:
                        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                        break;
                    }
                }

                // System.out.println("finished loop.");
            }
        } catch (Exception e) {
            System.out.println("Starting Process");
            e.printStackTrace();
            // ProcessBuilder process = new ProcessBuilder("notepad.exe");
            // Process notepadOpen = process.start();

            // robot.delay(1000);
            // type("You cannot escape your endless torment. You are left here to suffer for
            // eternity.");
            // robot.delay(1000);
            // startTheFrickening();
        }
    }

    public static void type(String str) throws AWTException {
        Robot robot = new Robot();

        for (char c : str.toCharArray()) {

            if (Character.isUpperCase(c))
                robot.keyPress(KeyEvent.VK_SHIFT);

            int keycode = KeyEvent.getExtendedKeyCodeForChar(c);

            robot.keyPress(keycode);
            robot.keyRelease(keycode);

            if (Character.isUpperCase(c))
                robot.keyRelease(KeyEvent.VK_SHIFT);

            robot.delay(150);
        }
    }

    public static void startTheFrickening() throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screen.getWidth();
        int height = (int) screen.getHeight();

        while (true) {
            robot.mouseMove(random.nextInt(width), random.nextInt(height));
            robot.delay(25);
        }
    }
}
