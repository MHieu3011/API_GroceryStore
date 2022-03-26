Tầng Cache cho nâng cao hiệu năng
Có thể cài đặt các biến thể cache dạng cluster (aerospike, ignite,...) hoặc các biến thể local (Guava Cache,..)
Trong project này lựa chọn local cache

mô hình chính:
Client --> Controller --> Service --> Cache --> DAO (nếu cache miss)
                                  --> API bên ngoài (nếu có)