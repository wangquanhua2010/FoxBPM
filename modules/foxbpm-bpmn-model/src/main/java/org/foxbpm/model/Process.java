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
package org.foxbpm.model;

import java.util.List;

/**
 * 流程定义模型
 * @author ych
 *
 */
public class Process extends FlowElement {

	private static final long serialVersionUID = 1L;

	/**
	 * 流程定义编号（不唯一，可能有多个版本）
	 */
	protected String key;
	
	/**
	 * 流程主题
	 */
	protected String subject;
	
	/**
	 * 默认操作表单
	 */
	protected String formUri;
	
	/**
	 * 默认浏览表单
	 */
	protected String formUriView;
	
	/**
	 * 流程分类
	 */
	protected String category;
	
	/**
	 * 流程可发起人
	 */
	protected List<PotentialStarter> potentialStarters;

	/**
	 * 流程变量定义
	 */
	protected List<DataVariable> dataVariables;

	protected List<LaneSet> laneSets;

	/**
	 * 流程元素集合
	 */
	protected List<FlowElement> flowElements;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFormUri() {
		return formUri;
	}

	public void setFormUri(String formUri) {
		this.formUri = formUri;
	}

	public String getFormUriView() {
		return formUriView;
	}

	public void setFormUriView(String formUriView) {
		this.formUriView = formUriView;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<PotentialStarter> getPotentialStarters() {
		return potentialStarters;
	}

	public void setPotentialStarters(List<PotentialStarter> potentialStarters) {
		this.potentialStarters = potentialStarters;
	}

	public List<DataVariable> getDataVariables() {
		return dataVariables;
	}

	public void setDataVariables(List<DataVariable> dataVariables) {
		this.dataVariables = dataVariables;
	}

	public List<LaneSet> getLaneSets() {
		return laneSets;
	}

	public void setLaneSets(List<LaneSet> laneSets) {
		this.laneSets = laneSets;
	}

	public List<FlowElement> getFlowNodes() {
		return flowElements;
	}

	public void setFlowNodes(List<FlowElement> flowElements) {
		this.flowElements = flowElements;
	}

}