# Custom PropertyEditor
---

In this example, we demonstrate registering a custom PropertyEditor for converting a String that we take in a @RequestParam
to an object.

We pass in the URL a request parameter named `person` as a comma-delimited set of details.
The custom PropertyEditor translates it to a Person object.

## Usage
----

Browse to http://<host>:<port>/app/person?person=yoav,klein,marva,karmiel
