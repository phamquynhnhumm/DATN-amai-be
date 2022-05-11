package com.example.amai.config.exception;

/**
 * Tài nguyên không tồn tại
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Thông báo mặc định khi tài nguyên không tìm thấy
     */
    private static final String MESSAGE_DEFAULT = "Tài nguyên không tồn tại";

    /**
     * Tạo một ngoại lệ với thông báo mặc định
     */
    public ResourceNotFoundException() {
        super(MESSAGE_DEFAULT);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo và nguyên nhân
     *
     * @param message Thông báo
     * @param cause   Nguyên nhân
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Tạo một ngoại lệ kèm thông báo
     *
     * @param message Thông báo
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Tạo một ngoại lệ kèm nguyên nhân
     *
     * @param cause Nguyên nhân
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
