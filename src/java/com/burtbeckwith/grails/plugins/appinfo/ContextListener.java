package com.burtbeckwith.grails.plugins.appinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Listener for context events (app start/end) and session events (session start/end).
 *
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
public class ContextListener implements ServletContextListener, HttpSessionListener {

	private static final String START_TIME = "grails.plugins.appinfo.start_time";

	private static ContextListener instance;

	private final Logger log = Logger.getLogger(getClass());

	private final List<HttpSession> sessions = new LinkedList<HttpSession>();
	private final Map<String, HttpSession> sessionsById = new HashMap<String, HttpSession>();

	/**
	 * Constructor, called by the container (singleton).
	 */
	public ContextListener() {
		instance = this;
	}

	/**
	 * Get the singleton instance.
	 * @return  the instance
	 */
	public static ContextListener instance() {
		return instance;
	}

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.ServletContextListener#contextInitialized(
	 * 	javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(final ServletContextEvent event) {
		log.debug("app startup");
		event.getServletContext().setAttribute(START_TIME, new Date());
	}

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.ServletContextListener#contextDestroyed(
	 * 	javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(final ServletContextEvent event) {
		log.debug("app shutdown");
	}

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(
	 * 	javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(final HttpSessionEvent event) {
		HttpSession session = event.getSession();
		synchronized (this) {
			sessions.add(session);
			sessionsById.put(session.getId(), session);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(
	 * 	javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(final HttpSessionEvent event) {
		HttpSession session = event.getSession();
		synchronized (this) {
			sessions.remove(session);
			sessionsById.remove(session.getId());
		}
	}

	/**
	 * All current sessions.
	 * @return  unmodifiable list of sessions (order by login time)
	 */
	public synchronized List<HttpSession> getSessions() {
		return new ArrayList<HttpSession>(sessions);
	}

	/**
	 * Get an individual session by id.
	 * @param id  the session id
	 * @return  the session or <code>null</code> if none with that id
	 */
	public synchronized HttpSession getSession(final String id) {
		return sessionsById.get(id);
	}
}
