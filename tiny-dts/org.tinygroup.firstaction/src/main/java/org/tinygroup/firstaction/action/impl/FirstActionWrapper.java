package org.tinygroup.firstaction.action.impl;

import java.util.List;
import java.util.UUID;

import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.dts.base.BusinessActionContext;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;
import org.tinygroup.firstaction.action.FirstAction;

public class FirstActionWrapper implements  FirstAction{

	private CEPCore core;
	

	public CEPCore getCore() {
		return core;
	}

	public void setCore(CEPCore core) {
		this.core = core;
	}
	
	private void callService(String serviceId,Context context) throws Exception{
		Event event = getEvent(serviceId,context);
		core.process(event);
	}
	
	private Event getEvent(String serviceId,Context context) throws Exception{
		Event event = new Event();
		event.setEventId(UUID.randomUUID().toString());
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setContext(context);
		serviceRequest.setServiceId(serviceId);
		event.setServiceRequest(serviceRequest);
		return event;
	}
	
	private <T> T callServiceAndCallBack(String serviceId,Context context) throws Exception{
		Event event = getEvent(serviceId,context);
		core.process(event);
		ServiceInfo info = core.getServiceInfo(serviceId);
		List<Parameter> resultsParam = info.getResults();
		if (resultsParam==null||resultsParam.size() == 0) {
			return null;
	}
		return event.getServiceRequest().getContext()
			.get(resultsParam.get(0).getName());
	}

	public void prepare_minus(BusinessActionContext businessActionContext,
			String accountNo, double amount) {
		String serviceId = "prepare_minus";

		try{
			Context context = new ContextImpl();
			context.put("businessActionContext" ,businessActionContext);
			context.put("accountNo" ,accountNo);
			context.put("amount" ,amount);

			callService(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public boolean commit(BusinessActionContext businessActionContext) {
		String serviceId = "commit";

		try{
			Context context = new ContextImpl();
			context.put("businessActionContext" ,businessActionContext);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}

	public boolean rollback(BusinessActionContext businessActionContext) {
		String serviceId = "rollback";

		try{
			Context context = new ContextImpl();
			context.put("businessActionContext" ,businessActionContext);

			return callServiceAndCallBack(serviceId,context);
		}catch(Exception e){
			throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
		}
	}
}
