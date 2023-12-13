package httpfilter;

import server.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements HttpFilter {

    @Override
    public boolean myCheckLogic(HttpServletRequest request) {
        Optional<String> cookieValue = Auth.getCookieValue(request);
        return cookieValue.isPresent();
    }

    @Override
    public void behaviorIfLogicNotPassed(HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }

}
