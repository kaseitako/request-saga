package dev.kaseitako.requestsaga.player.domain;

import dev.kaseitako.requestsaga.player.domain.vo.*;

public class Player {
    private final PlayerId playerId;
    private final PlayerName playerName;
    private Health health;
    private final Power power;
    private final Stamina stamina;

    public Player(
            PlayerId playerId, PlayerName playerName, Health health, Power power, Stamina stamina) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.health = health;
        this.power = power;
        this.stamina = stamina;
    }

    public Player copy() {
        return new Player(playerId, playerName, health, power, stamina);
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Health getHealth() {
        return health;
    }

    public Power getPower() {
        return power;
    }

    public Stamina getStamina() {
        return stamina;
    }

    public void takeDamage(int damage) {
        health = health.takeDamage(damage);
    }

    public boolean isDead() {
        return health.isEmpty();
    }
}
