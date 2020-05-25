class Pair {
    public final int num1;
    public final int num2;
    
    Pair(int a, int b) {
        this.num1 = a;
        this.num2 = b;
    }

    public Pair next() {
        return new Pair(num2, num1 % num2); 
    }
}
