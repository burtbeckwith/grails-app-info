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
			out << """<link rel='stylesheet' media="screen" href='${resource(dir:'css', file: 'main.css', plugin: 'none')}' />"""
		}
	}

	def layoutResources = { attrs ->
		boolean hasResourcesPlugin = PluginManagerHolder.pluginManager.hasGrailsPlugin('resources')

		if (hasResourcesPlugin) {
			out << r.layoutResources()
		}
		else {
			for (name in ['jquery/jquery.positionBy', 'jquery/jquery.bgiframe', 'jquery/jquery.jdMenu',
			              'jquery/jquery.dataTables.min', 'jquery/jquery.tabs.min']) {
				out << g.javascript(src: name + '.js', plugin: 'appInfo')
			}
		}
	}
}
