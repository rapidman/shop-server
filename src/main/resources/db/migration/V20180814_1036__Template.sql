CREATE TABLE IF NOT EXISTS ajax.template (
  id             BIGSERIAL NOT NULL PRIMARY KEY,
  key            VARCHAR   NOT NULL,
  content        VARCHAR   NOT NULL,
  last_modified_at TIMESTAMP NULL,
  created_at       TIMESTAMP NOT NULL
);
