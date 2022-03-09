package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Post;
import play.libs.Json;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.*;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class DPostController extends Controller {
    private final WSClient ws;

    @Inject
    public DPostController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> index() {
        WSRequest request = ws.url("https://jsonplaceholder.typicode.com/posts");

        // apiKey = jumbotron
        // key = jumbo
        // request.addHeader("jumbo", "jumbotron");

        return request.setMethod("GET").stream().thenApply(res -> {
            if (res.getStatus() == 200) {
                JsonNode json = res.getBody(WSBodyReadables.instance.json());
                List<Post> posts = Arrays.asList(Json.fromJson(json, Post[].class));

                return ok(views.html.dposts.index.render(posts));
            }

            return badRequest();
        });
    }
}
