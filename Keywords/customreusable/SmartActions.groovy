package customreusable

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType

class SmartActions {

	/**
	 * Click on element whose repository name best matches the keyword.
	 * Example: smartClick("login") will find and click an object named like "btn_login_xyz"
	 */
	@Keyword

	static def smartClick(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.click(to)
			KeywordUtil.logInfo("smartClick: Clicked element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartClick: No object matched for '${keyword}'")
		}
	}

	/**
	 * Set plain text into an input field matched by keyword.
	 * Example: smartSetText("username", "john@example.com")
	 */
	@Keyword
	static def smartSetText(String keyword, String text) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.setText(to, text)
			KeywordUtil.logInfo("smartSetText: Set text on element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartSetText: No object matched for '${keyword}'")
		}
	}

	/**
	 * Set encrypted text (passwords etc.) into an input field matched by keyword.
	 * Example: smartSetEncryptedText("password", "<encrypted_value>")
	 */
	@Keyword
	static def smartSetEncryptedText(String keyword, String encryptedText) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.setEncryptedText(to, encryptedText)
			KeywordUtil.logInfo("smartSetEncryptedText: Set encrypted text on element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartSetEncryptedText: No object matched for '${keyword}'")
		}
	}


	/**
	 * Fetch visible text from the element matched by the given keyword.
	 */
	@Keyword
	static def smartGetText(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			def visibleText = WebUI.getText(to)
			KeywordUtil.logInfo("smartGetText: Retrieved text '${visibleText}' from element matched by '${keyword}'")
			return visibleText
		} else {
			KeywordUtil.markFailed("smartGetText: No object matched for '${keyword}'")
			return null
		}
	}




	/**
	 * Select an option by visible label from a dropdown matched by keyword.
	 * Example: smartSelectOptionByLabel("country", "India")
	 */
	@Keyword
	static def smartSelectOptionByLabel(String keyword, String optionLabel) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.selectOptionByLabel(to, optionLabel, false)
			KeywordUtil.logInfo("smartSelectOptionByLabel: Selected '${optionLabel}' on element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartSelectOptionByLabel: No object matched for '${keyword}'")
		}
	}

	/**
	 * Accept any open browser alert.
	 * Example: smartAcceptAlert()
	 */
	@Keyword
	static def smartAcceptAlert() {
		try {
			WebUI.acceptAlert()
			KeywordUtil.logInfo("smartAcceptAlert: Accepted alert")
		} catch (Exception e) {
			KeywordUtil.markFailed("smartAcceptAlert: No alert to accept or error: " + e.getMessage())
		}
	}

	/**
	 * Dismiss any open browser alert.
	 * Example: smartDismissAlert()
	 */
	@Keyword
	static def smartDismissAlert() {
		try {
			WebUI.dismissAlert()
			KeywordUtil.logInfo("smartDismissAlert: Dismissed alert")
		} catch (Exception e) {
			KeywordUtil.markFailed("smartDismissAlert: No alert to dismiss or error: " + e.getMessage())
		}
	}

	/**
	 * Get text from any open alert popup.
	 * Returns alert text or null if no alert present.
	 * Example: String alertText = smartGetAlertText()
	 */
	@Keyword
	static def smartGetAlertText() {
		try {
			def alertText = WebUI.getAlertText()
			KeywordUtil.logInfo("smartGetAlertText: Alert text: " + alertText)
			return alertText
		} catch (Exception e) {
			KeywordUtil.markFailed("smartGetAlertText: No alert present or error: " + e.getMessage())
			return null
		}
	}

	/**
	 * Waits for an element matched by keyword to be visible.
	 * Default timeout 10 seconds.
	 * Example: smartWaitForElementVisible("dashboard", 15)
	 */
	@Keyword
	static def smartWaitForElementVisible(String keyword, int timeoutSeconds = 10) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.waitForElementVisible(to, timeoutSeconds)
			KeywordUtil.logInfo("smartWaitForElementVisible: Waited for element matched by '${keyword}' to be visible")
		} else {
			KeywordUtil.markFailed("smartWaitForElementVisible: No object matched for '${keyword}'")
		}
	}

	/**
	 * Check a checkbox or radio button matched by keyword.
	 * Example: smartCheck("accept_terms")
	 */
	@Keyword
	static def smartCheck(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.check(to)
			KeywordUtil.logInfo("smartCheck: Checked element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartCheck: No object matched for '${keyword}'")
		}
	}

	/**
	 * Uncheck a checkbox or radio button matched by keyword.
	 * Example: smartUncheck("subscribe_newsletter")
	 */
	@Keyword
	static def smartUncheck(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.uncheck(to)
			KeywordUtil.logInfo("smartUncheck: Unchecked element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartUncheck: No object matched for '${keyword}'")
		}
	}

	/**
	 * Upload a file to an input matched by keyword.
	 * Example: smartUploadFile("profile_picture", "/path/to/file.jpg")
	 */
	@Keyword
	static def smartUploadFile(String keyword, String filePath) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.uploadFile(to, filePath)
			KeywordUtil.logInfo("smartUploadFile: Uploaded file to element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartUploadFile: No object matched for '${keyword}'")
		}
	}

	/**
	 * Scrolls to an element matched by keyword.
	 * Example: smartScrollToElement("footer")
	 */
	@Keyword
	static def smartScrollToElement(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.scrollToElement(to, 10)
			KeywordUtil.logInfo("smartScrollToElement: Scrolled to element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartScrollToElement: No object matched for '${keyword}'")
		}
	}

	/**
	 * Highlights an element matched by keyword by adding a red border.
	 * Useful for demo/debug.
	 * Example: smartHighlightElement("search_box")
	 */
	@Keyword
	static def smartHighlightElement(String keyword) {
		def to = SmartEngine.findBestMatch(keyword)
		if (to != null) {
			WebUI.executeJavaScript("arguments[0].style.border='3px solid red'", Arrays.asList(to))
			KeywordUtil.logInfo("smartHighlightElement: Highlighted element matched by '${keyword}'")
		} else {
			KeywordUtil.markFailed("smartHighlightElement: No object matched for '${keyword}'")
		}
	}

	@Keyword
	static def smartSelectDropdownOptionIgnoreCase(String dropdownKeyword, String optionText) {
		def dropdownTo = SmartEngine.findBestMatch(dropdownKeyword)
		if (dropdownTo == null) {
			KeywordUtil.markFailed("No dropdown matched for keyword '${dropdownKeyword}'")
			return
		}

		WebUI.click(dropdownTo)

		String xpath = "//*[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '" + optionText.toLowerCase() + "']"

		TestObject optionToClick = new TestObject("dynamicOption")
		optionToClick.addProperty("xpath", ConditionType.EQUALS, xpath)

		try {
			WebUI.click(optionToClick)
			KeywordUtil.logInfo("Clicked option '${optionText}' (case-insensitive match)")
		} catch (Exception e) {
			KeywordUtil.markFailed("Failed to click option '${optionText}': " + e.message)
		}
	}
}
