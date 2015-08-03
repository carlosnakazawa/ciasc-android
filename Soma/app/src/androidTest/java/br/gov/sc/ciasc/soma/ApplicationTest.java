package br.gov.sc.ciasc.soma;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void test_um_mais_um_deve_ser_dois() throws Exception {
        solo.assertCurrentActivity("Estou na Activity esperada", MainActivity.class);

        assertTrue(solo.waitForText("Soma"));

        solo.enterText(((EditText) solo.getView(R.id.primeiro)), "1");
        solo.enterText(((EditText) solo.getView(R.id.segundo)), "1");

        solo.clickOnButton("Somar");

        assertTrue("Nao encontrou o resultado 2.", solo.waitForText("2", 1, 3));

        solo.goBack();
    }

    public void test_nove_mais_cinco_deve_ser_quatorze() throws Exception {
        solo.assertCurrentActivity("Estou na Activity esperada", MainActivity.class);

        assertTrue(solo.waitForText("Soma"));

        solo.enterText(((EditText) solo.getView(R.id.primeiro)), "5");
        solo.enterText(((EditText) solo.getView(R.id.segundo)), "9");

        solo.clickOnButton("Somar");

        assertTrue("Nao encontrou o resultado 2.", solo.waitForText("14", 1, 3));

        solo.goBack();
    }
}