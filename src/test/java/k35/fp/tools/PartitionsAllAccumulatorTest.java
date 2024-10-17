package k35.fp.tools;

import static org.junit.Assert.assertEquals;

import java.util.function.BinaryOperator;

import org.junit.Test;

public class PartitionsAllAccumulatorTest {

    private final BinaryOperator<Integer> add = (a, b) -> a + b;

    @Test
    public void testAdd() {

        final var size = 10;
        Integer sum1 = 0;

        final var acc = new PartitionsAllAccumulator<Integer>(size);

        assertEquals(0, acc.getList().size());

        for (int i = 0; i < 33; i++) {
            acc.add(i);
            sum1 += i;
        }

        assertEquals(4, acc.getList().size());

        final var accSumm = acc.getList().stream().map(part -> part.stream().reduce(0, add)).reduce(0, add);

        assertEquals(sum1, accSumm);

    }

    @Test
    public void testCombine() {

        final var itemsCount = 75879;

        final var size1 = 10;
        final var size2 = 5;

        final var acc1 = new PartitionsAllAccumulator<Integer>(size1);
        final var acc2 = new PartitionsAllAccumulator<Integer>(size2);

        Integer sum = 0;
        for (int i = 0; i < itemsCount; i++) {
            acc1.add(i);
            sum += i;

            acc2.add(i);
            sum += i;
        }

        acc1.combine(acc2);

        assertEquals(acc1.getList().size(), (itemsCount * 2) / size1 + (itemsCount % size1 == 0 ? 0 : 1));

        final var sumAcc1 = acc1.getList().stream().map(part -> part.stream().reduce(0, add)).reduce(0, add);

        assertEquals(sum, sumAcc1);

    }

}
