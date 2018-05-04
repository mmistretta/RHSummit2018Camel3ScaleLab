# Get an OpenShift Online Account

## Create Account

To create an OpenShift Online account, you will first need to have a Red Hat Developer account.
https://developers.redhat.com/

Then go here to create your OpenShift Online account: https://www.openshift.com/container-platform/

![Signup-for-free.png](./../images/00-lab-images/Signup-for-free.png)

1. Select sign up for free

![openshift-online-add-plan.png](./../images/00-lab-images/openshift-online-add-plan.png)

2. Select ‘Add a New Plan’, Free Plan

![openshift-online-email.png](./../images/00-lab-images/openshift-online-email.png)

3. Wait for email

## Get OC Tools

To login to your OpenShift instance from your machine, you will need to install the OpenShift command line tool, ‘oc’.

You can do so by going to 'About' section in web console.

![openshift-online-about.png](./../images/00-lab-images/openshift-online-about.png)

1. Go to your webconsole, link similar to https://console.starter-ca-central-1.openshift.com/console/command-line
and select 'About' from the drop down when clicking the question mark.

![openshift-online-command-line.png](./../images/00-lab-images/openshift-online-command-line.png)

2. Select the 'Command Line Tools' link

![openshift-online-download-oc.png](./../images/00-lab-images/openshift-online-download-oc.png)

3. Download the appropriate ‘oc’ tool for your operating system

4. Extract the executable file from the tar (on Linux and Mac), or from the zip (on Windows)

### Optional
To use the 'oc' tool without specifying the location of the file, you can add the file to your PATH environment variable or if in a Linux/Unix OS, you can move the file to a bin folder.

In Fedora you could move it to /usr/local/bin/ or ~/bin for example.

#### Windows Adding to Path
1. Start the System Control Panel applet (Start - Settings - Control Panel - System). You may have to select View --> Small Icons
2. Select the Advanced tab.
3. Click the Environment Variables button.
4. Under System Variables, select Path, then click Edit.
5. You'll see a list of folders. For example, my system shows:
```
C:\Program Files\Windows Resource Kits\Tools\;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem;C:\Program Files\Support Tools\;C:\Program Files\Common Files\Roxio Shared\DLLShared;C:\Program Files\Common Files\Ulead Systems\MPEG;C:\Program Files\Intel\DMIX;C:\Program Files\Executive Software\Diskeeper\;C:\Program Files\Bonjour\;C:\Program Files\QuickTime\QTSystem\;C:\Program Files\Misc
```
6. You can add additional folders that you want to include in your path. Simply put "<folder with 'oc' tool>;" to the beginning of the PATH and click OK
7. You'll need to restart the command prompt to use the updated system path.
