package ai.nexusone.service;

import ai.nexusone.dto.FileFinding;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class SecretDetectionService {

    private static final Pattern AWS_ACCESS_KEY = Pattern.compile("AKIA[0-9A-Z]{16}");
    private static final Pattern PRIVATE_KEY = Pattern.compile("-----BEGIN (RSA |EC |OPENSSH )?PRIVATE KEY-----");
    private static final Pattern JWT = Pattern.compile("eyJ[A-Za-z0-9_-]+\\.eyJ[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+");

    public List<FileFinding> inspect(String key, String value, String file) {
        List<FileFinding> findings = new ArrayList<>();
        if (value == null || value.isBlank() || isExternalReference(value)) return findings;

        String normalizedKey = key == null ? "" : key.toLowerCase(Locale.ROOT)
                .replace("_", "-").replace(".", "-");

        if (AWS_ACCESS_KEY.matcher(value).find()) {
            findings.add(finding("CRITICAL", file, "SECRET-005",
                    "Possible AWS access key detected.",
                    "Revoke the key immediately and use an external secret manager."));
        }
        if (PRIVATE_KEY.matcher(value).find()) {
            findings.add(finding("CRITICAL", file, "SECRET-007",
                    "Private key material appears to be stored in source control.",
                    "Remove and rotate the private key, then use a secure secret store."));
        }
        if (JWT.matcher(value).find()) {
            findings.add(finding("HIGH", file, "SECRET-003",
                    "Possible hardcoded JWT token detected.",
                    "Remove the token and inject it at runtime from a secure source."));
        }

        String rule = ruleForKey(normalizedKey);
        if (rule != null && isLikelySecret(value)) {
            findings.add(switch (rule) {
                case "SECRET-001" -> finding("HIGH", file, rule,
                        "Possible hardcoded password detected for key '" + key + "'.",
                        "Move the password to an environment variable or secret manager.");
                case "SECRET-002" -> finding("HIGH", file, rule,
                        "Possible hardcoded secret detected for key '" + key + "'.",
                        "Store the secret outside source control and rotate exposed values.");
                case "SECRET-003" -> finding("HIGH", file, rule,
                        "Possible hardcoded token detected for key '" + key + "'.",
                        "Inject the token at runtime from a secret manager.");
                case "SECRET-004" -> finding("HIGH", file, rule,
                        "Possible hardcoded API key detected for key '" + key + "'.",
                        "Remove and rotate the API key, then load it securely at runtime.");
                case "SECRET-006" -> finding("HIGH", file, rule,
                        "Possible hardcoded JWT secret detected for key '" + key + "'.",
                        "Use a high-entropy secret supplied by a secret manager.");
                default -> null;
            });
        }
        findings.removeIf(java.util.Objects::isNull);
        return findings;
    }

    private String ruleForKey(String key) {
        if (key.contains("jwt-secret") || key.contains("jwt-signing")) return "SECRET-006";
        if (key.contains("password") || key.contains("passwd") || key.endsWith("pwd")) return "SECRET-001";
        if (key.contains("api-key") || key.contains("apikey")) return "SECRET-004";
        if (key.contains("token")) return "SECRET-003";
        if (key.contains("client-secret") || key.contains("private-key") || key.contains("secret")) return "SECRET-002";
        return null;
    }

    private boolean isLikelySecret(String value) {
        String v = stripQuotes(value.trim());
        if (v.length() < 4) return false;
        return !v.equalsIgnoreCase("changeme") && !v.equalsIgnoreCase("none")
                && !v.equalsIgnoreCase("null") && !v.equalsIgnoreCase("true")
                && !v.equalsIgnoreCase("false");
    }

    private boolean isExternalReference(String value) {
        String v = value.trim();
        return (v.startsWith("${") && v.endsWith("}"))
                || v.startsWith("#{") || v.contains("secretKeyRef")
                || v.startsWith("vault:") || v.startsWith("aws-secretsmanager:")
                || v.startsWith("azure-keyvault:");
    }

    private String stripQuotes(String value) {
        if (value.length() >= 2 && ((value.startsWith("\"") && value.endsWith("\""))
                || (value.startsWith("'") && value.endsWith("'")))) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    private FileFinding finding(String severity, String file, String rule,
                                String issue, String recommendation) {
        return new FileFinding(severity, file, rule, issue, recommendation);
    }
}
