package cricinfo;

import java.util.List;

public class CricinfoSystem {
    private final MatchService matchService;
    private final ScoreCardService scorecardService;

    public CricinfoSystem() {
        matchService = MatchService.getInstance();
        scorecardService = ScoreCardService.getInstance();
    }

    public void addMatch(Match match) {
        matchService.addMatch(match);
    }

    public Match getMatch(String matchId) {
        return matchService.getMatch(matchId);
    }

    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    public void updateMatchStatus(String matchId, MatchStatus status) {
        matchService.updateMatchStatus(matchId, status);
    }

    public void createScorecard(Match match) {
        scorecardService.createScorecard(match);
    }

    public ScoreCard getScorecard(String scorecardId) {
        return scorecardService.getScorecard(scorecardId);
    }

    public void updateScore(String scorecardId, String teamId, int score) {
        scorecardService.updateScore(scorecardId, teamId, score);
    }

    public void addInnings(String scorecardId, Innings innings) {
        scorecardService.addInnings(scorecardId, innings);
    }
}
