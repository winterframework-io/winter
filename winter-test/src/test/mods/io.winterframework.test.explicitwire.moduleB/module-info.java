@io.winterframework.core.annotation.Module

@io.winterframework.core.annotation.Wire(beans={"service1"}, into="beanA:service")
module io.winterframework.test.explicitwire.moduleB {
	requires io.winterframework.core;
	requires io.winterframework.core.annotation;
	
	exports io.winterframework.test.explicitwire.moduleB;
}