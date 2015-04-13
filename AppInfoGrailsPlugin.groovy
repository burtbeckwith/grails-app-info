import com.burtbeckwith.grails.plugins.appinfo.ContextListener

class AppInfoGrailsPlugin {

	String version = '1.1.3-SNAPSHOT'
	String grailsVersion = '2.0 > *'
	List pluginExcludes = [
		'scripts/CreateAppInfoTestApps.groovy'
	]

	String author = 'Burt Beckwith'
	String authorEmail = 'burt@burtbeckwith.com'
	String title = 'Application Info'
	String description = "UI for inspecting various aspects of the application's configuration"
	String documentation = 'http://grails.org/plugin/app-info'

	String license = 'APACHE'
	def issueManagement = [system: 'JIRA', url: 'http://jira.grails.org/browse/GPAPPINFO']
	def scm = [url: 'https://github.com/burtbeckwith/grails-app-info']

	def doWithWebDescriptor = { xml ->
		def useContextListener = application.config.grails.plugins.appinfo.useContextListener
		if (useContextListener == null || (useContextListener instanceof Boolean && useContextListener)) {
			def filterMapping = xml.'filter-mapping'
			filterMapping[filterMapping.size() - 1] + {
				listener {
					'listener-class'(ContextListener.name)
				}
			}
		}
	}
}
