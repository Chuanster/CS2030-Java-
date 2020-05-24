public class DivisibleBy implements BooleanCondition<Integer> {
    private final int divisor;

    public DivisibleBy(int divisor) {
        this.divisor = divisor;
    }

    @Override
    // check if the Boxed integer value is divisible by a given value
    public boolean test(Integer dividend) { 
        return dividend % this.divisor == 0;
    }
}
