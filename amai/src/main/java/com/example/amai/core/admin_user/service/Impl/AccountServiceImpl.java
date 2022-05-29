package com.example.amai.core.admin_user.service.Impl;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.repository.AccountRepository;
import com.example.amai.core.admin_user.service.AccountService;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountReponsitory;
    /**
     * Đây là interface con (subinterface) của MailSender, nó hỗ trợ các tin nhắn kiểu MIME,
     * nó thường đươc sử dụng với lớp MimeMessageHelper để tạo ra MimeMessage.
     * Một lời khuyên là nên sử dụng interface MimeMessagePreparator cùng với interface này.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Boolean isUsernameExists(String username) {
        return accountReponsitory.existsByUserName(username);
    }

    @Override
    public Optional<Account> findById(String username) {
        return accountReponsitory.findById(username);
    }

    @Override
    public Boolean senOtpEmail(String email, String otp) {
        try {
//            MailMessage Là một interface đại diện cho một tin nhắn (message) đơn giản. Nó bao gồm các thông tin cơ bản của một email như người gửi, người nhận, tiêu đề (subject) và nội tin nhắn.
//            MimeMessage Đây là một lớp thực hiện interface MailMessage, được sử dụng để tạo ra một tin nhắn hỗ trợ MIME.

            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject("MÃ OTP- Đổi mật khẩu");
            helper.setText("<h3>Xin chào !</h3>" +
                    "<p>vui long khong chia se ma nay cho bat ky ai." +
                    "<p>Ma OTP cua ban la: <span style='color: blue; font-size: x-large'>" + otp + "</span></p>" +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
            );
            this.javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    @Override
    public List<Account> getAll() {
        return accountReponsitory.findAccountByIsDeletedFalse();
    }

    @Override
    public Optional<Account> getById(String id) {
        return accountReponsitory.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountReponsitory.save(account);
    }

    @Override
    public void deleteById(String id) {
        accountReponsitory.deleteById(id);
    }
}
