package org.example;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MyMatcher extends BaseMatcher {
    @Override
    public boolean matches(Object o) { return false; }

    @Override
    public void describeTo(Description d) {}
}
