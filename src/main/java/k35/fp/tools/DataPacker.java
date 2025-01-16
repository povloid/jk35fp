package k35.fp.tools;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class DataPacker<B> {

	private final List<B> boxes;

	private DataPacker(List<B> box) {
		this.boxes = box;
	}

	public static <T> DataPacker<T> of(List<T> boxes) {
		return new DataPacker<>(boxes);

	}

	/**
	 * Точно дополнить записи
	 * 
	 * @param <I>
	 * @param getItems
	 * @param on
	 * @param setAs
	 * @return
	 */
	public <I> DataPacker<B> innerJoinOneToOne(
			Function<List<B>, List<I>> getItems,
			BiPredicate<B, I> on,
			BiFunction<B, I, B> setAs) {

		final var items = getItems.apply(this.boxes);

		return innerJoinOneToOne(items, on, setAs);
	}

	/**
	 * Точно дополнить записи
	 * 
	 * @param <I>
	 * @param getItems
	 * @param on
	 * @param setAs
	 * @return
	 */
	public <I> DataPacker<B> innerJoinOneToOne(
			List<I> items,
			BiPredicate<B, I> on,
			BiFunction<B, I, B> setAs) {

		final var newBoxes = this.boxes.stream()
				.map(box -> {
					for (final I item : items) {
						if (on.test(box, item))
							return setAs.apply(box, item);
					}

					return null;
				})
				.filter(o -> o != null)
				.toList();

		return new DataPacker<B>(newBoxes);
	}

	/**
	 * Точно дополнить записи
	 * 
	 * @param <I>
	 * @param getItems
	 * @param on
	 * @param setAs
	 * @return
	 */
	public <I> DataPacker<B> leftJoinOneToOne(
			Function<List<B>, List<I>> getItems,
			BiPredicate<B, I> on,
			BiFunction<B, Optional<I>, B> setAs) {

		final var items = getItems.apply(this.boxes);

		return leftJoinOneToOne(items, on, setAs);
	}

	/**
	 * Точно дополнить записи
	 * 
	 * @param <I>
	 * @param getItems
	 * @param on
	 * @param setAs
	 * @return
	 */
	public <I> DataPacker<B> leftJoinOneToOne(
			List<I> items,
			BiPredicate<B, I> on,
			BiFunction<B, Optional<I>, B> setAs) {

		final var newBoxes = this.boxes.stream()
				.map(box -> {
					for (final I item : items) {
						if (on.test(box, item))
							return setAs.apply(box, Optional.ofNullable(item));
					}

					return box;
				})
				.toList();

		return new DataPacker<B>(newBoxes);
	}
}
