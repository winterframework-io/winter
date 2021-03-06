/*
 * Copyright 2020 Jeremy KUHN
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
package io.inverno.core.test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.inverno.test.InvernoCompilationException;
import io.inverno.test.InvernoModuleLoader;
import io.inverno.test.InvernoModuleProxy;

/**
 * @author <a href="mailto:jeremy.kuhn@inverno.io">Jeremy Kuhn</a>
 *
 */
public class TestNestedBean extends AbstractCoreInvernoTest {

	private static final String MODULEA = "io.inverno.core.test.nested.moduleA";
	private static final String MODULEB = "io.inverno.core.test.nested.moduleB";
	private static final String MODULEC = "io.inverno.core.test.nested.moduleC";
	private static final String MODULED = "io.inverno.core.test.nested.moduleD";
	private static final String MODULEE = "io.inverno.core.test.nested.moduleE";
	private static final String MODULEF = "io.inverno.core.test.nested.moduleF";
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNested() throws IOException, InvernoCompilationException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		InvernoModuleLoader moduleLoader = this.getInvernoCompiler().compile(MODULEA);
		InvernoModuleProxy moduleA = moduleLoader.load(MODULEA).build();
		moduleA.start();
		try {
			Object beanA = moduleA.getBean("beanA");
			Assertions.assertNotNull(beanA);
			
			Object beanA_someSupplier = beanA.getClass().getField("someSupplier").get(beanA);
			Assertions.assertNotNull(beanA_someSupplier);
			
			Assertions.assertEquals("some supplier", ((Supplier<String>)beanA_someSupplier).get());
		}
		finally {
			moduleA.stop();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNestedNested() throws IOException, InvernoCompilationException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		InvernoModuleLoader moduleLoader = this.getInvernoCompiler().compile(MODULEB);
		InvernoModuleProxy moduleB = moduleLoader.load(MODULEB).build();
		moduleB.start();
		try {
			Object beanB = moduleB.getBean("beanB");
			Assertions.assertNotNull(beanB);
			
			Object beanB_someSupplier = beanB.getClass().getField("someSupplier").get(beanB);
			Assertions.assertNotNull(beanB_someSupplier);
			
			Assertions.assertEquals("some supplier", ((Supplier<String>)beanB_someSupplier).get());
		}
		finally {
			moduleB.stop();
		}
	}
	
	@Test
	public void testNestedInvalidNoArg() throws IOException {
		try {
			this.getInvernoCompiler().compile(MODULEC);
			Assertions.fail("Should throw an InvernoCompilationException");
		}
		catch(InvernoCompilationException e) {
			Assertions.assertEquals(2, e.getDiagnostics().size());

			String ignoringInvalidNested = "Ignoring invalid NestedBean which should be defined as a no-argument method";
			String missingBean = "No bean was found matching required socket io.inverno.core.test.nested.moduleC:beanC:someSupplier of type java.util.function.Supplier<java.lang.String>, consider defining a bean or a socket bean matching the socket in module io.inverno.core.test.nested.moduleC";
			
			Assertions.assertTrue(e.getDiagnostics().stream().map(d -> d.getMessage(Locale.getDefault())).collect(Collectors.toList()).containsAll(List.of(ignoringInvalidNested, missingBean)));
		}
	}
	
	@Test
	public void testNestedNestedInvalidNoArg() throws IOException {
		try {
			this.getInvernoCompiler().compile(MODULED);
			Assertions.fail("Should throw an InvernoCompilationException");
		}
		catch(InvernoCompilationException e) {
			Assertions.assertEquals(2, e.getDiagnostics().size());

			String ignoringInvalidNested = "Ignoring invalid NestedBean nested.someNested, io.inverno.core.test.nested.moduleD.NestedNested#someSupplier(java.lang.String) should be a no-argument method";
			String missingBean = "No bean was found matching required socket io.inverno.core.test.nested.moduleD:beanD:someSupplier of type java.util.function.Supplier<java.lang.String>, consider defining a bean or a socket bean matching the socket in module io.inverno.core.test.nested.moduleD";
			
			Assertions.assertTrue(e.getDiagnostics().stream().map(d -> d.getMessage(Locale.getDefault())).collect(Collectors.toList()).containsAll(List.of(ignoringInvalidNested, missingBean)));
		}
	}
	
	@Test
	public void testNestedInvalidNonVoid() throws IOException {
		try {
			this.getInvernoCompiler().compile(MODULEE);
			Assertions.fail("Should throw an InvernoCompilationException");
		}
		catch(InvernoCompilationException e) {
			Assertions.assertEquals(2, e.getDiagnostics().size());

			String ignoringInvalidNested = "Ignoring invalid NestedBean which should be defined as a non-void method";
			String missingBean = "No bean was found matching required socket io.inverno.core.test.nested.moduleE:beanE:someSupplier of type java.util.function.Supplier<java.lang.String>, consider defining a bean or a socket bean matching the socket in module io.inverno.core.test.nested.moduleE";
			
			Assertions.assertTrue(e.getDiagnostics().stream().map(d -> d.getMessage(Locale.getDefault())).collect(Collectors.toList()).containsAll(List.of(ignoringInvalidNested, missingBean)));
		}
	}
	
	@Test
	public void testNestedNestedInvalidNonVoid() throws IOException {
		try {
			this.getInvernoCompiler().compile(MODULEF);
			Assertions.fail("Should throw an InvernoCompilationException");
		}
		catch(InvernoCompilationException e) {
			Assertions.assertEquals(2, e.getDiagnostics().size());

			String ignoringInvalidNested = "Ignoring invalid NestedBean nested.someNested, io.inverno.core.test.nested.moduleF.NestedNested#someSupplier() should be a non-void method";
			String missingBean = "No bean was found matching required socket io.inverno.core.test.nested.moduleF:beanF:someSupplier of type java.util.function.Supplier<java.lang.String>, consider defining a bean or a socket bean matching the socket in module io.inverno.core.test.nested.moduleF";
			
			Assertions.assertTrue(e.getDiagnostics().stream().map(d -> d.getMessage(Locale.getDefault())).collect(Collectors.toList()).containsAll(List.of(ignoringInvalidNested, missingBean)));
		}
	}
}
