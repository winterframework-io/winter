/**
 * 
 */
package io.winterframework.core.compiler.bean;

import java.util.Collections;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import io.winterframework.core.annotation.Bean;
import io.winterframework.core.annotation.Scope;
import io.winterframework.core.compiler.common.AbstractBeanInfo;
import io.winterframework.core.compiler.spi.BeanQualifiedName;
import io.winterframework.core.compiler.spi.ModuleBeanInfo;
import io.winterframework.core.compiler.spi.ModuleBeanSocketInfo;

/**
 * @author jkuhn
 *
 */
class CommonModuleBeanInfo extends AbstractBeanInfo implements ModuleBeanInfo {

	private Bean.Visibility visibility;
	
	private Scope.Type scope;
	
	private List<ExecutableElement> initElements;
	
	private List<ExecutableElement> destroyElements;
	
	private List<? extends ModuleBeanSocketInfo> socketInfos;
	
	private TypeMirror providedType;
	
	public CommonModuleBeanInfo(ProcessingEnvironment processingEnvironment, 
			Element element, 
			AnnotationMirror annotation, 
			BeanQualifiedName qname, 
			TypeMirror type, 
			TypeMirror providedType,
			List<? extends ModuleBeanSocketInfo> beanSocketInfos) {
		this(processingEnvironment, element, annotation, qname, type, providedType, Bean.Visibility.PUBLIC, Scope.Type.PROTOTYPE, Collections.emptyList(), Collections.emptyList(), beanSocketInfos);
	}
	
	public CommonModuleBeanInfo(ProcessingEnvironment processingEnvironment,
			Element element, 
			AnnotationMirror annotation, 
			BeanQualifiedName qname, 
			TypeMirror type, 
			TypeMirror providedType,
			Bean.Visibility visibility, 
			Scope.Type scope, 
			List<ExecutableElement> initElements, 
			List<ExecutableElement> destroyElements, 
			List<? extends ModuleBeanSocketInfo> beanSocketInfos) {
		super(processingEnvironment, element, annotation, qname, type);
		
		this.providedType = providedType;
		this.visibility = visibility != null ? visibility : Bean.Visibility.PUBLIC;
		this.scope = scope != null ? scope : Scope.Type.SINGLETON;
		this.initElements = initElements != null ? Collections.unmodifiableList(initElements) : Collections.emptyList();
		this.destroyElements = destroyElements != null ? Collections.unmodifiableList(destroyElements) : Collections.emptyList();
		this.socketInfos = beanSocketInfos != null ? Collections.unmodifiableList(beanSocketInfos) : Collections.emptyList();
	}

	@Override
	public TypeMirror getProvidedType() {
		return this.providedType;
	}
	
	@Override
	public Scope.Type getScope() {
		return this.scope;
	}

	@Override
	public Bean.Visibility getVisibility() {
		return this.visibility;
	}
	
	@Override
	public ExecutableElement[] getInitElements() {
		return this.initElements.stream().toArray(ExecutableElement[]::new);
	}

	@Override
	public ExecutableElement[] getDestroyElements() {
		return this.destroyElements.stream().toArray(ExecutableElement[]::new);
	}

	@Override
	public ModuleBeanSocketInfo[] getSockets() {
		return this.socketInfos.stream().toArray(ModuleBeanSocketInfo[]::new);
	}

	@Override
	public ModuleBeanSocketInfo[] getRequiredSockets() {
		return this.socketInfos.stream().filter(socketInfo -> !socketInfo.isOptional()).toArray(ModuleBeanSocketInfo[]::new);
	}

	@Override
	public ModuleBeanSocketInfo[] getOptionalSockets() {
		return this.socketInfos.stream().filter(socketInfo -> socketInfo.isOptional()).toArray(ModuleBeanSocketInfo[]::new);
	}
}