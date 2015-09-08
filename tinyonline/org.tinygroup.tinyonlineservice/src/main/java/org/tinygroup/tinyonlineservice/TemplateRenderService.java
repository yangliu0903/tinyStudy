/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.tinyonlineservice;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceParameter;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.impl.TemplateContextDefault;
import org.tinygroup.template.impl.TemplateEngineDefault;
import org.tinygroup.template.loader.StringResourceLoader;

@ServiceComponent()
public class TemplateRenderService{
	@ServiceMethod(serviceId = "renderTemplate")
	@ServiceResult(name = "result")
	public String renderTemplate(@ServiceParameter String example) throws TemplateException, FileNotFoundException, UnsupportedEncodingException {
		
		final TemplateEngine engine = new TemplateEngineDefault();
		StringResourceLoader  resourceLoader = new StringResourceLoader();
		engine.addResourceLoader(resourceLoader);
		Template template = resourceLoader.createTemplate(example);
		TemplateContext context = new TemplateContextDefault();
		//context.put("name", "abc111");
		 Writer writer=null;
		 OutputStream stream =null;
		 try{
			 stream = new ByteArrayOutputStream();
			 writer = new OutputStreamWriter(stream,"utf-8");
			 template.render(context,stream);
			 return stream.toString();
		 }finally{
			 if(stream!=null){
				 try {
					stream.close();
				} catch (IOException e) {
					//ignore
				}
			 }
			 if(writer!=null){
				 try {
					writer.close();
				} catch (IOException e) {
					//ignore
				} 
			 }
		 }
		 
	}
}
