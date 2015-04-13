package com.burtbeckwith.grails.plugins.appinfo

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
class AppInfoTagLib {

	static namespace = 'ai'

	def pluginManager

	def resources = { attrs ->
		if (pluginManager.hasGrailsPlugin('resources')) {
			r.require(module: 'appInfo')
			out << r.resources()
		}
		else {
			out << g.javascript(library: 'jquery', plugin: 'jquery')
			for (name in ['jquery.jdMenu', 'jquery.jdMenu.slate',
			              'tabs', 'tabs-accordion', 'appinfo']) {
				out << """<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:"${name}.css",plugin:'appInfo')}"/>"""
			}
			for (name in ['jquery.dataTables.min', 'jquery.dataTables_themeroller']) {
				out << """<link rel="stylesheet" media="screen" href="${resource(dir:'DataTables-1.10.6/media/css',file:"${name}.css",plugin:'appInfo')}"/>"""
			}
			for (name in ['dataTables.tableTools.min']) {
				out << """<link rel="stylesheet" media="screen" href="${resource(dir:'DataTables-1.10.6/extensions/TableTools/css',file:"${name}.css",plugin:'appInfo')}"/>"""
			}
			for (name in ['appInfo.dataTable']) {
				out << g.javascript(src: '' + name + '.js', plugin: 'appInfo')
			}
			for (name in ['jquery.dataTables.min']) {
				out << g.javascript(src: '../DataTables-1.10.6/media/js/' + name + '.js', plugin: 'appInfo')
			}
			for (name in ['dataTables.tableTools.min']) {
				out << g.javascript(src: '../DataTables-1.10.6/extensions/TableTools/js/' + name + '.js', plugin: 'appInfo')
			}
		}
	}

	def layoutResources = { attrs ->
		if (pluginManager.hasGrailsPlugin('resources')) {
			out << r.layoutResources()
		}
		else {
			for (name in ['jquery.dimensions', 'jquery.jdMenu', 'jquery.bgiframe',
			              'jquery.positionBy', 'jquery.tabs.min']) {
				out << g.javascript(src: 'jquery/' + name + '.js', plugin: 'appInfo')
			}
		}
	}
}
