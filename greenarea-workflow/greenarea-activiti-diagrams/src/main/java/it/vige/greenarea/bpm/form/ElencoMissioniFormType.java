package it.vige.greenarea.bpm.form;

import static it.vige.greenarea.Constants.BASE_URI_RICHIESTE;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.slf4j.LoggerFactory.getLogger;
import it.vige.greenarea.dto.Missione;
import it.vige.greenarea.dto.RichiestaMissioni;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.form.AbstractFormType;
import org.slf4j.Logger;

public class ElencoMissioniFormType extends AbstractFormType implements
		Serializable {

	private Logger logger = getLogger(getClass());
	private static final long serialVersionUID = 1L;
	protected Map<String, String> values = new LinkedHashMap<String, String>();

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");

	@Override
	public String getName() {
		return "missioniEnum";
	}

	@Override
	public Object getInformation(String key) {
		if (key.equals("values")) {
			return values;
		}
		return null;
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
		validateValue(propertyValue);
		return propertyValue;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
		getMissioni();
		return (String) modelValue;
	}

	protected void validateValue(String value) {
		if (value != null) {
			if (values != null && !values.containsKey(value)) {
				throw new ActivitiIllegalArgumentException(
						"Invalid value for enum form property: " + value);
			}
		}
	}

	private void getMissioni() {
		Client client = newClient();
		Builder bldr = client
				.target(BASE_URI_RICHIESTE + "/getSintesiMissioni").request(
						APPLICATION_JSON);
		RichiestaMissioni richiesta = new RichiestaMissioni();
		String todayStr = dateFormat.format(new Date());
		Date today;
		try {
			today = dateFormat.parse(todayStr);
			richiesta.setDataInizio(today);
			richiesta.setDataFine(today);
		} catch (ParseException e) {
			logger.error("parsinge della data", e);
		}
		List<Missione> missioni = bldr.post(
				entity(richiesta, APPLICATION_JSON),
				new GenericType<List<Missione>>() {
				});
		values.clear();
		if (missioni != null)
			for (Missione missione : missioni) {
				values.put(missione.getNome(), missione.getNome());
			}
	}

}