dsad/*
 * Author: Rushil Patel, rushil2011@my.fit.edu
 * Fall 2012 Project: snake
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;

public final class SnakeMover {

    private static final int XPOSITION_SCORE = 260;
    private static final int MS_TO_S = 1000;
    private static final int YPOSITION_TEXT = 255;
    private static final int XPOSITION_TEXT = -90;
    private static final int TOTAL_TIME = 15;
    private static final int ONE = 1;
    private static final int HUNDRED = 100;
    private static final int TWO = 2;
    private static final int FIVE = 5;
    private static int length; // Length of the game screen
    private static int height; // Height of the game screen
    private static int xScale; // sets screen scale for x-axis
    private static int yScale; // sets screen scale for y-axis
    private final int foodRadius = FIVE; // radius of the food
    private final int snakeRadius = FIVE; // radius of the snake
    private final int move = snakeRadius * TWO; // number points to skip for the
                                                // next move
    // Total number of spots that the snake can fill up on the screen
    private final int totalPositions = ( length / move) * ( height / move);
    private final FoodGenerator food; // Generates food at random places
    private int xFood_one; // Tracks xCoordinate of first food
    private int yFood_one; // Tracks yCoordinate of first food
    private int xFood_two; // Tracks xCoordinate of second food
    private int yFood_two; // Tracks yCoordinate of second food
    private int xSnake = 0; // Tracks xCoordinate of snake's head
    private int ySnake = 0; // Tracks yCoordinate of snake's head
    private int snakeLength = FIVE; // Track snake's length
    private final int snakeSpeed = HUNDRED; // Speed at which the snake will
                                            // travel
    // Store the previous xPosition of the snake
    private final ArrayList<Integer> xPoints = new ArrayList<> ();
    // Store the previous yPosition of the snake
    private final ArrayList<Integer> yPoints = new ArrayList<> ();
    private final ScoreTracker score = new ScoreTracker (); // handles the score
    // tracks time when the second food was generated
    private long pink_startTime = System.currentTimeMillis ();
    // Tracks time since the second food was generated
    private long pink_elapsedTime;
    // tracks the time when the first food was generated
    private long red_startTime = System.currentTimeMillis ();
    // Tracks time since the first food was generated
    private long red_elapsedTime;
    private int noOfmoves = 0; // tracks total number of moves

    public SnakeMover (final int xWidth, final int yWidth) {
        length = xWidth;
        height = yWidth;
        xScale = length / TWO;
        yScale = height / TWO;
        // Creates a new window for the game
        StdDraw.setCanvasSize (length, height);
        StdDraw.setXscale (-xScale, xScale);
        StdDraw.setYscale (-yScale, yScale);
        food = new FoodGenerator (xScale - foodRadius, yScale - foodRadius,
                foodRadius);

        // sets up the initial length and position of the snake
        xSnake = -( TWO * move);
        xPoints.add (xSnake); // Sets the xHead
        yPoints.add (0);
        xPoints.add (-move);
        yPoints.add (0);
        xPoints.add (0);
        yPoints.add (0);
        xPoints.add (move);
        yPoints.add (0);
        xPoints.add ( ( TWO * move));
        yPoints.add (0);
        generateFood (ONE); // generates first food
        generateFood (TWO); // generates second food
    }

    // moves the snake
    public boolean draw (final boolean playing) {

        StdDraw.show (snakeSpeed); // sets the snake speed
        StdDraw.clear (StdDraw.DARK_GRAY); // sets the game background
        StdDraw.setPenColor (StdDraw.RED); // sets the color of first food
        // draw the first food on the screen
        StdDraw.filledSquare (xFood_one, yFood_one, foodRadius);
        StdDraw.setPenColor (StdDraw.PINK); // sets the color of second food
        // draw the second food on the screen
        StdDraw.filledSquare (xFood_two, yFood_two, foodRadius);

        // if the snake is moving then move the body
        // of the snake along with its head
        if (playing) {
            noOfmoves++;
            for (int i = snakeLength - ONE; i > 0; i--) {
                xPoints.set (i, xPoints.get (i - ONE));
                yPoints.set (i, yPoints.get (i - ONE));
            }
            xPoints.set (0, xSnake);
            yPoints.set (0, ySnake);
        }

        // moves the snake on the screen
        for (int i = 0; i < snakeLength; i++) {

            if (i == 0) {
                // make snake's head yellow
                StdDraw.setPenColor (StdDraw.YELLOW);
                StdDraw.filledCircle (xPoints.get (i), yPoints.get (i),
                        snakeRadius);
            } else {
                // make the body the snake green
                StdDraw.setPenColor (StdDraw.GREEN);
                StdDraw.filledSquare (xPoints.get (i), yPoints.get (i),
                        snakeRadius);
            }

        }
        // Sets the color of the wall
        StdDraw.setPenColor (StdDraw.BLUE);
        // Draw the walls
        StdDraw.line (-xScale, -yScale, xScale, -yScale);
        StdDraw.line (-xScale, -yScale, -xScale, yScale);
        StdDraw.line (-xScale, yScale, xScale, yScale);
        StdDraw.line (xScale, -yScale, xScale, yScale);
        // sets the color of food timer and the score
        StdDraw.setPenColor (StdDraw.WHITE);
        // Displays game score in the top right corner
        StdDraw.text (XPOSITION_SCORE, YPOSITION_TEXT, "Current Score: "
                + score.getScore ());
        // Displays the countdown after which a particular food will change it
        // position
        final String timers = String.format (
                "Pink Food Timer: %02d seconds   Red Food Timer: %02d seconds",
                TOTAL_TIME - ( pink_elapsedTime / MS_TO_S), TOTAL_TIME
                        - ( red_elapsedTime / MS_TO_S));
        StdDraw.text (XPOSITION_TEXT, YPOSITION_TEXT, timers);

        // checks the time since the food was last created
        checkTime ();
        // checks for the collision
        return ( checkPoints ());

    }

    // sets the direction the snake
    public void setDirection (final String key) {
        // moves snake upwards on the screen
        if (key.equals ("up")) {
            ySnake += move;
            // moves snake downwards on the screen
        } else if (key.equals ("down")) {
            ySnake -= move;
            // moves snake towards left on the screen
        } else if (key.equals ("left")) {
            xSnake -= move;
            // moves snake towards right on the screen
        } else if (key.equals ("right")) {
            xSnake += move;
        }
    }

    // Checks the position of the snake
    private boolean checkPoints () {
        // if the snake hits the wall or eats itself then end the game and
        // display the score
        if ( ( ( Math.abs (xSnake) + snakeRadius) > xScale)
                || ( ( Math.abs (ySnake) + snakeRadius) > yScale)
                || checkCollision ()) {
            final String message = String.format (
                    "You Lost !!!\nYour score: %d", score.getScore ());
            JOptionPane.showMessageDialog (null, message, "Game Over !!!", TWO);
            return true;

            // if the snake has eaten the first food then generate a new one
        } else if ( ( xSnake == xFood_one) && ( ySnake == yFood_one)) {
            ateFood (ONE);
            return false;

            // if the snake has eaten the second food then generate a new one
        } else if ( ( xSnake == xFood_two) && ( ySnake == yFood_two)) {
            ateFood (TWO);
            return false;

            // if the snake fill up every spot available, then declare the
            // winner display the results
        } else if (snakeLength == ( totalPositions - TWO)) {
            final String message = String.format (
                    "You Win !!!\nYour score: %d", score.getScore ());
            JOptionPane.showMessageDialog (null, message, "Game Over!", TWO);
            return true;
        } else {
            return false;
        }
    }

    // This checks whether the snake has eaten itself or not
    private boolean checkCollision () {
        boolean status = false;
        final int xHead = xPoints.get (0);
        final int yHead = yPoints.get (0);
        if (noOfmoves > ONE) {
            // see if the head has collided with its body
            for (int i = ONE; i < snakeLength; i++) {

                if ( ( xHead == xPoints.get (i)) && ( yHead == yPoints.get (i))) {
                    System.out.println (i + " " + xPoints.get (i) + " "
                            + yPoints.get (i));
                    status = true;
                }
            }
        }
        return status;
    }

    // generates a new food when the snake eats the old one
    // and increases the size of the snake
    private void ateFood (final int foodNo) {
        generateFood (foodNo);
        score.addScore ();
        snakeLength++;
        xPoints.add (xSnake);
        yPoints.add (ySnake);
    }

    // tracks time since the new food was generated
    // and generates new food after every 15 seconds
    private void checkTime () {
        pink_elapsedTime = System.currentTimeMillis () - pink_startTime;
        red_elapsedTime = System.currentTimeMillis () - red_startTime;

        if (pink_elapsedTime >= (MS_TO_S * TOTAL_TIME)) {
            generateFood (TWO);
        } else if (red_elapsedTime >= (MS_TO_S * TOTAL_TIME)) {
            generateFood (ONE);
        }
    }

    // generates the requested food
    private void generateFood (final int foodNo) {
        // generate the first food upon request
        if (foodNo == ONE) {
            xFood_one = food.newX ();
            yFood_one = food.newY ();
            // checks that the first food is not being generated at same place as
            // the second food
            if ( ( xFood_one == xFood_two) && ( yFood_one == yFood_two)) {
                generateFood (ONE);
            } else {
                for (int i = 0; i < snakeLength; i++) {
                    if ( ( ( xFood_one == xPoints.get (i)) && ( yFood_one == yPoints
                            .get (i)))) {
                        generateFood (ONE);
                        return;
                    }
                }
            }

            red_startTime = System.currentTimeMillis (); // restart timer
        } else {
            xFood_two = food.newX ();
            yFood_two = food.newY ();
            // checks if the second food is not being generated at same place as
            // the first food
            if ( ( xFood_two == xFood_one) && ( yFood_two == yFood_one)) {
                generateFood (TWO);
            } else {
                for (int i = 0; i < snakeLength; i++) {
                    if ( ( ( xFood_two == xPoints.get (i)) && ( yFood_two == yPoints
                            .get (i)))) {
                        generateFood (TWO);
                        return;
                    }
                }
            }
            // restart the timer for the second food
            pink_startTime = System.currentTimeMillis ();
        }
    }

}