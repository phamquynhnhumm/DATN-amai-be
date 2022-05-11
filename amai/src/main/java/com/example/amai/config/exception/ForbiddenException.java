package com.example.amai.config.exception;

/**
 * Người dùng không có quyền truy cập vào chức năng
 */
public class ForbiddenException extends RuntimeException
{
    /**
     * Thông báo mặc định khi người dùng không có quyền truy cập
     */
    private static final String MESSAGE_DEFAULT = "Không có quyền truy cập";

    /**
     * Tạo một ngoại lệ với thông báo mặc định
     */
    public ForbiddenException()
    {
        super(MESSAGE_DEFAULT);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo và nguyên nhân
     *
     * @param message Thông báo
     * @param cause   Nguyên nhân
     */
    public ForbiddenException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo
     *
     * @param message Thông báo
     */
    public ForbiddenException(String message)
    {
        super(message);
    }

    /**
     * Tạo một ngoại lệ kèm nguyên nhân
     *
     * @param cause Nguyên nhân
     */
    public ForbiddenException(Throwable cause)
    {
        super(cause);
    }
}
