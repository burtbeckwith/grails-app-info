<html>

<head>
<title><g:layoutTitle default='Application Information' /></title>

<link rel='shortcut icon' href='${resource(dir: '/images', file: 'favicon.ico', plugin: 'none')}' type='image/x-icon' />

<ai:resources/>

<g:layoutHead />

</head>

<body>

	<g:if test='${flash.message}'>
	<br/><div class='message'>${flash.message}</div><br/>
	</g:if>

	<div>

	<div id="left">

		<ul class="jd_menu jd_menu_slate">
			<li><a class="accessible">Attributes</a>
				<ul>
					<li><g:link action='applicationInfo'>Application</g:link></li>
					<li><g:link action='requestInfo'>Request</g:link></li>
					<li><g:link action='sessionInfo'>Session</g:link></li>
				</ul>
			</li>
			<li><a class="accessible">Properties</a>
				<ul>
					<li><g:link action='ds'>Data Source</g:link></li>
					<li><g:link action='grailsProperties'>Grails Properties</g:link></li>
					<li><g:link action='sysProperties'>System Properties</g:link></li>
					<li><g:link action='getenv'>Environment Variables</g:link></li>
				</ul>
			</li>
			<li><a class="accessible">Info</a>
				<ul>
					<li><g:link action='controllers'>Controllers</g:link></li>
					<li><g:link action='logging'>Logging</g:link></li>
					<li><g:link action='memory'>Memory</g:link></li>
					<li><g:link action='sessions'>Sessions</g:link></li>
					<li><g:link action='spring'>Spring Beans</g:link></li>
					<li><g:link action='threadDump'>Thread Dump</g:link></li>
				</ul>
			</li>
			<g:each var="additionalNavGroup" in="${grailsApplication.config.grails.plugins.appinfo.additional}">
			<li><a class="accessible">${additionalNavGroup.key}</a>
				<ul>
					<g:each var="additionalNavItem" in="${additionalNavGroup.value}">
						<li><g:link action='${additionalNavItem.key}'>${additionalNavItem.value}</g:link></li>
					</g:each>
				</ul>
			</li>
			</g:each>
		</ul>
	</div>

</div>

<div id="right"></div>

<ai:layoutResources/>

<g:layoutBody />

</body>
</html>
