import java.util.regex.Pattern

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory

import functionaltestplugin.FunctionalTestCase

abstract class AbstractAppInfoWebTest extends FunctionalTestCase {
	protected String sessionId

	@Override
	protected void tearDown() {
		super.tearDown()
		logout()
	}

	protected void verifyListSize(int size) {
		int actual = evaluateXpath("count(//div//table//tbody/tr)") as Integer
		assertEquals "$size row(s) of data expected", size, actual
	}

	protected void verifyXPath(String expression, String expected, boolean regex) {
		String result = evaluateXpath(expression)
		if (regex) {
			assertTrue Pattern.compile(expected, Pattern.DOTALL).matcher(result).find()
		}
		else {
			assertEquals expected, result
		}
	}

	protected evaluateXpath(String expression) {
		def documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(convertResponseToXml().bytes)).documentElement
		XPathFactory.newInstance().newXPath().evaluate(expression, documentElement)
	}

	protected String convertResponseToXml() {
		StringBuilder fixed = new StringBuilder()
		response.contentAsString.eachLine { String line ->
			line = line.replaceAll('&hellip;', '').trim()
			if (!line.startsWith('<link ') && !line.startsWith('<meta ')) {
				fixed << line << '\n'
			}
		}
		fixed.toString().replaceAll('<!doctype ', '<!DOCTYPE ')
	}

	protected void clickButton(String idOrText) {
		def button = byId(idOrText)
		if (!button) {
			def form = page.forms[0]
			for (element in form.getElementsByAttribute('input', 'type', 'submit')) {
				if (element.valueAttribute == idOrText) {
					button = element
					break
				}
			}
		}

		if (!button) {
			throw new IllegalArgumentException("No such element for id or button text [$idOrText]")
		}

		println "Clicked [$idOrText] which resolved to a [${button.class}]"
		button.click()
		handleRedirects()
	}

	protected void login(String username, String password) {
		// login as user1
		get '/login/auth'
		assertContentContains 'Please Login'

		form {
			j_username = username
			j_password = password
			_spring_security_remember_me = true
			clickButton 'Login'
		}
	}

	protected void logout() {
		post '/logout'
	}
}
