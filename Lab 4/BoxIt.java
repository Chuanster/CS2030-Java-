class BoxIt<T> implements Transformer<T,Box<T>> {
    @Override
    public Box<T> transform(T t) { // turning a Boxed value of type T to type U
        return Box.ofNullable(t);
    } 
}
