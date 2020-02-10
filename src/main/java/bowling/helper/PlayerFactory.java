package bowling.helper;

import bowling.io.pojo.PlayerShoot;
import bowling.model.Player;
import bowling.model.Shoot;
import bowling.model.Turn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static bowling.model.Shoot.createShootFromDescription;
import static bowling.model.Shoot.missedShoot;
import static bowling.model.Turn.incompleteExtraTurn;

public class PlayerFactory {

    private static final Logger logger = LoggerFactory.getLogger(PlayerFactory.class.getName());

    public void registerPlayerShoot(List<Player> players, PlayerShoot playerShoot) {
        String name = playerShoot.getName();
        String shootDesc = playerShoot.getShootDesc();

        int playerPosition = getPlayerPosition(players, name);

        if (playerPosition == -1) {
            Player p = new Player(name);
            Turn newTurn = new Turn(createShootFromDescription(shootDesc));
            p.registerTurn(newTurn);
            players.add(p);
        } else {
            Player player = players.get(playerPosition);
            Shoot shoot = createShootFromDescription(shootDesc);
            boolean isLastTurnInProgress = player.isLastTurnInProgress();

            if (player.isPlayingTenthTurn()) {
                handleTenthTurn(name, shootDesc, player);
                return;
            }

            if (player.isPlayingExtraTurn()) {
                handleExtraTurn(name, shootDesc, player);
                return;
            }

            if (isLastTurnInProgress) {
                player.registerSecondShoot(shoot);
                return;
            }

            Turn newTurn = new Turn(createShootFromDescription(shootDesc));
            player.registerTurn(newTurn);
        }
    }

    private void handleTenthTurn(String name, String shootDesc, Player player) {
        if (player.getTenthTurn().isInProgress()) {
            player.registerSecondShoot(createShootFromDescription(shootDesc));
            return;
        }

        if (player.getTenthTurn().isRegular()) {
            logger.warn("Ignoring shoot ({}) for player {} with regular score on the last turn", shootDesc, name);
            return;
        }

        Turn extraTurn =  player.didNotStrikeOnTenthTurn()
                ? new Turn(createShootFromDescription(shootDesc), missedShoot())
                : incompleteExtraTurn(createShootFromDescription(shootDesc));

        player.registerTurn(extraTurn);
    }

    private void handleExtraTurn(String name, String shootDesc, Player player) {
        if (player.hasCompletedExtraTurn()) {
            logger.warn("Ignoring shoot ({}) for player {} with extra turn completed", shootDesc, name);
            return;
        }

        player.registerSecondShoot(createShootFromDescription(shootDesc));
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
