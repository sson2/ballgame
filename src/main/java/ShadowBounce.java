import bagel.*;
import bagel.util.*;

public class ShadowBounce extends AbstractGame {

    private final static int NUM_PEGS = 50;
    private final static int MAGNITUDE = 10;
    private final static int WINDOW_LEFT = 0;
    private final static int WINDOW_RIGHT = 1024;
    private final static int WINDOW_TOP = 0;
    private final static int WINDOW_BOTTOM = 768;

    private BallPlayer ballPlayer;
    private Peg[] peg;
    private boolean placed;

    private ShadowBounce() {

        placed = false;
        ballPlayer = new BallPlayer();
        peg = new Peg[NUM_PEGS];
        /* Create 50 peg instances and assign those in the array.*/
        for (int i = 0; i < peg.length; i++) {
            peg[i] = new Peg();
        }

    }

    /* Delete pegs once ball intersects with pegs*/
    private void deletePeg() {

        for (int i = 0; i < peg.length; i++) {
            if (peg[i] != null && (ballPlayer.getBox().intersects(peg[i].getBox()))) {
                peg[i] = null;
            }
        }
    }

    /* Once the mouse position is entered, find the displacement between mouse position and the ball position.*/
    private double[] calculateDisplacement(Point mousePoint) {

        double mouseX = mousePoint.x;
        double mouseY = mousePoint.y;
        double diffX = mouseX - ballPlayer.getX();
        double diffY = mouseY - ballPlayer.getY();
        Vector2 vec = new Vector2(diffX, diffY);
        Vector2 direct = vec.normalised();
        double dy = MAGNITUDE * direct.y;
        double dx = MAGNITUDE * direct.x;
        return new double[]{dx,dy};
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    @Override
    public void update(Input input) {

        double dx = 0, dy = 0;

        /* When left button of mouse is clicked execute the following:
        1. Change the bool logic "placed" to "true" so that the ball image remains
           drawn on next frame without getting updated.
        2. Return mouse position where it was clicked.
        3. Then calculate the displacement between mouse position and the ball position.
        4. Change the ball direction.
        The loop resumes when the new left mouse is clicked. */

        if (input.wasPressed(MouseButtons.LEFT)) {

            Point mousePoint = input.getMousePosition();
            double[] changes = calculateDisplacement(mousePoint);
            dx = changes[0];
            dy = changes[1];
            ballPlayer.changeDirection(dx,dy);
            placed = true;
        }

        /* Simulate gravity*/
        ballPlayer.gravity();

        /* Draw ball images when its array memory space is not null*/
        for (Peg value : peg) {
            if (value != null)
                value.render();
        }

        /* While the ball is placed on screen,
        1. Draw the ball image using Rectangle box.
        2. Change Ball's position.
        3. If the ball reaches the side of window, reverse the movement so that the ball can stays on screen.
        4. Delete the pegs when it intersects with the ball. */
        if(placed) {

            if(ballPlayer.getBox().right() >= WINDOW_RIGHT || ballPlayer.getBox().left() <= WINDOW_LEFT) {
                ballPlayer.redirectX();
            }
            if(ballPlayer.getBox().top() <= WINDOW_TOP || ballPlayer.getBox().bottom() >= WINDOW_BOTTOM) {
                ballPlayer.redirectY();
            }
            ballPlayer.move();
            ballPlayer.render();
            deletePeg();
        }
    }
}