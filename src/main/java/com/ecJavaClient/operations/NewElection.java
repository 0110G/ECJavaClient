package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.NewElectionQuery;
import com.electionController.structures.Response;

public class NewElection extends BaseOperation<NewElectionQuery, Response> {

    @Override
    protected final String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected final String getOperation() {
        return "kNewElection";
    }

    @Override
    public final Response executeAction() {
        System.out.println(super.execute());
        return null;
    }
}