grails.project.work.dir = 'target'
grails.project.source.level = 1.6
grails.project.docs.output.dir = 'docs/manual' // for backwards-compatibility, the docs are checked into gh-pages branch

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {}

	plugins {
		compile ':dynamic-controller:0.4'
		compile ':google-visualization:0.5.5'
		compile ':jquery:1.8.0'

		build(':release:2.0.4', ':rest-client-builder:1.0.2') {
			export = false
		}
	}
}
