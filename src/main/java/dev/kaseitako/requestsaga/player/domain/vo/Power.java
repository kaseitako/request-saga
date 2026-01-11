package dev.kaseitako.requestsaga.player.domain.vo;

import dev.kaseitako.requestsaga.shared.domain.Parameter;

public record Power(int value) implements Parameter<Power> {

    public Power {
        ensureParameterValid(value);
    }
}
