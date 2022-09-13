package com.span.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.span.dao.RankingDao;
import com.span.pojo.MatchResult;

class RankingDaoImplTest {

	private RankingDao rankingDao = new RankingDaoImpl();

	@Test
	public void testAdd() {
		List<String> teamNames = new ArrayList<>();
		teamNames.add("Snakes");
		teamNames.add("FC Awesome");
		teamNames.add("Bears");
		teamNames.add("Lions");

		List<MatchResult> results = rankingDao.findAll();
		Assertions.assertEquals(0, results.size());

		rankingDao.add("Bears", 1);
		rankingDao.add("Snakes", 2);
		rankingDao.add("Lions", 3);
		rankingDao.add("FC Awesome", 4);
		results = rankingDao.findAll();
		Assertions.assertEquals(4, results.size());

		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();

			Assertions.assertTrue(teamNames.contains(name));

			switch (name) {
			case "Bears":
				Assertions.assertEquals(1, score);
				break;
			case "Snakes":
				Assertions.assertEquals(2, score);
				break;
			case "Lions":
				Assertions.assertEquals(3, score);
				break;
			case "FC Awesome":
				Assertions.assertEquals(4, score);
				break;
			default:

			}
		}

	}

	@Test
	public void testFindAll() {
		List<MatchResult> results = rankingDao.findAll();
		Assertions.assertEquals(0, results.size());
		
		rankingDao.add("Bears", 1);
		rankingDao.add("Snakes", 2);
		rankingDao.add("Lions", 3);
		rankingDao.add("FC Awesome", 4);
		results = rankingDao.findAll();
		Assertions.assertEquals(4, results.size());
	}

}
