package dev.kaseitako.requestsaga.combat.domain;

import static org.junit.jupiter.api.Assertions.*;

import dev.kaseitako.requestsaga.combat.domain.vo.BattleId;
import dev.kaseitako.requestsaga.player.domain.Player;
import dev.kaseitako.requestsaga.player.domain.vo.*;
import org.junit.jupiter.api.Test;

class BattleTest {

    @Test
    void testEnemyDies() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(100, 100),
                        new Power(30),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵"),
                        new Health(50, 50),
                        new Power(5),
                        new Stamina(20, 20));

        var battle = new Battle(new BattleId(1L), player, enemy);
        var result = battle.execute();

        assertTrue(enemy.isDead(), "敵は死んでいるべき");
        assertFalse(player.isDead(), "プレイヤーは生存しているべき");
        assertFalse(result.battleLog().isEmpty(), "バトルログが記録されているべき");
    }

    @Test
    void testPlayerDies() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(50, 50),
                        new Power(5),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵"),
                        new Health(100, 100),
                        new Power(30),
                        new Stamina(20, 20));

        var battle = new Battle(new BattleId(1L), player, enemy);
        var result = battle.execute();

        assertTrue(player.isDead(), "プレイヤーは死んでいるべき");
        assertFalse(enemy.isDead(), "敵は生存しているべき");
        assertFalse(result.battleLog().isEmpty(), "バトルログが記録されているべき");
    }

    @Test
    void testBothDie() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(50, 50),
                        new Power(50),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵"),
                        new Health(50, 50),
                        new Power(50),
                        new Stamina(20, 20));

        var battle = new Battle(new BattleId(1L), player, enemy);
        var result = battle.execute();

        assertTrue(player.isDead(), "プレイヤーは死んでいるべき");
        assertTrue(enemy.isDead(), "敵は死んでいるべき");
        assertFalse(result.battleLog().isEmpty(), "バトルログが記録されているべき");
    }

    @Test
    void testMaxTurnReached() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(1000, 1000),
                        new Power(10),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵"),
                        new Health(1000, 1000),
                        new Power(10),
                        new Stamina(20, 20));

        var battle = new Battle(new BattleId(1L), player, enemy);
        var result = battle.execute();

        assertFalse(player.isDead(), "プレイヤーは生存しているべき");
        assertFalse(enemy.isDead(), "敵は生存しているべき");
        assertEquals(10, result.battleLog().size(), "バトルログは10エントリであるべき");

        var lastTurn = result.battleLog().get(result.battleLog().size() - 1).turn();
        assertEquals(10, lastTurn, "最終ターンは10であるべき");
    }

    @Test
    void testSamePlayerThrowsException() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(100, 100),
                        new Power(10),
                        new Stamina(20, 20));
        var samePlayer =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("同じプレイヤー"),
                        new Health(100, 100),
                        new Power(10),
                        new Stamina(20, 20));

        var exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> new Battle(new BattleId(1L), player, samePlayer));

        assertTrue(exception.getMessage().contains("同じプレイヤー同士では戦闘を行えません"));
    }

    @Test
    void testDeadPlayerThrowsException() {
        var deadPlayer =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("死んだプレイヤー"),
                        new Health(0, 100),
                        new Power(10),
                        new Stamina(20, 20));
        var enemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("敵"),
                        new Health(100, 100),
                        new Power(10),
                        new Stamina(20, 20));

        var exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> new Battle(new BattleId(1L), deadPlayer, enemy));

        assertTrue(exception.getMessage().contains("戦闘に死者が参加しています"));
    }

    @Test
    void testDeadEnemyThrowsException() {
        var player =
                new Player(
                        new PlayerId(1L),
                        new PlayerName("プレイヤー"),
                        new Health(100, 100),
                        new Power(10),
                        new Stamina(20, 20));
        var deadEnemy =
                new Player(
                        new PlayerId(2L),
                        new PlayerName("死んだ敵"),
                        new Health(0, 100),
                        new Power(10),
                        new Stamina(20, 20));

        var exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> new Battle(new BattleId(1L), player, deadEnemy));

        assertTrue(exception.getMessage().contains("戦闘に死者が参加しています"));
    }
}
