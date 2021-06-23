package com.ecJavaClient.operations;

import com.electionController.structures.APIParams.GetElectionResultsQuery;
import com.electionController.structures.Response;

public class GetElectionResults extends BaseOperation<GetElectionResultsQuery> {

    @Override
    protected String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected String getOperation() {
        return "GetResults";
    }

    @Override
    public Response executeAction() {
        return super.execute();
    }
}
