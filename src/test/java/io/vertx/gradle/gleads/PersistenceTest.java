package io.vertx.gradle.gleads;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.gradle.gleads.models.User;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class PersistenceTest {

  @BeforeEach
  public void setUp(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new PersistenceVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }
  @AfterEach
  public void tearDown(Vertx vertx,VertxTestContext testContext) {
    vertx.close(testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  public void testRegisterUser(Vertx vertx,VertxTestContext testContext) {


    JsonObject userToRegister=new JsonObject()
      .put("email", "jake2@jake.jake")
      .put("username", "Jacob")
      .put("password", "jakejake");

    JsonObject message=new JsonObject()
      .put("action", "register-user")
      .put("user", userToRegister);

    vertx.<JsonObject>eventBus().send("persistence-address",message, ar->{
      if (ar.succeeded()) {
        Assertions.assertNotNull(ar.result().body());
        User returnedUser= Json.decodeValue(new JsonObject(ar.result().body().toString()).getJsonObject("details").toString(),User.class);
        Assertions.assertEquals("jake2@jake.jake", returnedUser.getEmail());
        Assertions.assertEquals("Jacob", returnedUser.getUsername());
        testContext.completeNow();

      }else{
        Assertions.assertTrue(ar.succeeded());
        testContext.completeNow();
    }
    });

  }


}
