package com.theladders.bankkata.time;

import java.util.Date;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;


public class TimeKeeper
{
  Iterator<Date> timeSequence= null;
  
  public TimeKeeper()
  {
  }
  
  public TimeKeeper(Date ...times)
  {
    List<Date> timeList= Arrays.asList(times);
    timeSequence = timeList.iterator();
  }
  public void now(TimeListener listener)
  {
    listener.sendTime(getTime());
  }
  
  public Time getTime()
  {
    // could just pass in a sequence.NewDate and not have this as a special case at all....
    if (timeSequence == null)
    {
      return new Time(new Date());
    }
    if (!timeSequence.hasNext())
    {
      throw new RuntimeException("Ran out of times");
    }
    return new Time(timeSequence.next());
  }
  
}
