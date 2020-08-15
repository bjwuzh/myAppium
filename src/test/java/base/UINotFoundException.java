package base;


import utils.Log;

/**
 * @program: myAppium
 * @description:
 * @author: bjwuzh
 * @create: 2020-08-15 14:29
 **/
public class UINotFoundException extends Exception {
    public UINotFoundException(){

    }
    public UINotFoundException(String errorMsg) {
        Log.info(errorMsg);
    }
}