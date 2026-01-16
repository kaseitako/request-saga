package dev.kaseitako.requestsaga.shared.domain;

public interface Parameter<T extends Parameter<T>> {
    int value();

    default void ensureParameterValid(int value) {
        if (value >= 0) {
            return;
        }
        throw new IllegalArgumentException("%s の初期化に失敗しました".formatted(getClass().getSimpleName()));
    }
}
