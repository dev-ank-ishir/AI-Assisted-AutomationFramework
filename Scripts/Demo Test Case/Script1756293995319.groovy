import internal.GlobalVariable as GlobalVariable
import customreusable.SmartActions as smart
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.baseURL)

smart.smartSetText('email', GlobalVariable.userName)

smart.smartSetText('password', GlobalVariable.password)

smart.smartClick('Login')

