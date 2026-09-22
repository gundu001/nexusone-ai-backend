CREATE TABLE IF NOT EXISTS execution_fabric_records (
 id BIGINT AUTO_INCREMENT PRIMARY KEY, decision_id BIGINT, execution_name VARCHAR(255) NOT NULL,
 application_name VARCHAR(255) NOT NULL, environment VARCHAR(50) NOT NULL, action_type VARCHAR(50) NOT NULL,
 target_type VARCHAR(80) NOT NULL, target_name VARCHAR(255) NOT NULL, requested_by VARCHAR(255) NOT NULL,
 approval_reference VARCHAR(255), idempotency_key VARCHAR(120) NOT NULL UNIQUE, parameters TEXT,
 status VARCHAR(50) NOT NULL, progress_percentage INT, result_message VARCHAR(1200), error_message VARCHAR(1200),
 parent_execution_id BIGINT, started_at DATETIME(6), completed_at DATETIME(6), created_at DATETIME(6), updated_at DATETIME(6),
 INDEX idx_execution_status(status), INDEX idx_execution_created(created_at)
);
