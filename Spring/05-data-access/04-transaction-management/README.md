# Transaction Management
---

This project demonstrates transaction management in Spring.
It's mocks a banking system with accounts, and the `transfer` method should be transactional - we first pull money from the sender, put it in the receiver accout, and write it to the transaction table.

We demonstrate what happens when an exception is thrown during the transaction - it rolls back.
