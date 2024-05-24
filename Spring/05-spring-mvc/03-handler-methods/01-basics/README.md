# Handler Method basics
---

Demonstrates the following annotations in Handler Methods:
* @RequestParam
* @RequestHeader
* @PathVariable

## @RequestParam
---

Usage:
```
$ curl localhost:8080/app/hello?name=yoav
```

## @RequestHeader
---

Usage:
```
$ curl -H "X-My-Header: somevalue" localhost:8080/app/header
```
