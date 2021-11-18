package group825.vetapp.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    public List<User> selectAllUsers() {
        return database;
    }

    public int addUser(User user) {
        database.add(user);

        return 1;
    }

    public int editUser(UUID id, UUID newId, String name, String email) {
        return selectUserById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setId(newId);
                database.get(accountIdx).setName(name);
                database.get(accountIdx).setEmail(email);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    public int blockUser(UUID id) {
        return selectUserById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setActive(false);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    private Optional<User> selectUserById(UUID id) {
        return database.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
