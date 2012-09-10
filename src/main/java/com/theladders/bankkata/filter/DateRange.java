package com.theladders.bankkata.filter;

import com.theladders.bankkata.time.Time;

public class DateRange {
	Time from;
	Time to;
	public DateRange(Time from, Time to)
	{
		this.from = from;
		this.to = to;
	}
	
	public boolean meetsCriteria(Time check)
	{
		if (from.compareTo(check) <= 0 && to.compareTo(check) >= 0)
		{
			return true;
		}
		return false;
	}

}
