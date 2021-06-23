package com.ecJavaClient.operations;

import com.electionController.exceptions.*;
import com.electionController.structures.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * BaseOperation class needs to be implemented by
 * all the operations
 * @author bhavya saraf
 * @param <Query> - Type of query the operation requires
 * TODO: Refine the errors handling better
 */
public abstract class BaseOperation<Query> {

    protected abstract String getHttpRequestMethod();

    protected abstract String getOperation();

    public abstract Response executeAction();

    private String host = "localhost";
    public final BaseOperation<Query> withHost(final String host) {
        this.host = host;
        return this;
    }

    private int port = 8080;
    public final BaseOperation<Query> withPort(final int port) {
        this.port = port;
        return this;
    }

    private Query query;
    public final BaseOperation<Query> withQuery(final Query query) {
        if (query == null) {throw new InvalidParameterException("NULL_QUERY"); }
        this.query = query;
        return this;
    }

    protected final Response execute() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String payload = objectMapper.writeValueAsString(this.query);
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = null;
            switch (getHttpRequestMethod()) {
                case "GET":
                    response = get();
                    break;
                case "POST":
                    response = post(payload);
                    break;
                case "DELETE":
                    response = delete(payload);
                    break;
                case "PUT":
                    break;
                default:
                    throw new IllegalStateException("INVALID_HTTP_REQUEST_TYPE: " + getHttpRequestMethod());
            }
            if (response == null) {
                throw new NullPointerException("SERVER_RETURNED_NULL_RESPONSE");
            }
            return interceptErrors(objectMapper.readValue(response.getBody(), Response.class));
        } catch (UnirestException ex) {
            ex.printStackTrace();
            throw new InternalServiceException("CANNOT_CONNECT_TO_THE_SERVER:" + ex.getMessage());
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new InternalServiceException("RESPONSE_PARSE_ERROR:" + ex.getMessage());
        }
    }

    private final String buildUrl(final String host, final int port, final String operation) {
        return "http://" + host + ":" + port + "/" + operation;
    }

    private final HttpResponse<String> get() throws UnirestException {
        return Unirest.get(buildUrl(this.host, this.port, getOperation()))
                .header("Content-Type", "application/json")
                .asString();
    }

    private final HttpResponse<String> post(final String payload) throws UnirestException {
        return Unirest.post(buildUrl(host, port, getOperation()))
                .header("Content-Type", "application/json")
                .body(payload)
                .asString();
    }

    private final HttpResponse<String> delete(final String payload) throws UnirestException {
        return Unirest.delete(buildUrl(host, port, getOperation()))
                .header("Content-Type", "application/json")
                .body(payload)
                .asString();
    }

    private final Response interceptErrors(final Response ecResponse) {
        if (ecResponse == null) {throw new NullPointerException("SERVER_RETURNED_NULL_RESPONSE");}
        System.out.println(ecResponse.getStatusCode());
        switch (ecResponse.getStatusCode()/100) {
            case 2:
                return ecResponse;
            case 3:
                throw new InvalidParameterException(ecResponse.getStatusCode(),
                        ecResponse.getStatus(), "");
            case 4:
                throw new InternalServiceException(ecResponse.getStatusCode(),
                        ecResponse.getStatus(), "");
            case 5:
                throw new RestrictedActionException(ecResponse.getStatusCode(),
                        ecResponse.getStatus(), "");
            case 6:
                throw new EntityNotFoundException(ecResponse.getStatusCode(),
                        ecResponse.getStatus(), "");
            case 7:
                throw new InvalidCredentialException(ecResponse.getStatusCode(),
                        ecResponse.getStatus(), "");
            default:
                throw new InternalServiceException(ecResponse.getStatusCode(),
                        ecResponse.getStatus());
        }
    }
}
