package it.vige.greenarea.itseasy.sgrl.wswrapper;

import it.vige.greenarea.sgrl.webservices.LogisticNetworkRouting;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LogisticNetworkRouting", targetNamespace = "http://webservices.sgrl.greenarea.vige.it/", wsdlLocation = "http://NBW72011844431.vige.local:8080/SGRL/LogisticNetworkRouting?wsdl")
public class LogisticNetworkRoutingService extends Service {

	private static URL LOGISTICNETWORKROUTING_WSDL_LOCATION;
	private static WebServiceException LOGISTICNETWORKROUTING_EXCEPTION;
	private final static QName LOGISTICNETWORKROUTING_QNAME = new QName(
			"http://webservices.sgrl.greenarea.vige.it/",
			"LogisticNetworkRouting");

	public static void setRootUrl(String root) {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL(root + "LogisticNetworkRouting?wsdl");
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		LOGISTICNETWORKROUTING_WSDL_LOCATION = url;
		LOGISTICNETWORKROUTING_EXCEPTION = e;
	}

	public LogisticNetworkRoutingService() {
		super(__getWsdlLocation(), LOGISTICNETWORKROUTING_QNAME);
	}

	public LogisticNetworkRoutingService(WebServiceFeature... features) {
		super(__getWsdlLocation(), LOGISTICNETWORKROUTING_QNAME, features);
	}

	public LogisticNetworkRoutingService(URL wsdlLocation) {
		super(wsdlLocation, LOGISTICNETWORKROUTING_QNAME);
	}

	public LogisticNetworkRoutingService(URL wsdlLocation,
			WebServiceFeature... features) {
		super(wsdlLocation, LOGISTICNETWORKROUTING_QNAME, features);
	}

	public LogisticNetworkRoutingService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public LogisticNetworkRoutingService(URL wsdlLocation, QName serviceName,
			WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 * 
	 * @return returns LogisticNetworkRouting
	 */
	@WebEndpoint(name = "LogisticNetworkRoutingPort")
	public LogisticNetworkRouting getLogisticNetworkRoutingPort() {
		return super.getPort(new QName(
				"http://webservices.sgrl.greenarea.vige.it/",
				"LogisticNetworkRoutingPort"), LogisticNetworkRouting.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns LogisticNetworkRouting
	 */
	@WebEndpoint(name = "LogisticNetworkRoutingPort")
	public LogisticNetworkRouting getLogisticNetworkRoutingPort(
			WebServiceFeature... features) {
		return super.getPort(new QName(
				"http://webservices.sgrl.greenarea.vige.it/",
				"LogisticNetworkRoutingPort"), LogisticNetworkRouting.class,
				features);
	}

	private static URL __getWsdlLocation() {
		if (LOGISTICNETWORKROUTING_EXCEPTION != null) {
			throw LOGISTICNETWORKROUTING_EXCEPTION;
		}
		return LOGISTICNETWORKROUTING_WSDL_LOCATION;
	}

}