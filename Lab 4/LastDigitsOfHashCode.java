class LastDigitsOfHashCode implements Transformer<Object,Integer> {
    private final Integer k;

    LastDigitsOfHashCode(Integer k) {
        this.k = k;
    }

    @Override
    public Integer transform(Object t) {
        int mul = (int)Math.pow(10, (int)this.k);
        return Math.abs(t.hashCode() % mul);
    }
}
