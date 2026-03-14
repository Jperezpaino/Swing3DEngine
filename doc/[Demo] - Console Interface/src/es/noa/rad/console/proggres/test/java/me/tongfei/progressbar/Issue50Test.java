package es.noa.rad.console.proggres.test.java.me.tongfei.progressbar;

import org.junit.Test;
import es.noa.rad.console.proggres.main.java.me.tongfei.progressbar.ProgressBar;

public class Issue50Test {
    @Test
    public void testCloseSpeed() throws Exception {
        int tenSecondsInMS = 10 * 1000;
        long startTime = System.currentTimeMillis();

        try(ProgressBar pb = new ProgressBar("Foo", 100, tenSecondsInMS)){
            Thread.sleep(5);
        }

        long endTime = System.currentTimeMillis();

        assert((endTime - startTime) < tenSecondsInMS);
    }
}
