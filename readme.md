# Secure Secret Credentials

This app is a minimal example that shows how to hide secret credentials like API keys from 
being exposed on GitHub.

If you are using GitHub as public repository you should avoid to include secret credentials 
like an external API key into the source code.

An alternative could be the usage of **BuildConfig** to include the data.

These are the steps needed to do this:

1) change the menu on the left from "Android" to "Project" to see all files in your project
2) create a file "keys.properties" in the root folder of your application
3) place the secret credential like this
```plaintext
SECURE_API_KEY = "5678-SECURE-###"
```
4) edit the ".gitignore" file and append the new filename from point 2:
```plaintext
...
keys.properties
```
**Note: I did not edit the ".gitignore" in this example so the "key.properties" file remain on GitHub !** 

5) edit the build.gradle (app) file and append code at 2 places. The first part is direct under 
the plugins-section and defines the file to read. The second part is in the android/defaultConfig 
section: this line adds a new field. 

Note: if you need different data for debug or release versions you need to place the line in the 
android/buildTypes section.
```plaintext
 plugins {
    id 'com.android.application'
}

// new for reading the secure api key
def keysFile = rootProject.file("keys.properties")
def keyProperties = new Properties()
keyProperties.load(new FileInputStream(keysFile))

android {
    namespace 'de.androidcrypto.securesecretcredentials'
    compileSdk 33

    defaultConfig {
        applicationId "de.androidcrypto.securesecretcredentials"
        minSdk 21
        targetSdk 33
        ...
        // new for reading the secure api key
        buildConfigField("String", "SECURE_API_KEY", keyProperties['SECURE_API_KEY'])
    }
   ...
}

dependencies {
   ...
}
```

7) Do not forget to sync the build.gradle-file after edit and **run 3 additional steps**:
```plaintext
AndroidStudio Build/Clean Project
AndroidStudio Build/Rebuild Project 
AndroidStudio File/Sync Project with Gradle Files
``` 

8) In your app's sourcecode you now have access to the secret data with "BuildConfig.xxx" like:  
```plaintext
private static String secureApiKey = BuildConfig.SECURE_API_KEY 
```

A last note and warning: The secret credential is hidden from source code that is stored in 
Version control systems like GitHub. **The secret data is still available in the 
generated APK-file and can get accessed**.


![secure_secret_credentials](docs/securesecretcredentials00.png?raw=true)

