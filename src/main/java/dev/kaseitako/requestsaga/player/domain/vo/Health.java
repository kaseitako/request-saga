package dev.kaseitako.requestsaga.player.domain.vo;

import dev.kaseitako.requestsaga.shared.domain.Resource;

public record Health(int current, int max) implements Resource<Health> {
    public Health {
        ensureResourceValid(current, max);
    }

    public Health takeDamage(int damage) {
        int newCurrent = Math.max(0, current - damage);
        return new Health(newCurrent, max);
    }
}
