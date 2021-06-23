package com.ecJavaClient.operations;

import com.electionController.structures.Response;

public class Ping extends BaseOperation<Object> {

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
        return super.execute();
    }
}