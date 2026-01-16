package dev.kaseitako.requestsaga.combat.domain;

import dev.kaseitako.requestsaga.combat.domain.vo.BattleId;
import dev.kaseitako.requestsaga.player.domain.Player;
import java.util.LinkedList;

public record Battle(BattleId battleId, Player player, Player enemy) {
    private static final int MAX_TURN = 10;

    public Battle {
        if (player.getPlayerId().value() == enemy.getPlayerId().value()) {
            throw new IllegalArgumentException("同じプレイヤー同士では戦闘を行えません");
        }

        if (player.isDead() || enemy.isDead()) {
            throw new IllegalArgumentException("戦闘に死者が参加しています");
        }
    }

    public BattleResult execute() {

        var turn = 1;
        var battleLog = new LinkedList<TurnAction>();
        while (!(player.isDead() || enemy.isDead()) && turn <= MAX_TURN) {

            enemy.takeDamage(player.getPower().value());
            battleLog.add(new TurnAction(turn, player, enemy, player.getPower().value()));
            turn++;

            player.takeDamage(enemy.getPower().value());
            battleLog.add(new TurnAction(turn, enemy, player, enemy.getPower().value()));
            turn++;
        }

        return new BattleResult(battleLog);
    }
}
