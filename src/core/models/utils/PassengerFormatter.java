/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.utils;

import core.models.Passenger;

/**
 *
 * @author jdiaz
 */
public class PassengerFormatter {

    public static String getFullname(Passenger p) {
        return p.getFirstname() + " " + p.getLastname();
    }

    public static String generateFullPhone(Passenger p) {
        return "+" + p.getCountryPhoneCode() + " " + p.getPhone();
    }
}
