import java.util.Arrays;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class KinderGarden {
    public static void main(String[] args) throws FileNotFoundException {
        File pedaFile = new File("./src/Pedagogue.txt");

        Scanner input = new Scanner(System.in);

        Children[] childrenArr = new Children[60];
        Parent[] parentArr = new Parent[120];
        HashMap<Integer, String> phoneList = new HashMap<Integer, String>();
        ArrayList<Pedagogue> pedaList = new ArrayList<>();
        ArrayList<Children> childrenWaitList = new ArrayList<>();
        Leader[] leaderArr = new Leader[1];

        readPedagogueFromFile(pedaList);
        readChildrenFromFile(childrenArr);
        readLeaderFromFile(leaderArr);
        readParentsFromFile(parentArr);
        readPhoneListFromFile(phoneList);
        readWaitListFromFile(childrenWaitList);
        int x = 3;
        enterPassword(pedaFile, input, childrenArr,pedaList, parentArr, leaderArr, childrenWaitList, phoneList, x);

    }
    public static void enterPassword(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList, int x) throws FileNotFoundException {


        System.out.println("Enter the password, to access the system: ");
        String pw = input.next();

        if(pw.equals(leaderArr[0].getPassword())) {
            mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }else{
            System.out.println("Wrong password - You got: " + x + " tries left");
            x--;
            if(x == -1) {
                System.exit(-1);
            }
            enterPassword(f, input, childrenArr,pedaList, parentArr, leaderArr, childrenWaitList, phoneList, x);
        }
    }

    public static void mainMenu(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Welcome to Roskilde frie Børnehave:");
        System.out.println("1. Show members");
        System.out.println("2. Create members");
        System.out.println("3. Phonelist");
        System.out.println("4. Sort list");
        System.out.println("5. Waitlist");
        System.out.println("6. Write to file");
        System.out.println("7. Exit");
        int mainOption = input.nextInt();
        if (mainOption == 1) {
            showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (mainOption == 2) {
            createMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (mainOption == 3) {
            System.out.println("1. Add member to Phonelist");
            System.out.println("2. Show Phonelist");
            System.out.println("3. Delete person from Phonelist");
            mainOption = input.nextInt();
            if (mainOption == 1) {
                addToPhoneList(input, phoneList);
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (mainOption == 2) {
                printPhoneList(phoneList);
                System.out.println("1. Back");
                input.nextInt();
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (mainOption == 3) {
                deleteFromPhoneList(input, phoneList);
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else {
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }

        } else if (mainOption == 4) {
            viewSortList(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (mainOption == 5) {
            System.out.println("Type [1] to add child to wait list, type [2] to show wait list and type, [3] to delete a child from the wait list");
                int choice = input.nextInt();
                if(choice == 1){
                    addChildToWaitList(input, childrenWaitList);
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }else if(choice == 2){
                    printWaitList(childrenWaitList);
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }else if(choice == 3){
                    deleteFromWaitList(input, childrenArr, childrenWaitList);
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }else {
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
        } else if (mainOption == 6) {
            writeToWaitList(childrenWaitList);
            writeChildrenToFile(childrenArr);
            writePedagogueToFile(f, pedaList);
            writeParentsToFile(parentArr);
            writeLeaderToFile(leaderArr);
            mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    private static void showMembers(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Which member do you want to show?");
        System.out.println("1. Show list of Children");
        System.out.println("2. Show list of Parents");
        System.out.println("3. Show leader");
        System.out.println("4. Show list of Pedagogues");
        System.out.println("5. Back");
        int ans = input.nextInt();
        if (ans == 1) {
            readChildrenFromFile(childrenArr);
            for (Children c : childrenArr) {
                if(c != null) {
                    System.out.println(c);
                }
            }
            System.out.println("1. Edit children\n2. Delete children\n3. back");
            ans = input.nextInt();
            if (ans == 1) {
                editChildren(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (ans == 2) {
                deleteChildren(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else {
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }
        } else if (ans == 2) {
            readParentsFromFile(parentArr);
            for(Parent p : parentArr) {
                if(p != null) {
                    System.out.println(p);
                }

            }
            System.out.println("1. Edit parent\n2. Delete parent\n3. back\n");
            ans = input.nextInt();
            if (ans == 1) {
                editParents(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (ans == 2) {
                deleteParents(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else {
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }
        } else if (ans == 3) {
            readLeaderFromFile(leaderArr);
            for(Leader l : leaderArr) {
                System.out.println(l);
            }
            System.out.println("1. Edit leader\n2. Delete leader\n3. back");
            ans = input.nextInt();
            if (ans == 1) {
                editLeader(f, input, childrenArr, pedaList,  parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (ans == 2) {
                deleteLeader(f, input, childrenArr, pedaList,  parentArr, leaderArr, childrenWaitList, phoneList);
            } else {
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }
        } else if (ans == 4) {
            readPedagogueFromFile(pedaList);
            System.out.println(pedaList);
            System.out.println("1. Edit pedagogue\n2. Delete pedagogue\n3. back");
            ans = input.nextInt();
            if (ans == 1) {
                editPedagogue(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else if (ans == 2) {
                deletePedagogue(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            } else {
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }
        } else if (ans == 5) {
            mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    public static void createMembers(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Chose what you want to create");
        System.out.println("1. Create Child\n2. Create Pedagogue\n3. Create Parent\n4. Create Leader\n5. Back");
        int ans = input.nextInt();
        if (ans == 1) {
            createChildren(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (ans == 2) {
            createPedagogue(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (ans == 3) {
            createParent(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        } else if (ans == 4) {
            createLeader(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }else if (ans == 5){
            mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    public static void createChildren(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the Children: ");
        String fn = input.next();
        System.out.println("Enter the lastname of the Children: ");
        String ln = input.next();
        System.out.println("Enter the age of the Children: ");
        int age = input.nextInt();
        System.out.println("Assign the child to an area, Beefalo, Wholphin or Jaglion area: ");
        String ar = input.next();
        Children c1 = new Children(fn, ln, age, ar);
        for (int i = 0; i < childrenArr.length - 1; i++) {
            if (childrenArr[i] == null) {
                childrenArr[i] = c1;
                break;
            }
        }
        mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void createPedagogue(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the Pedagogue: ");
        String fn = input.next();
        System.out.println("Enter the lastname of the Pedagogue: ");
        String ln = input.next();
        System.out.println("Enter the phone number of the Pedagogue: ");
        int pn = input.nextInt();
        System.out.println("Enter the mail of the Pedagogue: ");
        String mail = input.next();
        System.out.println("Assign the Pedagogue to an area, Beefalo, Wholphin and Jaglion");
        String area = input.next();

        Pedagogue p1 = new Pedagogue(fn, ln, pn, mail, area);
        pedaList.add(p1);

        showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void createParent(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the name of the parent");
        String fn = input.next();
        System.out.println("Enter the lastname of the parent");
        String ln = input.next();
        System.out.println("Enter the phone number of the parent");
        int phonenumber = input.nextInt();
        System.out.println("Enter the mail address of the parent");
        String mail = input.next();
        System.out.println("Enter the firstname of the children, that you want the parent to be attached to: ");
        String childFn = input.next();
        System.out.println("Enter the lastname of the children, that you want the parent to be attached to: ");
        String childLn = input.next();
        for (int i = 0; i <= childrenArr.length - 1; i++) {
            if (childrenArr[i].getFirstName().equalsIgnoreCase(childFn) && childrenArr[i].getLastName().equalsIgnoreCase(childLn)) {
                Parent p1 = new Parent(fn, ln, phonenumber, mail);
                for (int k = 0; k < parentArr.length - 1; k++) {
                    if (parentArr[k] == null) {
                        p1.setChildren(childrenArr[i]);
                        parentArr[k] = p1;
                        break;
                    } else if (parentArr[k] != null) {
                        System.out.println("The list of parents is full");
                    }
                }
            }
            break;
        }

        showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void createLeader(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException{

        System.out.println("Enter the firstname of the leader: ");
        String firstname = input.next();
        System.out.println("Enter the lastname of the leader: ");
        String lastname = input.next();
        System.out.println("Enter the phonenumber of the leader: ");
        int phonenumber = input.nextInt();
        System.out.println("Enter the mail of the leader: ");
        String mail = input.next();
        System.out.println("Enter a password: ");
        String pw = input.next();

        Leader l1 = new Leader(firstname, lastname, phonenumber, mail, pw);
        leaderArr[0] = l1;

        System.out.println("This leader is created: " + Arrays.toString(leaderArr));
        showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void writePedagogueToFile(File f, ArrayList<Pedagogue> pedagogueList) {
        try {
            FileWriter writer = new FileWriter(f, true);
            for (int i = 0; i <= pedagogueList.size() - 1; i++) {
                writer.write(pedagogueList.get(i).getFirstName() + " " + pedagogueList.get(i).getLastName() + " " + pedagogueList.get(i).getPhoneNumber() + " " + pedagogueList.get(i).getMail() + " " + pedagogueList.get(i).getArea() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readPedagogueFromFile(ArrayList<Pedagogue> pedaList) {
        try {
            File f = new File("./src/Pedagogue.txt");
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int phoneNumber = lineScan.nextInt();
                String mail = lineScan.next();
                String area = lineScan.next();
                Pedagogue pedas = new Pedagogue(firstName, lastName, phoneNumber, mail, area);
                pedaList.add(pedas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLeaderToFile(Leader[] leaderArr) {
        try {
            int i = 0;
            File f = new File("./src/Leader.txt");
            FileWriter fileWriter = new FileWriter(f, false);
            BufferedWriter br = new BufferedWriter(fileWriter);
            while(leaderArr[i] != null) {
                br.write(leaderArr[i].getFirstName() + " " + leaderArr[i].getLastName() + " " + leaderArr[i].getPhoneNumber() + " " + leaderArr[i].getMail() + " " + leaderArr[i].getPassword() + "\n");
                i++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readLeaderFromFile(Leader[] leaderArr) throws FileNotFoundException{
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
                String password = lineScan.next();
                Leader l1 = new Leader(firstName, lastName, phoneNumber, mail, password);
                leaderArr[0] = l1;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeChildrenToFile(Children[] childrenArr) {
        int i = 0;
        try {
            File f = new File("./src/Children.txt");
            FileWriter fileWriter = new FileWriter(f, false);
            BufferedWriter br = new BufferedWriter(fileWriter);

            while (childrenArr[i] != null) {
                br.write(childrenArr[i].getFirstName() + " " + childrenArr[i].getLastName() + " " + childrenArr[i].getAge() + " " + childrenArr[i].getArea() + "\n");
                i++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readChildrenFromFile(Children[] childArr) throws FileNotFoundException {
        try {
            File f = new File("./src/Children.txt");
            Scanner scan = new Scanner(f);
            int a = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String firstName = lineScan.next();
                String lastName = lineScan.next();
                int age = lineScan.nextInt();
                String area = lineScan.next();
                Children child = new Children(firstName, lastName, age, area);
                if (childArr[a] != null) {
                    for (int i = 0; i < childArr.length; i++) {
                        if (childArr[i] == null) {
                            childArr[i] = child;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeParentsToFile(Parent[] parentArr) throws FileNotFoundException {
        int i = 0;
        try {
            File f = new File("./src/Parent.txt");
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter br = new BufferedWriter(fileWriter);

            while (parentArr[i] != null) {
                br.write(parentArr[i].getFirstName() + " " + parentArr[i].getLastName() + " " + parentArr[i].getPhoneNumber() + " " + parentArr[i].getMail() + " " + parentArr[i].getChildren() + "\n");
                i++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readParentsFromFile(Parent[] parentArr) throws FileNotFoundException {
        //try{
        File f = new File("./src/Parent.txt");
        Scanner scan = new Scanner(f);
        int a = 0;
        while (scan.hasNextLine()) {
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
            Parent parent = new Parent(firstName, lastName, phoneNumber, mail, child);
            if (parentArr[a] != null) {
                for (int i = 0; i < parentArr.length; i++) {
                    if (parentArr[i] == null) {
                        parentArr[i] = parent;
                        break;
                    }
                }
            }
            //}catch(Exception e) {
            //  e.printStackTrace();
        }
    }

    public static void writePhoneListToFile(HashMap<Integer, String> phoneList) throws FileNotFoundException{
       try {
           File f = new File("./src/PhoneList.txt");
           FileWriter fileWriter = new FileWriter(f);
           BufferedWriter br = new BufferedWriter(fileWriter);
           for(Map.Entry<Integer, String> entry : phoneList.entrySet()) {
               br.write(entry.getKey() + " " + entry.getValue() );
               br.newLine();
           }
           br.close();
       }catch(IOException e) {
            e.printStackTrace();
       }
    }

    public static void writeToWaitList(ArrayList<Children> childrenWaitList){
        try {
            File file = new File("./src/waitList.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i <= childrenWaitList.size() - 1; i++){
                bWriter.write(childrenWaitList.get(i).getFirstName() + " " + childrenWaitList.get(i).getLastName() + " " + childrenWaitList.get(i).getAge() + "\n");
            }
            bWriter.close();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void readPhoneListFromFile(HashMap<Integer, String> phoneList) throws FileNotFoundException{
       File f = new File("./src/PhoneList.txt");
       Scanner scan = new Scanner(f);
       int a = 0;
       while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner lineScan = new Scanner(line);
            int number = lineScan.nextInt();
            String name = lineScan.next();
            phoneList.put(number, name);
       }
    }

    public static void editChildren(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the child you want to edit: ");
        String fn = input.next();
        System.out.println("Enter the lastname of the child you want to edit: ");
        String ln = input.next();
        for (int i = 0; i <= childrenArr.length - 1; i++) {
            if (childrenArr[i].getFirstName().equalsIgnoreCase(fn) && childrenArr[i].getLastName().equalsIgnoreCase(ln)) {
                System.out.println("You are now editing: " + childrenArr[i] + "\nAre you sure, that you want to edit this person? \n1. Yes \n2. No");
                int answer = input.nextInt();
                if (answer == 1) {
                    System.out.println("Press 0 to keep the information - To edit the current information, press the new information: ");
                    System.out.println("The current firstname is: " + childrenArr[i].getFirstName() + "\nEdit this information to: ");
                    String editFn = input.next();
                    if (!editFn.equalsIgnoreCase("0")) {
                        childrenArr[i].setFirstName(editFn);
                    }
                    System.out.println("The current lastname is: " + childrenArr[i].getLastName() + "\nEdit this information to: ");
                    String editLn = input.next();
                    if (!editLn.equalsIgnoreCase("0")) {
                        childrenArr[i].setLastName(editLn);
                    }
                    System.out.println("The current age is: " + childrenArr[i].getAge() + "\nEdit this information to: ");
                    int editAge = input.nextInt();
                    if (editAge != 0) {
                        childrenArr[i].setAge(editAge);
                    }
                    System.out.println("The current Area is: " + childrenArr[i].getArea() + "\nEdit this information to: ");
                    String editArea = input.next();
                    if (!editArea.equalsIgnoreCase("0")) {
                        childrenArr[i].setArea(editArea);
                    }
                    System.out.println("These are the changes: " + childrenArr[i].getFirstName() + "\n" + childrenArr[i].getLastName() + "\n" + childrenArr[i].getAge() + "\n" + childrenArr[i].getArea() + "\n");
                    System.out.println("Do you want to try again? y/n");
                    String ans = input.next();
                    if (ans.equalsIgnoreCase("y")) {
                        editChildren(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    } else {
                        mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    }
                } else if (answer == 2) {
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                } else {
                    System.out.println("Unknown input - Returning to main menu ....");
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }

        }

    }

    public static void editParents(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the parent you want to edit: ");
        String fn = scan.next();
        System.out.println("Enter the lastname of the parent you want to edit: ");
        String ln = scan.next();
        for (int i = 0; i <= parentArr.length - 1; i++) {
            if (parentArr[i].getFirstName().equalsIgnoreCase(fn) && parentArr[i].getLastName().equalsIgnoreCase(ln)) {
                System.out.println("You are now editing: " + parentArr[i] + "\nAre you sure, that you want to edit this person? \n1. Yes \n2. No");
                int answer = scan.nextInt();
                if (answer == 1) {
                    System.out.println("Press 0 to keep the information - To edit the current information, press the new information: ");
                    System.out.println("The current firstname is: " + parentArr[i].getFirstName() + "\nEdit this information to: ");
                    String editFn = scan.next();
                    if (!editFn.equalsIgnoreCase("0")) {
                        parentArr[i].setFirstName(editFn);
                    }
                    System.out.println("The current lastname is: " + parentArr[i].getLastName() + "\nEdit this information to: ");
                    String editLn = scan.next();
                    if (!editLn.equalsIgnoreCase("0")) {
                        parentArr[i].setLastName(editLn);
                    }
                    System.out.println("The current phonenumber is: " + parentArr[i].getPhoneNumber() + "\nEdit this information to: ");
                    int editPn = scan.nextInt();
                    if (editPn != 0) {
                        parentArr[i].setPhoneNumber(editPn);
                    }
                    System.out.println("The current Mail is: " + parentArr[i].getMail() + "\nEdit this information to: ");
                    String editMail = scan.next();
                    if (!editMail.equalsIgnoreCase("0")) {
                        parentArr[i].setMail(editMail);
                    }
                    System.out.println("These are the changes: " + parentArr[i].getFirstName() + "\n" + parentArr[i].getLastName() + "\n" + parentArr[i].getPhoneNumber() + "\n" + parentArr[i].getMail() + "\n");
                    System.out.println("Do you want to try again? y/n");
                    String ans = scan.next();
                    if (ans.equalsIgnoreCase("y")) {
                        editParents(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    } else {
                        mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    }
                } else if (answer == 2) {
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                } else {
                    System.out.println("Unknown input - Returning to main menu ....");
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }

        }
    }

    public static void editPedagogue(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the pedagogue you want to edit: ");
        String fn = scan.next();
        System.out.println("Enter the lastname of the pedagogue you want to edit: ");
        String ln = scan.next();
        for (int i = 0; i <= pedaList.size() - 1; i++) {
            if (pedaList.get(i).getFirstName().equalsIgnoreCase(fn) && pedaList.get(i).getLastName().equalsIgnoreCase(ln)) {
                System.out.println("You are now editing: " + pedaList.get(i) + "\nAre you sure, that you want to edit this person? \n1. Yes \n2. No");
                int answer = scan.nextInt();
                if (answer == 1) {
                    System.out.println("Press 0 to keep the information - To edit the current information, press the new information: ");
                    System.out.println("The current firstname is: " + pedaList.get(i).getFirstName() + "\nEdit this information to: ");
                    String editFn = scan.next();
                    if (!editFn.equalsIgnoreCase("0")) {
                        pedaList.get(i).setFirstName(editFn);
                    }
                    System.out.println("The current lastname is: " + pedaList.get(i).getLastName() + "\nEdit this information to: ");
                    String editLn = scan.next();
                    if (!editLn.equalsIgnoreCase("0")) {
                        pedaList.get(i).setLastName(editLn);
                    }
                    System.out.println("The current phonenumber is: " + pedaList.get(i).getPhoneNumber() + "\nEdit this information to: ");
                    int editPn = scan.nextInt();
                    if (editPn != 0) {
                        pedaList.get(i).setPhoneNumber(editPn);
                    }
                    System.out.println("The current Mail is: " + pedaList.get(i).getMail() + "\nEdit this information to: ");
                    String editMail = scan.next();
                    if (!editMail.equalsIgnoreCase("0")) {
                        pedaList.get(i).setMail(editMail);
                    }
                    System.out.println("These are the changes: " + pedaList.get(i).getFirstName() + "\n" + pedaList.get(i).getLastName() + "\n" + pedaList.get(i).getPhoneNumber() + "\n" + pedaList.get(i).getMail() + "\n");
                    System.out.println("Do you want to try again? y/n");
                    String ans = scan.next();
                    if (ans.equalsIgnoreCase("y")) {
                        editParents(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    } else {
                        mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    }
                } else if (answer == 2) {
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                } else {
                    System.out.println("Unknown input - Returning to main menu ....");
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }

        }
    }

    public static void editLeader(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList)throws FileNotFoundException {
        System.out.println("Enter the firstname of the leader you want to edit: ");
        String fn = scan.next();
        System.out.println("Enter the lastname of the leader you want to edit: ");
        String ln = scan.next();
        for (int i = 0; i <= leaderArr.length - 1; i++) {
            if (leaderArr[i].getFirstName().equalsIgnoreCase(fn) && leaderArr[i].getLastName().equalsIgnoreCase(ln)) {
                System.out.println("You are now editing: " + leaderArr[i] + "\nAre you sure, that you want to edit this person? \n1. Yes \n2. No");
                int answer = scan.nextInt();
                if (answer == 1) {
                    System.out.println("Press 0 to keep the information - To edit the current information, press the new information: ");
                    System.out.println("The current firstname is: " + leaderArr[i].getFirstName() + "\nEdit this information to: ");
                    String editFn = scan.next();
                    if (!editFn.equalsIgnoreCase("0")) {
                        leaderArr[i].setFirstName(editFn);
                    }
                    System.out.println("The current lastname is: " + leaderArr[i].getLastName() + "\nEdit this information to: ");
                    String editLn = scan.next();
                    if (!editLn.equalsIgnoreCase("0")) {
                        leaderArr[i].setLastName(editLn);
                    }
                    System.out.println("The current phonenumber is: " + leaderArr[i].getPhoneNumber() + "\nEdit this information to: ");
                    int editPn = scan.nextInt();
                    if (editPn != 0) {
                        leaderArr[i].setPhoneNumber(editPn);
                    }
                    System.out.println("The current Mail is: " + leaderArr[i].getMail() + "\nEdit this information to: ");
                    String editMail = scan.next();
                    if (!editMail.equalsIgnoreCase("0")) {
                        leaderArr[i].setMail(editMail);
                    }
                    System.out.println("The current Password is: " + leaderArr[i].getPassword() + "\nEdit this information to: ");
                    String editPassword = scan.next();
                    if (!editPassword.equalsIgnoreCase("0")) {
                        leaderArr[i].setPassword(editPassword);
                    }
                    System.out.println("These are the changes: " + leaderArr[i].getFirstName() + "\n" + leaderArr[i].getLastName() + "\n" + leaderArr[i].getPhoneNumber() + "\n" + leaderArr[i].getMail() + "\n");
                    System.out.println("Do you want to try again? y/n");
                    String ans = scan.next();
                    if (ans.equalsIgnoreCase("y")) {
                        editParents(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    } else {
                        mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    }
                } else if (answer == 2) {
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                } else {
                    System.out.println("Unknown input - Returning to main menu ....");
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }
        }
    }

    public static void deleteChildren(File f, Scanner scan, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the first name of the child that you want to delete");
        String fn = scan.next();
        System.out.println("Enter the last name of the child that you want to delete");
        String ln = scan.next();
        for (int i = 0; i <= childrenArr.length - 1; i++) {
            if (childrenArr[i].getFirstName().equalsIgnoreCase(fn) && childrenArr[i].getLastName().equalsIgnoreCase(ln)) {
                System.out.println("Are you sure you want to delete this " + "Firstname: " + childrenArr[i].getFirstName() + " Lastname: " + childrenArr[i].getLastName() + " Age: " + childrenArr[i].getAge() + " Area: " + childrenArr[i].getArea() + "\n[1] = YES [2] = No");
                int confirm = scan.nextInt();
                if (confirm == 1) {
                    childrenArr[i].setFirstName(null);
                    childrenArr[i].setLastName(null);
                    childrenArr[i].setAge(0);
                    childrenArr[i].setArea(null);
                    break;
                } else {
                    mainMenu(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }
        }
        showMembers(f, scan, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void deleteParents(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
        System.out.println("Enter the firstname of the Parent, that you want to delete: ");
        String delFn = input.next();
        System.out.println("Enter the lastname of the Parent, that you want to delete: ");
        String delLn = input.next();
        for(int i = 0; i <= parentArr.length - 1; i++) {
            if(parentArr[i].getFirstName().equalsIgnoreCase(delFn) && parentArr[i].getLastName().equalsIgnoreCase(delLn)) {
                System.out.println("Are you sure you want to delete this " + parentArr[i] + "\n[1] = YES [2] = No");
                int confirm = input.nextInt();
                if(confirm == 1) {
                    parentArr[i].setFirstName(null);
                    parentArr[i].setLastName(null);
                    parentArr[i].setPhoneNumber(0);
                    parentArr[i].setMail(null);
                    break;
                }else{
                    mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                }
            }
        }
        showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void deletePedagogue(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException {
            System.out.println("Enter the firstname of the pedagogue, that you want to delete: ");
            String delFn = input.next();
            System.out.println("Enter the lastname of the pedagogue, that you want to delete: ");
            String delLn = input.next();
            for(int i = 0; i <= pedaList.size() - 1; i++) {
                if(pedaList.get(i).getFirstName().equalsIgnoreCase(delFn) && pedaList.get(i).getLastName().equalsIgnoreCase(delLn)) {
                    System.out.println("Are you sure you want to delete this: " + pedaList.get(i) + "\n[1] = YES [2] = No");
                    int confirm = input.nextInt();
                    if(confirm == 1) {
                        pedaList.remove(i);
                        break;
                    }else{
                        mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
                    }
                }
            }
            showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void deleteLeader(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList)throws FileNotFoundException{
        System.out.println("Enter the first name of the leader that you want to delete");
        String fn = input.next();
        System.out.println("Enter the last name of the leader that you want to delete");
        String ln = input.next();
        for(int i = 0; i <= leaderArr.length - 1; i++){
            if(leaderArr[i].getFirstName().equalsIgnoreCase(fn) && leaderArr[i].getLastName().equalsIgnoreCase(ln)){
                System.out.println("Are you sure you want to delete this: " + leaderArr[i] + "\n[1] = YES [2] = No");
                int ans = input.nextInt();
                if(ans == 1){
                    leaderArr[i].setFirstName(null);
                    leaderArr[i].setLastName(null);
                    leaderArr[i].setPhoneNumber(0);
                    leaderArr[i].setMail(null);
                    leaderArr[i].setPassword(null);
                    break;
                }
            }else{
                mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
            }
        }
        showMembers(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
    }

    public static void addToPhoneList(Scanner input, HashMap<Integer, String> phoneList) {
        System.out.println("Enter the name of the person, you want to add to the phonelist: ");
        String name = input.next();
        System.out.println("Enter the phonenumber of the person, you want to add to the phonelist: ");
        int number = input.nextInt();
        phoneList.put(number, name);
        System.out.println();
    }

    public static void printPhoneList(HashMap<Integer, String> phoneList)throws FileNotFoundException {
        writePhoneListToFile(phoneList);
        readPhoneListFromFile(phoneList);
        System.out.println(phoneList);
    }

    public static void deleteFromPhoneList(Scanner input, HashMap<Integer, String> phoneList) {
        System.out.println("Enter the phonenumber, of the person you want to delete from the phonelist: ");
        int pn = input.nextInt();
        phoneList.remove(pn);
    }

    public static void addChildToWaitList(Scanner input, ArrayList<Children> childrenWaitList){
        System.out.println("Enter the first name of the child, you want to add to the wait list");
        String fn = input.next();
        System.out.println("Enter the last name of the child, you want to add to the wait list");
        String ln = input.next();
        System.out.println("Enter the child's age: ");
        int age = input.nextInt();
        Children child = new Children(fn, ln, age);
        childrenWaitList.add(child);
        System.out.println("Child has been added to the waitlist");
    }

    public static void printWaitList(ArrayList<Children> childrenWaitList) throws FileNotFoundException{
        for(Children i : childrenWaitList) {
            System.out.println(i);
        }
    }

    public static void readWaitListFromFile(ArrayList<Children> childrenWaitList) throws FileNotFoundException{
        File f = new File("./src/waitList.txt");
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner lineScan = new Scanner(line);
            String firstName = lineScan.next();
            String lastName = lineScan.next();
            Children child = new Children(firstName, lastName);
            childrenWaitList.add(child);
        }
    }

    public static void deleteFromWaitList(Scanner input, Children[] childrenArr, ArrayList<Children> childrenWaitList){
        System.out.println("Enter the firstName of the person you want to delete");
        String fn = input.next();
        System.out.println("Enter the lastName of the person you want to delete");
        String ln = input.next();
        for(int i = 0; i <= childrenWaitList.size() - 1; i++){
            if(childrenWaitList.get(i).getFirstName().equalsIgnoreCase(fn) && childrenWaitList.get(i).getLastName().equalsIgnoreCase(ln)){
                System.out.println("Child: " + childrenWaitList.get(i).getFirstName() + " " + childrenWaitList.get(i).getLastName() + " " + "has been removed from the wait list");
                childrenWaitList.remove(i);
            }
        }
    }

    public static void viewSortList(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList)throws FileNotFoundException{
        System.out.print("Here you can sort any list by firstname, lastname, age etc.");
        System.out.print("Press any action:\n1. Sort children list\n2. Sort parent list\n3. Sort pedagogue list\n4. back\n");
        int ans = input.nextInt();
        if(ans == 1){
            sortChildren(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }else if(ans == 2){
            sortParent(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }else if(ans == 3){
            sortPedagogue(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }else{
            mainMenu(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    public static void sortChildren(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException{
        System.out.println("1: to sort by firstname\n2: to sort på lastname\n3: to sort by age\n4: to sort by area\n5: back");
        List<Children> childrenList = Arrays.asList(childrenArr);
        int ans = input.nextInt();
        if (ans == 1) {
            System.out.println("List of children sorted by firstname: ");
            childrenList.sort(Comparator.comparing(Children::getFirstName));
            System.out.println(childrenList);
        }
        if (ans == 2) {
            System.out.println("List of children sorted by lastname: ");
            childrenList.sort(Comparator.comparing(Children::getLastName));
            System.out.println(childrenList);
        }
        if (ans == 3) {
            System.out.println("List of children sorted by age: ");
            childrenList.sort(Comparator.comparing(Children::getAge));
            System.out.println(childrenList);
        }
        if (ans == 4) {
            System.out.println("List of children sorted by area: ");
            childrenList.sort(Comparator.comparing(Children::getArea));
            System.out.println(childrenList);
        }
        else if(ans == 5) {
            viewSortList(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    public static void sortParent(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException{
        System.out.println("1: to sort by firstname\n2: to sort på lastname\n3: back");
        List<Parent> parentList = Arrays.asList(parentArr);
        int ans = input.nextInt();
        if (ans == 1) {
            System.out.println("List of parents sorted by firstname: ");
            parentList.sort(Comparator.comparing(Parent::getFirstName));
            System.out.println(parentList);
        }
        if (ans == 2) {
            System.out.println("List of parents sorted by lastname: ");
            parentList.sort(Comparator.comparing(Parent::getLastName));
            System.out.println(parentList);
        }
        else if(ans == 3) {
            viewSortList(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }

    public static void sortPedagogue(File f, Scanner input, Children[] childrenArr, ArrayList<Pedagogue> pedaList, Parent[] parentArr, Leader[] leaderArr, ArrayList<Children> childrenWaitList, HashMap<Integer, String> phoneList) throws FileNotFoundException{
        System.out.println("1: to sort by firstname\n2: to sort på lastname\n3: to sort by area\n4: back");
        int ans = input.nextInt();
        if (ans == 1) {
            System.out.println("List of Pedagogues sorted by firstname: ");
            pedaList.sort(Comparator.comparing(Pedagogue::getFirstName));
            System.out.println(pedaList);
        }
        if (ans == 2) {
            System.out.println("List of Pedagogues sorted by lastname: ");
            pedaList.sort(Comparator.comparing(Pedagogue::getLastName));
            System.out.println(pedaList);
        }
        if (ans == 3) {
            System.out.println("List of Pedagogues sorted by area: ");
            pedaList.sort(Comparator.comparing(Pedagogue::getArea));
            System.out.println(pedaList);
        }
        else if(ans == 4) {
            viewSortList(f, input, childrenArr, pedaList, parentArr, leaderArr, childrenWaitList, phoneList);
        }
    }


}