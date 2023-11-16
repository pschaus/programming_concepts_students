package basics;

import org.javagrader.Grade;
import org.javagrader.GradeFeedback;
import org.javagrader.TestResultStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class PentagoTest {
    @Test
    @Grade(value = 1, cpuTimeout = 3)
    @GradeFeedback(message = "Congrats!", on= TestResultStatus.SUCCESS)
    @GradeFeedback(message = "Something is wrong in the rotation of a square matrix", on=TestResultStatus.FAIL)
    @GradeFeedback(message = "Too slow, infinite loop ?", on=TestResultStatus.TIMEOUT)
    public void testRotateSquare(){
        Pentago game = new Pentago();
        Pentago.Player[][] matrix = {{Pentago.Player.A, Pentago.Player.A, Pentago.Player.B, null},
                {null, Pentago.Player.B, Pentago.Player.A, Pentago.Player.A},
                {null, Pentago.Player.B, null, null},
                {Pentago.Player.A, Pentago.Player.B, Pentago.Player.A, Pentago.Player.B}};
        Pentago.Player[][] rotated = game.rotateMatrix(matrix);
        Pentago.Player[][] expected = {{Pentago.Player.A, null, null, Pentago.Player.A},
                {Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.A},
                {Pentago.Player.A, null, Pentago.Player.A, Pentago.Player.B},
                {Pentago.Player.B, null, Pentago.Player.A, null}};
        for(int i=0; i<expected.length; i++)
            assertArrayEquals(expected[i], rotated[i]);
    }



    @Test
    @Grade(value = 3, cpuTimeout = 3)
    @GradeFeedback(message = "Congrats!", on= TestResultStatus.SUCCESS)
    @GradeFeedback(message = "Something is wrong in the detection of a winner with checkWinPlayer method",
                    on=TestResultStatus.FAIL)
    @GradeFeedback(message = "Too slow, infinite loop ?", on=TestResultStatus.TIMEOUT)
    public void testWinVector(){
        Pentago game = new Pentago();

        Pentago.Player[] vector;

        // size six with all empty positions
        vector = new Pentago.Player[]{null, null, null, null, null, null};
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.B), "No player should have won in an empty row");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "No player should have won in an empty row");

        // size six
        vector = new Pentago.Player[]{Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.A, Pentago.Player.A};
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.B), "This player should not be winning");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "This player should not be winning");

        // size six
        vector = new Pentago.Player[]{Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.A};
        assertTrue(game.checkWinPlayer(vector, Pentago.Player.B), "This player should be winning");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "This player should not be winning");

        // size five
        vector = new Pentago.Player[]{Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B};
        assertTrue(game.checkWinPlayer(vector, Pentago.Player.B), "This player should be winning");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "This player should not be winning");

        // size four
        vector = new Pentago.Player[]{Pentago.Player.B, Pentago.Player.B, Pentago.Player.B, Pentago.Player.B};
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.B), "This player should be winning");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "This player should not be winning");

        vector = new Pentago.Player[]{Pentago.Player.B, Pentago.Player.A, Pentago.Player.A, Pentago.Player.A, Pentago.Player.A, Pentago.Player.A};
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.B), "This player should not be winning");
        assertTrue(game.checkWinPlayer(vector, Pentago.Player.A), "This player should be winning");

        vector = new Pentago.Player[]{Pentago.Player.A, Pentago.Player.A, Pentago.Player.A, null, Pentago.Player.A, Pentago.Player.A};
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.B), "Hint: Winning sequence must be contiguous (no null)");
        assertFalse(game.checkWinPlayer(vector, Pentago.Player.A), "Hint: Winning sequence must be contiguous (no null)");
    }

    // Test to check that an exception is thrown when trying to play on an already used position
    @Test
    @Grade(value = 1, cpuTimeout = 10)
    @GradeFeedback(message = "Congrats!", on= TestResultStatus.SUCCESS)
    @GradeFeedback(message = "Something is wrong when trying to play on an already used position", on=TestResultStatus.FAIL)
    @GradeFeedback(message = "Too slow, infinite loop ?", on=TestResultStatus.TIMEOUT)
    public void testExceptionNonEmptyCell(){
        Pentago game = new Pentago();
        game.play(2, 4, Pentago.Player.B, Pentago.BoardSubpart.TOP_RIGHT, Pentago.RotationDirection.LEFT);
        game.play(5, 0, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.RIGHT);
        assertThrows(IllegalArgumentException.class, () -> {
            game.play(1, 5, Pentago.Player.B, Pentago.BoardSubpart.TOP_LEFT, Pentago.RotationDirection.LEFT);
        }, "The first move of player B followed by the rotation is already using the position 1,5 in the bottom LEFT subpart");
    }

    // Simple test to check that the right winner is found and that it is not found too soon
    @Test
    @Grade(value = 1, cpuTimeout = 10)
    @GradeFeedback(message = "Congrats!", on= TestResultStatus.SUCCESS)
    @GradeFeedback(message = "Something is wrong when the rotations do not change the board's state (i.e. rotating an empty subpart)", on=TestResultStatus.FAIL)
    @GradeFeedback(message = "Too slow, infinite loop ?", on=TestResultStatus.TIMEOUT)
    public void testPlay1(){
        Pentago game = new Pentago();
        Pentago.Winner winner;
        winner = game.play(0, 0, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(5, 5, Pentago.Player.B, Pentago.BoardSubpart.TOP_RIGHT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(1, 0, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_RIGHT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(3, 3, Pentago.Player.B, Pentago.BoardSubpart.TOP_RIGHT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(2, 0, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(2, 2, Pentago.Player.B, Pentago.BoardSubpart.TOP_LEFT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(0, 3, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_RIGHT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(4, 3, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(0, 4, Pentago.Player.A, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.A_WINS, winner, "Player A should have won");
    }

    @Test
    @Grade(value = 0.4, cpuTimeout = 3)
    @GradeFeedback(message = "Congrats!", on= TestResultStatus.SUCCESS)
    @GradeFeedback(message = "Something is wrong in the finding of a diagonal win", on=TestResultStatus.FAIL)
    @GradeFeedback(message = "Too slow, infinite loop ?", on=TestResultStatus.TIMEOUT)
    public void testSimpleDiagonal(){
        /*
         * This is the state of the game after the different moves of the test.
         * The player B wins because he has 5 tokens in a diagonal.
         * ------------------------------
         * |  x   B   x  |  x   x   x   |
         * |  x   x   B  |  x   x   x   |
         * |  x   x   x  |  B   x   x   |
         * ------------------------------
         * |  x   x   x  |  x   B   x   |
         * |  x   x   x  |  x   x   B   |
         * |  x   x   x  |  x   x   x   |
         * ------------------------------
         */
        Pentago game = new Pentago();
        Pentago.Winner winner;
        winner = game.play(0, 1, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_RIGHT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(1, 2, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(2, 3, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_RIGHT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(3, 4, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.LEFT);
        assertEquals(Pentago.Winner.NO_WINNER, winner, "No one should have won yet");
        winner = game.play(4, 5, Pentago.Player.B, Pentago.BoardSubpart.BOTTOM_LEFT, Pentago.RotationDirection.RIGHT);
        assertEquals(Pentago.Winner.B_WINS, winner, "B should have won");
    }

}
