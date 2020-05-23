# date-picker-automation

The test will pickup the correct implementation of the DatePickerPage class based on the configuration.
Every page has to to defined in an interface and Android and IOS implementation should implement that interface.

Example`page.datepicker.DatePicker` is the Interface and `page.datepicker.ios.IOS12_1.IOSDatePicker` and 
`page.datepicker.android.SDK24.AndroidDatePicker` is the corresponding IOS12_1 and Android SDK24 implemenation.
It is important that the package name should be in this specifc format.

This Framework uses reflecation to find the correct implementation class. 

Inorder to confgure the test run, the following paramters should be defined in the `test.properties` file.

Not that, at a time, only one of the platform properties can be defined.

````
os=Android
version=SDK24
appPackage=gmbh.ambidexter.testapplication
appActivity=.main.MainActivity
app=/path/to/apk/app-debug.apk
deviceName=Android Emulator
````
````
os=iOS
version=IOS12_1
app=/path/to/TestApplication.app
automationName=XCUITest
deviceName=iPhone XR
````

### How to run the Tests ?

#### Android
- Make sure that there is an Android Device is connected in `adb`
- Configure the Android Test Properties.
- `./gradlew test`

#### iOS
- Configure the iOS properties.
- Make sure that the Device Emulator is available.
- `./gradlew test`

