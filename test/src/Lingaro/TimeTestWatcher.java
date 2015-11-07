package Lingaro;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.google.common.base.Stopwatch;

public class TimeTestWatcher extends TestWatcher {
    private Stopwatch stopwatch = Stopwatch.createUnstarted();

    protected void starting(Description description) {
        stopwatch.start();
    }

    protected void finished(Description description) {
    	
        stopwatch.stop();
        
        String testName = description.getMethodName();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(String.format("Test %s took: %d ms.", testName, elapsed));
			
    }
};