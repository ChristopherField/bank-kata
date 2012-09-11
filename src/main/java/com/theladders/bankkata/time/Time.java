package com.theladders.bankkata.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time implements Comparable<Time> {
	private Date time;

	public Time(Date time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Time))
			return false;
		Time other = (Time) o;
		Date otherTime = other.time;
		return time.equals(otherTime);
	}

	@Override
	public int hashCode() {
		return time.hashCode();
	}

	public int compareTo(Time other) {
		Date otherTime = other.time;
		return time.compareTo(otherTime);
	}

	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(time);
	}

	public static Time MIN_TIME = new Time(new Date(0));

	@SuppressWarnings("deprecation")
	public static Time MAX_TIME = new Time(new Date(9999, 12, 31));
}
