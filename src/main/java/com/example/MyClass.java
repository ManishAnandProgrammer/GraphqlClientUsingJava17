package com.example;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyClass {
  
  private final GraphQLWebClient graphQLWebClient;
  
  public MyClass(GraphQLWebClient graphQLWebClient) {
    this.graphQLWebClient = graphQLWebClient;
  }
  
  public String helloWorld() {

    GraphQLRequest request = GraphQLRequest.builder().query("query { hello }").build();
    GraphQLResponse response = graphQLWebClient.post(request).block();
    assert response != null;
    return response.get("hello", String.class);
  }

  public User getUser() {
    String query = """
            query($Id: Int!) {
              getUser(id: $Id) {
                id
                name
                state {
                  id
                  name
                  country {
                    id
                    name
                  }
                }
              }
            }""";

    GraphQLRequest request = GraphQLRequest.builder()
            .query(query)
            .variables(Map.of("Id", 1))
            .build();

    GraphQLResponse post = graphQLWebClient.post(request).block();
    assert post != null;
    User obj = post.get("getUser", User.class);
    return obj;
  }
}