# Manage Your API with 3scale

## Connecting your Customers API to 3scale API Management

In order to connect your Customers API to 3scale, you need to follow three simple steps:

1. Access your 3scale Admin Portal and set up your first service.
2. Customize your Developer Portal and sign up as a developer.
3. Integrate your API with 3scale using the API gateway.

## Step 0: Review Pre-Reqs

Before provisioning an on-premises API gateway environment, you will want to check on the following regarding your 3scale SaaS account :

1. 3scale Domain
    * You should know what the domain name of your 3scale SaaS account is.
    * The name of your 3scale domain is referenced in the URL to your Administrative Portal of the 3scale SaaS environment, e.g. `https://&lt;YOURDOMAIN&gt;-admin.3scale.net/p/admin/dashboard`

2. 3scale Access Token
    * To get an Access Token, navigate to:
        `Gear Icon in top right corner -> Personal Settings -> Tokens -> Add Access Token`
         + The scope of your access token should be: *Account Management API*.
    * Also ensure that your access token has *Read Only* permissions.

> **Note:** Don't forget to copy your token into a safe place as this is the only point where you'll be able to view it. If you fail to do so, you can always create a new access token.

## Step 1: Define your API

Your 3scale Admin Portal (http://&lt;YOURDOMAIN&gt;-admin.3scale.net) provides access to a number of configuration features.

![3scale-initial-signin.png](./../images/03-lab-images/3scale-initial-signin.png)

1. Log into the Admin Portal.

2. If this is the first time you access the 3scale portal, such as when you click the *activate* link from the sign up email, dismiss and close the wizard by clicking on the top right **X**.

![3scale-click-integration.png](./../images/03-lab-images/3scale-click-integration.png)

3. The first page you will land is the API tab. From here we will create our API definition. Click on the `Integration` link.

![3scale-after-click-integration.png](./../images/03-lab-images/3scale-after-click-integration.png)

4. Click on the `edit integration settings` to edit the API settings for the gateway.

![3scale-hosted-nginx.png](./../images/03-lab-images/3scale-hosted-nginx.png)

5. Select the **APIcast** Gateway deployment option. (Select the one that does **not** say "self-managed".)

![3scale-auth-apikey.png](./../images/03-lab-images/3scale-auth-apikey.png)

6. Keep the **API Key (user_key)** Authentication. (You will need to scroll down for the Authentication section.)

7. Click on **Update Service**

![3scale-add-base-url.png](./../images/03-lab-images/3scale-add-base-url.png)

8. Click on the **add the Base URL of your API and save the configuration** button.  This is the url you can hit from your deployed OpenShift service. If you already configured a 3scale API when creating your account, this button will not be available. Click `edit APIcast configuration` instead and add the OpenShift route URL as the "Private Base URL".
    > **Note:** If you copied the link address from the OpenShift console, ensure the "Private Base URL" does not include a '/' at the end.

![3scale-add-mapping-rules.png](./../images/03-lab-images/3scale-add-mapping-rules.png)

9. Expand the **mapping rules** section to define the allowed methods on our exposed API. In our example this is `/camel/hello`
    > **Note:** the default mapping is the root ("/") of our API resources, something that we might want to avoid.

10. Click on the **Metric or Method (Define)**  link.

![3scale-new-method.png](./../images/03-lab-images/3scale-new-method.png)

11. Click on the **New Method** link in the *Methods* section.

![3scale-api-new-method-def.png](./../images/03-lab-images/3scale-api-new-method-def.png)

12. Fill in the information for your Fuse Method.

    **Friendly name:** `hello_World`
    **System name:** `hello_World`
    **Description:** `Method to say Hello World`

13. Click on **Create Method**

![3scale-add-mapping-rule.png](./../images/03-lab-images/3scale-add-mapping-rule.png)

14. Click on the **Add mapping rule** link

![3scale-added-mapping-rule.png](./../images/03-lab-images/3scale-added-mapping-rule.png)

15. Click on the edit icon next to the GET mapping rule.

16. Enter `/camel/hello` as the Pattern, or whatever pattern you chose when developing your Camel Route

18. Select `hello_World` as Method.

19. Scroll down to the **API Test GET request**.

![3scale-update-test-request-to-correct-url.png](./../images/03-lab-images/3scale-update-test-request-to-correct-url.png)

20. Enter `/camel/hello` for your pattern

![3scale-update-and-test.png](./../images/03-lab-images/3scale-update-and-test.png)

![3scale-back-to-integration-and-configuration.png](./../images/03-lab-images/3scale-back-to-integration-and-configuration.png)

21. Click on the **Update & test in Staging Environment** to save the changes.

22. Copy the staging URL into a web browser or entire curl command to test against the staging environment. It will be something similar to:
```
curl "https://mary-test-summit-23981.staging.gw.apicast.io:443/camel/hello?user_key=910283921732184328643276dz9asd9324"
```

23. Click on the **Back to Integration & Configuration** link.

![3scale-promote-to-prod.png](./../images/03-lab-images/3scale-promote-to-prod.png)

24. Click on the **Promote v.1 to Production** button to promote your configuration from staging to production.

25. Success! Your 3scale access control layer will now only allow authenticated calls through to your OpenShift backend API.


## Step 2: Play with your API and 3scale

### Add Rate Limits to Your API

![3scale-click-integration.png](./../images/03-lab-images/3scale-click-integration.png)

1. From your 3scale admin homepage click 'Definition'.

![3scale-application-plan.png](./../images/03-lab-images/3scale-application-plan.png)

2. From the side bar select 'Application Plans'

![3scale-bsaic-application-plan.png](./../images/03-lab-images/3scale-basic-application-plan.png)

3. Click on Basic

![3scale-edit-application-plan.png](./../images/03-lab-images/3scale-edit-application-plan.png)

![3scale-methods-limits.png](./../images/03-lab-images/3scale-methods-limits.png)

4. Click on Limits next to your method ('hello_World' or other)

![3scale-usage-limit.png](./../images/03-lab-images/3scale-usage-limit.png)

5. Add new Usage Limit

![3scale-update-application-plan.png](./../images/03-lab-images/3scale-update-application-plan.png)

6. Select 'Update Application Plan'

### Setup Alerts

1. From your 3scale homepage select 'Definition' just like you did above.

![3scale-select-alerts.png](./../images/03-lab-images/3scale-select-alerts.png)

2. Select 'Alerts' from the side menu

![3scale-check-boxes-alerts.png](./../images/03-lab-images/3scale-check-boxes-alerts.png)

3. Select whatever checkboxes you would like to see for 'Alerts'.  Then hit your API as many times as needed to hit the alert.  

### View Analytics

![3scale-select-analytics.png](./../images/03-lab-images/3scale-select-analytics.png)

1. From any page in your 3scale admin portal select 'Analytics' from the top bar.  Then browse the analytis as you desire.

### Register a new Developer in the Developer Portal

The focal point of your developers’ experience is the API developer portal, and the level of effort you put into it will determine the level of decreased support costs and increased developer engagement. 3scale provides a built-in, state-of-the-art CMS portal, making it very easy to create your own branded hub with a custom domain to manage developer interactions and increase API adoption.

You can customize the look and feel of the entire Developer Portal to match your own branding. You have complete control over every element of the portal, so you can make it as easy as possible for developers to learn how to use your API.

The Developer Portal's CMS consists of a few elements:
* Horizontal menu in the Admin Portal with access to content, redirects, and changes
* The main area containing details of the sections above
* CMS mode, accessible through the preview option

Liquid is a simple programming language used for displaying and processing most of the data from the 3scale system available for API providers. In the 3scale platform, it is used to expose server-side data to your API developers, greatly extending the usefulness of the CMS while maintaining a high level of security.

![3scale-developer-portal-tab.png](./../images/03-lab-images/3scale-developer-portal-tab.png)

1. Click on the `Developer Portal` tab to access the developer portal settings.

![3scale-default-developer-portal.png](./../images/03-lab-images/3scale-default-developer-portal.png)

2. Click on the `Visit Developer Portal` to take a look of how your developer portal looks like.

You can see there is a default portal with information of your API and how to signup. Unfortunately the API information is incorrect.
    > **Note:** We will edit our portal to update it with the correct information

![3scale-developer-portal-homepage.png](./../images/03-lab-images/3scale-developer-portal-homepage.png)

4. Go back to browse the top of the content sub-sections and find the `Homepage` section.

5. Change all the code `Echo` references in the homepage code for `Hello World`.

6. Update the API call examples to reflect your real Fuse API calls.
    > **Note:** Use your production base url and add your defined methods. Dont worry if you don't have the "real" output, it won't affect the rest of the lab.

7. Refresh your Developer Portal's browser tab to check the changes.

![3scale-developer-portal-signup.png](./../images/03-lab-images/3scale-developer-portal-signup.png)

8. Take the place of one of your developers and signup for the **Basic** plan.

![3scale-developer-portal-signup-details.png](./../images/03-lab-images/3scale-developer-portal-signup-details.png)

9. Fill in your information and your email to register as a developer. Click on the `Sign up` button.
    > **Note:** Use an email address you can actually access.

![3scale-signup-email.png](./../images/03-lab-images/3scale-signup-email.png)

10. Check your email and click on the `activate` link.

![3scale-developer-portal-access-code.png](./../images/03-lab-images/3scale-developer-portal-access-code.png)

11. As your portal is not currently public, you will need your portal code to finish the registration. You can get the code in your admin portal navigating to: `Settings > Developer Portal > Domains & Access`.

![3scale-developer-portal-type-in-code.png](./../images/03-lab-images/3scale-developer-portal-type-in-code.png)

12. Type your portal code to finish the account activation.

![3scale-developer-portal-signin.png](./../images/03-lab-images/3scale-developer-portal-signin.png)

13. Now that your developer account is active, sign in the portal.

![3scale-get-api-key.png](./../images/03-lab-images/3scale-get-api-key.png)

14. You will land in the developers homepage, where you will be able to check your developers settings and retrieve your `User Key`.
    > **Note:** Copy down this key as it is used to authenticate yourself to the managed API.

15. Now you can make test requests to your API using this api key
