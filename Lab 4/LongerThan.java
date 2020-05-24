public class LongerThan implements BooleanCondition<String> {
    private final int length;

    public LongerThan(int length) {
        this.length = length;
    }

    @Override
    // chceck if the length of the Boxed string is longer than the value given
    public boolean test(String s) { 
        return s.length() > this.length;
    }
}
