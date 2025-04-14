# Multi-Tenant Application
---

This project is a minimal Multi-Tenant application.

The application is a simple application where each tenant holds a list of products, and each member of a tenant can add products to the list.

## How is Multi-Tenancy Achieved
---

### Database
We have one database which is global, called `tenant_system`. This database holds data about the tenants and the correlation of users to tenants.
The `tenant` table includes a list of all the tenants. Each tenant is identified by a GUID.
The `user` table represents all the users of our application
The `tenant_user` represenst the correlation of users to tenants.

Additionally, we have a database per tenant, which holds the tenant-sepcific data (in this example - the products of this tenant).

Whenever a new tenant is created, we add a row to the `tenant_system.tenant` table, and creates and initializes a database for this tenant.

### Interceptor
After a user selects a tenant to connect to, we set a cookie with the tenant ID. There is an Interceptor `TenantInterceptor` which we
register with the ApplicationContext which intercepts requests to the `/products` page, and looks for this cookie in the request.
If there's such cookie, it sets it as the current tenant in the `TenantContext` class. This is then used by the service to know
what tenant is currently active. If no such cookie found, we throw an exception.

