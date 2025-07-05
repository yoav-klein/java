# Multi Tenant App with Google Auth
---

This is a demo application that demonstrates a multi-tenant application where users login with their Google accounts.
It works in a schema-per-tenant strategy. Each tenant has its own schema in MySQL instance, named `tenant_<tenantId>`
There's a central schema `tenant_system` where information about tenants and users reside.

Connecting to a tenant, technically, means setting a Cookie named `tenant` that contains the tenant ID. For each endpoint that is tenant-specific, we
apply the `TenantInterceptor` that reads the cookie, checks if the user has access to the tenant, and processes the request.

We use Spring Security for OAuth2 login. `AutoRegistrationSuccessHandler` is a `AuthenticationSuccessHandler` which automatically 
registers the user in our database once he successfully logs in.

Additionally, we use Spring Security's method security to secure service-level methods (`@PreAuthorize`).

## Flows
---
* User registration (auto registration)
* Create tenant
* Invite user to tenant (scenarios: user doesn't exist, user part of tenant)
* Accept invitation (scenario: accepting cancelled invitation)
* Decline invitation (scenario: declining cancelled invitation)
* Delete user from tenant
* Leave tenant
* Delete tenant

## Tasks
---
* Implement security checks
* Implement transactional
* Promoting of users
* Confirmation prompts for removing user, leaving group and deleting tenant
* Confirmation prompt for logout
* Decide on a strtegy of user hierarchy - like seniority for example (like who can demote who etc.)
* Situations:

