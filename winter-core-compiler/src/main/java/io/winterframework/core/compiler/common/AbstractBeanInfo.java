/**
 * 
 */
package io.winterframework.core.compiler.common;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

import io.winterframework.core.compiler.spi.BeanInfo;
import io.winterframework.core.compiler.spi.BeanQualifiedName;

/**
 * @author jkuhn
 *
 */
public abstract class AbstractBeanInfo extends AbstractInfo<BeanQualifiedName> implements BeanInfo {

	protected TypeMirror type;
	
	public AbstractBeanInfo(ProcessingEnvironment processingEnvironment, Element element, BeanQualifiedName qname, TypeMirror type) {
		this(processingEnvironment, element, null, qname, type);
	}

	public AbstractBeanInfo(ProcessingEnvironment processingEnvironment, Element element, AnnotationMirror annotation, BeanQualifiedName qname, TypeMirror type) {
		super(processingEnvironment, element, annotation, qname);
		
		this.qname = qname;
		this.type = type;
	}
	
	@Override
	public TypeMirror getType() {
		return this.type;
	}

}