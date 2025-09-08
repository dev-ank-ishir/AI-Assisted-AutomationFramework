
import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.*
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.*
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import com.aventstack.extentreports.reporter.configuration.Theme
import com.aventstack.extentreports.MediaEntityBuilder
import java.text.SimpleDateFormat
import java.io.File
import java.nio.file.Paths
import java.util.Date

class ExtentReportListener {

	private static ExtentReports extent
	private static ExtentTest test
	private static String reportPath
	private static Date suiteStartTime

	@BeforeTestSuite
	def beforeSuite(TestSuiteContext suite) {
		suiteStartTime = new Date()

		String suiteReportsDir = Paths.get(RunConfiguration.getReportFolder(), "Extent").toString()
		new File(suiteReportsDir).mkdirs()

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
		reportPath = Paths.get(suiteReportsDir, "ExtentReport_" + timestamp + ".html").toString()

		def spark = new ExtentSparkReporter(reportPath)
		spark.config().setTheme(Theme.DARK)
		spark.config().setDocumentTitle("Katalon QA Test Report")
		spark.config().setReportName("Test Suite: ${suite.getTestSuiteId()}")

		extent = new ExtentReports()
		extent.attachReporter(spark)

		// Environment / system info
		extent.setSystemInfo("OS", System.getProperty("os.name"))
		extent.setSystemInfo("Java Version", System.getProperty("java.version"))
		extent.setSystemInfo("Suite Start Time", suiteStartTime.toString())

		println "[ExtentReportListener] Suite started: ${suite.getTestSuiteId()}"
	}

	@BeforeTestCase
	def beforeCase(TestCaseContext testCase) {
		test = extent.createTest(testCase.getTestCaseId())
		test.getModel().setStartTime(new Date())
		println "[ExtentReportListener] Test case started: ${testCase.getTestCaseId()}"
	}

	@AfterTestCase
	def afterCase(TestCaseContext testCase) {
		try {
			String status = testCase.getTestCaseStatus()
			String screenshotPath = null

			if (status?.equalsIgnoreCase("FAILED")) {
				String screenshotsDir = Paths.get(RunConfiguration.getReportFolder(), "Screenshots").toString()
				new File(screenshotsDir).mkdirs()

				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
				screenshotPath = Paths.get(screenshotsDir, "Screenshot_" + timestamp + ".png").toString()

				WebUI.takeScreenshot(screenshotPath)
				Thread.sleep(500) // ensure file is saved

				// Make path relative to report and normalize
				File reportFile = new File(reportPath)
				File screenshotFile = new File(screenshotPath)
				String relativePath = reportFile.parentFile.toPath().relativize(screenshotFile.toPath()).toString().replace("\\", "/")

				test.fail("Test case failed", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build())
				println "[FAIL] Screenshot attached: ${relativePath}"

			} else if (status?.equalsIgnoreCase("PASSED")) {
				test.pass("Test case passed")
			} else {
				test.skip("Test case skipped or unknown status")
			}

			// Calculate duration in mm:ss.SSS
			Date endTime = new Date()
			long durationMs = endTime.time - test.getModel().getStartTime().time
			long minutes = durationMs / 60000
			long seconds = (durationMs % 60000) / 1000
			long millis = durationMs % 1000
			String formattedDuration = String.format("%02d:%02d.%03d", minutes, seconds, millis)
			test.getModel().setEndTime(endTime)
			test.info("Duration: ${formattedDuration}")

		} catch(Exception e) {
			println "[ExtentReportListener] Error capturing screenshot: ${e.message}"
		}
	}

	@AfterTestSuite
	def afterSuite(TestSuiteContext suite) {
		Date suiteEndTime = new Date()
		extent.setSystemInfo("Suite End Time", suiteEndTime.toString())

		long durationMs = suiteEndTime.time - suiteStartTime.time
		long minutes = durationMs / 60000
		long seconds = (durationMs % 60000) / 1000
		long millis = durationMs % 1000
		String formattedDuration = String.format("%02d:%02d.%03d", minutes, seconds, millis)
		extent.setSystemInfo("Suite Duration", formattedDuration)

		extent.flush()
		println "[ExtentReportListener] Suite finished. Report generated at: ${reportPath}"
	}
}
