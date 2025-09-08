import internal.GlobalVariable as GlobalVariable
import customreusable.SmartActions as smart
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import utils.TestDataUtils  // Adjust if your package name is different

// Find your test data file once
TestData loginTestData = TestDataFactory.findTestData('Data Files/LoginUserData')

int totalRows = loginTestData.getRowNumbers()

for (int i = 1; i <= totalRows; i++) {
    // Get row as map using your utility
    Map<String, String> loginData = TestDataUtils.getTestDataRow('Data Files/LoginUserData', i)

    WebUI.openBrowser('')
    WebUI.navigateToUrl(GlobalVariable.baseURL)

    // Use smart actions with values from test data row
    smart.smartSetText('email', loginData['username'])  // Use your actual column name here
    smart.smartSetText('password', loginData['password'])
    smart.smartClick('Login')


    WebUI.closeBrowser()
}
