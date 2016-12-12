package org.phpbee.t5.TestBank;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexk on 11/22/16.
 */
public class RequestedStatus {
    static final String[] values = {"Approved", "Declined"};

    static List<String> values() {
        return Arrays.asList(values);
    }
}
