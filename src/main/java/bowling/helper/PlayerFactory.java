package bowling.helper;

import bowling.io.pojo.PlayerShoot;
import bowling.model.Player;
import bowling.model.Shoot;
import bowling.model.Turn;

import java.util.List;

public class PlayerFactory {

    public void registerPlayerShoot(List<Player> players, PlayerShoot playerShoot) {
        int playerPosition = getPlayerPosition(players, playerShoot.getName());

        if (playerPosition == -1) {
            Player p = new Player(playerShoot.getName());
            Turn newTurn = new Turn(Shoot.createFromDesc(playerShoot.getShootDesc()));
            p.registerTurn(newTurn);
            players.add(p);
        } else {
            Shoot shoot = Shoot.createFromDesc(playerShoot.getShootDesc());
            boolean isLastTurnInProgress = players.get(playerPosition).isLastTurnInProgress();

            if (isLastTurnInProgress) {
                players.get(playerPosition).registerSecondShoot(shoot);
            } else {
                Turn newTurn = new Turn(Shoot.createFromDesc(playerShoot.getShootDesc()));
                players.get(playerPosition).registerTurn(newTurn);
            }
        }
    }

    private int getPlayerPosition(List<Player> players, String playerName) {
        int position = -1;

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equalsIgnoreCase(playerName)) {
                position = i;
                break;
            }
        }

        return position;
    }
}
