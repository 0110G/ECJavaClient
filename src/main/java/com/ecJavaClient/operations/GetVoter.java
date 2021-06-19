package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.GetVoterQuery;
import com.electionController.structures.Response;

public class GetVoter extends BaseOperation<GetVoterQuery>{

    @Override
    protected final String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected final String getOperation() {
        return "GetVoter";
    }

    @Override
    public final Response executeAction() {
        return super.execute();
    }
}
