package io.vertx.gradle.gleads;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class RegisterUserTest {



  private WebClient webClient;

  @BeforeEach
  public void setUp(Vertx vertx, VertxTestContext testContext) {
    webClient = WebClient.create(vertx);

    vertx.deployVerticle(new MainVerticle(),testContext.succeeding(id -> testContext.completeNow()));
  }

  @AfterEach
  public void tearDown(Vertx vertx,VertxTestContext testContext) {
    vertx.close(testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  public void testRegisterUser(Vertx vertx,VertxTestContext tc) {


    JsonObject userJson=new JsonObject()
      .put("username", "Jacob")
      .put("email", "jake2@jake.jake")
      .put("password", "jakejake");

    JsonObject user= new JsonObject()
      .put("user", userJson);

    WebClient webClient = WebClient.create(vertx);

    webClient.post(8080, "localhost", "/api/users")
      .putHeader("Content-Type", "application/json")
      .putHeader("X-Requested-With", "XMLHttpRequest")
      .sendJsonObject(user, ar -> {
        if (ar.succeeded()) {
          Assertions.assertEquals(201, ar.result().statusCode());
          JsonObject returnedJson = ar.result().bodyAsJsonObject();
          Assertions.assertNotNull(returnedJson);

          JsonObject returnedUser = returnedJson.getJsonObject("user");
          Assertions.assertEquals("Jacob", returnedUser.getString("username"), "Username should be 'User2");
          Assertions.assertEquals("jake2@jake.jake", returnedUser.getString("email"), "Email should be 'user2@user2.user2");
          Assertions.assertNull(returnedUser.getString("bio"), "Bio should be null/empty");
          Assertions.assertNull(returnedUser.getString("image"), "image should be null/empty");
          Assertions.assertNull(returnedUser.getString("token", "Token should not be null/empty"));
          tc.completeNow();
        }else{
          tc.failNow(ar.cause());
        }

      });


  }
}
