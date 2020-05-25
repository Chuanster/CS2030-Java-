import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

interface InfiniteList<T> {

    Optional<T> get();

    public static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
        return InfiniteListImpl.generate(s);
    } 

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(seed, f);
    } 

    public InfiniteList<T> limit(long maxSize);

    public InfiniteList<T> filter(Predicate<? super T> p);
    
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapperlimit);
    
    public Optional<T> reduce(BinaryOperator<T> accumulator);
    
    public T reduce(T id, BinaryOperator<T> accumulator);

    public Object[] toArray();
    
    public void forEach(Consumer<? super T> action);
    
    public long count();
}
