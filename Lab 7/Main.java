import java.util.stream.Stream;

import java.util.stream.Stream;
import java.util.stream.IntStream;

class Main {

    public static boolean isPrime(int num) {
        return IntStream.rangeClosed(2, (int)num / 2).noneMatch(x -> num % x == 0); 
    }

    public static int[] twinPrimes(int a) {
        return IntStream.rangeClosed(3,a)
                .filter(x -> Main.isPrime(x) && (isPrime(x - 2) || isPrime(x + 2)))
                .toArray();
    }

    public static int gcd(int a, int b) {
        return Stream.iterate(new Pair(a,b), x -> x.next())
                .filter(x -> x.num2 == 0)
                .findFirst()
                .get()
                .num1;
    }
    
    public static long countRepeats(int... arr) {
        return IntStream.range(0, arr.length)
            .filter(x -> x > 1 ? arr[x] != arr[x - 2] : true)
            .filter(x -> x > 0 ? arr[x] == arr[x - 1] : false)
            .count();
    }
    
    public static double normalizedMean(Stream<Integer> stream) {
        double ans[] = stream.map(x -> new double[]{x,x,x, 1})
                            .reduce((x,y) -> new double[] {x[0] + y[0], y[1] > x[1] ? y[1] : x[1], y[2] < x[2] ? y[2] : x[2], x[3] + y[3]})
                            .orElse(new double[] {0,0,0,0});
        return ans[1] == ans[2] ? 0.0 : (ans[0] / ans[3] - ans[2]) / (ans[1] - ans[2]);
    }
}
