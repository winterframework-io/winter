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

/**
 * <p>
 * Base info interface specifying data and services common to all info.
 * </p>
 * 
 * <p>
 * An info represents meta data for a Winter element which can be a module, a
 * bean or a socket. Errors or warnings can be reported on an info to indicate
 * inconsistencies preventing a further processing of the element.
 * </p>
 * 
 * @author jkuhn
 *
 */
public interface Info {

	/**
	 * <p>
	 * Returns the qualified name identifying the info.
	 * </p>
	 * 
	 * @return A qualified name.
	 */
	QualifiedName getQualifiedName();

	/**
	 * <p>
	 * Indicates whether this info has errors.
	 * </p>
	 * 
	 * @return true if there are errors, false otherwise.
	 */
	boolean hasError();

	/**
	 * <p>
	 * Indicates whether this info has warnings.
	 * </p>
	 * 
	 * @return true if there are warnings, false otherwise.
	 */
	boolean hasWarning();

	/**
	 * <p>
	 * Reports an error on this info.
	 * </p>
	 * 
	 * @param message The message to report.
	 */
	void error(String message);

	/**
	 * <p>
	 * Reports a warning on this info.
	 * </p>
	 * 
	 * @param message The message to report.
	 */
	void warning(String message);
}
