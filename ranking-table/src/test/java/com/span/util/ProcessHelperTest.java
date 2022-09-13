package com.span.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.span.pojo.MatchResult;

class ProcessHelperTest {
	
	private ProcessHelper processHelper = new ProcessHelper();


	@Test
	void testProcessInput() {
		
		List<MatchResult> results = null;
		
		List<String> teamNames = new ArrayList<>();
		teamNames.add("Lions");
		teamNames.add("Snakes");
		teamNames.add("Tarantulas");
		teamNames.add("FC Awesome");
		teamNames.add("Grouches");
		teamNames.add("Bears");

		// test case 1
		// all matches tie
		// all teams -------> 1 point
		processHelper.processInput("Lions 0, Snakes 0");
		processHelper.processInput("Tarantulas 0, FC Awesome 0");
		results = processHelper.processInput("Grouches 0, Bears 0");

		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			Assertions.assertEquals(1, score);
		}

		// test case 2
		// all matches tie again
		// all teams -------> 2 points
		processHelper.processInput("Lions 0, Snakes 0");
		processHelper.processInput("Tarantulas 0, FC Awesome 0");
		results = processHelper.processInput("Grouches 0, Bears 0");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			Assertions.assertEquals(2, score);
		}
				
		// test case 3
		// Tarantulas +3 points, FC awesome +0 points
		// Tarantulas ------------> 5 points
		// remaining teams -------> 2 points
		results = processHelper.processInput("Tarantulas 1, FC Awesome 0");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Tarantulas":
					Assertions.assertEquals(5, score);
				break;
				default:
					Assertions.assertEquals(2, score);
			}
		}

		// test case 4
		// Bears +0 points, Lions +3 points
		// Lions  ------------> 5 points
		// Tarantulas --------> 5 points
		// remaining teams ---> 2 points
		results = processHelper.processInput("Bears 1, Lions 2");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Tarantulas":
				case "Lions":
					Assertions.assertEquals(5, score);
				break;
				default:
					Assertions.assertEquals(2, score);
			}
		}


		// test case 5
		// Snakes +1 point, Grouches +1 point
		// Lions  ------------> 5 points
		// Tarantulas --------> 5 points
		// Grouches ----------> 3 points
		// Snakes ------------> 3 points
		// remaining teams ---> 2 points
		results = processHelper.processInput("Snakes 3, Grouches 3");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Tarantulas":
				case "Lions":
					Assertions.assertEquals(5, score);
					break;
				case "Grouches":
				case "Snakes":
					Assertions.assertEquals(3, score);
					break;
				default:
					Assertions.assertEquals(2, score);
			}
			
		}

		// test case 6
		// Snakes +3 points, Bears +0 points
		// Snakes ------------> 6 points
		// Lions  ------------> 5 points
		// Tarantulas --------> 5 points
		// Grouches ----------> 3 points
		// remaining teams ---> 2 points
		results = processHelper.processInput("Snakes 1, Bears 0");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Snakes":
					Assertions.assertEquals(6, score);
					break;
				case "Tarantulas":
				case "Lions":
					Assertions.assertEquals(5, score);
					break;
				case "Grouches":
					Assertions.assertEquals(3, score);
					break;
				default:
					Assertions.assertEquals(2, score);
			}
			
		}

		// test case 7
		// Tarantulas +3 points, Snakes +0 points
		// Tarantulas --------> 8 points
		// Snakes ------------> 6 points
		// Lions  ------------> 5 points
		// Grouches ----------> 3 points
		// remaining teams ---> 2 points
		results = processHelper.processInput("Tarantulas 2, Snakes 1");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Tarantulas":
					Assertions.assertEquals(8, score);
					break;
				case "Snakes":
					Assertions.assertEquals(6, score);
					break;
				case "Lions":
					Assertions.assertEquals(5, score);
					break;
				case "Grouches":
					Assertions.assertEquals(3, score);
					break;
				default:
					Assertions.assertEquals(2, score);
			}
			
		}

		// test case 8
		// tie, Grouches +1 point, Bears +1 point
		// Tarantulas --------> 8 points
		// Snakes ------------> 6 points
		// Lions  ------------> 5 points
		// Grouches ----------> 4 points
		// Bears -------------> 3 points
		// FC Awesome----- ---> 2 points
		results = processHelper.processInput("Grouches 2, Bears 2");
		
		Assertions.assertEquals(6, results.size());
		
		for (MatchResult result : results) {
			String name = result.getTeamName();
			int score = result.getScore();
			
			Assertions.assertTrue(teamNames.contains(name));
			
			switch (name) {
				case "Tarantulas":
					Assertions.assertEquals(8, score);
					break;
				case "Snakes":
					Assertions.assertEquals(6, score);
					break;
				case "Lions":
					Assertions.assertEquals(5, score);
					break;
				case "Grouches":
					Assertions.assertEquals(4, score);
					break;
				case "Bears":
					Assertions.assertEquals(3, score);
					break;
				default:
					Assertions.assertEquals(2, score); // FC Awesome
			}
			
		}

	}
	
	@Test
	void testSortByScoreAndThenAlphabetically() {
		// all have 1 point
		// sorting alphabetically
		List<MatchResult> results = new ArrayList<>();
		results.add(new MatchResult("Tarantulas", 1));
		results.add(new MatchResult("Snakes", 1));
		results.add(new MatchResult("Bears", 1));
		results.add(new MatchResult("Lions", 1));
		results.add(new MatchResult("Grouches", 1));
		processHelper.sortByScoreAndThenAlphabetically(results);
		
		Assertions.assertEquals("Bears", results.get(0).getTeamName());
		Assertions.assertEquals("Grouches", results.get(1).getTeamName());
		Assertions.assertEquals("Lions", results.get(2).getTeamName());
		Assertions.assertEquals("Snakes", results.get(3).getTeamName());
		Assertions.assertEquals("Tarantulas", results.get(4).getTeamName());
		
		// Tarantulas have 2 points
		// remaining teams have 1 point
		// sorting alphabetically
		results.clear();
		results.add(new MatchResult("Tarantulas", 2));
		results.add(new MatchResult("Snakes", 1));
		results.add(new MatchResult("Bears", 1));
		results.add(new MatchResult("Lions", 1));
		results.add(new MatchResult("Grouches", 1));
		processHelper.sortByScoreAndThenAlphabetically(results);
		
		Assertions.assertEquals("Tarantulas", results.get(0).getTeamName());
		Assertions.assertEquals("Bears", results.get(1).getTeamName());
		Assertions.assertEquals("Grouches", results.get(2).getTeamName());
		Assertions.assertEquals("Lions", results.get(3).getTeamName());
		Assertions.assertEquals("Snakes", results.get(4).getTeamName());
		
		// sorted by score 
		// all have unique ranks so no alphabetical order is applied
		results.clear();
		results.add(new MatchResult("Tarantulas", 4));
		results.add(new MatchResult("Snakes", 1));
		results.add(new MatchResult("Bears", 2));
		results.add(new MatchResult("Lions", 3));
		results.add(new MatchResult("Grouches", 5));
		processHelper.sortByScoreAndThenAlphabetically(results);
		
		Assertions.assertEquals("Grouches", results.get(0).getTeamName());
		Assertions.assertEquals("Tarantulas", results.get(1).getTeamName());
		Assertions.assertEquals("Lions", results.get(2).getTeamName());
		Assertions.assertEquals("Bears", results.get(3).getTeamName());
		Assertions.assertEquals("Snakes", results.get(4).getTeamName());
	}

	@Test
	void testParseToMatchResult() {
		MatchResult matchResult1 = processHelper.parseToMatchResult("Lions 3");
		Assertions.assertEquals("Lions", matchResult1.getTeamName());
		Assertions.assertEquals(3, (int) matchResult1.getScore());

		MatchResult matchResult2 = processHelper.parseToMatchResult("Snakes 10");
		Assertions.assertEquals("Snakes", matchResult2.getTeamName());
		Assertions.assertEquals(10, (int) matchResult2.getScore());

		MatchResult matchResult3 = processHelper.parseToMatchResult("FC Awesome 0");
		Assertions.assertEquals("FC Awesome", matchResult3.getTeamName());
		Assertions.assertEquals(0, (int) matchResult3.getScore());

		MatchResult matchResult4 = processHelper.parseToMatchResult("FC Awesome Lions Snakes    1023420");
		Assertions.assertEquals("FC Awesome Lions Snakes", matchResult4.getTeamName());
		Assertions.assertEquals(1023420, (int) matchResult4.getScore());

		// Unexpected input, added for test coverage purposes
		MatchResult matchResult5 = processHelper.parseToMatchResult("");
		Assertions.assertEquals("", matchResult5.getTeamName());
		Assertions.assertNull(matchResult5.getScore());
	}

	@Test
	void testGenerateRankingResults() {
		// test case 1
		// all matches tie
		// all share #1 rank, sorting alphabetically
		// 1 point should show "1 pt"
		List<MatchResult> results = new ArrayList<>();
		results.add(new MatchResult("Lions", 1));
		results.add(new MatchResult("Snakes", 1));
		results.add(new MatchResult("Tarantulas", 1));
		results.add(new MatchResult("FC Awesome", 1));
		results.add(new MatchResult("Grouches", 1));
		results.add(new MatchResult("Bears", 1));
		List<String> output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Bears, 1 pt", output.get(0));
		Assertions.assertEquals("1. FC Awesome, 1 pt", output.get(1));
		Assertions.assertEquals("1. Grouches, 1 pt", output.get(2));
		Assertions.assertEquals("1. Lions, 1 pt", output.get(3));
		Assertions.assertEquals("1. Snakes, 1 pt", output.get(4));
		Assertions.assertEquals("1. Tarantulas, 1 pt", output.get(5));

		// test case 2
		// all matches tie again
		// all share #1 rank, sorting alphabetically
		// 2 points should show "2 pts"
		results.clear();
		results.add(new MatchResult("Lions", 2));
		results.add(new MatchResult("Snakes", 2));
		results.add(new MatchResult("Tarantulas", 2));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 2));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Bears, 2 pts", output.get(0));
		Assertions.assertEquals("1. FC Awesome, 2 pts", output.get(1));
		Assertions.assertEquals("1. Grouches, 2 pts", output.get(2));
		Assertions.assertEquals("1. Lions, 2 pts", output.get(3));
		Assertions.assertEquals("1. Snakes, 2 pts", output.get(4));
		Assertions.assertEquals("1. Tarantulas, 2 pts", output.get(5));

		// test case 3
		// Tarantulas leading, should get #1
		// other teams share #2
		// teams with #2 rank are sorted alphabetically
		results.clear();
		results.add(new MatchResult("Lions", 2));
		results.add(new MatchResult("Snakes", 2));
		results.add(new MatchResult("Tarantulas", 5));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 2));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Tarantulas, 5 pts", output.get(0));
		Assertions.assertEquals("2. Bears, 2 pts", output.get(1));
		Assertions.assertEquals("2. FC Awesome, 2 pts", output.get(2));
		Assertions.assertEquals("2. Grouches, 2 pts", output.get(3));
		Assertions.assertEquals("2. Lions, 2 pts", output.get(4));
		Assertions.assertEquals("2. Snakes, 2 pts", output.get(5));

		// test case 4
		// Lions and Tarantulas share #1 rank
		// Lions should be listed before Tarantulas
		// other teams share #3 rank and should be sorted alphabetically
		results.clear();
		results.add(new MatchResult("Lions", 5));
		results.add(new MatchResult("Snakes", 2));
		results.add(new MatchResult("Tarantulas", 5));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 2));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Lions, 5 pts", output.get(0));
		Assertions.assertEquals("1. Tarantulas, 5 pts", output.get(1));
		Assertions.assertEquals("3. Bears, 2 pts", output.get(2));
		Assertions.assertEquals("3. FC Awesome, 2 pts", output.get(3));
		Assertions.assertEquals("3. Grouches, 2 pts", output.get(4));
		Assertions.assertEquals("3. Snakes, 2 pts", output.get(5));

		// test case 5
		// Lions and Tarantulas share #1 rank
		// Lions should be listed before Tarantulas
		// Grouches and Snakes share #3
		// Grouches should be listed before Snakes
		// Bears and FC Awesome share #5 rank
		// Bears should be listed before FC Awesome
		results.clear();
		results.add(new MatchResult("Lions", 5));
		results.add(new MatchResult("Snakes", 3));
		results.add(new MatchResult("Tarantulas", 5));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 3));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Lions, 5 pts", output.get(0));
		Assertions.assertEquals("1. Tarantulas, 5 pts", output.get(1));
		Assertions.assertEquals("3. Grouches, 3 pts", output.get(2));
		Assertions.assertEquals("3. Snakes, 3 pts", output.get(3));
		Assertions.assertEquals("5. Bears, 2 pts", output.get(4));
		Assertions.assertEquals("5. FC Awesome, 2 pts", output.get(5));

		// test case 6
		// Snake #1 rank
		// Lions and Tarantulas share #2 rank 
		// Lions should be listed before Tarantulas
		// Grouches #4
		// Bears and FC Awesome share #2 rank
		// Bears should be listed before FC Awesome
		results.clear();
		results.add(new MatchResult("Lions", 5));
		results.add(new MatchResult("Snakes", 6));
		results.add(new MatchResult("Tarantulas", 5));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 3));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Snakes, 6 pts", output.get(0));
		Assertions.assertEquals("2. Lions, 5 pts", output.get(1));
		Assertions.assertEquals("2. Tarantulas, 5 pts", output.get(2));
		Assertions.assertEquals("4. Grouches, 3 pts", output.get(3));
		Assertions.assertEquals("5. Bears, 2 pts", output.get(4));
		Assertions.assertEquals("5. FC Awesome, 2 pts", output.get(5));

		// test case 7
		// Tarantulas #1 rank
		// Snakes #2 rank
		// Lions #3 rank
		// Grouches #4 rank
		// Bears and FC Awesome share rank #5
		// Bears should be listed before FC Awesome
		results.clear();
		results.add(new MatchResult("Lions", 5));
		results.add(new MatchResult("Snakes", 6));
		results.add(new MatchResult("Tarantulas", 8));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 3));
		results.add(new MatchResult("Bears", 2));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Tarantulas, 8 pts", output.get(0));
		Assertions.assertEquals("2. Snakes, 6 pts", output.get(1));
		Assertions.assertEquals("3. Lions, 5 pts", output.get(2));
		Assertions.assertEquals("4. Grouches, 3 pts", output.get(3));
		Assertions.assertEquals("5. Bears, 2 pts", output.get(4));
		Assertions.assertEquals("5. FC Awesome, 2 pts", output.get(5));

		// test case 8
		// All have unique ranks, no need to sort alphabetically
		results.clear();
		results.add(new MatchResult("Lions", 5));
		results.add(new MatchResult("Snakes", 6));
		results.add(new MatchResult("Tarantulas", 8));
		results.add(new MatchResult("FC Awesome", 2));
		results.add(new MatchResult("Grouches", 4));
		results.add(new MatchResult("Bears", 3));
		output = processHelper.generateRankingResults(results);

		Assertions.assertEquals("1. Tarantulas, 8 pts", output.get(0));
		Assertions.assertEquals("2. Snakes, 6 pts", output.get(1));
		Assertions.assertEquals("3. Lions, 5 pts", output.get(2));
		Assertions.assertEquals("4. Grouches, 4 pts", output.get(3));
		Assertions.assertEquals("5. Bears, 3 pts", output.get(4));
		Assertions.assertEquals("6. FC Awesome, 2 pts", output.get(5));
	}

}
