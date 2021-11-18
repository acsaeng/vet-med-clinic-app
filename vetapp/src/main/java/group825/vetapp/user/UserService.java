package group825.vetapp.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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



    public List<User> selectAllUsers() {
        return this.repo.selectAllUsers();
    }

    public int addUser(User user) {
        return this.repo.addUser(user);
    }

    public int editUser(UUID id, UUID newId, String name, String email) {
        return this.repo.editUser(id, newId, name, email);
    }

    public int blockUser(UUID id) {
        return this.repo.blockUser(id);
    }



}
