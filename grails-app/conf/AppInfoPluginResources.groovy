modules = {

	'appInfo' {

		dependsOn 'jquery'

		for (name in ['table', 'jquery.jdMenu', 'jquery.jdMenu.slate',
		              'tabs', 'tabs-accordion', 'appinfo']) {
			resource url: [dir: 'css', file: name + '.css', plugin: 'appInfo']
		}

		for (name in ['jquery.positionBy', 'jquery.bgiframe', 'jquery.jdMenu',
		              'jquery.dataTables.min', 'jquery.tabs.min']) {
			resource url: [dir: 'js/jquery', file: name + '.js', plugin: 'appInfo']
		}
	}
}
