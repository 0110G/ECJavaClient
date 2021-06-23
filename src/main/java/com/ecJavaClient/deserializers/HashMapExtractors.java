package com.ecJavaClient.deserializers;

import com.electionController.structures.Contestant;
import com.electionController.structures.Election;
import com.electionController.structures.Post;
import com.electionController.structures.Voter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HashMapExtractors {
    public static final Election extractElectionFromResponse(HashMap<String , Object> responseMap) {
        Election election = new Election();
        election.setAdminVoterId((String) responseMap.get("adminVoterId"));
        election.setElectionId((String) responseMap.get("electionId"));
        election.setElectionTitle((String) responseMap.get("electionTitle"));
        election.setCreatedOn((Date) responseMap.get("createdOn"));
        election.setElectionDescription((String) responseMap.get("electionDescription"));
        for (HashMap<String, Object> mp : (List<HashMap<String, Object>>)responseMap.get("eligibleVoters")) {
            election.getEligibleVoters().add(extractVoterFromResponse(mp));
        }

        for (HashMap<String, Object> pm : (List<HashMap<String, Object>>)responseMap.get("availablePost")) {
            election.getAvailablePost().add(extractPostFromResponse(pm));
        }
        return election;
    }

    public static final Voter extractVoterFromResponse(HashMap <String, Object> responseMap) {
        Voter voter = new Voter();
        voter.setVoterId((String) responseMap.get("voterId"));
        voter.setVoterName((String) responseMap.get("voterName"));
        voter.setVoterPassword((String) responseMap.get("voterPassword"));
        voter.setElectionList(null);
        return voter;
    }

    public static final Post extractPostFromResponse(HashMap <String, Object> responseMap) {
        Post post = new Post();
        post.setPostId((String) responseMap.get("postId"));
        post.setPostIndex((Integer) responseMap.get("postIndex"));
        post.setPostDescription((String) responseMap.get("postDescription"));
        post.setElectionId((String) responseMap.get("electionId"));
        post.setWinCriteria(Post.WinCriteria.getWinCriteria((String) responseMap.get("winCriteria")));
        for (HashMap<String, Object> mp : (List<HashMap<String, Object>>) responseMap.get("contestants")) {
            post.getContestants().add(extractPostContestantFromResponse(mp));
        }
        return post;
    }

    public static final Contestant extractPostContestantFromResponse(HashMap <String, Object> responseMap) {
        Contestant contestant = new Contestant();
        contestant.setElectionList(null);
        contestant.setVoterId((String) responseMap.get("voterId"));
        contestant.setVoterName((String) responseMap.get("voterName"));
        contestant.setVoterPassword((String) responseMap.get("voterPassword"));
        contestant.setRank((Integer) responseMap.get("votesSecured"));
        contestant.setRank((Integer) responseMap.get("rank"));
        return contestant;
    }
}
