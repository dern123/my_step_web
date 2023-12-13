package server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class Auth {

  private final static String CookieName = "CID";
  private final static Cookie[] EMPTY = new Cookie[0];

  private static Cookie[] checkNull(Cookie[] cookies) {
    return cookies != null ? cookies : EMPTY;
  }

  public static Optional<Cookie> getCookie(HttpServletRequest rq) {
    Cookie[] cookies = checkNull(rq.getCookies()); // null ;(((

    return Arrays.stream(cookies)
        .filter(c -> c.getName().equals(CookieName))
        .findFirst();
  }

  public static Optional<String> getCookieValue(HttpServletRequest rq) {
    return getCookie(rq)
        .map(Cookie::getValue);
  }

  public static String getCookieValueUnsafe(HttpServletRequest rq) {
    return getCookieValue(rq).orElseThrow(IllegalStateException::new);
  }

  public static void setCookie(Cookie cookie, HttpServletResponse rs) {
    rs.addCookie(cookie);
  }

  public static void setCookieValue(String cookieValue, HttpServletResponse rs) {
    Cookie cookie = new Cookie(CookieName, cookieValue);
    cookie.setMaxAge(10 * 60); // TTL in seconds (10 min)
    rs.addCookie(cookie);
  }

  public static void clearCookie(HttpServletResponse rs) {
    Cookie cookie = new Cookie(CookieName, "");
    cookie.setMaxAge(0); // expiration time 1h = 60s * 60m = 3600s
    rs.addCookie(cookie);
  }

}
