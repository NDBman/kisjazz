package hu.berryweb.kisjazz.http.response;

/**
 * Created by Nandi on 2018. 01. 21..
 */
public class ErrorInfo {

    public final String ex;

    public ErrorInfo(Exception e) {
        this.ex = e.getLocalizedMessage();
    }
}
