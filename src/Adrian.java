import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.Serializable;

class Adrian implements Serializable{
    Point mouseLocation;
    int keyPressed;

    public Adrian(Point mouseLocation, int keyPressed) {
        this.mouseLocation = mouseLocation;
        this.keyPressed = keyPressed;
    }

    public Point getMouse() {
        return mouseLocation;
    }

    public void setMouse(Point mouseLocation) {
        this.mouseLocation = mouseLocation;
    }

    public int getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    // public int getKeyReleased() {
    //     return keyReleased;
    // }

    // public void setKeyReleased(int keyReleased) {
    //     this.keyReleased = keyReleased;
    // }

    public String toString() {
        String str = "";

        str += "Mouse[" + mouseLocation.x + ", " + mouseLocation.y + "]";

        if(keyPressed >= 0)
            str += "KeyPress[" + KeyEvent.getKeyText(keyPressed) + "]";
        // str += "KeyReleased[" + KeyEvent.getKeyText(keyReleased) + "]";

        return str;
    }
}