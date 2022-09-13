package com.span.pojo;

public class MatchResult {

	private String teamName;
	private Integer score;

	public MatchResult() {
		super();
	}

	public MatchResult(String teamName, Integer score) {
		super();
		this.teamName = teamName;
		this.score = score;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
