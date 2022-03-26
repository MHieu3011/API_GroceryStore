tầng truy xuất dữ liệu vào database

mô hình chính:
Client --> Controller --> Service --> Cache --> Facade (nếu xử lý nhiều luồng)--> DAO (nếu cache miss)
                                  --> API bên ngoài (nếu có)