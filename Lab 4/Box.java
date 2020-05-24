class Box<T> {
    private final T t;
    private static final  Box<? extends Object> EMPTY_BOX = new Box<>(null);

    private Box(T t) {
        this.t = t;
    }

    public T get() { 
        return this.t;
    }

    boolean isPresent() {
        return !this.equals(Box.empty());
    }

    static <U> Box<U> of(U u) { // returns null if a null is given
        return (u == null ? null : new Box<>(u));
    }

    static <U> Box<U> ofNullable(U u) { // when input a null, a Boxed null will be returned instead
        return (u == null ? Box.empty() : new Box<U>(u));
    }
    
    @SuppressWarnings("unchecked")
    static <U> Box<U> empty() {
        return (Box<U>)EMPTY_BOX;
    }

    Box<T> filter(BooleanCondition<? super T> f) {
        return (this.t == null || !f.test(this.t) ? Box.empty() : this);
    }

    <U> Box<U> map(Transformer<? super T,? extends U> t) {
        return (this.equals(Box.empty()) || this.t == null) ? 
                                                Box.empty() : Box.ofNullable(t.transform(this.t));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Box) {
            Box<T> o = (Box<T>) obj;
            if (this.t == null || o.t == null) {
                return this == Box.empty() && o == Box.empty();
            } else {
                return this.t.equals((o.t));
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.t == null ? "[]" : String.format("[%s]", this.t);
    }

}
