package com.spec.knowyourspec;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.spec.knowyourspec.service.NotificationJobService;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    // you have to add the dependency for preferences
    public static class SettingsFragment extends PreferenceFragmentCompat {
        private static final int ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
        public static final int JOB_ID = 3;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.pref_settings);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            String notificationKey = getString(R.string.pref_key_notification_spec_of_the_day);

            if (preference.getKey().equals(notificationKey)){
                SwitchPreference switchPreference = (SwitchPreference)preference;
                boolean on = switchPreference.isChecked();
                JobScheduler jobScheduler = getContext().getSystemService(JobScheduler.class);
                // JobScheduler jobScheduler = getContext().getSystemService(JOB_SCHEDULER_SERVICE);

                if(on) {
                    ComponentName componentName = new ComponentName(getContext(), NotificationJobService.class);
                    JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(JOB_ID, componentName)
                            .setPeriodic(ONE_DAY_IN_MILLISECONDS)
                            .setPersisted(true);

                    JobInfo jobInfo = jobInfoBuilder.build();

                    jobScheduler.schedule(jobInfo);
                    Toast.makeText(getContext(), "Notification active", Toast.LENGTH_LONG).show();
                }else{
                    jobScheduler.cancel(JOB_ID);
                    Toast.makeText(getContext(), "Notification cancelled", Toast.LENGTH_LONG).show();
                }
            }
            return super.onPreferenceTreeClick(preference);
        }
    }
}
