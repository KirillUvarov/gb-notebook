package notebook;

import notebook.controller.UserController;
import notebook.model.repository.impl.FileOperation;
import notebook.model.repository.GBRepositoryable;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        FileOperation fileOperation = new FileOperation(DB_PATH);
        GBRepositoryable repository = new UserRepository(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();

    }
}
