package com.ecJavaClient.operations;

import com.electionController.exceptions.InvalidParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;

public abstract class BaseOperation<Query, Response> {

    protected abstract String getHttpRequestMethod();

    protected abstract String getOperation();

    public abstract Response executeAction();

    private String host = "localhost";
    public final BaseOperation<Query, Response> withHost(final String host) {
        this.host = host;
        return this;
    }

    private int port = 8080;
    public final BaseOperation<Query, Response> withPort(final int port) {
        this.port = port;
        return this;
    }

    private Query query;
    public final BaseOperation<Query, Response> withQuery(final Query query) {
        if (query == null) {throw new InvalidParameterException("NULL_QUERY"); }
        this.query = query;
        return this;
    }

    protected final String execute() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String payload = objectMapper.writeValueAsString(this.query);
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response;
            switch (getHttpRequestMethod()) {
                case "GET":
                    response = Unirest.get(buildUrl(this.host, this.port, getOperation()))
                            .header("Content-Type", "application/json")
                            .asString();
                    break;
                case "POST":
                    System.out.println(buildUrl(host, port, getOperation()));
                    response = Unirest.post(buildUrl(host, port, getOperation()))
                            .header("Content-Type", "application/json")
                            .body(payload)
                            .asString();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + getHttpRequestMethod());
            }
            return response.getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String buildUrl(final String host, final int port, final String operation) {
        return "http://" + host + ":" + port + "/" + operation;
    }
}
