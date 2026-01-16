package dev.kaseitako;

import dev.kaseitako.requestsaga.combat.domain.Battle;
import dev.kaseitako.requestsaga.combat.domain.vo.BattleId;
import dev.kaseitako.requestsaga.player.domain.Player;
import dev.kaseitako.requestsaga.player.domain.vo.*;

public class Main {
    static void main() {
        IO.println("Hello and welcome!");

        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("自分マン"),
                        new Health(180, 180),
                        new Power(23),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵太郎"),
                        new Health(80, 80),
                        new Power(9),
                        new Stamina(8, 8));

        var battleResult = new Battle(new BattleId(1L), player, enemy).execute();

        battleResult.battleLog().stream()
                .map(
                        log ->
                                "第%dターン %s が %s に攻撃 %dダメージ | %s の HP %d/%d"
                                        .formatted(
                                                log.turn(),
                                                log.activeCombatant().getPlayerName().value(),
                                                log.target().getPlayerName().value(),
                                                log.damage(),
                                                log.target().getPlayerName().value(),
                                                log.target().getHealth().current(),
                                                log.target().getHealth().max()))
                .forEach(IO::println);
    }
}
