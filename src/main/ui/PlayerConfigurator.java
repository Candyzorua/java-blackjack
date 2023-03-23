package ui;

import model.BGame;
import model.Player;

import static ui.InputTaker.takeInput;

public class PlayerConfigurator {
    private final BGame bg;

    public PlayerConfigurator(BGame bg) {
        this.bg = bg;
    }

    // MODIFIES: this
    // EFFECTS: enters game configuration menu
    public void configure() {
        System.out.println("Entering configuration menu... \n");
        configureGame();
        System.out.println("Exiting configuration menu... \n");
    }

    // MODIFIES: g1
    // EFFECTS: handles actions related to game configuration
    private void configureGame() {
        boolean keepConfiguring = true;
        while (keepConfiguring) {
            SummaryPrinter.displayGameSummary(bg);
            String userInput = takeInput("Press 'a' to add a player, 'r' to remove a player, "
                    + "'d' to set another player as the dealer, and 'c' to continue");
            switch (userInput) {
                case "a":
                    addPlayer();
                    break;
                case "r":
                    removePlayer();
                    break;
                case "d":
                    setNewDealer();
                    break;
                case "c":
                    keepConfiguring = false;
                    break;
                default:
                    System.out.println("Sorry, invalid input.");
            }
        }
    }

    // MODIFIES: g1
    // EFFECTS: tries to add a player to the game
    //          fails if player with the same name is already in the game
    //          prints appropriate confirmation messages (fail or succeed)
    private void addPlayer() {
        String newPlayerName = takeInput("New player name:");
        boolean result = bg.addPlayer(new Player(newPlayerName, 0));
        if (result) {
            System.out.println("New player " + newPlayerName + " added.");
        } else {
            System.out.println("Sorry, that player could not be added.");
        }
    }

    // MODIFIES: g1
    // EFFECTS: tries to remove a player from the game
    //          fails if the number of players <= g1.MIN_PLAYERS
    //          prints appropriate confirmation messages (fail or succeed)
    private void removePlayer() {
        String playerName = takeInput("Please select a player to remove");
        Player playerToRemove = selectPlayer(playerName);
        boolean result = bg.removePlayer(playerToRemove);
        if (result) {
            assert playerToRemove != null;
            System.out.println("Player " + playerToRemove.getName() + " removed.");
        } else {
            System.out.println("Sorry, that player could not be removed.");
        }
    }

    // MODIFIES: g1
    // EFFECTS: lets the user set a new regular player as the dealer by name, and the old dealer becomes
    //          a regular player
    //          fails if the player gives an invalid name
    //          prints appropriate confirmation messages
    private void setNewDealer() {
        String dealerName = takeInput("Please select a player to set as the dealer");
        Player newDealer = selectPlayer(dealerName);
        boolean result = bg.setPlayerAsDealer(newDealer);
        if (result) {
            System.out.println("New dealer " + dealerName + " set.");
        } else {
            System.out.println("Sorry, that player could not be set as the dealer.");
        }
    }

    // EFFECTS: returns a regular player with the given name from the list of regular players
    //          returns null if no player with the given name from the list of regular players
    private Player selectPlayer(String playerName) {
        for (Player p : bg.getRegularPlayers()) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }
}
