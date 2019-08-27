package com.hrm.mockprojecthrm.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /*
     * xử lí việc khi gặp lỗi trái phép khi người dùng cố truy cập
     * */
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        /*
         * Phương thức này được gọi bất cứ khi nào một ngoại lệ được đưa ra
         * do người dùng không được xác thực đang cố truy cập vào tài nguyên yêu cầu xác thực
         * */

        /*
         *  phản hồi với lỗi 401 có chứa thông báo ngoại lệ
         * */

        logger.error("Responding with unauthorized error. Message - {}", authException.getMessage());
        response.sendError(response.SC_UNAUTHORIZED, authException.getMessage());
    }
}
