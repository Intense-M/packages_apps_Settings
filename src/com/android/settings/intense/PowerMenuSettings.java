/*
 * Copyright (C) 2016 Intense-M Project
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
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.provider.Settings;

import com.android.settings.InstrumentedFragment;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class PowerMenuSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String PREF_SHOW_ADVANCED_REBOOT =
            "power_menu_show_advanced_reboot";

    private SwitchPreference mShowAdvancedReboot;

    private ContentResolver mResolver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.power_menu_settings);

        mResolver = getActivity().getContentResolver();

        mShowAdvancedReboot =
                (SwitchPreference) findPreference(PREF_SHOW_ADVANCED_REBOOT);
        mShowAdvancedReboot.setChecked((Settings.System.getInt(mResolver,
                Settings.System.POWER_MENU_SHOW_ADVANCED_REBOOT, 0) == 1));
        mShowAdvancedReboot.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {

        if (preference == mShowAdvancedReboot) {
            boolean value = (Boolean) objValue;
            Settings.System.putInt(mResolver,
                    Settings.System.POWER_MENU_SHOW_ADVANCED_REBOOT, value ? 1 : 0);
            return true;
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return InstrumentedFragment.BUTTONS;
    }
}
