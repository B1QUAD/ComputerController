import java.awt.Point;
import java.io.Serializable;

class Adrian implements Serializable {
    Point mouseLocation;
    int keyPressed;

    public Adrian(Point mouseLocation, int keyPressed) {
        this.mouseLocation = mouseLocation;
        this.keyPressed = keyPressed;
    }

    public String toString() {
        String str = "";

        str += "Mouse[" + mouseLocation.x + ", " + mouseLocation.y + "]";
        str += "\nKey[" + keyPressed + "]";

        return str;
    }
}
