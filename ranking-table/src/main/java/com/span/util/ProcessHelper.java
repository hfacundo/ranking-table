package com.span.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.span.dao.RankingDao;
import com.span.dao.impl.RankingDaoImpl;
import com.span.pojo.MatchResult;

/**
 * 
 * @author Harry Facundo Hernández
 *
 */
public class ProcessHelper {

	private static final RankingDao rankingDao = new RankingDaoImpl();

	/**
	 * 
	 * @param input
	 * 
	 *  Example 1: Lions 3, Snakes 3 
	 *  Example 2: Tarantulas 1, FC Awesome 0 
	 *  Example 3: Lions 1, FC Awesome 1
	 * 
	 */
	public List<MatchResult> processInput(String input) {
		String[] array = input.split(",");
		MatchResult resultsTeamA = parseToMatchResult(array[0]);
		MatchResult resultsTeamB = parseToMatchResult(array[1]);

		if (resultsTeamA.getScore() > resultsTeamB.getScore()) { // team A wins, +3 points, team B loses, +0 points
			rankingDao.add(resultsTeamA.getTeamName(), 3);
			rankingDao.add(resultsTeamB.getTeamName(), 0);
		} else if (resultsTeamB.getScore() > resultsTeamA.getScore()) { // team B wins, +3 points, team A loses, +0 points
			rankingDao.add(resultsTeamB.getTeamName(), 3);
			rankingDao.add(resultsTeamA.getTeamName(), 0);
		} else { // it's a tie, +1 point for team A and +1 point for team B
			rankingDao.add(resultsTeamB.getTeamName(), 1);
			rankingDao.add(resultsTeamA.getTeamName(), 1);
		}

		List<MatchResult> currentResults = rankingDao.findAll();
		return currentResults;
	}

	protected void sortByScoreAndThenAlphabetically(List<MatchResult> currentResults) {
		Collections.sort(currentResults,
				Comparator.comparing(MatchResult::getScore).reversed().thenComparing(MatchResult::getTeamName));
	}

	/**
	 * 
	 * @param result Convert the result of a single team score to a MatchResult
	 *               object.
	 * 
	 * 	Example 1: Lions 3 
	 * 	Example 2: FC Awesome 2 
	 * 	Example 3: Snakes 0
	 * 
	 */
	protected MatchResult parseToMatchResult(String result) {

		MatchResult matchResult = new MatchResult();
		StringBuilder builder = new StringBuilder();

		for (int index = 0; index < result.length(); index++) {
			char c = result.charAt(index);
			if (!Character.isDigit(c)) {
				builder.append(c);
			} else {
				String scoreString = result.substring(index, result.length());
				matchResult.setScore(Integer.parseInt(scoreString));
				break;
			}
		}

		matchResult.setTeamName(builder.toString().trim());

		return matchResult;
	}

	/**
	 * 
	 * @param sortedRankingList The list of MatchResult objects previously sorted
	 * 
	 */
	public List<String> generateRankingResults(List<MatchResult> currentResults) {
		
		sortByScoreAndThenAlphabetically(currentResults);
		
		int index = 0;
		int rank = 1;

		List<String> output = new ArrayList<>();
		while (index < currentResults.size()) {
			MatchResult matchResult = currentResults.get(index);
			StringBuilder outputLine = new StringBuilder();
			if (output.size() > 0) {
				MatchResult previous = currentResults.get(index - 1);
				if (matchResult.getScore() == previous.getScore()) {
					outputLine.append(rank);
				} else {
					outputLine.append(index + 1);
					rank = index + 1;
				}
			} else {
				outputLine.append(rank);
			}
			outputLine.append(". ").append(matchResult.getTeamName()).append(", ").append(matchResult.getScore())
					.append(matchResult.getScore() == 1 ? " pt" : " pts");
			output.add(outputLine.toString());
			index++;
		}

		return output;
	}

}
