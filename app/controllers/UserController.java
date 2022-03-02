package controllers;

import dtos.EditUserData;
import dtos.UserData;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.*;
import views.html.users.*;

import javax.inject.Inject;
import java.util.Set;

public class UserController extends Controller {
    private final Form<UserData> form;
    private final Form<EditUserData> editForm;
    private final MessagesApi messagesApi;

    @Inject
    public UserController(FormFactory formFactory, MessagesApi messagesApi) {
        this.form = formFactory.form(UserData.class);
        this.editForm = formFactory.form(EditUserData.class);
        this.messagesApi = messagesApi;
    }

    public Result index() {
        Set<User> users = User.allUsers();
        return ok(index.render(users));
    }

    public Result create(Http.Request request) {
        return ok(create.render(this.form, request, this.messagesApi.preferred(request)));
    }

    public Result save(Http.Request request) {
        Form<UserData> userForm = this.form.bindFromRequest(request);

        if (userForm.hasErrors()) {
            return badRequest(create.render(userForm, request, this.messagesApi.preferred(request)));
        }

        User user = userForm.get().toUser();
        User.add(user);

        return redirect(routes.UserController.index());

    }

    public Result edit(Integer id, Http.Request request) {
        User user = User.findById(id);

        if (user == null) {
            return notFound("User Not Found");
        }

        EditUserData userData = EditUserData.fromUser(user);

        return ok(edit.render(id, this.editForm.fill(userData), request, messagesApi.preferred(request)));

    }

    public Result update(Integer id, Http.Request request) {
        Form<EditUserData> userForm = this.editForm.bindFromRequest(request);
        EditUserData userData = userForm.get();

        User oldUser = User.findById(id);

        if (oldUser == null) {
            return notFound("User Not Found");
        }

        oldUser.firstName = userData.firstName;
        System.out.println(oldUser.firstName);

        return redirect(routes.UserController.index());
    }

    public Result show(Integer id) {
        User user = User.findById(id);

        if (user == null) {
            return notFound("User Not Found");
        }

        return ok(views.html.users.show.render(user));
    }

    public Result destroy(Integer id) {
        User user = User.findById(id);

        if (user == null) {
            return notFound("User Not Found");
        }

        User.remove(user);

        return redirect(routes.UserController.index());
    }
}
