import java.util.Scanner;
import java.util.ArrayList;

/* 
To make minimum changes to Main.java to add in a new type of Loader(RecycledLoader),
which will take a 60 min break for maintainence after serving a cruise
*/


class Main6 {
    // to purchase loaders or recycled loaders 
    static void purchaseLoader(ArrayList<Loader> loaders, int num) { 
        for (int i = 0; i < num;i++) {
            int id = loaders.size() + 1;
            loaders.add(id % 3 == 0 ? new RecycledLoader(id) : new Loader(id));
        }
    }

    public static void main(String[] args) {
        Cruise[] cruises = Main.getCruise();
        ArrayList<Loader> loaders = new ArrayList<>();
        for (Cruise c:cruises) {
            int loadersNeeded = c.getNumOfLoadersRequired();
            int loadersToPurchase = loadersNeeded - Main.numOfLoaderAvailable(loaders, c);
            if (loadersToPurchase > 0) {
                Main6.purchaseLoader(loaders,loadersToPurchase);
            }
            for (int i = 0; i < loadersNeeded;i++) {
                Loader l = Main.idleLoader(loaders, c);
                l = l.serve(c);
                loaders.set(l.getID() - 1,l);
                System.out.println(l);
            }
        }
    }
}
