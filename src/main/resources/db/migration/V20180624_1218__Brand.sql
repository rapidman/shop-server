CREATE TABLE IF NOT EXISTS ajax.brand (
  id              BIGSERIAL NOT NULL PRIMARY KEY,
  name            VARCHAR   NOT NULL,
  last_modified_at TIMESTAMP NULL,
  created_at       TIMESTAMP NOT NULL
);