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

abstract class InfiniteListImpl<T> implements InfiniteList<T> {

    public abstract Optional<T> get();

    public abstract boolean hasNext();

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new InfiniteListImpl<T>() {

            public Optional<T> get() {
                return Optional.ofNullable(s.get());
            }

            public boolean hasNext() {
                return true;
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            private T ele = seed;
            private boolean first = true;

            public Optional<T> get() {
                if (first) {
                    first = false;
                } else {
                    ele = f.apply(ele);
                }
                return Optional.ofNullable(ele);
            }

            public boolean hasNext() {
                return true;
            }
        };
    }
    
    public InfiniteListImpl<T> limit(long maxSize) throws IllegalArgumentException {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.format("%d", maxSize));
        } else {
            return new InfiniteListImpl<T>() {
                private long count = 0;
                private long limit = maxSize;
            
                public Optional<T> get() {
                    Optional<T> curr = InfiniteListImpl.this.get();
                    if (count < limit && curr.isPresent()) {
                        count++;
                        return curr;
                    } else {
                        return Optional.empty();
                    }
                }

                public boolean hasNext() {
                    return InfiniteListImpl.this.hasNext() && count < limit;
                }
            };
        }
    }

    public Object[] toArray() {
        List<Object> l = new ArrayList<>();
        forEach(x -> l.add(x));
        return l.toArray();
    }

    public void forEach(Consumer<? super T> action) {
        while (hasNext()) {
            this.get().ifPresent(action);
        }
    }

    public <S> InfiniteListImpl<S> map(Function<? super T,? extends S> mapper) {
        return new InfiniteListImpl<S>() {

            public Optional<S> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }
    
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {

            public Optional<T> get() {
                return InfiniteListImpl.this.get().filter(predicate);
            }
            
            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> p) {
        return new InfiniteListImpl<T>() {
            private Optional<T> nex = InfiniteListImpl.this.get();

            public Optional<T> get() {
                Optional<T> c = nex;
                nex = InfiniteListImpl.this.get();
                return c;
            }

            public boolean hasNext() {
                return (InfiniteListImpl.this.hasNext() && nex.filter(p).isPresent());
            }
        };
    }
    
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> result = get();
        while (hasNext()) {
            result = result.map(x -> accumulator.apply(x, get().get()));
        }
        return result;
    }
    
    public T reduce(T id, BinaryOperator<T> accumulator) {
        T ans = id;
        while (hasNext()) {
            Optional<T> next = get();
            if (next.isPresent()) {
                ans = accumulator.apply(ans, next.get());
            }
        }
        return ans;
    }
 
    public long count() {
        return map(x -> 1).reduce(0, (x, y) -> x + y);
    }
}
