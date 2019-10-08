import bagel.*;
import bagel.util.*;

public class Peg {

    private Image image;
    private double x;
    private double y;
    private Rectangle r;

    private final static int HEIGHT_CHANGE = 100;

    Peg() {

        /* Generate random position of pegs with x coordinates between 0 and 1024,
        and y coordinates between 100 and 768. */
        x = Math.random() * Window.getWidth();
        y = Math.random() * (Window.getHeight() - HEIGHT_CHANGE) + HEIGHT_CHANGE;
        image = new Image("res/peg.png");

    }

    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    /* Draw image using Rectangle box*/
    void render() {

        image.draw(x, y);
        r = image.getBoundingBoxAt(new Point(x,y));
    }
    /* Returns box that centered at point x,y */
    Rectangle getBox() {

        return r;
    }
}
