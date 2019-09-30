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
package io.winterframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

/**
 * <p>
 * Indicates that a annotated class or interface is a bean. Inside a module, a
 * bean represents an instance that can be wired to other bean instances visible
 * by this module.
 * </p>
 * 
 * <p>
 * A bean is fully identified by its name (which defaults to the name of the
 * class) and the name of the module exposing the bean (eg.
 * [MODULE_NAME]:[BEAN_NAME]). We can differentiate three kind of beans: module
 * bean, wrapper bean and socket bean.
 * </p>
 * 
 * <p>
 * A module bean is automatically instantiated and wired. Its dependencies must
 * be defined in injection points or sockets which can be either the constructor
 * for required dependencies or setter methods for optional dependencies. By
 * convention, any setter method is considered as a socket which may lead to
 * ambiguities. In that case a {@link BeanSocket} annotation can be used to
 * specify bean sockets explicitly.
 * </p>
 * 
 * <pre>
 * {@code
 *     &#64;Bean
 *     public class ModuleBean implements SomeService {
 *         
 *         public ModuleBean(RequiredDependency requiredDependency) {
 *             ...
 *         }
 *         
 *         public void setOptionalDependency(OptionalDependency optionalDependency) {
 *             ...
 *         }
 *         
 *         &#64;Init
 *         public void init() {
 *             ...
 *         }
 *         
 *         &#64;Destroy
 *         public void destroy() {
 *             ...
 *         }
 *     }
 * }
 * </pre>
 * 
 * <p>
 * A wrapper bean should be created to expose legacy code that can't be
 * instrumented by creating a bean class implementing {@link Supplier} and
 * annotated with {@link Wrapper}.
 * </p>
 * 
 * <pre>
 * {@code
 *     &#64;Bean
 *     &#64;Wrapper
 *     public class WrapperBean implements Supplier<SomeService> {
 *         
 *         private SomeService instance;
 *         
 *         public WrapperBean(RequiredDependency requiredDependency) {
 *             // Instantiate the wrapped instance
 *             ...
 *         }
 *         
 *         public void setOptionalDependency(OptionalDependency optionalDependency) {
 *             // Set optional dependency on the instance
 *             ...
 *         }
 *         
 *         public SomeService get() {
 *             return this.instance;
 *         }
 *         
 *         &#64;Init
 *         public void init() {
 *             // Init the instance
 *             ...
 *         }
 *         
 *         &#64;Destroy
 *         public void destroy() {
 *             // Destroy the instance
 *             ...
 *         }
 *     }
 * }
 * </pre>
 * 
 * <p>
 * A socket bean is a particular type of bean which is used to declare a module
 * dependency which is a bean required or desirable by the beans in the module
 * to operate properly. As for bean socket, it should be seen as an injection
 * point at module level to inject an external bean into the module (hence the
 * "socket" designation). From a dependency injection perspective, inside the
 * module, a socket bean is considered just like any other bean and is
 * automatically or explicitly injected in beans in the module or imported
 * modules. A socket bean must be an interface annotated with {@link Bean} with
 * a {@link Visibility#PUBLIC} visibility and extends {@link Supplier}.
 * </p>
 * 
 * <pre>
 * {@code
 *     &#64;Bean
 *     public interface SocketBean implements Supplier<SomeService> {
 *     }
 * }
 * </pre>
 * 
 * @author jkuhn
 * @Since 1.0
 * @see BeanSocket
 * @see Wrapper
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.TYPE })
public @interface Bean {

	/**
	 * Indicates the visibility of a bean in a module.
	 * 
	 * @author jkuhn
	 * @Since 1.0
	 */
	public static enum Visibility {
		/**
		 * A private bean is only accessible inside the module.
		 */
		PRIVATE,
		/**
		 * A public bean is accessible inside the module and in enclosing modules.
		 */
		PUBLIC;
	}

	/**
	 * <p>
	 * Indicates a name identifying the bean in the module, defaults to the name of
	 * the class (eg. [MODULE_NAME]:[BEAN_NAME]).
	 * </p>
	 */
	String name() default "";

	/**
	 * <p>
	 * Indicates the visibility of the bean in the module.
	 * </p>
	 * 
	 * <p>
	 * Usually, you're most likely to create public beans exposed to other modules.
	 * Private bean are provided as a convenience to let the framework instantiate
	 * and wire internal beans instead of doing it explicitly.
	 * </p>
	 */
	Visibility visibility() default Visibility.PUBLIC;

	/**
	 * <p>
	 * Defines the types actually provided by the bean, defaults to the actual bean
	 * type.
	 * </p>
	 * 
	 * <p>
	 * This allows to control how a bean is exposed to other modules. For instance,
	 * you might not want to expose the actual bean type which is most likely an
	 * implementation class that should not be exported by a module, you'd rather
	 * choose to expose one or more public interface instead.
	 * </p>
	 */
	Class<?>[] provides() default {};
}