package de.uniba.wiai.dsg.ajp.assignment1;

import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinder;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;
import de.uniba.wiai.dsg.ajp.assignment1.search.impl.SimpleTokenFinder;
import de.uniba.wiai.dsg.ajp.assignment1.util.Timer;

public class Main {

	public static void main(String[] args) throws TokenFinderException {
		validate(args);
		SearchTask searchTask = toSearchTask(args);
		print(searchTask);
		search(searchTask);
	}

	private static void validate(String[] args) {
		if (args.length != 5) {
			printUsage();
			throw new IllegalArgumentException("expected 5 arguments, given only " + args.length);
		}
	}

	private static void printUsage() {
		System.out.println("Usage: ");
		System.out.println("ROOT_FOLDER IGNORE_FILE FILE_EXTENSION SEARCH_TOKEN RESULT_FILE");
	}

	private static SearchTask toSearchTask(String[] args) {
		return new SearchTask(args[0], args[1], args[2], args[3], args[4]);
	}

	private static void print(SearchTask searchTask) {
		System.out.println("Processing the given input as follows:");
		System.out.println("Root Folder: " + searchTask.getRootFolder());
		System.out.println("Ignore File: " + searchTask.getIgnoreFile());
		System.out.println("File Extension: " + searchTask.getFileExtension());
		System.out.println("Search Token: " + searchTask.getToken());
		System.out.println("Result file: " + searchTask.getResultFile());
		System.out.println();
	}

	private static void search(SearchTask searchTask) throws TokenFinderException {
		Timer timer = new Timer();

		TokenFinder searchService = new SimpleTokenFinder();
		timer.start();
		try {
			searchService.search(searchTask);
			System.out.println("\nRUN SUCCESSFUL");
		} catch (TokenFinderException e) {
			System.out.println("\nRUN FAILED");
			throw e;
		} finally {
			timer.stop();
			System.out.println(timer);
		}
	}

}
