package com.example.amai.core.order.service.Impl;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.order.entity.contans.EStatusOrder;
import com.example.amai.core.order.repository.OrderRepository;
import com.example.amai.core.order.service.OrderService;
import com.example.amai.core.security.jwt.QRUtils;
import com.sun.deploy.association.utility.AppUtility;
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
        return orderRepository.findByIsDeletedAndAccount_IsDeleted(idDeleteOder, issDeleteAccount);
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
    public List<Oder> findAllSerachOder(boolean isDeleteOder, boolean isDeleteAccount, String fullName, String userName, String address, String phone) {
        return orderRepository.findAllSerachOder(isDeleteOder, isDeleteAccount, fullName, userName, address, phone);
    }

    @Override
    public Boolean senOrderEmail(Oder oder, String email) {
        try {
//            MailMessage Là một interface đại diện cho một tin nhắn (message) đơn giản. Nó bao gồm các thông tin cơ bản của một email như người gửi, người nhận, tiêu đề (subject) và nội tin nhắn.
//            MimeMessage Đây là một lớp thực hiện interface MailMessage, được sử dụng để tạo ra một tin nhắn hỗ trợ MIME.

            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject("Thong Tin Don Hang");
            helper.setText("<h3>Xin chao quy khach !</h3>" +
                    "<p>Ten xac nhan" + oder.getFullName() + "</span></p>" +
                    "<p>Dia chi " + oder.getAddress() + "</span></p>" +
                    "<p>Hinh thuc thanh toan " + oder.getPayments() + "</span></p>" +
                    "<p>So dien thoai" + oder.getPhone() + "</span></p>" +
                    "<p>Ma OR: <img src=\"https://bootdey.com/img/Content/avatar/avatar6.png\">" + oder.getPhone() + "</span></p>" +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
            );
            this.javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

//    @Override
//    public Boolean senOrderEmail(Oder oder) {
//        try {
//            MimeMessage message = this.javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setTo("nhuptq2809@gmail.com");
//            System.out.println(oder.getAccount().getUser().getEmail());
//            helper.setSubject("Thông tin đơn hàng");
//            helper.setText("<h3>Xin chào !</h3>" +
//                    "<p>vui long khong chia se ma nay cho bat ky ai." +
////                    " <img src=\"https://bootdey.com/img/Content/avatar/avatar6.png\">" +
////                    " <img src=\"" + oder.getQrcode() + "\">" +
//                    "<p>: Tên người nhận:" + oder.getFullName() + "</p>" +
//                    "<p>: Địa chỉ:" + oder.getAddress() + "</p>" +
//                    "<p>: Số điện thoại:" + oder.getPhone() + "</p>" +
//                    "<p>: Hình thưc thanh toán:" + oder.getPayments() + "</p>" +
//                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
//            );
//            this.javaMailSender.send(message);
//            System.out.println("Gửi email đơn hàng thành công");
//            return true;
//        } catch (MessagingException e) {
//            return false;
//        }
//    }
}
