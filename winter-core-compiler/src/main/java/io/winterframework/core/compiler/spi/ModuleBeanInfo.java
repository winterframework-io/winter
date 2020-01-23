/*
 * Copyright 2018 Jeremy KUHN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.winterframework.core.compiler.spi;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import io.winterframework.core.annotation.Bean;
import io.winterframework.core.annotation.Scope;

/**
 * <p>
 * A module bean info holds the data required to process a bean in a module.
 * </p>
 * 
 * @author jkuhn
 *
 */
public interface ModuleBeanInfo extends BeanInfo {

	/**
	 * <p>
	 * Returns the type provide by the bean which is the type that will be exposed
	 * outside the module.
	 * </p>
	 * 
	 * @return A type.
	 */
	TypeMirror getProvidedType();
	
	/**
	 * <p>
	 * Returns the scope type of the bean.
	 * </p>
	 * 
	 * @return A scope type
	 */
	Scope.Type getScope();
	
	/**
	 * <p>
	 * Returns the visibility of the bean.
	 * </p>
	 * 
	 * <p>
	 * A public bean is exposed to other modules whereas a private bean is only
	 * visible from inside the module.
	 * </p>
	 * 
	 * @return A visibility
	 */
	Bean.Visibility getVisibility();
	
	/**
	 * <p>
	 * Returns the methods that should be invoked to initialize a bean instance.
	 * </p>
	 * 
	 * @return An array of executable elements.
	 */
	ExecutableElement[] getInitElements();
	
	/**
	 * <p>
	 * Returns the methods that should be invoked to destroy a bean instance.
	 * </p>
	 * 
	 * @return An array of executable elements.
	 */
	ExecutableElement[] getDestroyElements();
	
	/**
	 * <p>
	 * Returns the list of sockets specified by the bean.
	 * </p>
	 * 
	 * <p>
	 * A bean socket defines an entry point for dependency injection on the bean.
	 * </p>
	 * 
	 * @return An array of bean socket info.
	 */
	ModuleBeanSocketInfo[] getSockets();
	
	/**
	 * <p>
	 * Returns the list of required sockets specified by the bean.
	 * </p>
	 * 
	 * <p>
	 * A required socket must be resolved during dependency injection in order to
	 * instantiate the bean.
	 * </p>
	 * 
	 * @return An array of bean socket info.
	 */
	ModuleBeanSocketInfo[] getRequiredSockets();

	/**
	 * <p>
	 * Returns the list of optional sockets specified by the bean.
	 * </p>
	 * 
	 * <p>
	 * Unlike required sockets, an optional socket is not required to instantiate
	 * the bean.
	 * </p>
	 * 
	 * @return An array of bean socket info.
	 */
	ModuleBeanSocketInfo[] getOptionalSockets();
}
