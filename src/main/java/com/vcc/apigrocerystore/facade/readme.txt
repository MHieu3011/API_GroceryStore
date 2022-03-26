được dùng để tăng hiệu xuất của ứng dụng khi service truy cập nhiều luồng độc lập đến dao

mô hình chính:
Client --> Controller --> Service --> Cache --> Facade (nếu xử lý nhiều luồng)--> DAO (nếu cache miss)
                                  --> API bên ngoài (nếu có)