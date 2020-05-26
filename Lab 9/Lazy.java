import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

class Lazy<T> {
    private T v;
    private Supplier<T> s;
    private boolean computed;
    
    Lazy(T v) {
        this.v = v;
        this.s = () -> v;
        this.computed = true;
    }

    Lazy(Supplier<T> s) {
        this.v = null;
        this.s = s;
        this.computed = false;
    }

    static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v);
    }

    Optional<T> get() {
        if (!this.computed) {
            this.computed = true;
            this.v = s.get();
            s = () -> v;
        }
        return Optional.ofNullable(this.v);
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<T>(() -> supplier.get());
    }
    
    <R> Lazy<R> map(Function<? super T,? extends R> mapper) {
        return new Lazy<R>(() -> this.get().map(mapper).orElse(null));
    }
    
    Lazy<T> filter(Predicate<? super T> predicate) {
        return new Lazy<T>(() -> this.get().filter(predicate).orElse(null));
    }
    
    @Override
    public String toString() {
        return String.format("%s", this.computed ? this.v : "?");
    }
}
