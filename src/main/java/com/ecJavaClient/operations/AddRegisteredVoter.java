package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.ChangeElection.AddRegisteredVoterToElectionQuery;
import com.electionController.structures.Response;

public class AddRegisteredVoter extends BaseOperation<AddRegisteredVoterToElectionQuery> {

    @Override
    protected final String getHttpRequestMethod() {
        return "PUT";
    }

    @Override
    protected final String getOperation() {
        return "/ChangeElection/AddVoters";
    }

    @Override
    public final Response executeAction() {
        return super.execute();
    }
}