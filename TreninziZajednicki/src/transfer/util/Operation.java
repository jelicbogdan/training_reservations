/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_KLIJENT = 2;
    public static final int DELETE_KLIJENT = 3;
    public static final int UPDATE_KLIJENT = 4;
    public static final int GET_ALL_KLIJENT = 5;

    public static final int ADD_TRENING = 6;
    public static final int DELETE_TRENING = 7;
    public static final int UPDATE_TRENING = 8;
    public static final int GET_ALL_TRENING = 9;

    public static final int GET_ALL_VEZBAC = 10;

    public static final int GET_ALL_SALA = 11;

    public static final int GET_ALL_VRSTA_TRENINGA = 12;

    public static final int GET_ALL_TRENER = 13;

}
