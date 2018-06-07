CREATE TABLE IF NOT EXISTS ajax.goods (
  id              BIGSERIAL NOT NULL PRIMARY KEY,
  name            VARCHAR   NOT NULL,
  category_id     INTEGER NOT NULL,
  CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES ajax.category (id)
);