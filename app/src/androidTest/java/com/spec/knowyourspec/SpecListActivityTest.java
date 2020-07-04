package com.spec.knowyourspec;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SpecListActivityTest {
    private ActivityScenario mActivityScenario;

    /*@Rule
    public ActivityTestRule<SpecListActivity> mActivityTestRule = new ActivityTestRule<>(SpecListActivity.class);*/
    @Before
    public void launchMainActivity(){
        mActivityScenario = ActivityScenario.launch(SpecListActivity.class);
    }

    @Test
    public void recyclerViewTestUsingActivityTestRule(){
        /*int itemCount = mActivityTestRule.getActivity().mRecyclerView.getAdapter().getItemCount();
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.spec_list_recycler_view);
        int itemCount2 = recyclerView.getAdapter().getItemCount();
        assertNotEquals(10, itemCount2);*/
    }

    @Test
    public void recyclerViewTestUsingActivityScenario(){
        final RecyclerView[] recyclerView = new RecyclerView[1];

        /*--gets the recyclerView from the activity--*/
        mActivityScenario.onActivity(new ActivityScenario.ActivityAction() {
            @Override
            public void perform(Activity activity) {
                //RecyclerView recyclerView = activity.getWindow().getDecorView().findViewById(R.id.spec_list_recycler_view);
                recyclerView[0] = activity.findViewById(R.id.spec_list_recycler_view);
                /*--testing assertion within this inner method also works, and bypasses need to create recyclerview as a final array--*/
                //int itemCount3 = recyclerView[0].getAdapter().getItemCount();
                //assertNotEquals(10, itemCount3);
            }
        });

        /*--get the itemCount from the recyclerview adapter--*/
        int itemCount3 = recyclerView[0].getAdapter().getItemCount();

        /*--assert that the itemCount is not zero --> itemCount is actually equal to 90 --*/
        assertNotEquals(0, itemCount3);
    }
}