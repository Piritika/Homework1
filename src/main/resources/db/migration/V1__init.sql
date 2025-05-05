create table if not exists users (
                       id BIGSERIAL  PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       age INTEGER NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       soft_deleted BOOLEAN DEFAULT FALSE
);

create table if not exists products (
                          id BIGSERIAL PRIMARY KEY,
                          name_of_product VARCHAR(100) NOT NULL,
                          price INTEGER NOT NULL,
                          soft_deleted BOOLEAN DEFAULT FALSE
);

create table if not exists orders (
                        id BIGSERIAL  PRIMARY KEY,
                        user_id BIGINT  NOT NULL REFERENCES users(id),
                        product_id BIGINT NOT NULL REFERENCES products(id),
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);