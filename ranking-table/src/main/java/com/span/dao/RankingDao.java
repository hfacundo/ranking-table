package com.span.dao;

import java.util.List;

import com.span.pojo.MatchResult;

public interface RankingDao {

	void add(String teamName, int score);

	List<MatchResult> findAll();
}
