package com.grupocmc.protein.administration.infrastructure.framework.controller;

import com.grupocmc.protein.ProteinApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProteinApplication.class)
@AutoConfigureMockMvc
public final class ActionControllerAcceptanceTest {
  @Autowired
  protected MockMvc mockMvc;

  @Test
  void create_a_valid_action() throws Exception {
    // given
    // when
    ResultActions actual = createAction(randomtActionData());

    // then
    actual.andExpect(status().is(201))
            .andExpect(content().string(""));
    Assertions.assertThat(true).isFalse();
  }

  private ResultActions createAction(final Map<String, Object> actionData) throws Exception {
    return this.performRequestWithBody(
            "POST",
            "/administration/actions",
            "{\"points\": " + actionData.get("points") + ", \"message\": \"" + actionData.get("message")
                    + "\", \"actionType\": " + actionData.get("actionType") +", \"scope\": \"" + actionData.get("scope")
                    + "\", \"isParent\": " + actionData.get("isParent") + ", \"codeParent\": \"" + actionData.get("codeParent")
                    + "\", \"slugReward\": \"" + actionData.get("slugReward")
                    + "\", \"name\": \"" + actionData.get("name")
                    + "\", \"slug\": \"" + actionData.get("slug")
                    + "\", \"code\": \"" + actionData.get("code")
                    + "\", \"desc\": \"" + actionData.get("desc")
                    + "\"}"
    );
  }

  private ResultActions performRequestWithBody(
          final String method,
          final String endpoint,
          final String body) throws Exception {
    return mockMvc
            .perform(request(HttpMethod.valueOf(method), endpoint)
                    .contentType(APPLICATION_JSON)
                    .content(body));
  }

  private Map<String, Object> randomtActionData() {
    HashMap<String, Object> processData = new HashMap<>();
    processData.put("points", 5);
    processData.put("message", "Some message");
    processData.put("actionType", 1);
    processData.put("scope", "Some scope");
    processData.put("isParent", false);
    processData.put("codeParent", "Some code");
    processData.put("slugReward", "Some slug reward");
    processData.put("slug", "Some slug");
    processData.put("name", "Some name");
    processData.put("desc", "Some desc");
    return processData;
  }
}
