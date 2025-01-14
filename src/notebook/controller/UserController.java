package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepositoryable;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepositoryable repository;

    public UserController(GBRepositoryable repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public List<User> getAllUser() {
        return repository.findAll();
    }

    public void delete(String userId) {
        repository.delete(Long.parseLong(userId));
    }

}