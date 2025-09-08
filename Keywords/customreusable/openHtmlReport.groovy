package customreusable

static void openHtmlReport(String htmlPath) {
	try {
		java.awt.Desktop.getDesktop().browse(new File(htmlPath).toURI())
		println("[SmartHtmlReporter] Opened HTML report in browser.")
	} catch (Exception e) {
		println("[SmartHtmlReporter] Failed to open HTML report: ${e.message}")
	}
}
