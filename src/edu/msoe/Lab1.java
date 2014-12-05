package edu.msoe;

import com.t00ter.*;
/**
 * Created by dennis on 12/3/2014.
 */
public class Lab1 {

    public static void main(String[] args) {

        T00ter api = new T00ter();
        while (api.hasNext()) {
            System.out.println(api.next().toString());
        }
    }
}
