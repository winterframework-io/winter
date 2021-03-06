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
package io.inverno.core.test.lazy.moduleB;

import io.inverno.core.annotation.Bean;
import io.inverno.core.annotation.Lazy;

import io.inverno.core.test.lazy.moduleA.BeanB;
import io.inverno.core.test.lazy.moduleA.BeanC;

import java.util.function.Supplier;

@Bean
public class BeanD {
	
	public BeanB beanB1;
	public BeanB beanB2;
	
	public BeanC beanC1;
	public BeanC beanC2;
	
	public BeanD(@Lazy Supplier<BeanB> beanB, @Lazy Supplier<BeanC> beanC) {
		this.beanB1 = beanB.get();
		this.beanB2 = beanB.get();
		
		this.beanC1 = beanC.get();
		this.beanC2 = beanC.get();
	}
}
