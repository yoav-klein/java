
CREATE TABLE user(id VARCHAR(70) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE tenant (id varchar(50) PRIMARY KEY, 
    name VARCHAR(50) NOT NULL, owner_id VARCHAR(70) NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES user(id)
);

CREATE TABLE tenant_user(tenant_id VARCHAR(50) NOT NULL, user_id VARCHAR(70) NOT NULL,
    FOREIGN KEY (tenant_id) REFERENCES tenant(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    UNIQUE (tenant_id, user_id)
);

