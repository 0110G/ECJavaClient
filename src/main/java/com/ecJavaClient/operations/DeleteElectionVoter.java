package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.ChangeElection.DeleteRegisteredVoterFromElectionQuery;
import com.electionController.structures.Response;

public class DeleteElectionVoter extends BaseOperation<DeleteRegisteredVoterFromElectionQuery> {

    @Override
    protected String getHttpRequestMethod() {
        return "DELETE";
    }

    @Override
    protected String getOperation() {
        return "ChangeElection/DeleteVoters";
    }

    @Override
    public Response executeAction() {
        return super.execute();
    }
}
