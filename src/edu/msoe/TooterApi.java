package edu.msoe;

import com.t00ter.T00t;
import com.t00ter.T00ter;

import java.util.ArrayList;

/**
 * Created by dennis on 12/9/2014.
 */
public class TooterApi extends T00ter {


    public boolean isValidUsername(String parameter) {
        T00ter api = new T00ter(parameter);

        return api.isValid();
    }

    public TootsContainer getRecentToots() {
        T00ter api = new T00ter();
        TootsContainer toots = new TootsContainer();

        while (api.hasNext()) {
            toots.add(api.next());
        }

        return toots;

    }

    public TootsContainer getRecentTootsForUser(String parameter) {
        T00ter api = new T00ter(parameter);
        TootsContainer toots = new TootsContainer();

        while (api.hasNext()) {
            toots.add(api.next());
        }

        return toots;
    }
}
