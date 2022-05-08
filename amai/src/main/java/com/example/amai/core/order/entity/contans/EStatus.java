package com.example.amai.core.order.entity.contans;

/**
 * Trạng thái order
 */
public enum EStatus {
    /**
     * Đơn hàng đang chờ xác nhận
     */
    UNCONFIRMED,
    /**
     * Đã được xác nhận đang chuẩn bị món
     */
    CONFIRMED,
    /**
     * Đang vận chuyển
     */
    TRANSPORT,
    /**
     * Đã nhận món
     */
    RECEIVED,

    /**
     * Đã hủy món
     */
    CANCEL
}
