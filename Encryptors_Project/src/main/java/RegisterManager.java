import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RegisterManager {
    private UsersRepository usersRepository;
    private Scanner scanner = new Scanner(System.in);
    private EncryptorsFactory encryptorsFactory;

    public RegisterManager(UsersRepository usersRepository, EncryptorsFactory encryptorsFactory) {
        this.usersRepository = usersRepository;
        this.encryptorsFactory = encryptorsFactory;
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

            String encryptMethodType = getEncryptMethodTypeFromConfigurationFile();
            Encrypt encrypt = encryptorsFactory.getEncrypt(encryptMethodType);
            encrypt.encrypt(userDetails);
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

    private String getEncryptMethodTypeFromConfigurationFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL path = classLoader.getResource("conf.txt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(path.toURI()));
            for(String line : lines) {
                if(line.contains("encrypt_method")) {
                    return getEncryptMethodTypeFromLine(line);
                }
            }
            throw new Exception("Encrypt method type wasn't defined in application.properties");

        }
        catch (Exception ex) {
            throw new Exception("Configuration file isn't exist");
        }
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
