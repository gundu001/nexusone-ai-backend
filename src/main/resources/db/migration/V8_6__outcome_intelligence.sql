CREATE TABLE IF NOT EXISTS outcome_intelligence_records (
 id BIGINT AUTO_INCREMENT PRIMARY KEY, execution_id BIGINT NOT NULL, decision_id BIGINT,
 outcome_name VARCHAR(255) NOT NULL, application_name VARCHAR(255) NOT NULL, environment VARCHAR(50) NOT NULL,
 outcome_type VARCHAR(50) NOT NULL, availability_score DOUBLE NOT NULL, performance_score DOUBLE NOT NULL,
 error_reduction_score DOUBLE NOT NULL, cost_efficiency_score DOUBLE NOT NULL, business_kpi_score DOUBLE NOT NULL,
 overall_score DOUBLE, status VARCHAR(50), summary VARCHAR(1200), recommendation VARCHAR(1200), evidence TEXT,
 measured_by VARCHAR(255) NOT NULL, measured_at DATETIME(6), created_at DATETIME(6), updated_at DATETIME(6),
 INDEX idx_outcome_execution(execution_id), INDEX idx_outcome_status(status), INDEX idx_outcome_created(created_at)
);
