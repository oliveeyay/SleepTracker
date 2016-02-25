# For development only, get rid of this options for real releases
#-dontobfuscate
#-dontoptimize

-target 1.7
-optimizationpasses 3
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-keepparameternames
-dontpreverify
-allowaccessmodification
-dontshrink
-verbose

# Dictionary
-obfuscationdictionary proguard-dictionary.txt
-classobfuscationdictionary proguard-dictionary.txt
-packageobfuscationdictionary proguard-dictionary.txt

# The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle.
-optimizations !field/removal/writeonly,!code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable,!code/simplification/cast

# TO KEEP ANNOTATION
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature
-keep class javax.annotation.** { *; }
-keepattributes *Annotation*
# END ANNOTATION

#Keep necessary classes and method
-dontwarn android.app.**
-keep class android.app.** { *; }
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-dontwarn net.sqlcipher.**
-keep class net.sqlcipher.** { *; }
-dontwarn com.robotium.**
-keep class com.robotium.** { *; }
-keep class org.apache..** { *; }
-keep class android.net.** { *; }
-keep class android.util.** { *; }
-keep class sun.misc.** { *; }
-keep class com.google.** { *; }
-keep class com.mixpanel.** { *; }
-keep class com.github.** { *; }
-keep class com.daimajia.** { *; }
#End Keep necessary classes and method

-keep public class * extends android.app.Activity
-keep public class * extends android.widget.RelativeLayout
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.ContentProvider
-keep class com.google.inject.Binder
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.content.BroadcastReceiver { *; }

-keepclassmembers class * {
	void finalizeReferent();
}

-keepclassmembers class * {
	<init>(...);
}

-keepclassmembers class * {
    void *(**On*Event);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}
# END ANDROID

#Google Analytics
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}


-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#End Google Analytics

# ActionBarSherlock
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-dontwarn com.actionbarsherlock.**
-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }
-keepattributes *Annotation*
# END ActionBarSherlock

#Random stuff from libraries
-dontwarn javax.tools.**
-keep class javax.tools.** { *; }

-dontwarn javassist.**
-keep class javassist.** { *; }

-dontwarn org.json.**
-keep class org.json.** { *; }

-dontwarn org.jdom.**
-keep class org.jdom.** { *; }

-dontwarn freemarker.**
-keep class freemarker.** { *; }

-dontwarn com.squareup.spoon.**
-keep class com.squareup.spoon.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *; }
-keep class sun.misc.Unsafe { *; }

# Needed by google-api-client to keep generic types and @Key annotations accessed via reflection
-keepclassmembers class * {
  @com.google.api.client.util.Key <fields>;
}
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault
#END Random stuff from libraries

#GreenDao
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
#End GreenDao

#Migrations
-dontwarn com.omada.prevent.migrations.**
-keep class com.omada.prevent.migrations.** { *; }
#End Migrations

#SQLCipher
-keep class net.sqlcipher.** {
    *;
}
-keep class net.sqlcipher.database.** {
    *;
}
#End SQLCipher

#MPAndroidChart
-dontwarn com.github.mikephil.charting.**
-keep class com.github.mikephil.charting.** { *; }
#End MPAndroidChart

#SDK 23 drops support for apache
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.google.android.gms.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.parse.**
-dontwarn junit.**

-keep class org.apache.http.** { *; }
-keep class android.net.http.AndroidHttpClient { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.android.volley.toolbox.** { *; }
-keep class com.parse.** { *; }
-keep class junit.** { *; }
#End SDK 23

#Data binding (classes used in xml etc...)
-dontwarn com.omada.prevent.bindings.adapters.AddMealAdapter
-keep class com.omada.prevent.bindings.adapters.AddMealAdapter { *; }
#End Data binding
