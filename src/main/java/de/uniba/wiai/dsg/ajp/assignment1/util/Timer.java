package de.uniba.wiai.dsg.ajp.assignment1.util;

/**
 * Counts the time between a start and a stop event and calculate as well as
 * print the difference.
 *
 * @author Simon Harrer, Joerg Lenhard
 * @version 1.0
 */
public class Timer {

	private long start;
	private long stop;

	public void start() {
		start = System.currentTimeMillis();
	}

	public void stop() {
		stop = System.currentTimeMillis();
	}

	public long diff() {
		return stop - start;
	}

	/**
	 * @return formatted diff using format XXX minutes and YYY.ZZZ seconds.
	 */
	public String formattedDiff() {
		long milliseconds = (diff() % 1000);
		long seconds = (diff() / 1000);
		long minutes = (seconds / 60);
		seconds -= 60 * minutes;

		return minutes + " minutes and " + seconds + "." + addLeadingZeros(milliseconds)
				+ " seconds";
	}

	private String addLeadingZeros(long milliseconds) {
		if(milliseconds > 99) {
			return "0" + milliseconds;
		} else if (milliseconds > 9) {
			return "00" + milliseconds;
		} else {
			return "000" + milliseconds;
		}
	}

	public String toString() {
		return "Duration: " + formattedDiff();
	}

}
