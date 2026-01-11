package dev.kaseitako.requestsaga.combat.domain;

import java.util.List;

public record BattleResult(List<TurnAction> battleLog) {}
