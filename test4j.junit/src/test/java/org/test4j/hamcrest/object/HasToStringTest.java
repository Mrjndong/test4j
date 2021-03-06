package org.test4j.hamcrest.object;

import static ext.test4j.hamcrest.MatcherAssert.assertThat;
import static ext.test4j.hamcrest.core.IsEqual.equalTo;
import static ext.test4j.hamcrest.core.IsNot.not;
import static ext.test4j.hamcrest.object.HasToString.hasToString;

import org.junit.Test;
import org.test4j.hamcrest.AbstractMatcherTest;

import ext.test4j.hamcrest.Matcher;
import ext.test4j.hamcrest.StringDescription;

public class HasToStringTest extends AbstractMatcherTest {
	private static final String TO_STRING_RESULT = "toString result";
	private static final Object ARG = new Object() {
		@Override
		public String toString() {
			return TO_STRING_RESULT;
		}
	};

	@Override
	protected Matcher<?> createMatcher() {
		return hasToString(equalTo("irrelevant"));
	}

	@Test
	public void testPassesResultOfToStringToNestedMatcher() {
		assertThat(ARG, hasToString(equalTo(TO_STRING_RESULT)));
		assertThat(ARG, not(hasToString(equalTo("OTHER STRING"))));
	}

	@Test
	public void testProvidesConvenientShortcutForHasToStringEqualTo() {
		assertThat(ARG, hasToString(TO_STRING_RESULT));
		assertThat(ARG, not(hasToString("OTHER STRING")));
	}

	@Test
	public void testHasReadableDescription() {
		Matcher<? super String> toStringMatcher = equalTo(TO_STRING_RESULT);
		Matcher<Matcher<String>> matcher = hasToString(toStringMatcher);

		want.string(descriptionOf(matcher)).isEqualTo("with toString() " + descriptionOf(toStringMatcher));
	}

	@Test
	public void testMismatchContainsToStringValue() {
		String expectedMismatchString = "toString() was \"Cheese\"";
		assertMismatchDescription(expectedMismatchString, hasToString(TO_STRING_RESULT), "Cheese");
	}

	private String descriptionOf(Matcher<?> matcher) {
		return StringDescription.asString(matcher);
	}
}
