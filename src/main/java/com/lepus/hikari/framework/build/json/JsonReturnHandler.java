package com.lepus.hikari.framework.build.json;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-23
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler{

	public boolean supportsReturnType(MethodParameter methodparameter) {
		System.out.println(1);
		return methodparameter.getMethodAnnotation(Json.class) != null;
	}

	public void handleReturnValue(Object obj, MethodParameter methodparameter, ModelAndViewContainer modelandviewcontainer, NativeWebRequest nativewebrequest) throws Exception {
		System.out.println(2);
		modelandviewcontainer.setRequestHandled(true);
		HttpServletResponse response = nativewebrequest.getNativeResponse(HttpServletResponse.class);
		Annotation[] annos = methodparameter.getMethodAnnotations();
		CustomJsonSerializer jsonSerializer = new CustomJsonSerializer();
		for(Annotation anno : annos){
			if(anno instanceof Json){
				Json json = (Json)anno;
				jsonSerializer.filter(json.type(), json.include(), json.exclude());
			}
		}
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		String json = jsonSerializer.toJson(obj);
		response.getWriter().write(json);
	}

}
