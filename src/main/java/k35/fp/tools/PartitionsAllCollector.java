package k35.fp.tools;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PartitionsAllCollector<T>
        implements Collector<T, PartitionsAllAccumulator<T>, List<List<T>>> {

    private final long size;

    public PartitionsAllCollector(long size) {
        this.size = size;
    }

    @Override
    public Supplier<PartitionsAllAccumulator<T>> supplier() {
        return () -> new PartitionsAllAccumulator<T>(size);
    }

    @Override
    public BiConsumer<PartitionsAllAccumulator<T>, T> accumulator() {
        return PartitionsAllAccumulator::add;
    }

    @Override
    public BinaryOperator<PartitionsAllAccumulator<T>> combiner() {
        return PartitionsAllAccumulator::combine;
    }

    @Override
    public Function<PartitionsAllAccumulator<T>, List<List<T>>> finisher() {
        return PartitionsAllAccumulator::getList;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Collector.Characteristics.CONCURRENT));
    }

}
