package dev.kaseitako.requestsaga.shared.domain;

/** プレイヤーのリソースを表す基底インターフェース */
public interface Resource<T extends Resource<T>> {

    int current();

    int max();

    default void ensureResourceValid(int current, int max) {
        if (current >= 0 && max > 0 && current <= max) {
            return;
        }
        throw new IllegalArgumentException("%s の初期化に失敗しました".formatted(getClass().getSimpleName()));
    }

    default int percentage() {
        if (max() == 0) return 0;
        return (current() * 100) / max();
    }

    default boolean isFull() {
        return current() >= max();
    }

    default boolean isEmpty() {
        return current() == 0;
    }

    default boolean canAfford(int amount) {
        return current() >= amount;
    }
}
