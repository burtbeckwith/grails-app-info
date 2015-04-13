<head>
	<meta name='layout' content='appinfo'/>
	<title>Environment Variables</title>
	<g:javascript>
		var dtv = {
			dataTableName : "#getenv",
			swfPath : "${resource(dir: 'DataTables-1.10.6/extensions/TableTools/swf', file: 'copy_csv_xls_pdf.swf', plugin: 'appInfo')}",
			pdfMessage : "dev-${InetAddress.getLocalHost().getHostAddress()}"
		};
		$(document).ready(function () {
			initDataTableExportOptions(dtv);
		});
	</g:javascript>
</head>

<body>
<br/>

<div id="getenvHolder">
	<h2>Environment Variables</h2>
	<table id="getenv" cellpadding="0" cellspacing="0" border="0" class="display">
		<thead><tr><th>Key</th><th>Value</th></tr></thead>
		<tbody>
		<g:each var='prop' in='${getenv}'>
			<tr>
				<td>${prop.key}</td>
				<td>${prop.value.encodeAsHTML()}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</div>

<br/>

</body>
