package dev.kaseitako.requestsaga.combat.domain;

import dev.kaseitako.requestsaga.player.domain.Player;

public record TurnAction(int turn, Player activeCombatant, Player target, int damage) {
    public TurnAction {
        activeCombatant = activeCombatant.copy();
        target = target.copy();
    }
}
