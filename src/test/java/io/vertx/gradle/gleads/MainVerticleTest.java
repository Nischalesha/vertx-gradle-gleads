package io.vertx.gradle.gleads;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(VertxExtension.class)
public class MainVerticleTest {



  @BeforeEach
  public void setUp(Vertx vertx,VertxTestContext testContext) {
    vertx.deployVerticle(MainVerticle.class.getName(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @AfterEach
  public void tearDown(Vertx vertx,VertxTestContext testContext) {
    vertx.close(testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  public void testThatTheServerIsStarted(Vertx vertx,VertxTestContext tc) {

    tc.verify(() -> {
      vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
        Assertions.assertEquals(response.statusCode(), 200);
        response.bodyHandler(body -> {
          Assertions.assertTrue(body.length() > 0);
          tc.completeNow();
        });

      });
    });
  }

}
