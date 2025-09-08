package assertions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import customreusable.SmartEngine

class SmartAssertions {

	@Keyword
	static void verifyElementPresent(String keyword, int timeout = 10) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.verifyElementPresent(to, timeout)
				KeywordUtil.markPassed("Element matched by '${keyword}' is present.")
			} catch (Exception e) {
				KeywordUtil.markFailed("Element matched by '${keyword}' not found.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyElementVisible(String keyword, int timeout = 10) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.waitForElementVisible(to, timeout)
				KeywordUtil.markPassed("Element matched by '${keyword}' is visible.")
			} catch (Exception e) {
				KeywordUtil.markFailed("Element matched by '${keyword}' is not visible.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyTextPresent(String expectedText, boolean caseSensitive = false) {
		try {
			WebUI.verifyTextPresent(expectedText, caseSensitive)
			KeywordUtil.markPassed("Text '${expectedText}' is present on the page.")
		} catch (Exception e) {
			KeywordUtil.markFailed("Text '${expectedText}' is NOT present on the page.")
		}
	}

	@Keyword
	static void verifyElementText(String keyword, String expectedText) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.verifyElementText(to, expectedText)
				KeywordUtil.markPassed("Element matched by '${keyword}' has expected text: '${expectedText}'.")
			} catch (Exception e) {
				def actualText = WebUI.getText(to)
				KeywordUtil.markFailed("Element matched by '${keyword}' does not have expected text: '${expectedText}'. Actual text: '${actualText}'.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyTitle(String expectedTitle) {
		try {
			WebUI.verifyTitle(expectedTitle)
			KeywordUtil.markPassed("Page title matches: '${expectedTitle}'.")
		} catch (Exception e) {
			KeywordUtil.markFailed("Page title does not match: '${expectedTitle}'.")
		}
	}

	@Keyword
	static void verifyElementClickable(String keyword, int timeout = 10) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.waitForElementClickable(to, timeout)
				KeywordUtil.markPassed("Element matched by '${keyword}' is clickable.")
			} catch (Exception e) {
				KeywordUtil.markFailed("Element matched by '${keyword}' is NOT clickable.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyAlertText(String expectedText) {
		try {
			String actualText = WebUI.getAlertText()
			if (actualText == expectedText) {
				KeywordUtil.markPassed("Alert text matches: '${expectedText}'.")
			} else {
				KeywordUtil.markFailed("Alert text '${actualText}' does not match expected: '${expectedText}'.")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("No alert present or alert text mismatch.")
		}
	}

	@Keyword
	static void verifyCheckboxChecked(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				boolean isChecked = WebUI.verifyElementChecked(to, 10, FailureHandling.OPTIONAL)
				if (isChecked) {
					KeywordUtil.markPassed("Checkbox matched by '${keyword}' is checked.")
				} else {
					KeywordUtil.markFailed("Checkbox matched by '${keyword}' is NOT checked.")
				}
			} catch (Exception e) {
				KeywordUtil.markFailed("Unable to verify checkbox matched by '${keyword}'.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyDropdownSelectedOption(String keyword, String expectedOption) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.verifyOptionSelectedByLabel(to, expectedOption, false, 10)
				KeywordUtil.markPassed("Dropdown matched by '${keyword}' has selected option: '${expectedOption}'.")
			} catch (Exception e) {
				KeywordUtil.markFailed("Dropdown matched by '${keyword}' does not have selected option: '${expectedOption}'.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}

	@Keyword
	static void verifyElementAttributeValue(String keyword, String attribute, String expectedValue) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			try {
				WebUI.verifyElementAttributeValue(to, attribute, expectedValue, 10)
				KeywordUtil.markPassed("Element matched by '${keyword}' has attribute '${attribute}' with value '${expectedValue}'.")
			} catch (Exception e) {
				KeywordUtil.markFailed("Element matched by '${keyword}' does not have attribute '${attribute}' with value '${expectedValue}'.")
			}
		} else {
			KeywordUtil.markFailed("No object matched for keyword '${keyword}'.")
		}
	}
}
