/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author ych
 */
package org.foxbpm.rest.service.api.model;

import org.foxbpm.engine.repository.ProcessDefinition;

public class ProcessDefinitionResponse {

	private String id;
	private String name;
	private String catory;
	private String deploymentId;
	private String key;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatory() {
		return catory;
	}
	public void setCatory(String catory) {
		this.catory = catory;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String processKey) {
		this.key = processKey;
	}
	
	public ProcessDefinitionResponse() {
		
	}
	
	public ProcessDefinitionResponse(ProcessDefinition processDefinition) {
		setId(processDefinition.getId());
		setCatory(processDefinition.getCategory());
		setDeploymentId(processDefinition.getDeploymentId());
		setName(processDefinition.getName());
		setKey(processDefinition.getKey());
	}
	
}
