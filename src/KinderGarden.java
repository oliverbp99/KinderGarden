import java.util.*;

public class KinderGarden {
    public static void main(String[] args) throws FileNotFoundException {
        File pedaFile = new File("./src/Pedagogue.txt");
        File employeeFile = new File("./src/Employee.txt");
        File leaderFile = new File("./src/Leader.txt");

        Scanner input = new Scanner(System.in);

        Children[] childrenArr = new Children[60];
        Parent[] parentArr = new Parent[120];

        ArrayList<Pedagogue> pedaList = new ArrayList<>();
        ArrayList<Leader> leaderList = new ArrayList<>();

        mainMenu(pedaFile, input, childrenArr, pedaList, parentArr);



    }
        public static void mainMenu(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList,  Parent[] parentArr) throws FileNotFoundException {
            System.out.println("Welcome to Roskilde frie Børnehave:");
            System.out.println("1. Children And Parents");
            System.out.println("2. Phonelist");
            System.out.println("3. List of employees");
            System.out.println("4. Waitlist");
            System.out.println("5. Write to file");
            System.out.println("6. Exit");
            int mainOption = scan.nextInt();
            if(mainOption == 1){
                showMembers(f, scan, childrenArr, pedaList, parentArr);
            }else if(mainOption == 5){
                //writeChildrenToFile(childrenArr);
                //writePedagogueToFile(f, pedaList);
                writeParentsToFile(parentArr);
            }
        }

    private static void showMembers(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr) throws FileNotFoundException{
        System.out.println(Arrays.toString(childrenArr));
        System.out.println("Chose what you want to create");
        System.out.println("1. Create Child\n2. Create Pedagogue\n3. Create Parent\n4. Back");
        int ans = input.nextInt();
        if(ans == 1) {
            createChildren(f, input, childrenArr, pedaList, parentArr);
        }else if(ans == 2) {
            createPedagogue(f, input, childrenArr, pedaList, parentArr);
        }else if(ans == 3){
            createParent(f, input, childrenArr, pedaList, parentArr);
        }else if(ans == 4){
            mainMenu(f, input, childrenArr, pedaList,  parentArr);
        }
    }

    public static void createChildren(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList,  Parent[] parentArr) throws FileNotFoundException {
        System.out.println("Enter the firstname of the Children: ");
        String fn = scan.next();
        System.out.println("Enter the lastname of the Children: ");
        String ln = scan.next();
        System.out.println("Enter the age of the Children: ");
        int age = scan.nextInt();
        System.out.println("Assign the child to an area, Beefalo, Wholphin or Jaglion area: ");
        String ar = scan.next();
        Children c1 = new Children(fn, ln, age, ar);

        for(int i = 0; i < childrenArr.length - 1; i++) {
            if(childrenArr[i] == null) {
                childrenArr[i] = c1;
                break;
            }//Der skal tilføjes en LinkedList her, som bruges til vores Waitlist
            else if(i == childrenArr.length - 1){
                System.out.println("There is not enough room for more children, but you can sign up for the waitlist.\nPress 1 to sign up for the waitlist\nPress 2 to cancel");
            }
        }

        showMembers(f, scan, childrenArr, pedaList, parentArr);
    }
    public static void createPedagogue(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr) throws FileNotFoundException {
        System.out.println("Enter the firstname of the Pedagogue: ");
        String fn = input.next();
        System.out.println("Enter the lastname of the Pedagogue: ");
        String ln = input.next();
        System.out.println("Enter the phonenumber of the Pedagogue: ");
        int pn = input.nextInt();
        System.out.println("Enter the mail of the Pedagogue: ");
        String mail = input.next();
        System.out.println("Assign the Pedagogue to an area, Beefalo, Wholphin and Jaglion");
        String area = input.next();

        Pedagogue p1 = new Pedagogue(fn, ln, pn, mail, area);
        pedaList.add(p1);

        showMembers(f, input, childrenArr, pedaList, parentArr);
    }
    public static void createParent(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr) throws FileNotFoundException{
        System.out.println("Enter the name of the parent");
        String fn = input.next();
        System.out.println("Enter the lastname of the parent");
        String ln = input.next();
        System.out.println("Enter the phonenumber of the parent");
        int phonenumber = input.nextInt();
        System.out.println("Enter the mail address of the parent");
        String mail = input.next();
        System.out.println("Enter the firstname of the children, that you want the parent to be attached to: ");
        String childFn = input.next();
        System.out.println("Enter the lastname of the children, that you want the parent to be attached to: ");
        String childLn = input.next();
        for(int i = 0; i <= childrenArr.length - 1; i++) {
            if(childrenArr[i].getFirstName().equalsIgnoreCase(childFn) && childrenArr[i].getLastName().equalsIgnoreCase(childLn)) {
                Parent p1 = new Parent(fn, ln, phonenumber, mail);
                for(int k = 0; k < parentArr.length - 1; k++) {
                    if(parentArr[k] == null) {
                        p1.setChildren(childrenArr[i]);
                        parentArr[k] = p1;
                        break;
                    }else if(parentArr[k] != null){
                        System.out.println("The list of parents is full");
                    }
                }
            }
            break;
        }

        showMembers(f, input, childrenArr, pedaList, parentArr);
    }

