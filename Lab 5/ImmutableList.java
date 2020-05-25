import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

class ImmutableList<T> {
    private final List<T> items;

    public ImmutableList(List<T> items) {
        this.items = new ArrayList<>(items); 
    }

    @SafeVarargs
    public ImmutableList(T...items) {
        this.items = Arrays.asList(items);
    }

    ImmutableList<T> add(T element) {
        List<T> newList = new ArrayList<>(this.items);
        newList.add(element);
        return new ImmutableList<T>(newList); 
    }

    ImmutableList<T> remove(T element) {
        List<T> newList = new ArrayList<>(this.items);
        newList.remove(element);
        return new ImmutableList<T>(newList); 
    }
    
    ImmutableList<T> replace(T a, T b) {
        List<T> newList = new ArrayList<>();
        for (T item: this.items) {
            newList.add(item == a ? b : item);
        }
        return new ImmutableList<T>(newList); 
    }
    
    ImmutableList<T> filter(Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T item: this.items) {
            if (predicate.test(item)) {
                newList.add(item);
            }
        }
        return new ImmutableList<T>(newList); 
    }
    
    <U> ImmutableList<U> map(Function<? super T, ? extends U> f) {
        List<U> newList = new ArrayList<>();
        for (T item: this.items) {
            newList.add(f.apply(item));
        }
        return new ImmutableList<>(newList); 
    }
    
    ImmutableList<T> limit(long len) {
        if (len < 0) {
            throw new IllegalArgumentException("limit size < 0");
            // catch unchecked exceptions for the purpose of testing only
        } else {
            List<T> newList = new ArrayList<>();
            for (int i = 0; i < Math.min(len, this.items.size());i++) {
                newList.add(this.items.get(i));
            }
            return new ImmutableList<>(newList);
        }
    }
    
    public ImmutableList<T> sorted() {
        if (items.size() == 0 || items.get(0) instanceof Comparable) {
            @SuppressWarnings("unchecked")
            T[] arr = (T[]) items.toArray();
            Arrays.sort(arr);
            return new ImmutableList<>(arr);
        } else {
            throw new IllegalStateException("List elements do not implement Comparable");
        }
    }
    
    public ImmutableList<T> sorted(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        } else { 
            ArrayList<T> newList = new ArrayList<>(this.items);
            newList.sort(comparator);
            return new ImmutableList<T>(newList);
        }
    }

    public Object[] toArray() {
        return this.items.toArray();
    }

    public <T> T[] toArray(T[] arr) {
        if (arr == null) {
            throw new NullPointerException("Input array cannot be null");
        } else {
            try {
                return this.items.toArray(arr);
            } catch (ArrayStoreException e) {
                throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
            }
        }
    }

    @Override
    public String toString() {
        return this.items.toString();   
    }
}
