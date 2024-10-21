package k35.fp.tools;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.junit.Test;

public class PartitionsAllCollectorTest {

    private final BinaryOperator<Integer> add = (a, b) -> a + b;

    @Test
    public void commonTest() {

        final var size = 187197;
        final var partSize = 10000;

        final var list = Stream.iterate(0, i -> i + 1).limit(size).toList();

        final var sum1 = list.stream().reduce(0, add);

        final var parts = list
                .stream()
                .parallel()
                .collect(new PartitionsAllCollector<>(partSize));

        final var sum2 = parts.stream().map(part -> part.stream().reduce(0, add)).reduce(0, add);
        assertEquals(sum1, sum2);

        final var count = parts.stream().map(List::size).reduce(0, add);
        assertEquals(count.intValue(), size);

        assertEquals(size / partSize + (size % partSize > 0 ? 1 : 0), parts.stream().toList().size());
    }
}
