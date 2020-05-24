import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static Cruise[] getCruise() {
        Scanner sc = new Scanner(System.in);
        int numOfCruise = sc.nextInt();
        Cruise[] cruises = new Cruise[numOfCruise];
        for (int i = 0; i < numOfCruise; i++) {
            String id = sc.next();
            if (id.charAt(0) == 'B') {
                cruises[i] = new BigCruise(id, sc.nextInt(), sc.nextInt(), sc.nextInt());
            } else {
                cruises[i] = new SmallCruise(id, sc.nextInt());
            }
        }
        sc.close();
        return cruises;
    }

    static Loader idleLoader(ArrayList<Loader> loaders, Cruise c) {
        for (Loader l : loaders) {
            if (l.canServe(c)) {
                return l;
            }
        }
        return null;
    }

    static int numOfLoaderAvailable(ArrayList<Loader> loaders, Cruise c) {
        int x = 0;
        for (Loader l: loaders) {
            if (l.canServe(c)) {
                x++;
            }
        }
        return x;
    }

    static void purchaseLoader(ArrayList<Loader> loaders, int num) {
        for (int i = 0; i < num;i++) {
            loaders.add(new Loader(loaders.size() + 1));
        }
    }

    public static void main(String[] args) {
        Cruise[] cruises = Main.getCruise();
        ArrayList<Loader> loaders = new ArrayList<>();

        for (Cruise c:cruises) {
            int loadersNeeded = c.getNumOfLoadersRequired();
            int loadersToPurchase = loadersNeeded - Main.numOfLoaderAvailable(loaders, c);
            if (loadersToPurchase > 0) {
                Main.purchaseLoader(loaders,loadersToPurchase);
            }
            for (int i = 0; i < loadersNeeded;i++) {
                Loader l = Main.idleLoader(loaders, c);
                l = l.serve(c);
                loaders.set(l.getID() - 1, l);
                System.out.println(l);
            }
        }
    }
}
