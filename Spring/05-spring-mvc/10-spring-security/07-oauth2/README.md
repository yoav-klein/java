# Authorization 101
---

In this project we demonstrate the use of authorization.

So let's look at the relevant piece of code:
```
http.authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/management").hasRole("ADMIN")
    .requestMatchers("/").permitAll()
    .anyRequest().authenticated()
);
```

What we're saying here is:
1. Requests to `/management` are only allowed to whoever has the role `ADMIN`
2. Requests to the homepage `/` are allowed to both authenticated AND unauthenticated users
3. All other urls are only allowed to authenticated users (nevermind their role)

We have 3 pages:
1. Homepage `/` - accessible for anonymous and authenticated users
2. Management `/management` - accessible for `ADMIN`s only
3. Some page `/usersonly` - accessible only for authenticated users.

