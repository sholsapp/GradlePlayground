package com.untitled;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;


public class Main {
  public int library() {
    return 42;
  }
  public static void main(String[] argv) {
    HttpRequestFactory requestFactory
        = new NetHttpTransport().createRequestFactory();
    try {
      HttpRequest request = requestFactory.buildGetRequest(
          new GenericUrl("https://github.com"));
      String rawResponse = request.execute().parseAsString();
      System.out.println(rawResponse);
    } catch (java.io.IOException e) {
    }
    System.out.println("Hello, world!");
  }
}
