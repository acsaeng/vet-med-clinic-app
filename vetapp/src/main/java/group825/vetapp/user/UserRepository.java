package group825.vetapp.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("tempAccountRepo")
public class UserRepository {

    private static final List<User> database = new ArrayList<>();

     public int loginUser(String email, String password) {
        for (User user : database) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return 1;
            }
        }

        return 0;
    }

    public int changeUserPassword(String email, String newPassword) {
        return selectUserByEmail(email).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setPassword(newPassword);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    private Optional<User> selectUserByEmail(String email) {
        Optional<User> accountOptional = database.stream().filter(user -> user.getEmail().equals(email)).findFirst();

//        if (accountOptional.isPresent()) {
//            return account;
//        } else {
//            throw new IllegalStateException("No account found.");
//        }

        return accountOptional;

    }





    // @Autowired
    public int addUser(User user) {
        database.add(user);

        return 1;
    }


    // **FOR TESTING PURPOSES**
    public List<User> selectAllUsers() {
        return database;
    }
}
