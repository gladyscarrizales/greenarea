/******************************************************************************
 * Vige, Home of Professional Open Source Copyright 2010, Vige, and           *
 * individual contributors by the @authors tag. See the copyright.txt in the  *
 * distribution for a full listing of individual contributors.                *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may    *
 * not use this file except in compliance with the License. You may obtain    *
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0        *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/
package it.vige.greenarea.bpm.operatorelogistico.test;

import static it.vige.greenarea.dto.Operazione.CHIUDI;
import it.vige.greenarea.bpm.GreenareaDemoData;
import it.vige.greenarea.bpm.mail.MyMessageHandlerFactory;
import it.vige.greenarea.bpm.operatorelogistico.service.anagrafeveicoli.RichiediVeicoliPopolati;
import it.vige.greenarea.dto.Veicolo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.subethamail.smtp.server.SMTPServer;

public class AnagrafeVeicoliOperatoreLogisticoTest extends
		ResourceActivitiTestCase {

	private final static String USER_NAME = "tnt";

	private GreenareaDemoData greenareaDemoData = new GreenareaDemoData();

	public AnagrafeVeicoliOperatoreLogisticoTest() {
		super("activiti.cfg-mem.xml");
	}

	@Deployment(resources = { "it/vige/greenarea/bpm/operatorelogistico/anagrafe_veicoli_operatore_logistico.bpmn20.xml" })
	public void testOK() {
		// PARTE IL SERVER DI POSTA
		MyMessageHandlerFactory myFactory = new MyMessageHandlerFactory();
		SMTPServer smtpServer = new SMTPServer(myFactory);
		smtpServer.setPort(25000);
		smtpServer.start();

		// RIEMPIMENTO UTENTI E GRUPPI DI TEST
		greenareaDemoData.initDemoGroups(identityService);
		greenareaDemoData.initDemoUsers(identityService);

		// AUTENTICAZIONE
		// Always reset authenticated user to avoid any mistakes
		identityService.setAuthenticatedUserId(USER_NAME);

		// INSERIMENTO FORM
		Map<String, Object> variables = new HashMap<String, Object>();
		String targa = "targa";
		variables.put("targa", targa);

		// POPOLO I VEICOLI
		ProcessDefinition aggiornaStatoVeicoli = repositoryService
				.createProcessDefinitionQuery().singleResult();
		BpmnModel aggiornaStatoVeicoliModel = repositoryService
				.getBpmnModel(aggiornaStatoVeicoli.getId());
		org.activiti.engine.repository.Deployment deployment = repositoryService
				.createDeploymentQuery().singleResult();
		ServiceTask richiediVeicoli = (ServiceTask) aggiornaStatoVeicoliModel
				.getFlowElement("richiediIVeicoli");
		richiediVeicoli.setImplementation(RichiediVeicoliPopolati.class
				.getName());
		repositoryService.deleteDeployment(deployment.getId());
		deployment = repositoryService.createDeployment()
				.addBpmnModel("dynamic-model.bpmn", aggiornaStatoVeicoliModel)
				.deploy();

		// INIZIO PROCESSO
		runtimeService.startProcessInstanceByKey(
				"anagrafeVeicoliOperatoreLogistico", variables);

		// VERIFICO LA CREAZIONE DEL TASK DI ELENCO VEICOLI
		List<Task> elencoVeicoli = taskService.createTaskQuery()
				.taskDefinitionKey("elencoVeicoli").includeProcessVariables()
				.list();
		assertEquals(elencoVeicoli.size(), 1);

		// CHIUDO
		@SuppressWarnings("unchecked")
		List<Veicolo> veicoli = (List<Veicolo>) taskService.getVariable(
				elencoVeicoli.get(0).getId(), "veicoli");
		assertEquals(veicoli.size(), 2);
		Map<String, Object> operazione = new HashMap<String, Object>();
		operazione.put("operazione", CHIUDI);
		taskService.complete(elencoVeicoli.get(0).getId(), operazione);

		// RIPULISCO IL DB
		greenareaDemoData.deleteAllIdentities(identityService);
		greenareaDemoData.deleteAllHistories(historyService);
		greenareaDemoData.deleteAllIDeployments(repositoryService);

		// FERMO IL SERVER DI POSTA
		smtpServer.stop();
	}

}
