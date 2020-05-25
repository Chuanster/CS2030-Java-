import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Roster r = new Roster("AY2019");
    
        // to register names, modules and assessments 
        for (int i = 0; i < num; i++) {
            String name = sc.next();
            if (r.get(name) == null) {
                Module mod = new Module(sc.next()).put(new Assessment(sc.next(), sc.next()));
                r.put(new Student(name).put(mod));
            } else {
                String modName = sc.next();
                if (r.get(name).get(modName) == null) {
                    r.get(name).put(new Module(modName).put(new Assessment(sc.next(), sc.next())));
                } else {
                    r.get(name).get(modName).put(new Assessment(sc.next(), sc.next()));
                }
            }
        }

        // assessing datas created above
        while (sc.hasNext()) {
            try {
                System.out.println(r.getGrade(sc.next(), sc.next(), sc.next()));  // r.getGrade(name of the student, module name, assessment name);
            } catch (NoSuchRecordException e) {                                   // if there is no such record in the data base
                System.out.println("NoSuchRecordException: " + e.getMessage());
            }
        }
        sc.close();
    }
}
