import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    public void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 10);
        player3 = new Player(3, "Charlie", 5);

        game.register(player1);
        game.register(player2);
        game.register(player3);
    }

    @Test
    public void testRoundWithRegisteredPlayers_Draw() {
        assertEquals(0, game.round("Alice", "Bob"));
    }

    @Test
    public void testRoundWithRegisteredPlayers_Player1Wins() {
        assertEquals(1, game.round("Alice", "Charlie"));
    }

    @Test
    public void testRoundWithRegisteredPlayers_Player2Wins() {
        assertEquals(2, game.round("Charlie", "Alice"));
    }

    @Test
    public void testRoundWithUnregisteredPlayers_ThrowsException() {
        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Unknown"));
    }

    @Test
    public void testRegisteringSamePlayerTwice() {
        game.register(player1);
        assertEquals(3, game.getRegisteredPlayers().size());
    }

    @Test
    public void testGetRegisteredPlayers() {
        assertEquals(3, game.getRegisteredPlayers().size());
        assertEquals(player1, game.getRegisteredPlayers().get(0));
        assertEquals(player2, game.getRegisteredPlayers().get(1));
        assertEquals(player3, game.getRegisteredPlayers().get(2));
    }
}