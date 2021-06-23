package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.ChangeElection.ChangeElectionTitleQuery;
import com.electionController.structures.Response;

public class ChangeElectionTitle extends BaseOperation<ChangeElectionTitleQuery> {

    @Override
    protected String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected String getOperation() {
        return "ChangeElection/ChangeTitle";
    }

    @Override
    public Response executeAction() {
        return super.execute();
    }
}
