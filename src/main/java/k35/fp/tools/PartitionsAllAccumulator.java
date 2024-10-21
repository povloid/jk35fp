package k35.fp.tools;

import java.util.ArrayList;
import java.util.List;

public final class PartitionsAllAccumulator<T> {
    private final int size;

    private final List<List<T>> accumulator = new ArrayList<>();
    private List<T> part;

    PartitionsAllAccumulator(int size) {
        this.size = size;
    }

    public synchronized void add(T item) {
        if (part == null || size == part.size()) {
            this.part = new ArrayList<>(this.size);
            this.accumulator.add(part);
        }

        part.add(item);
    }

    public List<List<T>> getList() {
        return accumulator;
    }

    public PartitionsAllAccumulator<T> combine(PartitionsAllAccumulator<T> otherPartList) {
        otherPartList.getList().forEach(otherPart -> otherPart.forEach(this::add));
        return this;
    }

}
