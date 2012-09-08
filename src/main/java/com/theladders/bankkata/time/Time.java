package com.theladders.bankkata.time;

import java.util.Date;

public class Time implements Comparable<Time>
{
  private Date time;
  public Time(Date time)
  {
    this.time = time;
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (!(o instanceof Time))
      return false;
    Time other = (Time) o;
    Date otherTime = other.time;
    return time.equals(otherTime);
  }
  
  @Override
  public int hashCode()
  {
    return time.hashCode();
  }
  
  @Override
  public int compareTo(Time other)
  {
    Date otherTime = other.time;
    return time.compareTo(otherTime);
  }
  

}
