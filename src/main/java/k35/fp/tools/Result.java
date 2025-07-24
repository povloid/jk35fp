package k35.fp.tools;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Result contatiner
 *
 * @param value
 * @param throwable
 * @param isSuccess
 * @param <V>
 * @param <E>
 */
public record Result<V, E extends Throwable>(
        V value,
        E throwable,
        boolean isSuccess) {

    public static <V, E extends Throwable> Result<V, E> success(V value) {
        return new Result<>(value, null, true);
    }

    public static <V, E extends Throwable> Result<V, E> failure(E throwable) {
        return new Result<>(null, throwable, false);
    }

    public <R> Optional<R> mapSuccess(Function<V, R> fn) {
        return this.isSuccess ? Optional.ofNullable(this.value).map(fn)
                : Optional.empty();
    }

    public <R> Optional<R> mapFailure(Function<E, R> fn) {
        return this.isSuccess ? Optional.empty()
                : Optional.ofNullable(this.throwable).map(fn);
    }

    public <R> R map(Function<V, R> successFn,
                     Function<E, R> failureFn) {
        return this.isSuccess ? successFn.apply(this.value)
                : failureFn.apply(this.throwable);
    }

    public void ifSuccess(Consumer<? super V> action) {
        if (this.isSuccess) {
            action.accept(this.value);
        }
    }

    public void ifFailure(Consumer<? super E> action) {
        if (!this.isSuccess) {
            action.accept(this.throwable);
        }
    }

    public void handle(Consumer<? super V> successAction,
                       Consumer<? super E> failureAction) {
        if (this.isSuccess) {
            successAction.accept(this.value);
        } else {
            failureAction.accept(this.throwable);
        }
    }

    public V orElse(V other) {
        return this.isSuccess ? this.value
                : other;
    }

    public V orElseGet(Supplier<? extends V> otherSupplier) {
        return this.isSuccess ? this.value
                : otherSupplier.get();
    }

    private <E2 extends Throwable> void sneakyThrow(Throwable e) throws E2 {
        throw (E2) e;
    }

    public V orElseThrow() {
        if (!this.isSuccess) {
            sneakyThrow(this.throwable);
            return null;
        }
        return this.value;
    }

    public boolean isFailure() {
        return !this.isSuccess;
    }
}
