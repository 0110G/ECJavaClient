import com.ecJavaClient.operations.*;
import com.electionController.exceptions.InternalServiceException;
import com.electionController.structures.APIParams.ChangeElection.ChangeElectionDescriptionQuery;
import com.electionController.structures.APIParams.ChangeElection.DeleteRegisteredVoterFromElectionQuery;
import com.electionController.structures.APIParams.GetVoterQuery;
import com.electionController.structures.APIParams.NewElectionQuery;
import com.electionController.structures.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try {
            runDeleteElectionVoter();
        } catch (InternalServiceException ex) {
            System.out.println(ex.getErrorMessage() + "adwawdaw555555555");
        }
    }

    private static void runNewElection() {
        System.out.println("STARTING runNewElection()");
        List<String> registeredVoters = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6"));
        List<String> post1Cand = new ArrayList<>(Arrays.asList("2", "2"));
        List<String> post2Cand = new ArrayList<>(Arrays.asList("3", "4", "5", "7"));
        List<NewElectionQuery.Post> posts = new ArrayList<>(Arrays
                .asList(buildPost("Post1", post1Cand),
                        buildPost("Post2", post2Cand)
                ));
        NewElectionQuery electionQuery = NewElectionQuery.Builder()
                .withElectionTitle("ELECTION_TITLE")
                .withElectionDescription("ELECTION_DESC")
                .withRegisteredPost(posts)
                .withRegisteredVoters(registeredVoters)
                .withVoterId("1")
                .withVoterPassword("111")
                .build();
        Response response = new NewElection()
                .withHost("localhost")
                .withPort(8080)
                .withQuery(electionQuery)
                .executeAction();
        System.out.println("COMPLETED with Response: " + response.getResponse());
    }

    private static NewElectionQuery.Post buildPost(final String postDesc, final List<String> registeredCandidates) {
        return NewElectionQuery.Post.Builder()
                .withRegisteredContestants(registeredCandidates)
                .withPostDescription(postDesc)
                .build();
    }

    private static void runPing() {
        System.out.println("STARTING runPing()");
        Response response = new Ping()
                .withHost("localhost")
                .withPort(8080)
                .executeAction();
        System.out.println("COMPLETED with Response: " + response);
    }

    private static void runChangeElectionDescription() {
        System.out.println("STARTING runChangeElectionDescription()");
        ChangeElectionDescriptionQuery changeElectionDescriptionQuery =
                ChangeElectionDescriptionQuery.Builder()
                    .withElectionDescription("NEW_DESCRIPTION")
                    .withElectionId("0")
                    .withVoterId("1")
                    .withVoterPassword("111")
                    .build();

        Response response = new ChangeElectionDescription()
                .withQuery(changeElectionDescriptionQuery)
                .executeAction();
        System.out.println("COMPLETED with Response: " + response);
    }

    private static void runGetVoter() {
        System.out.println("STARTING runGetVoter()");
        GetVoterQuery getVoterQuery = GetVoterQuery.Builder()
                .withVoterId("2")
                .withVoterPassword("222")
                .build();

        Response response = new GetVoter()
                .withQuery(getVoterQuery)
                .executeAction();
        System.out.println("COMPLETED with Response: " + response);
    }

    private static void runDeleteElectionVoter() {
        System.out.println("STARTING runDeleteElectionVoter()");
        List<String> voterToDelete = Arrays.asList("1", "2", "3", "4");
        DeleteRegisteredVoterFromElectionQuery deleteRegisteredVoterFromElectionQuery =
                DeleteRegisteredVoterFromElectionQuery.Builder()
                    .withElectionId("0")
                    .withVoterId("1")
                    .withVoterPassword("111")
                    .withVotersToDelete(voterToDelete)
                    .withForceDelete(true)
                    .build();
        Response response = new DeleteElectionVoter()
                .withQuery(deleteRegisteredVoterFromElectionQuery)
                .executeAction();
        System.out.println("COMPLETED with Response: " + response);
    }


}
