/*
 * About.java
 *
 * Copyright 2014 westcrip <westcrip@westcrip-altankrk>
 *
 * Copyright 2016 the Intense-M Project
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 */
package com.android.settings.vibhu0009;

import android.app.Activity;
import android.app.ActivityManagerNative;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.IWindowManager;
import android.view.Display;
import android.view.Window;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.internal.logging.MetricsLogger;
public class About extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

public static final String TAG = "About";

    Preference mDonateUrl;
    Preference mFacebookUrl;
    Preference mGoogleUrl;
    Preference mGithubUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_dev);
        PreferenceScreen prefSet = getPreferenceScreen();
        ContentResolver resolver = getContentResolver();
        mDonateUrl = findPreference("dev_donate");
        mFacebookUrl = findPreference("dev_facebook");
        mGoogleUrl = findPreference("dev_google_plus");
        mGithubUrl = findPreference("dev_github");
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        return false;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mDonateUrl) {
            launchUrl("http://forum.xda-developers.com/donatetome.php?u=5991465");
        } else if (preference == mFacebookUrl) {
            launchUrl("https://www.facebook.com/vibhor.chaudhary");
        } else if (preference == mGoogleUrl) {
            launchUrl("https://plus.google.com/u/0/111514531794740016669");
        } else if (preference == mGithubUrl) {
            launchUrl("https://github.com/vibhu0009");
        } else {
                // If not handled, let preferences handle it.
                return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
         return true;
    }
    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent wew = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(wew);
    }
    protected int getMetricsCategory()
    {
	return MetricsLogger.APPLICATION;
    }
}
