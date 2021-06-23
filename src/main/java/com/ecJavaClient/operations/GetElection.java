package com.ecJavaClient.operations;

import com.ecJavaClient.deserializers.HashMapExtractors;
import com.electionController.structures.APIParams.GetElectionQuery;
import com.electionController.structures.Response;

import java.util.LinkedHashMap;

public class GetElection extends BaseOperation<GetElectionQuery> {

    @Override
    protected String getHttpRequestMethod() {
        return "POST";
    }

    @Override
    protected String getOperation() {
        return "GetElection";
    }

    @Override
    public Response executeAction() {
        Response response = super.execute();
        response.setResponse(HashMapExtractors
                .extractElectionFromResponse((LinkedHashMap<String, Object>)response.getResponse()));
        return response;
    }
}
