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

import javax.lang.model.type.TypeMirror;

/**
 * <p>
 * Base socket bean interface.
 * </p>
 * 
 * <p>
 * A socket bean represents an injection point in a module for beans of type
 * assignable to the socket type.
 * </p>
 * 
 * @author jkuhn
 *
 */
public interface SocketBeanInfo extends BeanInfo, SocketInfo  {

	/**
	 * <p>
	 * Returns the actual type of socket bean.
	 * </p>
	 * 
	 * <p>
	 * This type should not be confused with the type returned by
	 * {@link SocketInfo#getType()}: the socket type is the type of the interface in
	 * a module defining the socket whereas the type is the type of bean that can be
	 * plugged into the socket.
	 * </p>
	 * 
	 * @return A type.
	 */
	TypeMirror getSocketType();
	
	/**
	 * <p>
	 * Returns the qualified names of all the beans directly or indirectly wired to
	 * the socket.
	 * </p>
	 * 
	 * @return An array of bean qualified names.
	 */
	BeanQualifiedName[] getWiredBeans();
}
