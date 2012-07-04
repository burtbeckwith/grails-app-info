<head>
<meta name='layout' content='appinfo' />
<title>Data Source</title>

<g:javascript>
$(document).ready(function() {
	$('#dataSource').dataTable({
		"bStateSave": true
	});
});
</g:javascript>

</head>

<body>

<br/>
<span class='appinfo_warning'>Note: Only visible form element values will be submitted</span><br/><br/>

<g:form action='updateDataSource'>

<div id="dataSourceHolder">
<h2>Data Source (${dataSourceClassName?.encodeAsHTML()})</h2>
<table id="dataSource" cellpadding="0" cellspacing="0" border="0" class="display">
	<thead><tr><th>Key</th><th>Value</th></thead>
	<tbody>
		<g:each var='property' in='${propertyInfo}'>
		<tr>
			<td>${property.name?.encodeAsHTML()}&nbsp;</td>
			<td>
			<g:if test='${property.type == "boolean"}'>
				<g:if test='${property.set}'>
					<g:checkBox name='SETTER_${property.set?.encodeAsHTML()}' value='${property.value}'/>
				</g:if>
				<g:else>
					${property.value}
				</g:else>
			</g:if>
			<g:else>
				<g:if test='${property.set}'>
					<g:textField size='70' class='text' name='SETTER_${property.set?.encodeAsHTML()}' value='${property.value?.encodeAsHTML()}'/>
				</g:if>
				<g:else>
					${property.value?.encodeAsHTML()}
				</g:else>
			</g:else>
			</td>
		</tr>
		</g:each>
	</tbody>
</table>
</div>

<input type='submit' value='Update' />

</g:form>

</body>
