package ai.nexusone.service;

import ai.nexusone.config.JenkinsProperties;
import ai.nexusone.dto.JenkinsApiBuildResponse;
import ai.nexusone.dto.JenkinsJobResponse;
import ai.nexusone.dto.JenkinsQueueResponse;
import ai.nexusone.dto.JenkinsRootResponse;
import ai.nexusone.dto.JenkinsTriggerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component
public class JenkinsClient {

 private final JenkinsProperties properties;
 private final ObjectMapper objectMapper;
 private final HttpClient httpClient;

 public JenkinsClient(
         JenkinsProperties properties,
         ObjectMapper objectMapper) {

  this.properties = properties;
  this.objectMapper = objectMapper;

  this.httpClient = HttpClient.newBuilder()
          .connectTimeout(
                  Duration.ofSeconds(
                          properties.getConnectTimeoutSeconds()))
          .build();

  System.out.println("Jenkins URL      : "
          + properties.getBaseUrl());

  System.out.println("Jenkins Username : "
          + properties.getUsername());

  System.out.println("Token Length     : "
          + (properties.getApiToken() == null
          ? 0
          : properties.getApiToken().length()));
 }

 public JenkinsTriggerResponse trigger(
         Long executionId,
         String jobName,
         Map<String, String> parameters) {

  boolean parameterized =
          parameters != null
                  && !parameters.isEmpty();

  String path =
          "/job/"
                  + encodePath(jobName)
                  + (parameterized
                  ? "/buildWithParameters"
                  : "/build");

  String body =
          parameterized
                  ? buildFormBody(parameters)
                  : "";

  HttpRequest.Builder requestBuilder =
          createRequest(path)
                  .POST(HttpRequest.BodyPublishers.ofString(body));

  if (parameterized) {
   requestBuilder.header(
           "Content-Type",
           "application/x-www-form-urlencoded");
  }

  HttpResponse<String> response =
          send(requestBuilder.build());

  if (response.statusCode() != 201) {

   throw new IllegalStateException(
           "Jenkins trigger failed. HTTP "
                   + response.statusCode()
                   + ": "
                   + response.body());
  }

  String location =
          response.headers()
                  .firstValue("Location")
                  .orElse("");

  Long queueId =
          parseQueueId(location);

  return new JenkinsTriggerResponse(
          executionId,
          queueId,
          null,
          "QUEUED",
          location,
          "Jenkins accepted the build request");
 }

 public JenkinsQueueResponse queue(long queueId) {

  System.out.println(
          "Calling Queue API = "
                  + queueId);

  return jsonGet(
          "/queue/item/"
                  + queueId
                  + "/api/json",
          JenkinsQueueResponse.class);
 }

 public JenkinsApiBuildResponse build(
         String jobName,
         int buildNumber) {

  return jsonGet(
          "/job/"
                  + encodePath(jobName)
                  + "/"
                  + buildNumber
                  + "/api/json",
          JenkinsApiBuildResponse.class);
 }

 public List<JenkinsJobResponse> jobs() {

  JenkinsRootResponse response =
          jsonGet(
                  "/api/json?tree=jobs[name,url,color]",
                  JenkinsRootResponse.class);

  return response.jobs() == null
          ? List.of()
          : response.jobs();
 }

 private <T> T jsonGet(
         String path,
         Class<T> responseType) {

  HttpResponse<String> response =
          send(createRequest(path).GET().build());

  if (response.statusCode() / 100 != 2) {

   throw new IllegalStateException(
           "Jenkins GET failed. HTTP "
                   + response.statusCode()
                   + ": "
                   + response.body());
  }

  try {

   return objectMapper.readValue(
           response.body(),
           responseType);

  } catch (Exception ex) {

   throw new IllegalStateException(
           "Invalid Jenkins JSON response",
           ex);
  }
 }

 private HttpRequest.Builder createRequest(
         String path) {

  String baseUrl =
          properties.getBaseUrl()
                  .replaceAll("/+$", "");

  String credentials =
          properties.getUsername().trim()
                  + ":"
                  + properties.getApiToken().trim();

  String authorization =
          Base64.getEncoder()
                  .encodeToString(
                          credentials.getBytes(
                                  StandardCharsets.UTF_8));

  System.out.println(
          "Calling Jenkins URL = "
                  + baseUrl + path);

  System.out.println(
          "Username = "
                  + properties.getUsername());

  System.out.println(
          "Token Length = "
                  + properties.getApiToken().length());

  return HttpRequest.newBuilder(
                  URI.create(baseUrl + path))
          .timeout(
                  Duration.ofSeconds(
                          properties.getRequestTimeoutSeconds()))
          .header(
                  "Authorization",
                  "Basic " + authorization)
          .header(
                  "Accept",
                  "application/json");
 }
 private HttpResponse<String> send(
         HttpRequest request) {

  try {

   HttpResponse<String> response =
           httpClient.send(
                   request,
                   HttpResponse.BodyHandlers.ofString());

   System.out.println(
           "Jenkins HTTP Status = "
                   + response.statusCode());

   return response;

  } catch (InterruptedException ex) {

   Thread.currentThread().interrupt();

   throw new IllegalStateException(
           "Interrupted while calling Jenkins",
           ex);

  } catch (Exception ex) {

   throw new IllegalStateException(
           "Cannot connect to Jenkins at "
                   + properties.getBaseUrl(),
           ex);
  }
 }

 private String encodePath(
         String value) {

  return Arrays.stream(value.split("/"))
          .map(part ->
                  URLEncoder.encode(
                                  part,
                                  StandardCharsets.UTF_8)
                          .replace("+", "%20"))
          .reduce((a, b) -> a + "/job/" + b)
          .orElse("");
 }

 private String buildFormBody(
         Map<String, String> parameters) {

  StringJoiner joiner =
          new StringJoiner("&");

  parameters.forEach((key, value) ->
          joiner.add(
                  URLEncoder.encode(
                          key,
                          StandardCharsets.UTF_8)
                          + "="
                          + URLEncoder.encode(
                          value,
                          StandardCharsets.UTF_8)));

  return joiner.toString();
 }

 private Long parseQueueId(
         String location) {

  try {

   String cleaned =
           location.replaceAll("/+$", "");

   return Long.valueOf(
           cleaned.substring(
                   cleaned.lastIndexOf('/') + 1));

  } catch (Exception ex) {

   return null;
  }
 }
}