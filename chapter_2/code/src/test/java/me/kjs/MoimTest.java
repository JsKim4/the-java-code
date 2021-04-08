package me.kjs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoimTest {

    @Test
    public void isFull() {
        Moim moim = new Moim();
        moim.maxNumberOfAttendees = 100;
        moim.numberOdEnrollment = 10;
        assertFalse(moim.isEnrolmentFull());

        moim.maxNumberOfAttendees = 0;
        moim.numberOdEnrollment = 10;
        assertFalse(moim.isEnrolmentFull());

        moim.maxNumberOfAttendees = 10;
        moim.numberOdEnrollment = 10;
        assertTrue(moim.isEnrolmentFull());
    }
}