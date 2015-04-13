<head>
	<meta name='layout' content='appinfo'/>
	<title>Controllers</title>
	<g:javascript>
		var dtv = {
			dataTableName : "#data",
			swfPath : "${resource(dir: 'DataTables-1.10.6/extensions/TableTools/swf', file: 'copy_csv_xls_pdf.swf', plugin: 'appInfo')}",
			pdfMessage : "dev-${InetAddress.getLocalHost().getHostAddress()}"
		};
		$(document).ready(function () {
			initDataTableExportOptions(dtv);
		});
	</g:javascript>
</head>
<body>
<h1>Controllers</h1>
<ul>
	<table id="data" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead><tr><th>Controller</th></tr></thead>
		<tbody>
		<g:each var='item' in='${data}'>
			<tr>
				<td>
					${item.controllerName}<br/>
					<ul>
						<g:each var='action' in='${item.actions}'>
							<li><g:link controller='${item.controller}' action='${action}'>${action}</g:link></li>
						</g:each>
					</ul>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>

</body>
