package de.uniba.wiai.dsg.ajp.assignment1.search;

/**
 * Groups together all parameters for a search task and does not contain any
 * logic.
 */
public final class SearchTask {

	/**
	 * the root folder from which the search is started
	 */
	private final String rootFolder;

	/**
	 * the file that contains the directories to be ignored
	 */
	private final String ignoreFile;

	/**
	 * the extension of the files which are searched
	 */
	private final String fileExtension;

	/**
	 * the string to be searched
	 */
	private final String token;

	/**
	 * the path for the search result output
	 */
	private final String resultFile;

	public SearchTask(String rootFolder, String ignoreFile, String fileExtension, String token, String resultFile) {
		super();
		this.rootFolder = rootFolder;
		this.ignoreFile = ignoreFile;
		this.fileExtension = fileExtension;
		this.token = token;
		this.resultFile = resultFile;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public String getIgnoreFile() {
		return ignoreFile;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getToken() {
		return token;
	}

	public String getResultFile() {
		return resultFile;
	}

}
