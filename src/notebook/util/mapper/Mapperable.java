package notebook.util.mapper;

import notebook.model.User;

public interface Mapperable {
    String toInput(User e);
    User toOutput(String str);
}
