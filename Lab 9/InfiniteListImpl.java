import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Lazy<InfiniteListImpl<T>> tail;

    private InfiniteListImpl(Lazy<T> head, Lazy<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    InfiniteListImpl() {
        this.head = Lazy.ofNullable(null);
        this.tail = Lazy.generate(() -> new EmptyList<T>());
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new InfiniteListImpl<T>(
            Lazy.generate(() -> s.get()),
            Lazy.generate(() -> InfiniteListImpl.generate(s)));
    }
    
    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        return new InfiniteListImpl<T>(
            Lazy.ofNullable(seed),
            Lazy.generate(() -> InfiniteListImpl.iterate(next.apply(seed), next)));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> curr = this;
        while (!curr.isEmpty()) {
            curr.head.get().ifPresent(action);
            curr = curr.tail.get().get();
        }
    }
 
    @Override
    public InfiniteListImpl<T> peek() {
        this.head.get().ifPresent(System.out::println);
        return this.tail.get().orElseThrow();
    }

    @Override
    public <S> InfiniteListImpl<S> map(Function<? super T, ? extends S> mapper) {
        Lazy<S> newHead = this.head.map(mapper);
        Lazy<InfiniteListImpl<S>> newTail = Lazy.generate(() -> this.tail.get().orElseThrow().map(mapper));
        return new InfiniteListImpl<S>(newHead, newTail);
    }
    
    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> p) {
        return new InfiniteListImpl<T>(
            this.head.filter(p),
            Lazy.generate(() ->  this.tail.get().orElseThrow().filter(p))
        );
    }

    @Override
    public Object[] toArray() {
        List<Object> l = new ArrayList<>();
        forEach(x -> l.add(x));
        return l.toArray();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<T>();
        } else if (n == 1) {
            return new InfiniteListImpl<T>(head, 
                    Lazy.generate(() -> head.get().isPresent() 
                        ? new EmptyList<T>() 
                        : tail.get().orElseThrow().limit(n)));
        } else {
            return new InfiniteListImpl<T>(head, 
                    tail.map(x -> head.get().isPresent() 
                        ? x.limit(n - 1) 
                        : x.limit(n)));
        }
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);
        return new InfiniteListImpl<T>(newHead,
                Lazy.generate(() -> head.get().isPresent() && newHead.get().isEmpty()
                    ? new EmptyList<T>()
                    : tail.get().orElseThrow().takeWhile(predicate)));
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        InfiniteListImpl<T> curr = this;
        U result = identity;
        while (!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (head.isPresent()) {
                result = accumulator.apply(result, head.orElseThrow());
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> result = this.head.get();
        InfiniteListImpl<T> xs = this.tail.get().orElseThrow();
        while (!xs.isEmpty()) {
            Optional<T> head = xs.head.get();
            if (result.isEmpty()) {
                result = head;
            } else if (head.isPresent()) {
                result = Optional.ofNullable(accumulator.apply(result.orElseThrow(), 
                        head.orElseThrow()));
            }
            xs = xs.tail.get().orElseThrow();
        }
        return result;
    }

    @Override
    public long count() {
        return map(x -> 1).reduce((x, y) -> x + y).orElse(0);
    }
} 
