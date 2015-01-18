package it.vige.greenarea.bpm.amministratore.gestiscifiltri;

import static it.vige.greenarea.bpm.risultato.Categoria.OK;
import static it.vige.greenarea.bpm.risultato.Tipo.NESSUNERRORE;
import static org.slf4j.LoggerFactory.getLogger;
import it.vige.greenarea.bpm.risultato.Messaggio;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;

public class EmptyCancellaFiltro implements JavaDelegate {

	private Logger logger = getLogger(getClass());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("Cancella Filtro");
		Messaggio messaggio = (Messaggio) execution.getVariable("messaggio");
		messaggio.setCategoria(OK);
		messaggio.setTipo(NESSUNERRORE);
	}

}