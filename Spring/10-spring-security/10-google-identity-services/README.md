# Google Identity Services (Sign in with Google)
---

_Sign in with Google_ (or: Google Identity Services) allows you to log users in with their Google account (like OpenID Connect).

It's not OpenID Connect - it works a bit differently. Your frontend loads the Google Identity Services JavaScript library, and render either a button or One-Tap, and then there's a redirect back to your application with the ID Token (no Authorization Code and stuff).

So in this project, we create a custom Authentication Filter to handle the ID Token returned to us by Google.

