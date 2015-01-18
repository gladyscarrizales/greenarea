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
package it.vige.greenarea.bpm.custom.ui.societaditrasporto;

import static com.vaadin.ui.themes.Reindeer.LAYOUT_WHITE;
import static org.activiti.engine.ProcessEngines.getDefaultProcessEngine;
import static org.activiti.explorer.ExplorerApp.get;
import static org.activiti.explorer.navigation.ProcessNavigator.process_URI_PART;
import it.vige.greenarea.bpm.custom.ui.dettaglio.KmlDocumentViewer;
import it.vige.greenarea.bpm.custom.ui.form.GreenareaFormPropertiesForm;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.explorer.NotificationManager;
import org.activiti.explorer.navigation.UriFragment;
import org.activiti.explorer.ui.form.FormPropertiesEventListener;
import org.activiti.explorer.ui.form.FormPropertiesForm.FormPropertiesEvent;
import org.activiti.explorer.ui.reports.ReportDetailPanel;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

public class PosizioneVeicolo extends ReportDetailPanel {

	private static final long serialVersionUID = -5903548585312958722L;

	protected transient RuntimeService runtimeService;
	protected transient TaskService taskService;
	protected NotificationManager notificationManager;
	protected GreenareaFormPropertiesForm processDefinitionStartForm;

	private Label mainTitle;

	private FormService formService;

	private HistoricVariableInstance posizioneVeicoloInstance;

	protected VerticalLayout detailPanelLayout;

	public PosizioneVeicolo(String processDefinitionId,
			SocietaDiTrasportoDetailPanel detailPanel) {
		super(processDefinitionId, detailPanel.getParentPage());
		this.mainTitle = (Label) detailPanel.getMainPanel()
				.getComponentIterator().next();
	}

	@Override
	protected void initUi() {
		runtimeService = getDefaultProcessEngine().getRuntimeService();
		taskService = getDefaultProcessEngine().getTaskService();
		notificationManager = get().getNotificationManager();
		formService = getDefaultProcessEngine().getFormService();
		setSizeFull();
		addStyleName(LAYOUT_WHITE);

		detailPanelLayout = new VerticalLayout();
		detailPanelLayout.setWidth(100, UNITS_PERCENTAGE);
		setDetailContainer(detailPanelLayout);
		executeProcess();
	}

	public void executeProcess() {
		// Check if process-definition defines a start-form

		StartFormData startFormData = formService
				.getStartFormData(processDefinition.getId());
		if (startFormData != null
				&& ((startFormData.getFormProperties() != null && startFormData
						.getFormProperties().size() > 0) || startFormData
						.getFormKey() != null)) {
			showStartForm(processDefinition, startFormData);
		}
	}

	@Override
	protected void initActions() {
	}

	@Override
	public void initForm() {
		if (processDefinitionStartForm == null) {
			StartFormData startFormData = formService
					.getStartFormData(processDefinition.getId());
			processDefinitionStartForm = new GreenareaFormPropertiesForm();
			processDefinitionStartForm.setMainTitle(mainTitle);
			processDefinitionStartForm.setFormProperties(startFormData
					.getFormProperties());
			processDefinitionStartForm.hideCancelButton();

			// When form is submitted/cancelled, show the info again
			processDefinitionStartForm
					.addListener(new FormPropertiesEventListener() {
						private static final long serialVersionUID = 1L;

						protected void handleFormSubmit(
								FormPropertiesEvent event) {
							// Report is generated by running a process and
							// storing the dataset in the history tablkes
							savedFormProperties = event.getFormProperties();
							ProcessInstance processInstance = startProcessInstanceWithFormProperties(
									processDefinition.getId(),
									event.getFormProperties());
							generateReport(processInstance);
						}

						protected void handleFormCancel(
								FormPropertiesEvent event) {
						}
					});
		}
		addComponent(processDefinitionStartForm);
	}

	protected void showProcessDefinitionDetail(String processDefinitionId) {
		changeUrl(processDefinitionId);
	}

	protected void changeUrl(String processDefinitionId) {
		UriFragment processDefinitionFragment = new UriFragment(
				process_URI_PART, processDefinitionId);
		get().setCurrentUriFragment(processDefinitionFragment);
	}

	public void showStartForm(ProcessDefinition processDefinition,
			StartFormData startFormData) {
		showProcessDefinitionDetail(processDefinition.getId());
		initForm();
	}

	public Label getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(Label mainTitle) {
		this.mainTitle = mainTitle;
	}

	@Override
	protected void generateReport(ProcessInstance processInstance) {
		// Report dataset is stored as historical variable as json
		posizioneVeicoloInstance = ProcessEngines.getDefaultProcessEngine()
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(processInstance.getId())
				.variableName("reportData").singleResult();
		GreenareaFormPropertiesForm form = (GreenareaFormPropertiesForm) mainPanel
				.getComponentIterator().next();
		mainPanel.removeAllComponents();
		addComponent(form);
		if (posizioneVeicoloInstance != null) {
			Coordinate coordinate = new Coordinate(
					(String) posizioneVeicoloInstance.getValue());
			KmlDocumentViewer kmlDocumentViewer = new KmlDocumentViewer("",
					coordinate);
			detailPanelLayout.addComponent(kmlDocumentViewer);
			addComponent(kmlDocumentViewer);
		}
		// The historic process instance can now be removed from the system
		// Only when save is clicked, the report will be regenerated
		ProcessEngines.getDefaultProcessEngine().getHistoryService()
				.deleteHistoricProcessInstance(processInstance.getId());
	}

}
