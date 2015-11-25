Feature: Test Account Migration

As a Old Style of IT user who is migrating the New Style of IT
I want my outlook service to migrate to the new style together with my email history, calendar events and contacts list
So that I can work seamlessly through the migration to new style of working

Scenario Outline: Migration of email account account: send and receive

Given I have an Old Style exchange account "<old-style-email-account>"
And exchange account is migrated to New Style account "<new-style-email-account>"
And "<email-correspondent-os>" exists as an Old Style email account
And "<email-correspondent-ns>" exists as a New Styile email acocunt

When I send an email from my new style account to "<email-correspondent-os>"
Then the message is received by "<email-correspondent-ns>"

When I send an email from my new style account to "<email-correspondent-ns>"
Then the message is received by "<email-correspondent-ns>"

Examples:
|old-style-email-account          | new-style-email-account        | email-correspondent-os           | email-correspondent-ns         |
|email@domain.co.uk| email@domain.com| email@domain.co.uk| email@domain.com|
