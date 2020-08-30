import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RegisterManager {
    private UsersRepository usersRepository;
    private Scanner scanner = new Scanner(System.in);
    private HashExecutorFactory hashExecutorFactory;

    public RegisterManager(UsersRepository usersRepository, HashExecutorFactory hashExecutorFactory) {
        this.usersRepository = usersRepository;
        this.hashExecutorFactory = hashExecutorFactory;
    }

    public void run() throws Exception {
        System.out.println("Welcome to register app\n");

        while(true) {
            printUsersList();
            UserDetails userDetails = getUserDetails();
            try {
                validateUserNameIsFree(userDetails);
            }
            catch (Exception ex) {
                System.out.println("User name already exists, try again\n");
                continue;
            }

            HashExecutor hashExecutor = hashExecutorFactory.getEncoder();
            userDetails.setPassword(hashExecutor.hash(userDetails.getPassword()));
            saveToDB(userDetails);
            System.out.println("User register successfully\n");
        }
    }

    private void printUsersList() {
        Map<Integer,UserDetails> users = usersRepository.getUsers();
        if(!users.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Users In System:\n");
            users.forEach((integer, userDetails) -> stringBuilder.append(String.format("%s\n", userDetails.getUserName())));
            System.out.println(stringBuilder.toString());
        }
    }

    private void validateUserNameIsFree(UserDetails userDetails) throws Exception {
        if(usersRepository.isUserNameExists(userDetails.getUserName())) {
            throw new Exception("User name is already exists in system, choose another one");
        }
    }

    private void saveToDB(UserDetails userDetails) {
        usersRepository.addUser(userDetails);
    }

    private String getEncryptMethodTypeFromLine(String s) {
        return s.split("=")[1];
    }

    private UserDetails getUserDetails() {
        System.out.println("Welcome to registration process\n");
        System.out.println("Please insert user name\n");
        String userName = scanner.nextLine();
        System.out.println("Please insert password\n");
        String password = scanner.nextLine();
        return new UserDetails(userName, password);
    }

}
