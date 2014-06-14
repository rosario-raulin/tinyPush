# TinyPush
A simple library for Apple Push Notifications

## Usage

### Attention: The documentation is still work in progress yet the library should be functional!

This is how you create a notification:

```Java
NotificationBuilder notificationBuilder = new NotificationBuilder();

notificationBuilder.tokenFromBase64("fnordfnordfnordfnordnfordfnord=")
    .jsonPayload("{ \"aps\" : { \"alert\" : \"New message\" } }")
    .randomIdentifier(randomGenerator)
    .expiresInMinutes(10)
    .priority(Priority.IMMEDIATELY);
    
Notification = notificationBuilder.builder();
```

You can send push notifications using the `Provider` class. Instances are created by `ProviderBuilder`:

```Java
ProviderBuilder providerBuilder = new ProviderBuilder();
providerBuilder.certificatePath("/path/to/your/cert.p12")
    .addNotification(notification);
    
Provider provider = providerBuilder.build();
provider.sendNotifications();    
```
