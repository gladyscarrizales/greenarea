package it.vige.greenarea.bpm.custom.ui.operatorelogistico;

import static com.vaadin.ui.themes.Reindeer.LAYOUT_WHITE;
import static org.activiti.engine.ProcessEngines.getDefaultProcessEngine;
import static org.activiti.explorer.ExplorerApp.get;
import static org.activiti.explorer.navigation.ProcessNavigator.process_URI_PART;
import it.vige.greenarea.bpm.custom.ui.dettaglio.operatorelogistico.anagrafeveicoli.AnagrafeVeicoliOpPage;
import it.vige.greenarea.bpm.custom.ui.dettaglio.operatorelogistico.anagrafeveicoli.AnagrafeVeicoliOpPanel;
import it.vige.greenarea.bpm.custom.ui.form.GreenareaFormPropertiesForm;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.explorer.NotificationManager;
import org.activiti.explorer.navigation.UriFragment;
import org.activiti.explorer.ui.AbstractPage;
import org.activiti.explorer.ui.process.AbstractProcessDefinitionDetailPanel;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AnagrafeVeicoli extends AbstractProcessDefinitionDetailPanel {

	private static final long serialVersionUID = -5903548585312958722L;

	protected transient RuntimeService runtimeService = getDefaultProcessEngine()
			.getRuntimeService();
	protected transient TaskService taskService = getDefaultProcessEngine()
			.getTaskService();
	protected NotificationManager notificationManager = get()
			.getNotificationManager();
	protected GreenareaFormPropertiesForm processDefinitionStartForm;

	private Label mainTitle;

	public AnagrafeVeicoli(String processDefinitionId,
			OperatoreLogisticoDetailPanel detailPanel) {
		super(processDefinitionId, detailPanel.getParentPage());
		this.mainTitle = (Label) detailPanel.getMainPanel()
				.getComponentIterator().next();
		executeProcess();
	}

	@Override
	protected void initUi() {
		setSizeFull();
		addStyleName(LAYOUT_WHITE);

		detailPanelLayout = new VerticalLayout();
		detailPanelLayout.setWidth(100, UNITS_PERCENTAGE);
		setDetailContainer(detailPanelLayout);
	}

	@Override
	protected void initActions(AbstractPage parentPage) {
	}

	public void executeProcess() {
		// Just start the process-instance since it has no form.
		// TODO: Error handling
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId());

		Task task = taskService.createTaskQuery()
				.processInstanceId(processInstance.getId()).singleResult();
		Component detailComponent = new AnagrafeVeicoliOpPanel(task,
				new AnagrafeVeicoliOpPage(processInstance.getId(),
						mainTitle));
		addComponent(detailComponent);
	}

	protected void showProcessDefinitionDetail(String processDefinitionId) {
		changeUrl(processDefinitionId);
	}

	protected void changeUrl(String processDefinitionId) {
		UriFragment processDefinitionFragment = new UriFragment(
				process_URI_PART, processDefinitionId);
		get().setCurrentUriFragment(processDefinitionFragment);
	}

	public Label getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(Label mainTitle) {
		this.mainTitle = mainTitle;
	}

}