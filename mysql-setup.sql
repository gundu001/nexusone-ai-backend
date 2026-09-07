CREATE DATABASE IF NOT EXISTS nexusone_ai
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Optional dedicated local application user.
-- Change the password before executing these statements.
CREATE USER IF NOT EXISTS 'nexusone_user'@'localhost' IDENTIFIED BY 'ChangeMe_StrongPassword';
GRANT ALL PRIVILEGES ON nexusone_ai.* TO 'nexusone_user'@'localhost';
FLUSH PRIVILEGES;
