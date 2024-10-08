# Dependencies
---

Demonstrates the usage of Dependencies - certain tests that depend on other test methods/groups.

The `aFastTest` method depends on the `slow` group, so all the test methods under the `slow` group must successfully finish
before the `aFastTest` method runs.
Since one of the `slow` methods fail, the `aFastTest` will be marked as SKIPPED.