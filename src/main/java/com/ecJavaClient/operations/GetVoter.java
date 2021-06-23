package com.ecJavaClient.operations;

import com.ecJavaClient.deserializers.HashMapExtractors;
import com.electionController.structures.APIParams.GetVoterQuery;
import com.electionController.structures.Response;

import java.util.LinkedHashMap;

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
        Response response = super.execute();
        response.setResponse(
                HashMapExtractors.extractVoterFromResponse((LinkedHashMap<String, Object>)response.getResponse()));
        return response;
    }
}
