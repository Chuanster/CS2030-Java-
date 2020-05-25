import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Roster r = new Roster("AY2019");

        for (int i = 0; i < num; i++) {
            r.put(sc.next(), sc.next(), sc.next(), sc.next()); 
        }

        while (sc.hasNext()) {
            try {
                System.out.println(r.getGrade(sc.next(), sc.next(), sc.next()));
            } catch (NoSuchRecordException e) {
                System.out.println("NoSuchRecordException: " + e.getMessage());
            }
        }
        sc.close();
    }

}
