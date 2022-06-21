package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import com.example.amai.core.order.repository.OrderRepository;
import com.example.amai.core.order.service.OrderService;
import com.example.amai.core.security.jwt.QRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final int ORDER_QR_CODE_SIZE_WIDTH = 300;
    private static final int ORDER_QR_CODE_SIZE_HEIGHT = 300;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Oder> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Oder> getById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Oder save(Oder entity) {
        return orderRepository.save(entity);
    }

    @Override
    public List<Oder> findByIsDeleted(boolean idDeleteOder, boolean issDeleteAccount) {
        return orderRepository.findAllByIsDeletedAndAccount_IsDeleted(idDeleteOder, issDeleteAccount);
    }

    @Override
    public List<Oder> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(boolean idDeleteOder, boolean issDeleteAccount, String userName) {
        return orderRepository.findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(idDeleteOder, issDeleteAccount, userName);
    }

    @Override
    public List<Oder> findAllByIsDeletedFalseAndStatus(EStatusOrder status) {
        return orderRepository.findAllByIsDeletedFalseAndStatus(status);
    }

    @Override
    public String generateQrCode(Oder sdi, String imagePath) {
        String prettyData = QRUtils.prettyObject(sdi);
        String qrCode = QRUtils.generateQrCode(prettyData, ORDER_QR_CODE_SIZE_WIDTH, ORDER_QR_CODE_SIZE_HEIGHT, imagePath);
        return qrCode;
    }

    @Override
    public String generateQrCodeOr(Integer id, String imagePath) {
        String prettyData = QRUtils.prettyObject(id);
        String qrCode = QRUtils.generateQrCode(prettyData, ORDER_QR_CODE_SIZE_WIDTH, ORDER_QR_CODE_SIZE_HEIGHT, imagePath);
        return qrCode;
    }

    @Override
    public List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone) {
        return orderRepository.findAllSerachOder(isDeleteOder, isDeleteAccount, fullName, userName, address, phone);
    }

    @Override
    public Boolean senOrderEmail(Oder oder, String email) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject("Thong Tin Don Hang");
            helper.setText("<h3>Xin chao quy khach !</h3>" +
                    "<p>Ten xac nhan :" + oder.getFullName() + "</p>" +
                    "<p>Dia chi : " + oder.getAddress() + "</span></p>" +
                    "<p>So dien thoai :" + oder.getPhone() + "</p>" +
                    "<p>Ma OR:" +
                    " <img src=\"" + oder.getQrcode() + "\" alt='mã qrr'></p>" +
                    " <p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
            );
            this.javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
