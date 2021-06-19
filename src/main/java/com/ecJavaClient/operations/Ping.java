package com.ecJavaClient.operations;

import com.electionController.structures.Response;

public class Ping extends BaseOperation<Object, Response> {

    @Override
    protected final String getHttpRequestMethod() {
        return "GET";
    }

    @Override
    protected final String getOperation() {
        return "Ping";
    }

    @Override
    public final Response executeAction() {
        String resp = super.execute();
        return null;
    }
}