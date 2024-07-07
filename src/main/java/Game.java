import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> registeredPlayers;

    public Game() {
        this.registeredPlayers = new ArrayList<>();
    }

    public void register(Player player) {
        if (!registeredPlayers.contains(player)) {
            registeredPlayers.add(player);
        }
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = getPlayerByName(playerName1);
        Player player2 = getPlayerByName(playerName2);

        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("One or both players are not registered.");
        }

        int strengthDiff = player1.getStrength() - player2.getStrength();
        if (strengthDiff > 0) {
            return 1; // player1 wins
        } else if (strengthDiff < 0) {
            return 2; // player2 wins
        } else {
            return 0; // draw
        }
    }

    private Player getPlayerByName(String playerName) {
        return registeredPlayers.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElse(null);
    }
}