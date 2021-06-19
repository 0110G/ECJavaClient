import com.ecJavaClient.operations.GetVoter;
import com.ecJavaClient.operations.NewElection;
import com.ecJavaClient.operations.Ping;
import com.electionController.exceptions.InternalServiceException;
import com.electionController.structures.APIParams.GetVoterQuery;
import com.electionController.structures.APIParams.NewElectionQuery;
import com.electionController.structures.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try {
            new Ping()
                    .withHost("localhost")
                    .withPort(8080)
                    .executeAction();

            List<String> registeredVoters = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6"));
            List<String> post1Cand = new ArrayList<>(Arrays.asList("2", "2"));
            List<String> post2Cand = new ArrayList<>(Arrays.asList("3", "4", "555", "7"));
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
            System.out.println(response.getResponse().toString() + "555adw");
        } catch (InternalServiceException ex) {
            System.out.println(ex.getErrorMessage() + "adwawdaw555555555");
        }
    }

    private static NewElectionQuery.Post buildPost(final String postDesc, final List<String> registeredCandidates) {
        return NewElectionQuery.Post.Builder()
                .withRegisteredContestants(registeredCandidates)
                .withPostDescription(postDesc)
                .build();
    }
}
