package com.example.amai.config.exception;

/**
 * Lớp xử lý trường hợp nhập dữ liệu không đúng
 */
public class BadRequestException  extends RuntimeException{
    /**
     * Thông báo mặc định khi người dùng nhập sai dữ liệu
     */
    private static final String MESSAGE_DEFAULT = "Yêu cầu không hợp lệ.";

    /**
     * Tạo một ngoại lệ với thông báo mặc định
     */
    public BadRequestException()
    {
        super(MESSAGE_DEFAULT);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo và nguyên nhân
     *
     * @param message Thông báo
     * @param cause   Nguyên nhân
     */
    public BadRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo
     *
     * @param message Thông báo
     */
    public BadRequestException(String message)
    {
        super(message);
    }

    /**
     * Tạo một ngoại lệ kèm nguyên nhân
     *
     * @param cause Nguyên nhân
     */
    public BadRequestException(Throwable cause)
    {
        super(cause);
    }
}
