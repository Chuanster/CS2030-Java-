import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

class EmptyList<T> extends InfiniteListImpl<T> {

    public EmptyList() {
        super();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public EmptyList<T> peek() {
        return this;
    }
    
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
    
    @Override
    public void forEach(Consumer<? super T> action) {
    }
    
    @Override
    public EmptyList<T> filter(Predicate<? super T> p) {
        return this; 
    }
    
    @Override
    public <S> EmptyList<S> map(Function<? super T, ? extends S> mapper) {
        return new EmptyList<>();
    }
    
    @Override
    public long count() {
        return 0;
    }
    
    @Override
    public EmptyList<T> takeWhile(Predicate<? super T> p) {
        return this;   
    }
    
    @Override
    public EmptyList<T> limit(long maxSize) {
        return this;
    }
}
