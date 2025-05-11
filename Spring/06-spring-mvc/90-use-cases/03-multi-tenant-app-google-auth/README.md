# Multi Tenant App with Google Auth
---

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

