grails.project.work.dir = 'target'
grails.project.docs.output.dir = 'docs/manual' // for backwards-compatibility, the docs are checked into gh-pages branch
grails.project.dependency.resolver="maven"

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenCentral()
	}

	plugins {
		compile ':dynamic-controller:0.5.1'
		compile ':google-visualization:1.0.1'
		compile ':jquery:1.11.1'
		build ":release:3.1.1", ':rest-client-builder:2.1.1', {
			export = false
		}
	}
}
