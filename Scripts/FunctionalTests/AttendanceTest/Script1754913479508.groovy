import com.kms.katalon.core.model.FailureHandling as FailureHandling
import customreusable.SmartActions as smart
import assertions.SmartAssertions as smartAssertions
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('CommonTests/ApplicationLogin'), [:], FailureHandling.STOP_ON_FAILURE)

smart.smartScrollToElement('Attendance slide Menu')

smart.smartClick('Attendance slide Menu')

smart.smartClick('Apply')

smart.smartClick('Date Pick')

WebUI.click(findTestObject('Object Repository/Page_Excellence/td_8'))

smart.smartSelectDropdownOptionIgnoreCase("reason drop down","work from home")

smart.smartClick('punch in')

WebUI.setText(findTestObject('Object Repository/Page_Excellence/input_Search_bootstrap-timepicker-hour'), '13')

smart.smartClick('punch out')

WebUI.setText(findTestObject('Object Repository/Page_Excellence/input_Search_bootstrap-timepicker-hour'), '23')

smart.smartClick('Submit')

smart.smartClick('yes button')

smartAssertions.verifyElementText(smart.smartGetText('valid reason'), 'please provide valid reason')

