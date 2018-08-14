CREATE TABLE IF NOT EXISTS ajax.template (
  id             BIGSERIAL NOT NULL PRIMARY KEY,
  key            VARCHAR   NOT NULL,
  content        VARCHAR   NOT NULL
);
