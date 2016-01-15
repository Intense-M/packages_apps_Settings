
package com.android.settings.intense;

import android.os.Bundle;

import com.android.settings.InstrumentedFragment;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class IntenseSettings extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.intense_settings);
    }

    @Override
    protected int getMetricsCategory() {
        return InstrumentedFragment.INTENSESETTINGS;
    }
 }
