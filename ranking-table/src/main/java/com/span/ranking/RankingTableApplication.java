package com.span.ranking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.span.pojo.MatchResult;
import com.span.util.ProcessHelper;

public class RankingTableApplication {

	protected static void rank(ProcessHelper processHelper, BufferedReader br) {
		try {
			System.out.println("Enter input: ");
			String input = br.readLine();

			List<MatchResult> results = processHelper.processInput(input);
			List<String> output = processHelper.generateRankingResults(results);

			output.forEach(System.out::println);
			System.out.println();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static void main(String[] args) {
		ProcessHelper processHelper = new ProcessHelper();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			rank(processHelper, br);
		}
	}

}
