<head>
	<meta name='layout' content='appinfo'/>
	<title>Application Scope</title>
	<g:javascript>
		var dtv1 = {
			dataTableName : "#attrNamesAndValues",
			swfPath : "${resource(dir: 'DataTables-1.10.6/extensions/TableTools/swf', file: 'copy_csv_xls_pdf.swf', plugin: 'appInfo')}",
			pdfMessage : "dev-${InetAddress.getLocalHost().getHostAddress()}"
		};
		var dtv2 = {
			dataTableName : "#initParamNamesAndValues",
			swfPath : "${resource(dir: 'DataTables-1.10.6/extensions/TableTools/swf', file: 'copy_csv_xls_pdf.swf', plugin: 'appInfo')}",
			pdfMessage : "dev-${InetAddress.getLocalHost().getHostAddress()}"
		};
		$(document).ready(function() {
			initDataTableExportOptions(dtv1);
			initDataTableExportOptions(dtv2);
			$('ul.tabs').tabs('div.panes > div');
		});
	</g:javascript>

</head>

<body>

<h1>Application-Scope Variables</h1>

<ul class="tabs">
	<li><a href="#">Context Attributes</a></li>
	<li><a href="#">Init Params</a></li>
</ul>

<div class='panes'>

	<div id="contextAttributesHolder" class="tabPane">
		<table id="attrNamesAndValues" cellpadding="0" cellspacing="0" border="0" class="display">
			<thead><tr><th>Key</th><th>Value</th></tr></thead>
			<tbody>
			<g:each var='entry' in='${attrNamesAndValues}'>
				<tr>
					<td>${entry.key}&nbsp;</td>
					<td>${entry.value}&nbsp;</td>
				</tr>
			</g:each>
			</tbody>
		</table>
	</div>

	<div id="initParamsHolder" class="tabPane">
		<table id="initParamNamesAndValues" cellpadding="0" cellspacing="0" border="0" class="display">
			<thead><tr><th>Key</th><th>Value</th></tr></thead>
			<tbody>
			<g:each var='entry' in='${initParamNamesAndValues}'>
				<tr>
					<td>${entry.key}&nbsp;</td>
					<td>${entry.value}&nbsp;</td>
				</tr>
			</g:each>
			</tbody>
		</table>
	</div>

</div>
</body>
