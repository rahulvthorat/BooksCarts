package org.selenium.dataproviders;

import org.testng.annotations.DataProvider;

public class AuthorDataProvider {

	
	 @DataProvider(name="Author")
	    public Object[][] bookData() {
	        return new Object[][] {
	        	{ "JKR", "Harry Potter and the Chamber of Secrets" },
	        	{ "JKR", "Harry Potter and the Prisoner of Azkaban" },
	        	{ "JKR", "Harry Potter and the Goblet of Fire" },
	        	{ "JKR", "Harry Potter and the Order of the Phoenix" },
	        };
	    }
}


