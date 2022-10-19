package keywords;

public class ValidationKeywords extends GenericKeywords{
	
	
	public void validateTitle(String expectedTitleKey) {
		driver.getTitle();
	}
	
	public void validateText(String expectedText, String locator) {
		
	}
	
	public void validateElementPresent(String locator) {
		
	}

}
