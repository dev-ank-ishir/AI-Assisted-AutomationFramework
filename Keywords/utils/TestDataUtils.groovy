package utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory

import internal.GlobalVariable


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory

class TestDataUtils {

	@Keyword
	static Map<String, String> getTestDataRow(String dataFileName, int rowNum) {
		TestData data = TestDataFactory.findTestData(dataFileName)

		Map<String, String> rowMap = [:]

		if (data != null && rowNum > 0 && rowNum <= data.getRowNumbers()) {
			for (String col : data.getColumnNames()) {
				rowMap[col] = data.getValue(col, rowNum)
			}
		} else {
			println "Invalid row number or test data not found"
		}

		return rowMap
	}
}
