package customreusable

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration
import java.util.regex.Pattern

class SmartEngine {

	static TestObject findBestMatch(String keyword) {
		File baseDir = new File(RunConfiguration.getProjectDir() + File.separator + "Object Repository")
		List<File> rsFiles = getAllRSFilesRecursively(baseDir)

		if (rsFiles.isEmpty()) {
			KeywordUtil.markFailed("SmartEngine: No Test Objects found in the repository.")
			return null
		}

		for (File file : rsFiles) {
			String relativePath = file.path
					.replace(baseDir.getAbsolutePath() + File.separator, "")
					.replace(".rs", "")
					.replaceAll(Pattern.quote("\\"), "/")  // Fix for Windows backslashes

			if (relativePath.toLowerCase().contains(keyword.toLowerCase())) {
				KeywordUtil.logInfo("SmartEngine: Matched object path: " + relativePath)
				TestObject to = ObjectRepository.findTestObject(relativePath)
				if (to == null) {
					KeywordUtil.markFailed("SmartEngine: Found path but failed to load object at '${relativePath}'")
				}
				return to
			}
		}

		KeywordUtil.markFailed("SmartEngine: No match found for '${keyword}'")
		return null
	}

	private static List<File> getAllRSFilesRecursively(File dir) {
		List<File> rsFiles = []
		dir.eachFileRecurse { file ->
			if (file.name.endsWith(".rs")) {
				rsFiles << file
			}
		}
		return rsFiles
	}
}
