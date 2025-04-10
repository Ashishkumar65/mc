package cricinfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreCardService {
    private static ScoreCardService instance;
    private final Map<String, ScoreCard> scorecards;
    private final AtomicInteger scorecardIdCounter;
    private ScoreCardService() {
        scorecards = new ConcurrentHashMap<>();
        scorecardIdCounter = new AtomicInteger(0);
    }
    public static synchronized ScoreCardService getInstance() {
        if (instance == null) {
            instance = new ScoreCardService();
        }
        return instance;
    }
    public synchronized void createScorecard(Match match) {
        String scorecardId = generateScorecardId(match.getId());
        ScoreCard scorecard = new ScoreCard(scorecardId, match);
        scorecards.put(scorecardId, scorecard);
    }

    public ScoreCard getScorecard(String scorecardId) {
        return scorecards.get(scorecardId);
    }

    public void updateScore(String scorecardId, String teamId, int score) {
        ScoreCard scorecard = scorecards.get(scorecardId);
        if (scorecard != null) {
            scorecard.updateScore(teamId, score);
        }
    }

    public void addInnings(String scorecardId, Innings innings) {
        ScoreCard scorecard = scorecards.get(scorecardId);
        if (scorecard != null) {
            scorecard.addInnings(innings);
        }
    }

    private String generateScorecardId(String matchId) {
        int scorecardId = scorecardIdCounter.incrementAndGet();
        return "SC-" + matchId + "-" + String.format("%04d", scorecardId);
    }

}
