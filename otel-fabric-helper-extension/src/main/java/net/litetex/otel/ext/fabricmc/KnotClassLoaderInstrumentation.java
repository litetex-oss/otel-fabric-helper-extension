/*
 * Copyright © 2025 ${owner} (${email})
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.litetex.otel.ext.fabricmc;

import static net.bytebuddy.matcher.ElementMatchers.isMethod;
import static net.bytebuddy.matcher.ElementMatchers.isPublic;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.not;
import static net.bytebuddy.matcher.ElementMatchers.takesArgument;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

import java.net.URL;

import io.opentelemetry.javaagent.bootstrap.internal.ClassLoaderMatcherCacheHolder;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.TypeTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;


public class KnotClassLoaderInstrumentation implements TypeInstrumentation
{
	@Override
	public ElementMatcher<TypeDescription> typeMatcher()
	{
		return named("net.fabricmc.loader.impl.launch.knot.KnotClassLoader");
	}
	
	@Override
	public void transform(final TypeTransformer transformer)
	{
		transformer.applyAdviceToMethod(
			isMethod()
				.and(named("addUrlFwd"))
				.and(takesArguments(1))
				.and(takesArgument(0, URL.class))
				.and(isPublic())
				.and(not(isStatic())),
			KnotClassLoaderInstrumentation.class.getName() + "$AddUrlAdvice");
	}
	
	@SuppressWarnings({"unused", "PMD.UseUtilityClass"})
	public static class AddUrlAdvice
	{
		@Advice.OnMethodExit(suppress = Throwable.class)
		public static void onExit(@Advice.This final ClassLoader loader)
		{
			ClassLoaderMatcherCacheHolder.invalidateAllCachesForClassLoader(loader);
		}
	}
}
