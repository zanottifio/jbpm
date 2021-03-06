package org.jbpm.services.task.commands;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.kie.internal.command.Context;
import org.kie.internal.task.api.TaskInstanceService;

@XmlRootElement(name="get-task-property-command")
@XmlAccessorType(XmlAccessType.NONE)
public class GetTaskPropertyCommand extends UserGroupCallbackTaskCommand<Object> {

	private static final long serialVersionUID = -836520791223188840L;

	@XmlElement
	@XmlSchemaType(name="integer")
	private Integer property;

	
	public GetTaskPropertyCommand() {
	}
	
	public GetTaskPropertyCommand(long taskId, String userId, Integer property) {
		this.taskId = taskId;
		this.userId = userId;
		this.property = property;
    }

    public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer name) {
		this.property = name;
	}

	public Object execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;

        TaskInstanceService service = context.getTaskInstanceService();
        Object result = null;
        switch (property) {
		case PRIORITY_PROPERTY:
			result = service.getPriority(taskId);
			break;
		case EXPIRATION_DATE_PROPERTY:
			result = service.getExpirationDate(taskId);
			break;
		case DESCRIPTION_PROPERTY:
			result = service.getDescriptions(taskId);
			break;
		case SKIPPABLE_PROPERTY:
			result = service.isSkipable(taskId);
			break;
		case SUB_TASK_STRATEGY_PROPERTY:
			result = service.getSubTaskStrategy(taskId);
			break;
		default:
			break;
		}
        return result;
    }

}
