package io.github.steveswinsburg;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
/**
 * The solver app
 *
 * tail -f /tmp/solver.log
 */
public class App {
	
	
	private static File file = new File("/tmp/solver.log");
	private static CharSink sink = Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
		
    public static void main( String[] args ) {
        
    	
    	String arg0 = args[0];
    	if(StringUtils.isAllBlank(arg0)) {
    		System.err.println("Must provide a value to solve");
    		System.exit(0);
    	}
    	
    	//move this to validation method
    	//check numbers passed in
    	//check from is less than to
    	
    	
    	String arg1 = StringUtils.defaultString(args[1], "0");
    	String arg2 = StringUtils.defaultString(args[2], "0");

    	int n = Integer.valueOf(arg0);
    	int from = Integer.valueOf(arg1);
    	int to = Integer.valueOf(arg2);
    
    	solveFor(n, from, to);
    	
    }
    
    private static void solveFor(int n, int from, int to) {
    	
    	Stopwatch timer = Stopwatch.createStarted();
    	
    	log("Solving for %s, start: %s, to: %s", n, from, to); 
    	
    	for(int x = from; x <= to; x++) {
    		
    		for(int y= from; y <= to; y++) {
    			
    			for(int z = from; z <= to; z++) {
        			
    				int x3 = cube(x);
		    		int y3 = cube(y);
		    		int z3 = cube(z);
		    		
		    		int result = x3+y3+z3;
		    		
		    		if(result == n) {
		    			log("Solved: %s = (%s^3)+(%s^3)+(%s^3) in %s seconds", n, x, y, z, timer.elapsed(TimeUnit.SECONDS));
		    		}
		    		
        		}
    			
    		}
    		
    		
    	}
    	
    	timer.stop();
		log("Finished the set. Total %s seconds \n", timer.elapsed(TimeUnit.SECONDS));
   
    	
    }
    
    private static int cube(int n) {
    	return (int)(Math.pow(n, 3));
    }
    
    @SafeVarargs
	private static <T> void log(String message, T... args) {
    	
    	String formattedMessage = String.format(message, args);
    	 
        try {
			sink.write(formattedMessage + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	
    }
    
    
    
    
}
