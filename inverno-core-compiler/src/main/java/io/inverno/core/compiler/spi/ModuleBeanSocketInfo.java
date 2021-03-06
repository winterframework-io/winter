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
package io.inverno.core.compiler.spi;

/**
 * <p>
 * Base module bean socket info.
 * </p>
 * 
 * <p>
 * A bean socket specifies a dependency injection point on a bean.
 * </p>
 * 
 * @author <a href="mailto:jeremy.kuhn@inverno.io">Jeremy Kuhn</a>
 *
 */
public interface ModuleBeanSocketInfo extends SocketInfo {

	/**
	 * <p>
	 * Returns a bean socket qualified name.
	 * </p>
	 * 
	 * @return a bean socket qualified name
	 */
	BeanSocketQualifiedName getQualifiedName();
	
	/**
	 * <p>
	 * Determines whether the socket is lazy.
	 * </p>
	 * 
	 * @return true if the socket is a lazy socket, false otherwise
	 */
	boolean isLazy();
}
