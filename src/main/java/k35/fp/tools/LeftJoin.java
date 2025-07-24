package k35.fp.tools;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class LeftJoin<L, R> {

    public record Item<A, B>(A left, B right) {

        public boolean isHaveRight() {
            return right != null;
        }

        public Optional<B> rightOptional() {
            return Optional.ofNullable(right);
        }
    }

    private final List<L> rightItems;
    private final List<R> leftItems;

    private LeftJoin(List<L> rightList, List<R> leftItems) {
        this.rightItems = rightList;
        this.leftItems = leftItems;
    }

    public <A, B> LeftJoin<A, B> of(List<A> rightList, List<B> leftList) {
        return new LeftJoin<>(rightList, leftList);
    }

    public Stream<Item<L, R>> on(BiPredicate<L, R> predicate) {
        return rightItems.stream().map(r -> {
            final var b = leftItems.stream().filter(l -> predicate.test(r, l)).findFirst().orElse(null);
            return new Item(r, b);
        });
    }

}
