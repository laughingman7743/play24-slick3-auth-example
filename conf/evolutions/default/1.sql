# --- !Ups
CREATE TABLE user_account(
  id BIGSERIAL NOT NULL,
  email VARCHAR (255) NOT NULL,
  password VARCHAR(64) NOT NULL ,
  password_salt VARCHAR(64) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  role_id BIGINT NOT NULL,
  failure_count INT NOT NULL DEFAULT 0,
  is_available BOOLEAN NOT NULL DEFAULT TRUE,
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
  is_password_change BOOLEAN NOT NULL DEFAULT FALSE,
  create_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  update_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_user_account PRIMARY KEY (id),
  CONSTRAINT uq_user_account_email UNIQUE (email)
);

insert into user_account(
  email,
  password,
  password_salt,
  first_name,
  last_name,
  role_id,
  failure_count
) values (
  'hoge.fuga@foo.bar',
  '83627ffa5ab48ecae9d0aafe55940d3d19040319e4b26a94f92d56b6734962e1', --password
  'f911422a271cf8a986b8a19d1e948c4d8ac4050bf090dd00d0d3e982c57647b8',
  'hoge',
  'fuga',
  1,
  0
);

# --- !Downs
DROP TABLE user_account;