    //24-02-2021 - Hashmap
    public void phoneList() {

    }

    public static void writePedagogueToFile(File f, ArrayList<Pedagogue> pedagogueList){
        try{
            FileWriter writer = new FileWriter(f, true);
            for(int i = 0; i <= pedagogueList.size() - 1; i++ ){
                writer.write(pedagogueList.get(i).getFirstName() + " " + pedagogueList.get(i).getLastName() + " " + pedagogueList.get(i).getPhoneNumber() + " " + pedagogueList.get(i).getMail() + " " + pedagogueList.get(i).getArea() + " ");
                if(i != pedagogueList.size() - 1){
                    System.out.println("\n");
                }
            }
            writer.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void readPedagogueFromFile(ArrayList<Pedagogue> pedaList){
        try{
            File f = new File("./src/Pedagogue.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int phoneNumber = lineScan.nextInt();
                String mail = lineScan.next();
                String area = lineScan.next();
                Pedagogue pedas = new Pedagogue(firstName, lastName, phoneNumber, mail,area);
                pedaList.add(pedas);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLeaderToFile(ArrayList<Leader> leaderList){
        try{
            File f = new File("./src/Leader.txt");
            FileWriter writer = new FileWriter(f);
            for(int i = 0; i <= leaderList.size() -1; i++ ){
                writer.write(leaderList.get(i).getFirstName() + " " + leaderList.get(i).getLastName() + " " + leaderList.get(i).getPhoneNumber() + " " + leaderList.get(i).getMail());
                writer.close();
                if(i != leaderList.size() - 1){
                    writer.write("\n");
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void readLeaderFromFile(ArrayList<Leader> leaderList) throws FileNotFoundException{
        try{
            File f = new File("./src/Leader.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int phoneNumber = lineScan.nextInt();
                String mail = lineScan.next();
                Leader pedas = new Leader(firstName, lastName, phoneNumber, mail);
                leaderList.add(pedas);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeChildrenToFile(Children[] childrenArr){
        int i = 0;
        try{
            File f = new File("./src/Children.txt");
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter br = new BufferedWriter(fileWriter);

           while(childrenArr[i] != null){
                br.write(childrenArr[i].getFirstName() + " " + childrenArr[i].getLastName() + " " + childrenArr[i].getAge() + " " + childrenArr[i].getArea() + "\n");
                i++;
            }
            br.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void readChildrenFromFile(Children[] childArr) throws FileNotFoundException{
        try{
            File f = new File("./src/Children.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int age = lineScan.nextInt();
                String area = lineScan.next();
                Children child = new Children(firstName, lastName, age, area);
                for(int i = 0; i < childArr.length; i++) {
                    if (childArr[i] == null) {
                        childArr[i] = child;
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeParentsToFile(Parent[] parentArr) throws FileNotFoundException{
        int i = 0;
        try{
            File f = new File("./src/Parent.txt");
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter br = new BufferedWriter(fileWriter);

            while(parentArr[i] != null){
                br.write(parentArr[i].getFirstName() + " " + parentArr[i].getLastName() + " " + parentArr[i].getPhoneNumber() + " " + parentArr[i].getMail() + " " + parentArr[i].getChildren() + "\n");
                i++;
            }
            br.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void readParentsFromFile(Parent[] parentArr) throws FileNotFoundException{
       //try{
            File f = new File("./src/Parent.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int phoneNumber = lineScan.nextInt();
                String mail = lineScan.next();
                String children = lineScan.next();
                String childFn = lineScan.next();
                String childLn = lineScan.next();
                int age = lineScan.nextInt();
                String area = lineScan.next();
                Children child = new Children(childFn, childLn, age, area);
                Parent parent = new Parent (firstName, lastName, phoneNumber, mail, child);
                for(int i = 0; i < parentArr.length; i++) {
                    if (parentArr[i] == null) {
                        parentArr[i] = parent;
                    }
                }
        //}catch(Exception e) {
          //  e.printStackTrace();
        }
    }

    /*public static void writeChildrenToFile(Children[] childrenArr){
        try{
            File f = new File("Children.txt");
            FileWriter writer = new FileWriter(f, true);
            BufferedWriter buff = new BufferedWriter();
            for(int i = 0; i <= childrenArr.length - 1; i++ ){
                //writer.write(childrenArr[i].getFirstName() + " " + childrenArr[i].getLastName() + " " + childrenArr[i].getArea() + " ");
                writer.write(childrenArr[i].getFirstName());
              /*  if(i != childrenArr.length - 1){
                    writer.write("\n");
                }
            }
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }*/
}
