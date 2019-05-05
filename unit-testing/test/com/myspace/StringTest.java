package com.myspace;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	private static final Logger LOGGER = Logger.getLogger(StringTest.class.getSimpleName());
	
	// This will run only once at the time of the class load.
	@BeforeAll
	static void beforeAll() {
		LOGGER.info(" Inititalze connection to database ");
	}
	
	// This will run only once after all teh test are done 
	@AfterAll
	static void afterAll() {
		LOGGER.info(" Close connection to database ");
	}
    
	// This will run for each method.
	@BeforeEach // In Junit 4 use @Before and there is no TestInfo
	void beforeEach(TestInfo info) {
		LOGGER.info("Inititalze Test data for :" + info.getDisplayName());
	}

	@AfterEach // In Junit 4 use @After and there is no TestInfo
	void afterEach(TestInfo info) {
		LOGGER.info("Inititalze Test data for :" + info.getDisplayName());
	}

	// This will pass if the values matches
	@Test
	// This is the new annotation of junit 5 
	@DisplayName("Testing the length method")
	void test_length() {
		int expectedLength = 3;
		assertEquals(expectedLength, "YNA".length());
	}

	// This a boolean check
	@Test
	void length_greater() {
		assertTrue("YNA".length() > 0);
	}

	// This a boolean check
	@Test
	// This helps the test to run multiple time 
	@RepeatedTest(5)
	void contains_basics() {
		assertFalse("Prashanth".contains("ll"));
	}

	// This will pass if the array matches
	@Test
	void split_string() {
		String str = "I have a car";
		String result[] = str.split(" ");
		String[] expected = new String[] { "I", "have", "a", "car" };
		assertArrayEquals(expected, result);
	}
	
	// This will throw a null pointer exception 
	@Test
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> { str.length(); });
	}
    
	@ParameterizedTest
	@ValueSource(ints= {10, 20 , 30 , 40})
	void check_number_greaterthan_five_using_paramaterizedTest(int numbers) {
		assertTrue("This Number must be greater than 5 ",numbers > 5);
	}
	
	// 
	@ParameterizedTest(name ="{0} toUpperCase is {1}")
	@CsvSource(value = {"abcd, ABCD","abc, ABC"})
	void upperCase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	// This will pass if the object is null
	@Test
	void whenAssertingNull_thenTrue() {
		Object obj = null;
		assertNull("The obj should be null", obj);
	}

	// This will pass if the object is not null
	@Test
	void whenAssertingNotNull_thenTrue() {
		Object obj = new Object();
		assertNotNull("The car should be null", obj);
	}
	
	// This is used to test the performance of the method 
	
	@Test
	// this will disable test
	@Disabled //@ Ignored
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(1), () -> {
		for (int i = 0; i <= 10000; i++ ) {
			int j = i;
			LOGGER.info("Current value :"+j);
		}
		});
	}
}
