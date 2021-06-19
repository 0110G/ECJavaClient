package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.NewElectionQuery;
import com.electionController.structures.Response;

public class NewElection extends BaseOperation<NewElectionQuery> {

    @Override
    protected final String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected final String getOperation() {
        return "NewElection";
    }

    @Override
    public final Response executeAction() {
        Response response = super.execute();
        return response;
    }
}