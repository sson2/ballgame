import bagel.*;
import bagel.util.*;

public class BallPlayer {

    private Image image;
    private double x;
    private double y;
    private Rectangle r;
    private double dy;
    private double dx;

    private final static int BALL_START_X = 512;
    private final static int BALL_START_Y = 32;
    private final static int APPOSITE = -1;
    private final static double GRAVITY = 0.15;

    BallPlayer() {

        /* Ball should appear at the position (512, 32).*/
        x = BALL_START_X;
        y = BALL_START_Y;
        image = new Image("res/ball.png");
        r = image.getBoundingBoxAt(new Point(x,y));
    }

    double getX() {

        return x;
    }

    double getY() {

        return y;
    }

    /* Change the ball direction using displacement */
    void changeDirection(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
    }
    /* Redirect the ball when it reaches the sides of the Window */
    void redirectX()
    {

        this.dx = dx * APPOSITE;
    }

    void redirectY()
    {

        this.dy = dy * APPOSITE;
    }

    /* Simulate Gravity */
    void gravity()
    {

        dy += GRAVITY;
    }

    /* New ball position due to mouse position and its displacement*/
    void move() {

        x += dx;
        y += dy;
    }
    /* x, y coordinate of point */
    private void point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /* Draw image using Rectangle box*/
    void render() {

        image.draw(x,y);
        r = image.getBoundingBoxAt(new Point(x,y));
    }
    /* Returns box that centered at point x,y */
    Rectangle getBox() {

        return r;
    }
}
