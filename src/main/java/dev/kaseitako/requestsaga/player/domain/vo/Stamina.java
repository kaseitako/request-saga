package dev.kaseitako.requestsaga.player.domain.vo;

import dev.kaseitako.requestsaga.shared.domain.Resource;

public record Stamina(int current, int max) implements Resource<Stamina> {
    public Stamina {
        ensureResourceValid(current, max);
    }
}
