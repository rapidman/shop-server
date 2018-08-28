CREATE TABLE IF NOT EXISTS ajax.goods (
  id              BIGSERIAL NOT NULL PRIMARY KEY,
  name            VARCHAR   NOT NULL,
  category_id     INTEGER NOT NULL,
  last_modified_at TIMESTAMP NULL,
  created_at       TIMESTAMP NOT NULL,
  CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES ajax.category (id)
);