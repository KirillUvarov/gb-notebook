package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду ( \n   " +
                    " NONE,\n" +
                    "    READ,\n" +
                    "    CREATE,\n" +
                    "    UPDATE,\n" +
                    "    LIST,\n" +
                    "    DELETE,\n" +
                    "    EXIT\n" +
                    ": ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {

                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;

                case NONE:
                    break;

                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user + "\n");
                        //System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                    break;

                case LIST:
                    System.out.println(userController.getAllUser());
                    break;

                case DELETE:
                    userId = prompt("Enter user id: ");
                    userController.delete(userId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        if(firstName.isEmpty()){
            throw new RuntimeException("Не может быть пустым");
        }
        if(lastName.isEmpty()){
            throw new RuntimeException("Не может быть пустым");
        }
        if(phone.isEmpty()){
            throw new RuntimeException("Не может быть пустым");
        }
        return new User(firstName.replaceAll(" ", ""), lastName.replaceAll(" ", ""), phone.replaceAll(" ", ""));
    }
}
