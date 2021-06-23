package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.ChangeElection.ChangeElectionDescriptionQuery;
import com.electionController.structures.Response;

public class ChangeElectionDescription extends BaseOperation<ChangeElectionDescriptionQuery> {

    @Override
    protected String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected String getOperation() {
        return "ChangeElection/ChangeDescription";
    }

    @Override
    public Response executeAction() {
        return super.execute();
    }
}
