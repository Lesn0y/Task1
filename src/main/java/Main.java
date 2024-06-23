import rest.RestService;
import rest.dto.User;

public class Main {
    public static void main(String[] args) {
        RestService restService = new RestService();
        // Step 1 - GET all users
        restService.getAllUsers();

        // Step 2 - POST new user
        User user = new User(3L, "James", "Brown", 23);
        String s1 = restService.saveUser(user);

        // Step 3 - PUT user with id = 3
        user.setName("Thomas");
        user.setLastName("Shelby");
        String s2 = restService.updateUser(user);

        //Step 4 - DELETE user with user = 3
        String s3 = restService.deleteUser(3);

        // Result
        System.out.println("Result code: " + s1 + s2 + s3);
    }
}

