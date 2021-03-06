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
 * @author kenshin
 * @author ych
 */
package org.foxbpm.engine.impl.interceptor;

import org.foxbpm.engine.impl.Context;
import org.foxbpm.engine.impl.ProcessEngineConfigurationImpl;
import org.foxbpm.engine.impl.scriptlanguage.GroovyScriptLanguageMgmtImpl;
import org.foxbpm.engine.scriptlanguage.AbstractScriptLanguageMgmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kenshin
 */
public class CommandContextInterceptor extends CommandInterceptor {

	protected CommandContextFactory commandContextFactory;
	protected ProcessEngineConfigurationImpl processEngineConfiguration;

	private static Logger log = LoggerFactory.getLogger(CommandContextInterceptor.class);

	public CommandContextInterceptor() {

	}

	public CommandContextInterceptor(CommandContextFactory commandContextFactory, ProcessEngineConfigurationImpl processEngineConfiguration) {
		this.commandContextFactory = commandContextFactory;
		this.processEngineConfiguration = processEngineConfiguration;
	}

	public <T> T execute(final CommandConfig config,Command<T> command) {

		CommandContext context = Context.getCommandContext();

		/*
		 * 表示CommandContext是否复用，当已经存在commandContext时，此变量为true
		 * 当cmd中嵌套调用cmd时，此变量为true，表示cmd中共享commandContext.
		 * 只有最上层的cmd调用完毕时，此变量为false,关闭commandContext和变量管理器等。
		 */
		boolean contextReused = false;
		if (context == null || !config.isContextReuse() || context.getException() !=null) {
			context = commandContextFactory.createCommandContext(command);
			setAbstractScriptLanguageMgmt();
		} else {
			log.debug("CommandContext已经存在，共享此commandContext '{}'", command.getClass().getCanonicalName());
			if(!config.isScriptEngineReuse()){
				setAbstractScriptLanguageMgmt();
			}
			contextReused = true;
		}
		
		try {
			// Push on stack
			Context.setCommandContext(context);
			Context.setProcessEngineConfiguration(processEngineConfiguration);
			return next.execute(config,command);

		}finally {
			try {
				if (!contextReused) {
					context.close();
					Context.clearAbstractScriptLanguageMgmt();
				}
			} finally {
				Context.removeCommandContext();
				Context.removeProcessEngineConfiguration();
			}
		}
	}
	
	private void setAbstractScriptLanguageMgmt(){
		AbstractScriptLanguageMgmt abstractScriptLanguageMgmt = null;
		abstractScriptLanguageMgmt = new GroovyScriptLanguageMgmtImpl();
		Context.setAbstractScriptLanguageMgmt(abstractScriptLanguageMgmt.init());
	}

	public CommandContextFactory getCommandContextFactory() {
		return commandContextFactory;
	}

	public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
		this.commandContextFactory = commandContextFactory;
	}

	public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
		return processEngineConfiguration;
	}

	public void setProcessEngineContext(ProcessEngineConfigurationImpl processEngineContext) {
		this.processEngineConfiguration = processEngineContext;
	}
}
