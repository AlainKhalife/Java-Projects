package hw5q1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class test {
	/*
	private static class MyTimeTask extends TimerTask
	{

	    public void run()
	    {
	        System.out.println("Hello");
	    }
	}
	*/
	
	
	public static void main(String[] args) {
		String a = "Alain";
		String b = "Miguel";
		System.out.println(a.compareTo(null));
		
		
		/*
		ArrayQueue a = new ArrayQueue();
		Date time = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
	    cal.setTime(time);
	    int hour = cal.get(Calendar.HOUR);
	    int min = cal.get(Calendar.MINUTE);
	    int sec = cal.get(Calendar.SECOND);
		System.out.println(cal);
		/*
		for(int i = 0; i<7; i++)
			a.enqueue(i);
		
		a.dequeue();
		a.dequeue();
		a.enqueue(20);
		a.enqueue(3);
		a.enqueue(2);
		a.enqueue(7);
		a.dequeue();
		a.enqueue(2);
		a.enqueue(99);
		}
		*/
		
		/*
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();

		Timer t = new Timer();
		t.schedule(new Task(), time);
		*/
	}
}
