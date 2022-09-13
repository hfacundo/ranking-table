package com.span.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.span.dao.RankingDao;
import com.span.pojo.MatchResult;

public class RankingDaoImpl implements RankingDao {
	

	/**
	 * Object where all the user inputs will be stored and will also be used to read
	 * data to produce a result.
	 */
	private Map<String, MatchResult> resultsTable = new HashMap<>();

	@Override
	public void add(String teamName, int score) {
		if (resultsTable.containsKey(teamName)) {
			MatchResult newMatchResult = resultsTable.get(teamName);
			int newScore = newMatchResult.getScore() + score;
			newMatchResult.setScore(newScore);
			resultsTable.put(teamName, newMatchResult);
		} else {
			resultsTable.put(teamName, new MatchResult(teamName, score));
		}
	}

	@Override
	public List<MatchResult> findAll() {
		return new ArrayList<>(resultsTable.values());
	}

}
