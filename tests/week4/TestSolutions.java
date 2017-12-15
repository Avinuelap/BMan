package week4;

import java.util.List;
import java.util.Scanner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * JUnit test class for the exercises of week 4
 * 
 * @author Angel García Olaya. Bachelor Degree in Computer Science. UC3M
 * @since September 2017
 * @version 1.0
 *
 */
public class TestSolutions {

	public static void main(String[] args) throws Exception {
		// Exercises for which tests are available
		final String[] tests = { "Exercise5", "Exercise6", "Exercise7", "Exercise8", "Exercise9", "Exercise10",
				"Exercise11", "Exercise12", "Exercise13", "Exercise14" };
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the exercise you want to test");
		String exercise = sc.next();
		sc.close();
		// Checking the exercise exists
		boolean found = false;
		for (int ii = 0; ii < tests.length && !found; ii++) {
			if (tests[ii].equals(exercise)) {
				found = true;
			}
		}
		if (found) {
			JUnitCore junit = new JUnitCore();
			// The real test class is inside the jar file
			exercise = "week4." + exercise + "Test";
			Class<?> programClass = Class.forName(exercise);
			Result result = junit.run(programClass);
			// If fails appear, we check if they are due to the lack of main
			// program
			// Usually this will be because the file has not been correctly
			// named
			// Also it any exception is thrown in the code it will reach here
			if (result.getFailureCount() > 0) {
				List<Failure> fails = result.getFailures();
				System.out.println("The following tests have failed");
				for (int ii = 0; ii < fails.size(); ii++) {
					String error = fails.get(ii).getTestHeader();
					error = error.substring(error.indexOf('(') + 1, error.indexOf(')'));
					System.out.println(error);
					if (fails.get(ii).getException() != null) {
						// We only print something if the exception is not null
						if (fails.get(0).getException().getCause() != null)
							System.out.println(fails.get(0).getException().getCause());
					}
				}
			}
		} else {
			System.out.println("There is no automatic test for this exercise");
			System.out.println("Tests available for:");
			for (int ii = 0; ii < tests.length; ii++) {
				System.out.print(tests[ii] + " ");
			}
		}
	}
}