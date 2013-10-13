includeTargets << grailsScript('_GrailsBootstrap')

functionalTestPluginVersion = '1.2.7'
projectfiles = new File(basedir, 'webtest/projectFiles')
grailsHome = null
dotGrails = null
projectDir = null
appName = null
pluginVersion = null
testprojectRoot = null
grailsVersion = null
deleteAll = false

target(createAppInfoTestApps: 'Creates test apps for functional tests') {

	def configFile = new File(basedir, 'testapps.config.groovy')
	if (!configFile.exists()) {
		error "$configFile.path not found"
	}

	new ConfigSlurper().parse(configFile.text).each { name, config ->
		printMessage "\nCreating app based on configuration $name: ${config.flatten()}\n"
		init name, config
		createApp()
		installPlugins()
		copySampleFiles()
		copyTests()
	}
}
	
private void init(String name, config) {

	pluginVersion = config.pluginVersion
	if (!pluginVersion) {
		error "pluginVersion wasn't specified for config '$name'"
	}

	def pluginZip = new File(basedir, "grails-app-info-${pluginVersion}.zip")
	if (!pluginZip.exists()) {
		error "plugin $pluginZip.absolutePath not found"
	}

	grailsHome = config.grailsHome
	if (!new File(grailsHome).exists()) {
		error "Grails home $grailsHome not found"
	}

	projectDir = config.projectDir
	appName = 'appinfo-test-' + name
	testprojectRoot = "$projectDir/$appName"

	grailsVersion = config.grailsVersion
	dotGrails = config.dotGrails + '/' + grailsVersion
}

private void createApp() {

	ant.mkdir dir: projectDir

	deleteDir testprojectRoot
	deleteDir "$dotGrails/projects/$appName"

	callGrails grailsHome, projectDir, 'dev', 'create-app', [appName]
}
	
private void installPlugins() {

	File buildConfig = new File(testprojectRoot, 'grails-app/conf/BuildConfig.groovy')
	String contents = buildConfig.text

	contents = contents.replace('grails.project.class.dir = "target/classes"', "grails.project.work.dir = 'target'")
	contents = contents.replace('grails.project.test.class.dir = "target/test-classes"', '')
	contents = contents.replace('grails.project.test.reports.dir = "target/test-reports"', '')

	contents = contents.replace('//mavenLocal()', 'mavenLocal()')
	contents = contents.replace('repositories {', '''repositories {
mavenRepo 'http://repo.spring.io/milestone' // TODO remove
''')

	contents = contents.replace('grails.project.fork', 'grails.project.forkDISABLED')

	contents = contents.replace('plugins {', """plugins {
test ":functional-test:$functionalTestPluginVersion"
runtime ":spring-security-core:2.0-RC2"
runtime ":app-info:$pluginVersion"
""")

	buildConfig.withWriter { it.writeLine contents }

	callGrails grailsHome, testprojectRoot, 'dev', 'compile', null, true // can fail when installing the functional-test plugin
	callGrails grailsHome, testprojectRoot, 'dev', 'compile'

	callGrails grailsHome, testprojectRoot, 'dev', 's2-quickstart', ['com.burtbeckwith.appinfo_test', 'User', 'Role']
}

private void copySampleFiles() {
//	-rw-r--r-- 1 burt burt 1010 Oct 13 14:03 BootStrap.groovy
//	-rw-r--r-- 1 burt burt  441 Jun 26  2010 UrlMappings.groovy
		
	ant.mkdir dir: "${testprojectRoot}/src/groovy/com/burtbeckwith/appinfo_test"
	ant.copy file: "$projectfiles.path/MyUserType.groovy",
	         todir: "${testprojectRoot}/src/groovy/com/burtbeckwith/appinfo_test"

	ant.copy file: "$projectfiles.path/ErrorsController.groovy",
	         todir: "${testprojectRoot}/grails-app/controllers"

	ant.copy(todir: "${testprojectRoot}/grails-app/conf", overwrite: true) {
		fileset(dir: "$projectfiles.path/conf")
	}

	ant.copy(todir: "${testprojectRoot}/grails-app/domain/com/burtbeckwith/appinfo_test", overwrite: true) {
		fileset(dir: "$projectfiles.path/domain")
	}

	ant.copy(todir: "${testprojectRoot}/grails-app/views") {
		fileset(dir: "$projectfiles.path/views")
	}

	new File("$testprojectRoot/grails-app/conf/Config.groovy").withWriterAppend { writer ->
		writer.write '''
//grails.plugins.springsecurity.controllerAnnotations.staticRules = [
//   '/adminmanage/**': ['ROLE_ADMIN']
//]

grails.plugins.dynamicController.mixins = [
	'com.burtbeckwith.grails.plugins.appinfo.IndexControllerMixin':       'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.HibernateControllerMixin':   'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.Log4jControllerMixin':       'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.SpringControllerMixin':      'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.MemoryControllerMixin':      'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.PropertiesControllerMixin':  'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.ThreadsControllerMixin':     'com.burtbeckwith.appinfo_test.AdminManageController',
	'com.burtbeckwith.grails.plugins.appinfo.ScopesControllerMixin':      'com.burtbeckwith.appinfo_test.AdminManageController'
]

grails.plugins.appinfo.useContextListener = true

grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.rejectIfNoRule = false
'''
	}
}

private void copyTests() {
	ant.copy(todir: "${testprojectRoot}/test/functional") {
		fileset(dir: "$basedir/webtest/tests")
	}
}

private void deleteDir(String path) {
	if (new File(path).exists() && !deleteAll) {
		String code = "confirm.delete.$path"
		ant.input message: "$path exists, ok to delete?", addproperty: code, validargs: 'y,n,a'
		def result = ant.antProject.properties[code]
		if ('a'.equalsIgnoreCase(result)) {
			deleteAll = true
		}
		else if (!'y'.equalsIgnoreCase(result)) {
			printMessage "\nNot deleting $path"
			exit 1
		}
	}

	ant.delete dir: path
}

private void error(String message) {
	errorMessage "\nERROR: $message"
	exit 1
}

private void callGrails(String grailsHome, String dir, String env, String action, List extraArgs = null, boolean ignoreFailure = false) {

	String resultproperty = 'exitCode' + System.currentTimeMillis()
	String outputproperty = 'execOutput' + System.currentTimeMillis()

	println "Running 'grails $env $action ${extraArgs?.join(' ') ?: ''}'"

	ant.exec(executable: "${grailsHome}/bin/grails", dir: dir, failonerror: false,
				resultproperty: resultproperty, outputproperty: outputproperty) {
		ant.env key: 'GRAILS_HOME', value: grailsHome
		ant.arg value: env
		ant.arg value: action
		extraArgs.each { ant.arg value: it }
		ant.arg value: '--stacktrace'
	}

	println ant.project.getProperty(outputproperty)

	int exitCode = ant.project.getProperty(resultproperty) as Integer
	if (exitCode && !ignoreFailure) {
		exit exitCode
	}
}

printMessage = { String message -> event('StatusUpdate', [message]) }
errorMessage = { String message -> event('StatusError', [message]) }

setDefaultTarget 'createAppInfoTestApps'
