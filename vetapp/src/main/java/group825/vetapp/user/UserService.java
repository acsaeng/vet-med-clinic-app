package group825.vetapp.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(@Qualifier("tempAccountRepo") UserRepository repo) {
        this.repo = repo;
    }

    public int loginUser(String email, String password) {
        return this.repo.loginUser(email, password);
    }

    public int changeUserPassword(String email, String newPassword) {
        return this.repo.changeUserPassword(email, newPassword);
    }






    public int addUser(User user) {
        return this.repo.addUser(user);
    }


    // **FOR TESTING PURPOSES**
    public List<User> selectAllUsers() {
        return this.repo.selectAllUsers();
    }

}
