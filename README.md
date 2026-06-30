# Quick Start

## Basic Usage (DOM-Based Self Healing)

No configuration is required.

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selfhealing.driver.HealingDriver;

WebDriver driver = HealingDriver.create(new ChromeDriver());

driver.get("https://example.com");
```

When a locator fails, the framework automatically:

- Captures the current DOM
- Searches for similar elements
- Finds the best matching locator
- Retries the Selenium command
- Continues the test if healing succeeds

---

## AI-Powered Healing (Optional)

Enable AI only when you want an additional fallback after DOM-based healing.

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selfhealing.driver.HealingDriver;
import com.selfhealing.config.HealingConfig;
import com.selfhealing.ai.AIProvider;

HealingConfig config = HealingConfig.builder()
        .provider(AIProvider.GROQ)
        .apiKey("YOUR_API_KEY")
        .model("llama-3.3-70b-versatile")
        .build();

WebDriver driver = HealingDriver.create(
        new ChromeDriver(),
        config
);
```

The framework will:

1. Try the original locator.
2. Perform DOM-based healing.
3. If DOM healing fails, invoke the configured AI provider.
4. Retry using the AI-generated locator.
5. Continue execution if healing succeeds.

---

## Supported AI Providers

- NVIDIA
- Groq

AI support is completely optional. If no AI configuration is provided, the framework uses only DOM-based self-healing.

---

## Example

```java
driver.findElement(By.id("login-button")).click();
```

If the locator changes from:

```
login-button
```

to

```
btn-login
```

the framework automatically discovers the new locator and retries the action without requiring test changes.