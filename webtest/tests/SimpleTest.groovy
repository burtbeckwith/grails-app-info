class SimpleTest extends AbstractAppInfoWebTest {

	protected void setUp() {
		super.setUp()
		javaScriptEnabled = false
	}

	void testIndex() {
		get '/admin/manage'
		assertContentContains 'Attributes'
		assertContentContains 'Properties'
		assertContentContains 'Info'
	}

	void testApplicationInfo() {
		get '/admin/manage/applicationInfo'
		assertContentContains 'Context Attributes'
		assertContentContains 'Init Params'
		assertContentContains 'grails.plugins.appinfo.start_time '
	}

	void testRequestInfo() {
		get '/admin/manage/requestInfo'
		assertContentContains 'Request Scope'
		assertContentContains 'Request Info'
		assertContentContains 'Cookies'
		assertContentContains 'Headers'
		assertContentContains 'Attributes'
		assertContentContains 'getAuthType()'
	}

	void testSessionInfo() {
		get '/admin/manage/sessionInfo'
		assertContentContains 'Session Scope'
		assertContentContains 'Session Variables'
		assertContentContains 'Session Id'
	}

	void testDs() {
		get '/admin/manage/ds'
		assertContentContains 'Data Source (org.apache.commons.dbcp.BasicDataSource)'
	}

	void testGrailsProperties() {
		get '/admin/manage/grailsProperties'
		assertContentContains 'Grails Properties'
		assertContentContains 'dataSource.dbCreate'
	}

	void testSysProperties() {
		get '/admin/manage/sysProperties'
		assertContentContains 'System Properties'
		assertContentContains 'base.dir'
	}

	void testControllers() {
		get '/admin/manage/controllers'
		assertContentContains 'ErrorsController'
		assertContentContains 'com.burtbeckwith.appinfo_test.AdminManageController'
	}

	void testLogging() {
		get '/admin/manage/logging'
		assertContentContains 'Estimated log4j.xml'
		assertContentContains 'org.codehaus.groovy.grails.plugins.log4j.appenders.GrailsConsoleAppender'
		assertContentContains 'org.codehaus.groovy.grails.plugins.web.filters.CompositeInterceptor'
	}

	void testMemory() {
		get '/admin/manage/memory'
		assertContentContains 'Garbage Collect'
	}

	void testSessions() {
		get '/admin/manage/sessions'
		assertContentContains 'Invalidate'
	}

	void testSpring() {
		get '/admin/manage/spring'
		assertContentContains 'GrailsWebApplicationContext'
		assertContentContains 'com.burtbeckwith.appinfo_test.AdminManageController'
	}
}
