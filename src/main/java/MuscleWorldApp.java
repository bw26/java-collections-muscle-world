import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MuscleWorldApp {

    //
    // Data members
    //

    private final Scanner scanner;
    private final GymDataStore gymDataStore;

    //
    // Constructors
    //

    MuscleWorldApp(GymDataStore gymDataStore, Scanner scanner) {
        this.scanner = scanner;
        this.gymDataStore = gymDataStore;
    }

    //
    // Public methods
    //

    public void run(){
        System.out.println("*** Muscle World ***");
        var userInput = "";
        while(!userInput.equals("q")) {
            System.out.print("Enter action (a, g, c, r, d, q): ");
            userInput = scanner.nextLine();
            try {
                switch (userInput) {
                    case "a":
                        addPerson();
                        break;
                    case "r":
                        runReport();
                        break;
                    case "g":
                        selectPerson();
                        break;
                    case "d":
                        deletePerson();
                        break;
                    case "c":
                        checkinPerson();
                        break;
                }
            }catch (ValidationException e){
                System.out.println(e.getMessage());
            }
//            switch (userInput) { // JDK 19
//                case "a" -> addPerson();
//                case "r" -> runReport();
//                case "g" -> selectPerson();
//                case "d" -> deletePerson();
//                case "c" -> checkinPerson();
//            }
        }
        System.out.println("Bye!");
    }

    //
    // Private methods.
    //

    private void deletePerson() throws ValidationException{
        try {
            System.out.print("Enter user Id: ");
            var userId = Integer.parseInt(scanner.nextLine());
            if (gymDataStore.removePerson(userId)) {
                System.out.println("Deleted person.");
            } else {
                System.out.println("Person not found.");
            }
        }catch(Exception e1){
            throw new ValidationException("Invalid Input: "+ e1.getMessage());
        }
    }

    private void addPerson() throws ValidationException{
        try {
            System.out.print("Enter user Id, first name, last name: ");
            var inputString = scanner.nextLine();
            var stringTokenizer = new StringTokenizer(inputString, ",");
            var userId = Integer.parseInt(stringTokenizer.nextToken());
            var firstName = stringTokenizer.nextToken().strip();
            var lastName = stringTokenizer.nextToken().strip();
            var person = new Person(firstName, lastName);
            gymDataStore.addPerson(userId, person);
        }catch(Exception e1){
            throw new ValidationException("Invalid Input: "+ e1.getMessage());
        }
    }

    private void selectPerson() throws ValidationException{
        try{
            System.out.print("Enter user Id: ");
            var in = Integer.parseInt(scanner.nextLine());
            var person = gymDataStore.getPerson(in);
            System.out.println("Name: " + person);
            System.out.println("Num visits: " + person.getNumCheckins());
        }catch(Exception e1){
            throw new ValidationException("Invalid Input: "+ e1.getMessage());
        }
    }

    private void checkinPerson() throws ValidationException{
        try{
            System.out.print("Enter user Id, checkin time: ");
            var in = scanner.nextLine();
            var stringTokenizer = new StringTokenizer(in, ",");
            var userId = stringTokenizer.nextToken();
            var checkinTime = stringTokenizer.nextToken().strip();
            var person = gymDataStore.getPerson(Integer.parseInt(userId));
            person.addCheckinTime(checkinTime);
            System.out.println(person + " " + checkinTime);
        }catch(Exception e1){
            throw new ValidationException("Invalid Input: "+ e1.getMessage());
        }
    }

    private void runReport(){
        System.out.println("*** Muscle World Report ****");
        var peopleList = gymDataStore.getAllPeople();
        for (Person p : peopleList) {
            System.out.println(p+" ("+p.getNumCheckins()+" visits)");
        }
    }
}
