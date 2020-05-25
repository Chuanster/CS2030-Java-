import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class KeyableMap<T,K,V extends Keyable<K>> implements Keyable<T> {
    protected Map<K,V> map;
    protected T key;

    KeyableMap(T key) {
        this.key = key;
        this.map = new HashMap<>();
    }
    
    public Optional<V> get(K key) {
        return Optional.ofNullable(this.map.get(key));
    }
    
    public KeyableMap<T,K,V> put(V item) {
        this.map.put(item.getKey(), item);
        return this; 
    }

    @Override
    public T getKey() {
        return this.key;
    }
    
    @Override
    public String toString() {
        return (this.key + ": " + map.values()).replace("[","{").replace("]","}");
    }
} 
