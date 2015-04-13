modules = {

	'appInfo' {

		dependsOn 'jquery'

		for (name in ['jquery.jdMenu', 'jquery.jdMenu.slate',
		              'tabs', 'tabs-accordion', 'appinfo']) {
			resource url: [dir: 'css', file: name + '.css', plugin: 'appInfo']
		}

		for (name in ['jquery.dataTables.min', 'jquery.dataTables_themeroller']) {
			resource url: [dir: 'DataTables-1.10.6/media/css', file: name + '.css', plugin: 'appInfo']
		}
		for (name in ['dataTables.tableTools.min']) {
			resource url: [dir: 'DataTables-1.10.6/extensions/TableTools/css', file: name + '.css', plugin: 'appInfo']
		}

		for (name in ['appInfo.dataTable']) {
			resource url: [dir: 'js', file: name + '.js', plugin: 'appInfo']
		}
		for (name in ['jquery.dimensions', 'jquery.jdMenu', 'jquery.bgiframe',
		              'jquery.positionBy', 'jquery.tabs.min']) {
			resource url: [dir: 'js/jquery', file: name + '.js', plugin: 'appInfo']
		}

		for (name in ['jquery.dataTables.min']) {
			resource url: [dir: 'DataTables-1.10.6/media/js', file: name + '.js', plugin: 'appInfo']
		}
		for (name in ['dataTables.tableTools.min']) {
			resource url: [dir: 'DataTables-1.10.6/extensions/TableTools/js', file: name + '.js', plugin: 'appInfo']
		}
		for (name in ['copy_csv_xls', 'copy_csv_xls_pdf']) {
			resource url: [dir: 'DataTables-1.10.6/extensions/TableTools/swf', file: name + '.swf', plugin: 'appInfo']
		}
	}
}
