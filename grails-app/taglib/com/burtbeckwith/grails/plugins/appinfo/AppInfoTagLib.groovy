package com.burtbeckwith.grails.plugins.appinfo

import org.codehaus.groovy.grails.plugins.PluginManagerHolder

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
class AppInfoTagLib {

	static namespace = 'ai'

	def resources = { attrs ->
		boolean hasResourcesPlugin = PluginManagerHolder.pluginManager.hasGrailsPlugin('resources')

		if (hasResourcesPlugin) {
			r.require(module: 'appInfo')
			out << r.layoutResources()
		}
		else {
			out << g.javascript(library: 'jquery', plugin: 'jquery')
			for (name in ['table', 'jquery.jdMenu', 'jquery.jdMenu.slate',
			              'tabs', 'tabs-accordion', 'appinfo']) {
				out << """<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:"${name}.css",plugin:'appInfo')}"/>"""
			}
		}
	}

	def layoutResources = { attrs ->
		boolean hasResourcesPlugin = PluginManagerHolder.pluginManager.hasGrailsPlugin('resources')

		if (hasResourcesPlugin) {
			out << r.layoutResources()
		}
		else {
			for (name in ['jquery.dimensions', 'jquery.jdMenu', 'jquery.bgiframe',
			              'jquery.positionBy', 'jquery.dataTables.min', 'jquery.tabs.min']) {
				out << g.javascript(src: 'jquery/' + name + '.js', plugin: 'appInfo')
			}
		}
	}
}
