/*
 * Copyright (C) 2016 The Intense-M Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.intense;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import com.android.settings.R;
import com.android.internal.logging.MetricsLogger;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class IntenseLogo extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String TAG = "IntenseLogo";

    private static final String KEY_INTENSE_LOGO_COLOR = "status_bar_intense_logo_color";

    private ColorPickerPreference mIntenseLogoColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.intense_logo);

        PreferenceScreen prefSet = getPreferenceScreen();

        // Intense logo color
        mIntenseLogoColor =
            (ColorPickerPreference) prefSet.findPreference(KEY_INTENSE_LOGO_COLOR);
        mIntenseLogoColor.setOnPreferenceChangeListener(this);
        int intColor = Settings.System.getInt(getContentResolver(),
                Settings.System.STATUS_BAR_INTENSE_LOGO_COLOR, 0xffffffff);
        String hexColor = String.format("#%08x", (0xffffffff & intColor));
            mIntenseLogoColor.setSummary(hexColor);
            mIntenseLogoColor.setNewPreviewColor(intColor);

    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mIntenseLogoColor) {
            String hex = ColorPickerPreference.convertToARGB(
                    Integer.valueOf(String.valueOf(newValue)));
            preference.setSummary(hex);
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.STATUS_BAR_INTENSE_LOGO_COLOR, intHex);
            return true;
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.INTENSEM;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}
