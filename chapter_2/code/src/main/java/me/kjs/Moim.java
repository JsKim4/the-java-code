package me.kjs;

public class Moim {
    int maxNumberOfAttendees;
    int numberOdEnrollment;

    public boolean isEnrolmentFull() {
        if (maxNumberOfAttendees == 0) {
            return false;
        }
        if (numberOdEnrollment < maxNumberOfAttendees) {
            return false;
        }
        return true;
    }
}
