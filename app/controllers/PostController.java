package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Post;
import play.libs.Json;
import play.mvc.*;
import play.libs.ws.*;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.*;

public class PostController extends Controller {
    private final WSClient ws;

    @Inject
    public PostController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> index() {
        CompletionStage<WSResponse> futureResponse = this.ws.url("https://jsonplaceholder.typicode.com/posts").setMethod("GET").stream();

        return futureResponse.thenApply(res -> {
            if (res.getStatus() == 200) {
                JsonNode jsonPromise = res.getBody(WSBodyReadables.instance.json());
                List<Post> posts = Arrays.asList(Json.fromJson(jsonPromise, Post[].class));
                return ok(views.html.posts.index.render(posts));
            }

            return badRequest();
        });
    }
}
