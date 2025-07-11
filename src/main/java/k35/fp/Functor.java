package k35.fp;

import k35.fp.tools.FP;

import java.util.function.Function;

/**
 * Апликативный функтор
 *
 * @param <A>
 * @param <B>
 */
public final class Functor<A, B> {

    public final Function<A, B> transform;

    private Functor(Function<A, B> transform) {
        this.transform = transform;
    }

    public static <A, B> Functor<A, B> of(Function<A, B> transform) {
        return new Functor<A, B>(transform);
    }

    public <C> Functor<A, C> map(Function<B, C> transform) {
        return new Functor<A, C>(FP.compose2(this.transform, transform));
    }
}
