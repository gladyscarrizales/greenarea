package it.vige.greenarea.bpm.trasportatoreautonomo.visualizzamissioniautorizzate;

import static it.vige.greenarea.Constants.BASE_URI_TS;
import static it.vige.greenarea.Conversioni.convertiTimeSlotToFasciaOraria;
import static it.vige.greenarea.Utilities.setDettaglio;
import static it.vige.greenarea.bpm.risultato.Categoria.ERROREGRAVE;
import static it.vige.greenarea.bpm.risultato.Categoria.ERRORELIEVE;
import static it.vige.greenarea.bpm.risultato.Tipo.ERROREDATIMANCANTI;
import static it.vige.greenarea.bpm.risultato.Tipo.ERRORESISTEMA;
import static java.util.Arrays.asList;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.slf4j.LoggerFactory.getLogger;
import it.vige.greenarea.bpm.risultato.Messaggio;
import it.vige.greenarea.cl.library.entities.ParameterGen;
import it.vige.greenarea.cl.library.entities.ParameterTS;
import it.vige.greenarea.cl.library.entities.Price;
import it.vige.greenarea.cl.library.entities.TimeSlot;
import it.vige.greenarea.dto.FasciaOraria;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;

public class RecuperoDatiPolicy extends EmptyRecuperoDatiPolicy {

	private Logger logger = getLogger(getClass());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		try {
			super.execute(execution);
			logger.info("CDI Recupero Dati Policy");
			Client client = null;
			List<TimeSlot> timeSlots = null;
			try {
				client = newClient();
				Builder bldr = client.target(BASE_URI_TS + "/findAllTimeSlot")
						.request(APPLICATION_JSON);
				timeSlots = bldr.get(new GenericType<List<TimeSlot>>() {
				});
			} catch (Exception ex) {
				Messaggio messaggio = (Messaggio) execution
						.getVariable("messaggio");
				messaggio.setCategoria(ERRORELIEVE);
				messaggio.setTipo(ERROREDATIMANCANTI);
				throw new BpmnError("notificaerrorereperimentodatipolicy");
			}
			@SuppressWarnings("unchecked")
			List<FasciaOraria> fasceOrarie = (List<FasciaOraria>) execution
					.getVariable("policy");
			for (TimeSlot timeSlot : timeSlots) {
				FasciaOraria fasciaOraria = convertiTimeSlotToFasciaOraria(
						timeSlot, asList(new ParameterTS[0]),
						asList(new ParameterGen[0]), asList(new Price[0]));
				setDettaglio(timeSlot, client, fasciaOraria);
				fasceOrarie.add(fasciaOraria);
			}
		} catch (Exception ex) {
			Messaggio messaggio = (Messaggio) execution
					.getVariable("messaggio");
			messaggio.setCategoria(ERROREGRAVE);
			messaggio.setTipo(ERRORESISTEMA);
			throw new BpmnError("notificaerrorereperimentodatipolicy");
		}
	}

}